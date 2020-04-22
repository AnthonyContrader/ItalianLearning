<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.QuizDTO" 
    import="it.contrader.dto.CategoryDTO" 
    import="it.contrader.dto.LevelDTO"
    import="java.util.List"%>
    <!-- created by Anna Cecere -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Quiz</title>
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

<%
QuizDTO q = (QuizDTO) request.getSession().getAttribute("dto");
List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");

%>
<form id="floatleft" action="/quiz/update?id=<%=q.getId()%>" method="post">

<input type="hidden" name="id" value =<%=q.getId() %>>
  <div class="row">
    <div class="col-25">
      <label for="quiz">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="enter the solution" value="<%=q.getSolution()%>">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" required placeholder="enter the correct answer" value="<%=q.getDefinition()%>">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="enter the clue" value="<%=q.getSentence()%>">
    </div>
  </div>
  
    <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
    </div>
    <div class="col-75">
      <select id="idCategory" name="idCategory" >
      <option value= ''> Choose one...</option>
      <%
      	for (CategoryDTO category : categoryList) {
      %>
      <option value=<%= category.getId()%> <%= category.getId() == q.getCategory().getId() ? "selected" : ""%> >
       <%= category.getTitle()%>
       </option>
      <%
      	  }
	  %>
   	</select>
    	</div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="quiz">Level</label>
    </div>
    <div class="col-75">
      <select id="idLevel" name="idLevel" >
      <option value= ''> Choose one...</option>
      <%
      	for (LevelDTO level : levelList) {
      %>
      <option value= <%= level.getId()%> <%= level.getId() == q.getLevel().getId() ? "selected" : ""%> >
      <%= level.getName() %>
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