<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">

 <title>ToDoList</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
<%@taglib uri="WEB-INF/tags.tld" prefix="tags" %>

<jsp:include page="navbarnotlogeduser.html"></jsp:include>

<div class="container" style="max-width:800px; margin:50px auto;" >
<div class="modal-body">
<!-- header -->
                <div class="modal-header">
                    <h3 class="modal-title">Register</h3>
   </div>
   </div>
<form action="register"   method="post">
					 <div class="form-group">
                         <input type="text" class="form-control" placeholder="First Name" name="firstName">
                     </div>
                     
                      <div class="form-group">
                         <input type="text" class="form-control" placeholder="Last Name" name="lastName">
                     </div>

                     <div class="form-group">
                         <input type="email" class="form-control" placeholder="Email" name="email">
                     </div>
                     
                     <div class="form-group">
                         <input type="password" class="form-control" placeholder="Password" name="password">
                     </div>
			 <!-- button -->
			<div class="modal-footer">
				<button class="btn btn-primary btn-block">Submit</button>
			</div>
                 </form>
                 
<hr/>				
<div>	
	<tags:helloTag/><br/>
	<a href="https://ofirelarat.github.io/TodoListProject/">github page</a>
</div>
</div>
</body>
</html>