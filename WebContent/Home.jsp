<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.main.model.User"%>
<%@page import="com.main.model.Item"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
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

<%@taglib uri="WEB-INF/tags.tld" prefix="tags"%>

<jsp:include page="navbarlogeduser.html"></jsp:include>

<script>

function callUpdateForm(id,title,content,status)
{
	//document.getElementById("updateForm").style.visibility = 'visible';
	document.getElementById("id").value=id;
	document.getElementById("title").value=title;
	document.getElementById("content").value=content;
	document.getElementById("status").value=status;
}

</script>

<div class="container" style="max-width:800px; margin:50px auto;" >
<%
User user = (User)request.getSession().getAttribute("logedUser");
if(user != null){
	out.println("<h1>Hello " + user.getFirstName() + "</h1>");
	
	Item[] items = (Item[])request.getAttribute("items");
	if(items.length>0){
		StringBuilder tableBuilder = new StringBuilder();
		%><table class="table table-striped"><thead> <tr> <th> Title </th> <th> description </th> <th>status</th> <th></th> <th></th> </tr></thead><tbody><%
		for(Item item : items){
			%><tr> <td> <%=String.valueOf(item.getTitle())%></td><td><%=String.valueOf(item.getContent())%></td><td><%=String.valueOf(item.getStatus())%></td>
			<td>
			<form action="deleteItem">
			<input type="hidden" name="itemId" value=<%=String.valueOf(item.getId())%>><input type="submit" class="btn-danger" value="delete item"></form> 
			</td>
			<td> <input type="button" class="btn-warning" value="edit" onclick="callUpdateForm('<%= item.getId()%>','<%= item.getTitle()%>','<%= item.getContent()%>','<%= item.getStatus().toString()%>')" data-toggle="modal" data-target="#updateForm"> </td>
			</tr><% 
		}
		
		%></tbody></table><% 
		
	}
}
else{
	out.println("Error!");
	response.sendRedirect("login");
}
%>


<br/>
<br/>
<br/>


 <button type="button" class="btn btn-success" data-toggle="modal" data-target="#popUpWindow">Add Item</button>

<div class="modal fade" id="popUpWindow">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Add Item</h3>
                </div>
                <!-- body (form) -->
                <div class="modal-body">
                    <form action="addItem" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Title" name="title" id="title">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Content" name="content">
                        </div>
						 <!-- button -->
						<div class="modal-footer">
							<button class="btn btn-primary btn-block">Submit</button>
						</div>
                    </form>
                </div>
            </div>
        </div>
    </div>




<br/>



<div class="modal fade" id="updateForm">
 <div class="modal-dialog">
             <div class="modal-content">
<!-- header -->
     <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
    	 <h3 class="modal-title">Edit Item</h3>
	 </div>
	 
 <div class="modal-body">
<form action="editItem" method="post">
	<input type="hidden" name="id" id="id">
	 <div class="form-group">
      <input type="text" class="form-control" placeholder="Title" id="title" name="title">
     </div>
      <div class="form-group">
	<input type="text" class="form-control" placeholder="Content" name="content" id="content">
	 </div>
	 <div class="form-group">
	<select class = "selectpicker" name="status" id="status">
	<option value="ready">ready</option>
	<option value="inProgress">inProgress</option>
	<option value="finish">finish</option>
	</select>
		 </div>
	<div class="modal-footer">
	<input type="submit" class = "btn btn-primary btn-block" value="submit">
	</div>
</form>
</div>
</div>
</div>
</div>


<hr/>				
<div>	
	<tags:helloTag name='<%= ((User)session.getAttribute("logedUser")).getFirstName() %>'/><br/>
	<a href="https://ofirelarat.github.io/TodoListProject/">github page</a>
</div>
</div>

</body>
</html>