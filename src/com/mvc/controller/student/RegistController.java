package com.mvc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.controller.Controller;
import com.mvc.model.student.StudentService;

/**
 * @author user1
 *	�л� ��� ��û�� ó���ϴ� ���� ��Ʈ�ѷ�
 *	��Ʈ�ѷ��� ���� �� 3(�Ͻ�Ų��), 4�ܰ�(�������) ����
 *	Ʈ����� ���ξ����� ��� �����ؾ� db�� commit�Ǵ� ���� ���� ����
 */
public class RegistController implements Controller {
	StudentService service=new StudentService();
	
	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.regist();
		
		return null;
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
