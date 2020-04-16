 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.FindAWordDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Find a Word</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="FindAWordServlet?mode=gamelist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%FindAWordDTO h = (FindAWordDTO) request.getAttribute("dto");%>

<div class="col-50">
	<br>
	<br>
	<br>
	<br>
	<br>
	<table class="col-100">
		<tr> 
			<th>ID</th>
			<th>Solution</th>
			<th>Hint</th>
			<th>Definition</th>
			<th>Score</th>
			<th>Category</th>
		</tr>
		<tr>
			<td><%=h.getId()%></td>
			<td><%=h.getSolution()%></td>
			<td><%=h.getSentence()%></td>
			<td><%=h.getDefinition()%></td>
			<td><%=h.getScore()%></td>
			<td><%=h.getCategory()%></td>
		</tr>	
	</table>
</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href=FindAWordServlet?mode=delete&id=<%=h.getId()%> class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="FindAWordServlet?mode=gamelist" class="col-50"><button class="btn-sm">Cancel</button></a>
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