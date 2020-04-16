<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HangmanDTO"
	import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Hangmen</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
  	<a href="GameServlet">Back</a>
  	<a class="active" href="HangmanServlet?mode=gamelist">Hangmen</a>
  	<a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<HangmanDTO> list = (List<HangmanDTO>) request.getAttribute("list");
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
	%>

<br>

	<table>
		<tr>
			<th>ID</th>
			<th>Solution</th>
			<th>Sentence</th>
			<th>Definition</th>
			<th>Score</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (HangmanDTO h : list) {
		%>
		<tr>
			<td>
				<a href="HangmanServlet?mode=read&id=<%=h.getId()%>" style="text-decoration: underline;">
					<%=h.getId()%>
				</a>
			</td>
			<td><%=h.getSolution()%></td>
			<td><%=h.getSentence()%></td>
			<td><%=h.getDefinition()%></td>
			<td><%=h.getScore()%></td>
			<td><%=h.getCategory()%></td>
			<td><a href=HangmanServlet?mode=read&update=true&id=<%=h.getId()%> style="text-decoration: underline;">Edit</a>
			</td>
			<td><a href=HangmanServlet?mode=read&delete=true&id=<%=h.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="HangmanServlet?mode=insert" method="post">

  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Insert the solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="sentence">Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="Insert the hint"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" placeholder="Insert the definition"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" required placeholder="Insert the score"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
   	</div>
   	<div class="col-75">
		<select id="idCategory" name="idCategory" required>
			<option value="">Choose one...</option>
 			<%
				for (CategoryDTO category : categoryList) {
			%>
			<option value=<%= category.getId() %>> <%= category.getTitle() %> </option>
			<%
				}
			%>
		</select>
   	</div>
  </div>
  <button type="submit" >Salva</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>