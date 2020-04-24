<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.PlaylistDTO"
%>

<!-- Created by Enzo, Gabriella -->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Playlist</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
	<a href="../homeadmin.jsp">Back</a>
	<a class="active" href="#">Playlist</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
	
	<%PlaylistDTO c = (PlaylistDTO) request.getSession().getAttribute("dto");%>

	<div class="col-50">
		<table class="col-100">
			<tr> 
			<th>Id</th>
			<th>Name</th>	
		</tr>
		<tr>
			<td><%=c.getId()%></td>
			<td> <%=c.getName()%></td>				
			</tr>	
		</table>
	
		<table class="col-100">
			<tr> 
				<th>Description</th>
			</tr>
			<tr>
				<td> <%=c.getDescription()%></td>
			</tr>	
		</table>
	</div>
	
	<div class="col-50">
		<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
		<div class='row'>
			<a href="/playlist/delete?id=<%=c.getId()%>" class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
			<a href="/playlist/getall" class="col-50"><button class="btn-sm">Cancel</button></a>
		</div>
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


</div>

<%@ include file="../css/footer.jsp" %>

</body>
</html>