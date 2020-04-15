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


<form id="floatright" action="GuessPictureServlet?mode=update&id=<%=g.getId()%>" method="post">

  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Inserisci la soluzione">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" required placeholder="Inserisci il punteggio"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="score">Image</label>
    </div>
    <div class="col-75">
      <input type="file" id="image" name="image"> 
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
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
  <button type="submit" >Salva</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>