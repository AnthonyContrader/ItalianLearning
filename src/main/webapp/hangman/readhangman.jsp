 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.HangmanDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Hangman</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="HangmanServlet?mode=gamelist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%HangmanDTO h = (HangmanDTO) request.getAttribute("dto");%>


<table>
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