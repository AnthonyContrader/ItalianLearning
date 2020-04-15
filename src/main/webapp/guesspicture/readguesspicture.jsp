<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Guess Picture</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active"  href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%CategoryDTO c = (CategoryDTO) request.getAttribute("dto");%>

<div class="col-100">
	<table>
		<tr> 
			<th>Id</th>
			<th>Title</th>		
		</tr>
		<tr>
			<td><%=c.getId()%></td>
			<td> <%=c.getTitle()%></td>
			
		</tr>	
	</table>
</div>

<div class="col-100" style="padding-top: 25px;">
	<table>
		<tr> 
			<th>Description</th>
		</tr>
		<tr>
			<td> <%=c.getDescription()%></td>
		</tr>	
	</table>
</div>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>