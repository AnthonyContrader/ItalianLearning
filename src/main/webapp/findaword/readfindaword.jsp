<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.FindAWordDTO"%>

<!-- created by Gabriella Brunetto -->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read FindAWord</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/findaword/getall">Back</a><!-- il tasto back ti riporta al link -->
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%FindAWordDTO u = (FindAWordDTO) request.getSession().getAttribute("dto");%>


<table>
	<tr> 
		
		<th>Solution</th>
		<th>Sentence</th>
		<th>Definition</th>
		<th>Level</th>
		<th>Category</th>		
	</tr>
	
	<tr>	
		
		<td><%=u.getSolution()%></td>
		<td><%=u.getSentence()%></td>
		<td><%=u.getDefinition()%></td>
		<td><%=u.getLevel().getName()%></td>
		<td><%=u.getCategory().getTitle()%></td>
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