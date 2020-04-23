 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.QuizDTO"%>
    <!-- created by Anna Cecere -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Quiz</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="../homeadmin.jsp">Home</a>
  
  <a class="active" href="/quiz/getall">Back</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>
<div class="main">
<br>

<div class="main">
<%QuizDTO q = (QuizDTO) request.getSession().getAttribute("dto");%>

<div class="col-50">
	<br>
	<br>
	<br>
	<br>
	<br>
	<table class="col-100">
		<tr> 
		<
		<th>Solution</th>
		<th>Sentence</th>
		<th>Definition</th>
		<th>Level</th>
		<th>Category</th>
		</tr>
		<tr>
			
			<td><%=q.getSolution()%></td>
			<td><%=q.getSentence()%></td>
			<td><%=q.getDefinition()%></td>
			<td><%=q.getLevel().getName()%></td>
			<td><%=q.getCategory().getTitle()%></td>
		</tr>	
	</table>
</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href="/quiz/delete?id=<%=q.getId()%>" class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="/quiz/getall" class="col-50"><button class="btn-sm">Cancel</button></a>
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
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>