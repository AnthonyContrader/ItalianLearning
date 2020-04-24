<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.PlaylistDTO"
%>

<!-- Created by Enzo, Gabriella -->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Playlist</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="/playlist/getall">Back</a>
	<a class="active" href="#">Playlist</a>
	<a href="/user/logout" id="logout">Logout</a>
</div>

<br>
<div class="main">

	<%PlaylistDTO c = (PlaylistDTO) request.getSession().getAttribute("dto");%>
	
	<form id="floatleft" action="/playlist/update?id=<%=c.getId()%>" method="post">
	  <div class="row">
	    <div class="col-25">
	      <label for="playlist">Playlist</label>
	    </div>
	    <div class="col-75">
	      <input type="text" required id="name" name="name" value="<%=c.getName()%>">
	    </div>
	  </div>
	  <div class="row">
	    <div class="col-25">
	     <label for="description">Description</label>
	    </div>
	    <div class="col-75">
	      <textarea id="description" name="description"><%=c.getDescription()%></textarea>
	    </div>
	  </div>
	  
	  <button type="submit" >Edit</button>
	</form>	


</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>