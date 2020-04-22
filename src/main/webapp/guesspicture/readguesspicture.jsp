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
  	<a href="../homeadmin.jsp">Home</a>
  	<a class="active" href="/guesspicture/getall">Back</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>

<div class="main">
<%GuessPictureDTO g = (GuessPictureDTO) request.getSession().getAttribute("dto");%>

	<div class="col-50">
		<table class="col-75">
			<tr>
				<th>Solution</th>
				<th>Level</th>
				<th>Category</th>
			</tr>
			<tr>
				<td><%=g.getSolution()%></td>
				<td><%=g.getLevel().getName()%></td>
				<td><%=g.getCategory().getTitle()%></td>
				
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