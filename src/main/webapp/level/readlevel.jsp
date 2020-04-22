<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.LevelDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Level</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="LevelServlet?mode=levellist">Back</a>  
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%LevelDTO x = (LevelDTO) request.getAttribute("dto");%>

<div class="col-100">
	<table>
		<tr> 
			<th>Id</th>
			<th>Name</th>
			<th>Score</th>		
		</tr>
		<tr>
			<td><%=x.getId()%></td>
			<td> <%=x.getName()%></td>
			<td> <%=x.getScore()%></td>
		</tr>	
	</table>
</div>

<div class="col-100" style="padding-top: 25px;">
	<table>
		<tr> 
			<th>Description</th>
		</tr>
		<tr>
			<td> <%=x.getDescription()%></td>
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