<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.PlaylistDTO"
%>

<!-- Created by Enzo, Gabriella -->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Playlist Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="../homeadmin.jsp">Home</a>
	<a class="active" href="#">Playlist</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>

<div class="main">
	<%
		// list contiene tutti gli elementi della category che veranno stampati nella tabella
		List<PlaylistDTO> list = (List<PlaylistDTO>) request.getSession().getAttribute("list");
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
	<!-- Stampo gli elementi contenuti in list nella tabella-->
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (PlaylistDTO c : list) {
		%>
		<tr>
			<td><a href="/playlist/read?id=<%=c.getId()%>">
				<%=c.getId()%>
			</a></td>
			<td><a href="/playlist/read?id=<%=c.getId()%>">
				<%=c.getName()%>
			</a></td>
			
			<td><a href="/playlist/preupdate?id=<%=c.getId()%>">Edit</a>
			</td>
			<td><a href="/playlist/predelete?id=<%=c.getId()%>" style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

	<form id="floatright" action="/playlist/insert" method="post">
	  <div class="row">
	    <div class="col-25">
	      <label>Name</label>
	    </div>
	    <div class="col-75">
	      <input required type="text" id="name" name="name" placeholder="insert the name">
	    </div>
	  </div>
	  
	  <div class="row">
	    <div class="col-25">
	     <label>Description</label>
	    </div>
	    <div class="col-75">
	      <textarea id="description" name="description" placeholder="insert the description"></textarea>
	    </div>
	  </div>
	  
	  <button type="submit">Insert</button>
	</form>
	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
