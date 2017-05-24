package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController implements Controller {

	@Override
	public String excute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("controller작성 완료");
		req.setAttribute("test", "test완료");
		
		return "/test";
	}

	

	@Override
	public boolean isForward() {
		return true;
	}

}
