<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CategoryDTO"
%>

<!-- created by Enzo Tasca -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href="../css/vittoriostyle.css" rel="stylesheet">
		<title>Categories Manager</title>
	</head>

<body>

<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="../homeadmin.jsp">Home</a>
	<a class="active" href="#">Categories</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>

<div class="main">

	<%
		// list contiene tutti gli elementi della category che veranno stampati nella tabella
		List<CategoryDTO> list = (List<CategoryDTO>) request.getSession().getAttribute("list");
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
			<th>Title</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (CategoryDTO c : list) {
		%>
		<tr>
			<td><a href="/category/read?id=<%=c.getId()%>"><%=c.getTitle()%></a></td>
			
			<td><a href="/category/preupdate?id=<%=c.getId()%>">Edit</a>
			
			<td><a href="/category/predelete?id=<%=c.getId()%>">Delete</a></td>

		</tr>
		<%
			}
		%>
	</table>

	<form id="floatright" action="/category/insert" method="post">
	  <div class="row">
	    <div class="col-25">
	      <label for="category">Title</label>
	    </div>
	    <div class="col-75">
	      <input required type="text" id="title" name="title" placeholder="inserisci il titolo">
	    </div>
	  </div>
	  
	  <div class="row">
	    <div class="col-25">
	     <label for="description">Description</label>
	    </div>
	    <div class="col-75">
	      <textarea id="description" name="description" placeholder="inserisci la descrizione"></textarea>
	    </div>
	  </div>
	  
	  <button type="submit">Insert</button>
	</form>
	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
