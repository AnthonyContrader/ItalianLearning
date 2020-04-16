<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.LevelDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Levels Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar"> <!-- questa e la navbar -->
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="LevelServlet?mode=levellist">Levels</a>
  <a href="LogoutServlet" id="logout">Logout</a>  
</div>
<div class="main">
	<%
		// list contiene tutti gli elementi della level che veranno stampati nella tabella
		List<LevelDTO> list = (List<LevelDTO>) request.getAttribute("list");
		String ans = null;
		try{
			ans = request.getAttribute("ans").toString();
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
			<th>Id</th>
			<th>Name</th>
			<th>Score</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (LevelDTO x : list) {
		%>
		<tr>
			<td><a href=LevelServlet?mode=read&id=<%=x.getId()%>>
				<%=x.getId()%>
			</a></td>
			<td><a href=LevelServlet?mode=read&id=<%=x.getId()%>>
				<%=x.getName()%>
			</a></td>
			<td><%=x.getScore()%></td>
			
			<td><a href=LevelServlet?mode=read&update=true&id=<%=x.getId()%>>Edit</a>
			</td>
			<td><a href=LevelServlet?mode=read&delete=true&id=<%=x.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

	<form id="floatright" action="LevelServlet?mode=insert" method="post">
	  <div class="row">
	    <div class="col-25">
	      <label for="level">Name</label>
	    </div>
	    <div class="col-75">
	      <input required type="text" id="name" name="name" placeholder="Insert name">
	    </div>
	  </div>
	  
	  <div class="row">
	    <div class="col-25">
	     <label for="description">Description</label>
	    </div>
	    <div class="col-75">
	      <textarea id="description" name="description" placeholder="Insert Description"></textarea>
	    </div>
	  </div>
	  
	  <div class="row">
	    <div class="col-25">
	     <label for="description">Score</label>
	    </div>
	    <div class="col-75">
	      <input type="number" required id="score" name="score" placeholder="Insert Score">
	    </div>
	  </div>
	  
	  <button type="submit" >Insert</button>
	</form>
	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
