<%@page import="com.main.controller.ServletListenerCounter"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="navbarnotlogeduser.html"></jsp:include>
<%out.println("<h1>Number of sessions is:" + ServletListenerCounter.getSessionCounter() + "</h1>"); %>
</body>
</html>