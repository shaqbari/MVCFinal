<%@page import="com.mvc.model.student.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Student> list=(List)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px solid">
		<tr>
			<td>No</td>
			<td>id</td>
			<td>name</td>
			<td>혈액형</td>
			<td>몸무게</td>			
		</tr>
		<%for(Student s : list){ %>		
		<tr>
			<td><%=s.getStudent_id() %></td>
			<td><%=s.getId() %></td>
			<td><%=s.getName() %></td>
			<td><%=s.getPhysical().getBlood() %></td>
			<td><%=s.getPhysical().getWeight() %></td>			
		</tr>
		<%} %>
	</table>
</body>
</html>