<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.GuessPictureDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Guess Picture Manager</title>
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
		// list contiene tutti gli elementi della category che veranno stampati nella tabella
		List<GuessPictureDTO> list = (List<GuessPictureDTO>) request.getAttribute("list");

	%>

	private Integer score;
	private String solution;
	private String image;
	private String category;

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
