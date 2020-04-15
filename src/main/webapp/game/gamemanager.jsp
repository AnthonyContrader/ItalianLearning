<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HangmanDTO"
	import="it.contrader.dto.CategoryDTO"%>
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
  	<a href="FindAWordServlet?mode=gamelist">Find a Words</a>
  	<a href="FindMistakeServlet?mode=gamelist">Find Mistakes</a>
  	<a href="GuessPictureServlet?mode=gamelist">Guess Pictures</a>
  	<a href="HangmanServlet?mode=gamelist">Hangmen</a>
  	<a href="OrganizeSentenceServlet?mode=gamelist">Organize Sentences</a>
  	<a href="QuizServlet?mode=gamelist">Quizzes</a>
  	<a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>