 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.FindMistakeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Find Mistake</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="FindMistakeServlet?mode=gamelist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%FindMistakeDTO f = (FindMistakeDTO) request.getAttribute("dto");%>

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
			<th>Option A</th>
			<th>Option B</th>
			<th>Option C</th>
			<th>Score</th>
			<th>Category</th>
		</tr>
		<tr>
			<td><%=f.getId()%></td>
			<td><%=f.getSolution()%></td>
			<td><%=f.getSentence()%></td>
			<td><%=f.getDefinition()%></td>
			<td><%=f.getOptionA()%></td>
			<td><%=f.getOptionB()%></td>
			<td><%=f.getOptionC()%></td>
			<td><%=f.getScore()%></td>
			<td><%=f.getCategory()%></td>
		</tr>
	</table>
</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href=FindMistakeServlet?mode=delete&id=<%=f.getId()%> class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="FindMistakeServlet?mode=gamelist" class="col-50"><button class="btn-sm">Cancel</button></a>
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