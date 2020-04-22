<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Games</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="homeadmin.jsp">Home</a>	
	<a class="active" href="GameServlet">Games</a>
  	<a href=/findawords/getall>Find a Words</a>
  	<a href=/findmistakes/getall>Find Mistakes</a>
  	<a href=/guesspictures/getall>Guess Pictures</a>
  	<a href=/hangmen/getall>Hangmen</a>
  	<a href=/organizesentences/getall>Organize Sentences</a>
  	<a href=/quiz/getall>Quiz</a>
  	<a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>