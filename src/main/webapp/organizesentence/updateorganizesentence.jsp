<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
     import="it.contrader.dto.OrganizeSentenceDTO"
    import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Organize Sentence</title>
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

<% OrganizeSentenceDTO o = (OrganizeSentenceDTO) request.getAttribute("dto");
   List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>


<form id="floatleft" action="OrganizeSentenceServlet?mode=update&id=<%=o.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" value="<%=o.getSolution()%>" required placeholder="inserisci soluzione">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="sentence">Organize Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" value="<%=o.getSentence()%>" required placeholder="inserisci la frase disordinata"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" value="<%=o.getDefinition()%>"  placeholder="inserisci la definizione"> 
    </div>
  </div>
  
    <div class="row">
    <div class="col-25">
      <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" value="<%=o.getScore()%>" required placeholder="inserisci il punteggio">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
    </div>
  <div class="col-75">
  	<select id="IdCategory" name="IdCategory" required>
  		<option value=''>Choose one...</option>
  		<%
  			for(CategoryDTO c: categoryList){
  		//valore id categoria, etichetta titolo categoria
  		%>
  				<option value=<%= c.getId() %>><%= c.getTitle()  %></option>
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
</body>
</html>