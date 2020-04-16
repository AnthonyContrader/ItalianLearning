<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" 
	import="java.util.List"
	import="it.contrader.dto.GuessPictureDTO"
	import="it.contrader.dto.CategoryDTO"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Guess Pictures Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
  	<a href="GameServlet">Back</a>
  	<a class="active" href="GuessPictureServlet?mode=gamelist">Guess Picture</a>
  	<a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
	<%
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
		List<GuessPictureDTO> list = (List<GuessPictureDTO>) request.getAttribute("list");
		
		String ans = null;
		try{
			ans = request.getAttribute("ans").toString();
		}
		catch (Exception e){}
	%>
<% if (ans != null) {%>
	<h2 style="text-align: center; color: <%= ans != "true" ? "red" : "green" %>">
		<%= ans == "true" ? "Operation completed successfully." : "An error has occurred!" %>
	</h2>
<% } %>
	

<br>
	<table>
		<tr>
			<th>Id</th>
			<th>Solution</th>
			<th>Score</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (GuessPictureDTO g : list) {
		%>
		<tr>
			<td><a href=GuessPictureServlet?mode=read&id=<%=g.getId()%>>
				<%=g.getId()%>
			</a></td>
			<td><a href=GuessPictureServlet?mode=read&id=<%=g.getId()%>>
				<%=g.getSolution()%>
			</a></td>
			
			<td><%=g.getScore()%></td>
			
			<td><%=g.getCategory()%></td>
			
			<td><a href=GuessPictureServlet?mode=read&update=true&id=<%=g.getId()%>>Edit</a>
			</td>
			<td><a href=GuessPictureServlet?mode=read&delete=true&id=<%=g.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

<form id="floatright" action="GuessPictureServlet?mode=insert" method="post">

  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Insert solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" min=1 required placeholder="Insert score"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Image</label>
    </div>
    <div class="col-75">
      <input required type="file" id="imageFile" name="imageFile" size="500000" accept="image/*">
      <input required type="hidden" id="image" name="image">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="type">Category</label>
    </div>
   		 <div class="col-75">
			<select id="idCategory" name="idCategory" required>
				<option value="">Choose one...</option>
	 			<%
					for (CategoryDTO category : categoryList) {
				%>
				<option value=<%= category.getId() %>> <%= category.getTitle() %> </option>
				<%
					}
				%>
			</select>
    	</div>
  </div>
  
  <button type="submit" >Insert</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>

<script>
	var input = document.getElementById("imageFile");
	
	input.onchange = function () {
		var file = input.files[0],
	 	reader = new FileReader();
	
		reader.onloadend = function () {
	 		var b64 = reader.result.replace(/^data:.+;base64,/, '');
	 		document.getElementById("image").value = b64;
		};
		reader.readAsDataURL(file);
	};
</script>

</body>
</html>
