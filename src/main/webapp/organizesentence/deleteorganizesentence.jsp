 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.OrganizeSentenceDTO"%>
    <!-- created by Torquato Di Maio -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Delete Organize Sentence</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/organizesentence/getall">Back</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%OrganizeSentenceDTO o = (OrganizeSentenceDTO) request.getSession().getAttribute("dto");%>

<div class="col-50">
	<br>
	<br>
	<br>
	<br>
	<br>
	<table class="col-100">
		<tr> 
			
			<th>Solution</th>
			<th>Organize Sentence</th>
			<th>Definition</th>
			<th>Level</th>
			<th>Category</th>
		</tr>
		<tr>
			
			<td><%=o.getSolution()%></td>
			<td><%=o.getSentence()%></td>
			<td><%=o.getDefinition()%></td>
			<td><%=o.getLevel().getName()%></td>
			<td><%=o.getCategory().getTitle()%></td>
		</tr>	
	</table>
</div>
<div class="col-50">
	<h2 style="text-align: center">Are you sure you want to delete this element?</h2>
	<div class='row'>
		<a href="/organizesentence/delete?id=<%=o.getId()%>" class="col-50" style="text-align: right"><button class="btn-sm">Confirm</button></a>
		<a href="/organizesentence/getall" class="col-50"><button class="btn-sm">Cancel</button></a>
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