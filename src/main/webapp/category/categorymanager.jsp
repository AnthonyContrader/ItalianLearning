<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar"> <!-- questa e la navbar -->
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>  
</div>
<div class="main">
	<%
		// list contiene tutti gli elementi della category che veranno stampati nella tabella
		List<CategoryDTO> list = (List<CategoryDTO>) request.getAttribute("list");

	%>

<br>
	<!-- Stampo gli elementi contenuti in list nella tabella-->
	<table>
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (CategoryDTO c : list) {
		%>
		<tr>
			<td><a href=CategoryServlet?mode=read&id=<%=c.getId()%>>
				<%=c.getId()%>
			</a></td>
			<td><a href=CategoryServlet?mode=read&id=<%=c.getId()%>>
				<%=c.getTitle()%>
			</a></td>
			
			<td><a href=CategoryServlet?mode=read&update=true&id=<%=c.getId()%>>Edit</a>
			</td>
			<td><a href=CategoryServlet?mode=read&delete=true&id=<%=c.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

	<form id="floatright" action="CategoryServlet?mode=insert" method="post">
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
	  
	  <button type="submit" >Insert</button>
	</form>
	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
