<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Error Page</title>
</head>
<body>

exception type - <%= exception.getClass().getName() %>
<br/>
exception message - <%= exception.getMessage() %>

</body>
</html>