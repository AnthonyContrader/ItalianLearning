<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.QuizDTO" 
    import="it.contrader.dto.CategoryDTO" 
    import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Modifica Quiz</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="GameServlet?mode=gamelist">Back</a>
  <a class="active" href="QuizServlet?mode=gamelist">Quiz</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%QuizDTO q = (QuizDTO) request.getAttribute("dto");
List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>
<form id="floatleft" action="QuizServlet?mode=update&id=<%=q.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="quiz">Solution</label>
    </div>
    <div class="col-75">
      <input value="<%= q.getSolution()%>" type="text" id="solution" name="solution" required placeholder="enter the solution">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Definition</label>
    </div>
    <div class="col-75">
      <input value="<%= q.getDefinition()%>" type="text" id="definition" name="definition" required placeholder="enter the correct answer">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Sentence</label>
    </div>
    <div class="col-75">
      <input value="<%= q.getSentence()%>" type="text" id="sentence" name="sentence" required placeholder="enter the clue">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="quizr">Score</label>
    </div>
    <div class="col-75">
      <input value="<%= q.getScore()%>" type="number" id="score" name="score" required placeholder="enter score"> 
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Category</label>
    </div>
    <div class="col-75">
      <select id="idCategory" name="idCategory" required>
      <option value= ''> Choose one...</option>
      <%
      for (CategoryDTO category : categoryList){
      %>
      <option value=<%= category.getId()%> <%= Integer.valueOf(category.getId()) == q.getIdCategory() ? "selected" : ""%> >
       <%= category.getTitle()%>
       </option>
      <% 
      	  }
      %>
     	</select>
      	</div>
    </div>
       <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>