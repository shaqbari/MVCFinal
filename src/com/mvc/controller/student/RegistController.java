package com.mvc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.mvc.controller.Controller;
import com.mvc.model.student.Physical;
import com.mvc.model.student.Student;
import com.mvc.model.student.StudentService;

import common.exception.RegistFailException;

/**
 * @author user1
 *	�л� ��� ��û�� ó���ϴ� ���� ��Ʈ�ѷ�
 *	��Ʈ�ѷ��� ���� �� 3(�Ͻ�Ų��), 4�ܰ�(�������) ����
 *	Ʈ�����: ���ξ����� ��� �����ؾ� db�� commit�Ǵ� ���� ���� ����
 */
public class RegistController implements Controller {
	StudentService service=new StudentService();
	boolean isForward;
	String viewPage;
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�Ķ���� �ޱ�
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		String name=req.getParameter("name");

		String blood=req.getParameter("blood");
		String weight=req.getParameter("weight");
		
		Student student=new Student();
		student.setId(id);
		student.setPassword(password);
		student.setName(name);		
		
		Physical physical=new Physical();
		physical.setBlood(blood);
		physical.setWeight(Integer.parseInt(weight));
		
		//3�ܰ�: �˸��� ���� ��ü�� �� ��Ų��.
		try {
			service.regist(student, physical);
			viewPage="result/student/regist";
		} catch (RegistFailException e) {
			e.printStackTrace();
			//Ŭ���̾�Ʈ���� �������� ���� ���� ��ü�� �����غ���
			req.setAttribute("ex", e);//����Ǹ� forward, �ȵǸ� redirect
			isForward=true;
			viewPage="result/student/error";
		}//��Ÿ�� ���ܶ� ����ó�� ���ص� ������ �Ȱ���. ���ϸ� ��Ĺ�� Ŭ���̾�Ʈ�� ���� �׳� �ѷ�������.
		
		//4�ܰ�: �����ٰ� ����.
		
		return viewPage;
	}

	@Override
	public boolean isForward() {
		return isForward;
	}

}
