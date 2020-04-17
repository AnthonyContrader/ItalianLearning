<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.PlaylistDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Playlist</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="PlaylistServlet?mode=playlistlist">Back</a>  
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">

<%PlaylistDTO c = (PlaylistDTO) request.getAttribute("dto");%>

<div class="col-100">
	<table>
		<tr> 
			<th>Id</th>
			<th>Name</th>	
			<th>Description</th>	
		</tr>
		<tr>
			<td><%=c.getId()%></td>
			<td> <%=c.getName()%></td>
			<td> <%=c.getDescription()%></td>
			
		</tr>	
	</table>
</div>
