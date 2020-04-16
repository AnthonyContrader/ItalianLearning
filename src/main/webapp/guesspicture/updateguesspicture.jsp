<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.List"
    import="it.contrader.dto.GuessPictureDTO"
    import="it.contrader.dto.CategoryDTO"
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Guess Picture</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
	GuessPictureDTO g = (GuessPictureDTO) request.getAttribute("dto");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>


<form id="floatleft" action="GuessPictureServlet?mode=update&id=<%=g.getId()%>" enctype="multipart/form-data" method="post">

  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input value="<%=g.getSolution()%>" type="text" id="solution" name="solution" required placeholder="Insert solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input value="<%=g.getScore()%>" type="number" id="score" name="score" required placeholder="insert score"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Image</label>
    </div>
    <div class="col-75">
      <input type="file" size="50" id="image" name="image"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="type">Categoria</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="idCategory">
	 			<%
					for (CategoryDTO c : categoryList) {
				%>
  				<option value=<%= c.getId() %> <%= Integer.valueOf(c.getId()) == g.getIdCategory() ? "selected" : ""%>>
  					<%= c.getTitle() %>
				</option>
  				<%
					}
  				%>

			</select>
    	</div>
  </div>
  
  <button type="submit" >Salva</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>