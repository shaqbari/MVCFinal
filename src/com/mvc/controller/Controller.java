package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public String excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	public boolean isForward();
}
