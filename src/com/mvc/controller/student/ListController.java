package com.mvc.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.controller.Controller;
import com.mvc.model.student.StudentService;

public class ListController implements Controller{
	StudentService service;

	public ListController() {
		service=new StudentService();
		//new 연산자로 인해 ListController가 StudentService 자료형에 의존적이다. 다른 자료형을 써야 할경우 에러발생
		//1. 상위 객체를 만들고 거기에 주입해서 사용하자!!! pan fryingpan, electricpan
		//2. xml설정을 읽어 주입해주자.
		//private Pan pan
		//public void setPan(Pan pan){
		//	this.pan=pan;
		//}
		/*
		 * DI = dependency injection (의존성 주입X)
		 * 의존성은 나쁜것 new를 사용하지 말자
		 * 의존성을 탈피하기 위해서 외부에서 객체를 넣는다.
		 * 
		 * */
	}
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//req.setAttribute("test", "test완료");
		List list=service.getList();
		System.out.println("ListController에서 list size="+list.size());
		req.setAttribute("list", list);
		
		
		return "result/student/list";
	}

	@Override
	public boolean isForward() {
	
		return true;
	}
	
	
	
	
}
