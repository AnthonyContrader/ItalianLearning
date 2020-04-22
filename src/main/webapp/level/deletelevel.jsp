 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.LevelDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Level</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="LevelServlet?mode=levellist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%LevelDTO x = (LevelDTO) request.getAttribute("dto");%>

<div class="col-50">

		<table class="col-100">
			<tr> 
				<th>Id</th>
				<th>Name</th>
				<th>Score</th>		
			</tr>
			<tr>
				<td><%=x.getId()%></td>
				<td> <%=x.getName()%></td>
				<td> <%=x.getScore()%></td>
			</tr>	
		</table>
	
		<table class="col-100">
			<tr> 
				<th>Description</th>
			</tr>
			<tr>
				<td> <%=x.getDescription()%></td>
			</tr>	
		</table>



</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href=LevelServlet?mode=delete&id=<%=x.getId()%> class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="LevelServlet?mode=levellist" class="col-50"><button class="btn-sm">Cancel</button></a>
	</div>
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


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>