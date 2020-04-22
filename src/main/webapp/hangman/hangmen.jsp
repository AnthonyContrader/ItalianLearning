<!-- Created By @Alessandro Alfieri -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HangmanDTO"
	import="it.contrader.dto.CategoryDTO"
	import="it.contrader.dto.LevelDTO"%>
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
	<a href="../homeadmin.jsp">Home</a>
  	<a href="/game/getall">Back</a>
  	<a class="active">Hangmen</a>
  	<a href="/user/logout" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<HangmanDTO> list = (List<HangmanDTO>) request.getSession().getAttribute("list");
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
		List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");
		
		String ans = null;
		try{
			ans = request.getSession().getAttribute("ans").toString();
			request.getSession().removeAttribute("ans");
		}
		catch (Exception e){}
	%>
<% if (ans != null) {%>
	<h2 style="text-align: center; color: <%= ans != "true" ? "red" : "green" %>">
		<%= ans == "true" ? "Operation completed successfully." : "An error has occurred!" %>
	</h2>
<% } %>
<br>

	<table>
		<tr>
			<th>Solution</th>
			<th>Sentence</th>
			<th>Definition</th>
			<th>Level</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (HangmanDTO h : list) {
		%>
		<tr>
			<td>
				<a href="/hangman/read?id=<%=h.getId()%>" style="text-decoration: underline;">
					<%=h.getSolution()%>
				</a>
			</td>
			<td><%=h.getSentence()%></td>
			<td><%=h.getDefinition()%></td>
			<td><%=h.getLevel().getName()%></td>
			<td><%=h.getCategory().getTitle()%></td>
			<td><a href=/hangman/preupdate?id=<%=h.getId()%> style="text-decoration: underline;">Edit</a>
			</td>
			<td><a href=/hangman/predelete?id=<%=h.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="/hangman/insert" method="post">

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
     <label for="idLevel">Level</label>
    </div>
    <div class="col-75">
		<select id="idLevel" name="idLevel" required>
			<option value="">Choose one...</option>
 			<%
				for (LevelDTO level : levelList) {
			%>
			<option value=<%= level.getId() %>> <%= level.getName() %> </option>
			<%
				}
			%>
		</select>
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