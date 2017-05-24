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
		System.out.println("xml�� configLocatoin= "+configLocation);
		
		ServletContext context=config.getServletContext();
		String path= context.getRealPath(configLocation);
		System.out.println("configLocation�� realPath= "+path);
		
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
			System.out.println("sb���� json������= "+sb.toString());			
			
			parser=new JSONParser(); //�ļ� �ø��°��� ���� ����
			jsonObject=(JSONObject) parser.parse(sb.toString());
			System.out.println("parse�� �����= "+jsonObject.toJSONString());
						
			
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
			System.out.println("close�� reader���� ="+reader);//���׾��µ�?
			if (reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("close�� reader���� ="+reader);//close�� ��ü�� ������ �����ϴ°Ŵ� �ƴѰ� ����
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
		//1�ܰ� ��û�� �޴´�.
		//�ѱۼ���
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
						
		JSONObject controllerObj=(JSONObject) jsonObject.get("controllerMapping");
		JSONObject viewObj=(JSONObject) jsonObject.get("viewMapping");
		
		
		//2�ܰ� ��û�� �м��Ѵ�
		String uri=req.getRequestURI();
		System.out.println("��û uri="+uri);
		
		String controllerName=(String) controllerObj.get(uri);//mapping�Ҷ� uri�տ� /�� �����Ƿ� json���� �ۼ��Ҷ� ����
		System.out.println("controllerName��= "+controllerName);
		
		Controller controller=null;
		try {
			//Ŭ���� ���� �ε�
			Class controllerClass = Class.forName(controllerName);
			controller=(Controller) controllerClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//�������� �̿��� ��Ʈ�ѷ� �޼ҵ� ȣ��(ū�ڷ������� �޼ҵ� ȣ��������, �������̵�� �ڽ� �޼ҵ尡 ȣ��ȴ�.)
		String ResultKey=controller.excute(req, resp);
		String view=(String) viewObj.get(ResultKey);
		System.out.println("json�� view="+view);
		
		//5�ܰ�. ��� �����ֱ�
		if (controller.isForward()) {//�������ؾ� �ϸ�
			RequestDispatcher dis=req.getRequestDispatcher(view);
			dis.forward(req, resp);
		}else{
			resp.sendRedirect(view);
		}
				
	}
	

}
