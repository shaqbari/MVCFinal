<%@page import="common.exception.RegistFailException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="orange">
	<%
		RuntimeException ex=(RegistFailException)request.getAttribute("ex");
		String msg= ex.getMessage();
		out.print(msg);
	%>
</body>
</html>