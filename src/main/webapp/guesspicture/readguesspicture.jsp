<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.GuessPictureDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Guess Picture</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active"  href="GuessPictureServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%GuessPictureDTO g = (GuessPictureDTO) request.getAttribute("dto");%>

	<div class="col-50">
		<table class="col-75">
			<tr>
				<th>Id</th>
				<th>Solution</th>
				<th>Score</th>
				<th>Category</th>
			</tr>
			<tr>
			
				<td><%=g.getId()%></td>
				<td><%=g.getSolution()%></td>
				<td><%=g.getScore()%></td>
				<td><%=g.getCategory()%></td>
				
			</tr>
		</table>
	</div>
	
	<div class="col-50">
		<table class="col-100">
			<tr> 
				<th>Image</th>
			</tr>
			<tr>
				<td><img style="max-width: 100%;" src="data:image/png;base64,<%=g.getImage()%>"></td>
			</tr>	
		</table>
	</div>
	
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>