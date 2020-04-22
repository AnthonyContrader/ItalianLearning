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
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/level/levels">Back</a>  
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%LevelDTO l = (LevelDTO) request.getSession().getAttribute("dto");%>

<div class="col-100">
	<table>
		<tr> 
			<th>Name</th>
			<th>Description</th>
			<th>Score</th>		
		</tr>
		<tr>
			<td> <%=l.getName()%></td>
			<td> <%=l.getDescription()%></td>
			<td> <%=l.getScore()%></td>
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