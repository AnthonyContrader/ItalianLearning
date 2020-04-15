<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" 
	import="java.util.List"
	import="it.contrader.dto.GuessPictureDTO"
	import="it.contrader.dto.CategoryDTO"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Guess Pictures Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>  
</div>
<div class="main">
	<%
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
		List<GuessPictureDTO> list = (List<GuessPictureDTO>) request.getAttribute("list");
	%>

<br>
	<table>
		<tr>
			<th>Id</th>
			<th>Solution</th>
			<th>Score</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (GuessPictureDTO g : list) {
		%>
		<tr>
			<td><a href=GuessPictureServlet?mode=read&id=<%=g.getId()%>>
				<%=g.getId()%>
			</a></td>
			<td><a href=GuessPictureServlet?mode=read&id=<%=g.getId()%>>
				<%=g.getSolution()%>
			</a></td>
			
			<td><%=g.getScore()%></td>
			
			<td><%=g.getCategory()%></td>
			
			<td><a href=GuessPictureServlet?mode=read&update=true&id=<%=g.getId()%>>Edit</a>
			</td>
			<td><a href=GuessPictureServlet?mode=delete&id=<%=g.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

<form id="floatright" action="GuessPictureServlet?mode=insert" method="post">

  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Inserisci la soluzione">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" required placeholder="Inserisci il punteggio"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Image</label>
    </div>
    <div class="col-75">
      <input type="file" id="image" name="image" required> 
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
