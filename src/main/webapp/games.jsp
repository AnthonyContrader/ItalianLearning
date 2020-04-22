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
	<a href="../homeadmin.jsp">Home</a>	
	<a class="active" href="#">Games</a>
  	<a href=/findaword/getall>Find a Words</a>
  	<a href=/findmistake/getall>Find Mistakes</a>
  	<a href=/guesspicture/getall>Guess Pictures</a>
  	<a href=/hangman/getall>Hangmen</a>
  	<a href=/organizesentence/getall>Organize Sentences</a>
  	<a href=/quiz/getall>Quiz</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>
<div class="main">
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>