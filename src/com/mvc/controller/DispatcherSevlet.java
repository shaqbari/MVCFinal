package com.mvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.Request;

public class DispatcherSevlet extends HttpServlet{
	FileInputStream fis;
	InputStreamReader reader;
	BufferedReader buffr;	
	JSONParser parser;
	JSONObject jsonObject;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String configLocation=config.getInitParameter("configLocation");
		System.out.println("xml의 configLocatoin= "+configLocation);
		
		ServletContext context=config.getServletContext();
		String path= context.getRealPath(configLocation);
		System.out.println("configLocation의 realPath= "+path);
		
		try {
			fis=new FileInputStream(new File(path));
			reader=new InputStreamReader(fis);
			buffr=new BufferedReader(reader);
			
			String data=null;
			StringBuffer sb=new StringBuffer();					
			while(true){
				data=buffr.readLine();
				if (data==null) break;
				sb.append(data);
			}	
			System.out.println("sb에서 json내용은= "+sb.toString());			
			
			parser=new JSONParser(); //파서 올리는것을 잊지 말자
			jsonObject=(JSONObject) parser.parse(sb.toString());
			System.out.println("parse한 결과는= "+jsonObject.toJSONString());
						
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("close전 reader값은 ="+reader);//안죽었는데?
			if (reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("close후 reader값은 ="+reader);//close가 객체를 완전히 제거하는거는 아닌가 보다
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	protected void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1단계 요청을 받는다.
		//한글셋팅
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
						
		JSONObject controllerObj=(JSONObject) jsonObject.get("controllerMapping");
		JSONObject viewObj=(JSONObject) jsonObject.get("viewMapping");
		
		
		//2단계 요청을 분석한다
		String uri=req.getRequestURI();
		System.out.println("요청 uri="+uri);
		
		String controllerName=(String) controllerObj.get(uri);//mapping할때 uri앞에 /가 있으므로 json파일 작성할때 유의
		System.out.println("controllerName은= "+controllerName);
		
		Controller controller=null;
		try {
			//클래스 동적 로드
			Class controllerClass = Class.forName(controllerName);
			controller=(Controller) controllerClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//다형성을 이용해 컨트롤러 메소드 호출(큰자료형으로 메소드 호출하지만, 오버라이드된 자식 메소드가 호출된다.)
		String ResultKey=controller.excute(req, resp);
		String view=(String) viewObj.get(ResultKey);
		System.out.println("json의 view="+view);
		
		//5단계. 결과 보여주기
		if (controller.isForward()) {//포워드해야 하면
			RequestDispatcher dis=req.getRequestDispatcher(view);
			dis.forward(req, resp);
		}else{
			resp.sendRedirect(view);
		}
				
	}
	

}
