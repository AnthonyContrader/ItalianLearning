<!-- Created By @Alessandro Alfieri -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.PlaylistDTO"
    import="java.util.HashMap"
    import="java.util.Map"
    import="java.util.List"%>
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
  <a class="active" href="/playlist/getall">Back</a>  
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">

<%
	PlaylistDTO c = (PlaylistDTO) request.getSession().getAttribute("dto");
	List<List<Map<String,String>>> gameList = (List<List<Map<String,String>>>) request.getSession().getAttribute("gameList");
%>

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

<br>
<br>
<br>
<br>

<div class="col-100">
	<table>
		<tr> 
			<th>Solution</th>	
			<th>Name</th>
			<th>Selected</th>
		</tr>
		<%
			for (List<Map<String,String>> l : gameList) {
				for (Map<String,String> rk : l) {
		%>
		<tr>
			<td><%=(String) rk.get("solution")%></td>
			<td><%=(String) rk.get("name")%></td>
			<td>
				<input type="checkbox" class="selection_cb" id="selected" name="selected" data-id="<%= rk.get("id")%>" data-type-game="<%= rk.get("typeGame")%>" <%= rk.get("checked").equals("true") ? "checked" : "" %>>
			</td>
		</tr>
		<%
				}
			}
		%>
	</table>
</div>
<div class="col-25">
	<button type="submit" onclick="save_playlist()" class="btn-sm" >Save</button>
</div>

<script>
	function save_playlist(e) {
		var list = document.getElementsByClassName("selection_cb");
		var gameList = []
		for(i of list){
			if (i.checked){
				gameList.push([i.getAttribute('data-id'),i.getAttribute('data-type-game')]) 
			}
		}
		console.log(gameList);
		this.document.location.href = "/playlist/editplaylist?gameList=" + gameList + "&id=" + <%=c.getId() %>;
	}
</script>
