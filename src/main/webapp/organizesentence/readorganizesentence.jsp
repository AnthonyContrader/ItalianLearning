<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.OrganizeSentenceDTO"%>
    <!-- created by Torquato Di Maio -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read OrganizeSentence</title>
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


	<table>
		<tr>
			
			<th>Solution</th>
			<th>OrganizeSentence</th>
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