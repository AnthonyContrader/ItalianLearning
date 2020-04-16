<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.QuizDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Quiz</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active"  href="QuizServlet?mode=gamelist">Quiz</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%QuizDTO q = (QuizDTO) request.getAttribute("dto");%>


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
		<td><%=q.getId()%></td>
		<td><%=q.getSolution()%></td>
		<td><%=q.getSentence()%></td>
		<td><%=q.getDefinition()%></td>
		<td><%=q.getScore()%></td>
		<td><%=q.getCategory()%></td>
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