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
		//new �����ڷ� ���� ListController�� StudentService �ڷ����� �������̴�. �ٸ� �ڷ����� ��� �Ұ�� �����߻�
		//1. ���� ��ü�� ����� �ű⿡ �����ؼ� �������!!! pan fryingpan, electricpan
		//2. xml������ �о� ����������.
		//private Pan pan
		//public void setPan(Pan pan){
		//	this.pan=pan;
		//}
		/*
		 * DI = dependency injection (������ ����X)
		 * �������� ���۰� new�� ������� ����
		 * �������� Ż���ϱ� ���ؼ� �ܺο��� ��ü�� �ִ´�.
		 * 
		 * */
	}
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//req.setAttribute("test", "test�Ϸ�");
		List list=service.getList();
		System.out.println("ListController���� list size="+list.size());
		req.setAttribute("list", list);
		
		
		return "result/student/list";
	}

	@Override
	public boolean isForward() {
	
		return true;
	}
	
	
	
	
}
