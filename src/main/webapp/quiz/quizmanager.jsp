<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.QuizDTO" import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Quizzes</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="gamemanager.jsp">Home</a>
  <a class="active" href="QuizServlet?mode=gamelist">Quizzes</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<!-- commento -->

<div class="main">
	<%
	//Lista contiene tutti gli elementi del quiz che verranno stampati nella tabella
		List<QuizDTO> list = (List<QuizDTO>) request.getAttribute("list");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
				
		%>

<br>
<!-- stampo i contenuti di list inclusi nella tabella sottostante -->
	
	<table>
		<tr>
			<th>Id</th>
			<th>Solution</th>
			<th>Sentence</th>
			<th>Definition</th>
			<th>Score</th>
			<th>Category</th>
			<th></th>
			<th></th>
			
		</tr>
		<%
			for (QuizDTO q : list) {
		%>
		<tr>
		<td><a href=CategoryServlet?mode=read&id=<%=q.getId()%>>
					<%=q.getId()%>
			</a></td>
			<td><a href=QuizServlet?mode=read&id=<%=q.getId()%>>
					<%=q.getSolution()%>
			</a></td>
			<td><%=q.getId()%></td>
			<td><%=q.getSolution()%></td>
			<td><%=q.getSentence()%></td>
			<td><%=q.getDefinition()%></td>
			<td><%=q.getScore()%></td>
			<td><%=q.getCategory()%></td>
			<td><a href=QuizServlet?mode=read&update=true&id=<%=q.getId()%>>Edit</a>
			</td>
			<td><a href=QuizrServlet?mode=delete&id=<%=q.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="QuizServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="quiz">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="inserisci la soluzione">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" required placeholder="inserisci la risposta corretta">
    </div>
    <div class="row">
    <div class="col-25">
      <label for="quiz">Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="inserisci l'indizio">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="quizr">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" required placeholder="inserisci punteggio"> 
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
      <option value= <%= category.getId()%>> <%= category.getTitle() %> </option>
     <%
      	  }
     %>
   	</select>
    	</div>
  </div>
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
