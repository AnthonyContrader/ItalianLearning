<!-- Created By @Alessandro Alfieri -->
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
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/level/getall">Back</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%LevelDTO l = (LevelDTO) request.getSession().getAttribute("dto");%>

<div class="col-50">

		<table class="col-100">
			<tr> 
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Score</th>		
			</tr>
			<tr>
				<td><%=l.getId()%></td>
				<td><%=l.getName()%></td>
				<td><%=l.getDescription()%></td>
				<td><%=l.getScore()%></td>
			</tr>	
		</table>



</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href=/level/delete?id=<%=l.getId()%> class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="/level/getall" class="col-50"><button class="btn-sm">Cancel</button></a>
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