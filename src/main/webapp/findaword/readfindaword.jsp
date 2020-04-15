<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.FindAWordDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read FindAWord</title>
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
<%FindAWordDTO u = (FindAWordDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Id</th>
		<th>Solution</th>
		<th>Sentence</th>
		<th>Definition</th>
		<th>Score</th>
		<th>Category</th>		
	</tr>
	
	<tr>	
		<td><%=u.getId()%></td>
		<td><%=u.getSolution()%></td>
		<td><%=u.getSentence()%></td>
		<td><%=u.getDefinition()%></td>
		<td><%=u.getScore()%></td>
		<td><%=u.getCategory()%></td>
	</tr>
		
</table>

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