<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
     import="it.contrader.dto.OrganizeSentenceDTO"
     import="it.contrader.dto.LevelDTO"
    import="it.contrader.dto.CategoryDTO"%>
    <!-- created by Torquato Di Maio -->
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
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/organizesentence/getall">Back</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>
<div class="main">

<% OrganizeSentenceDTO o = (OrganizeSentenceDTO) request.getSession().getAttribute("dto");
   List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
   List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");
%>


<form id="floatleft" action="/organizesentence/update" method="post">
  <!-- il seguente campo e' nascosto quindi posso metterlo ovunque nella form-->
  <input type="hidden" name="id" value =<%=o.getId() %>>
  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" value="<%=o.getSolution()%>" required placeholder="Insert Solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="sentence">Organize Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" value="<%=o.getSentence()%>" required placeholder="Insert Organize Sentence"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" value="<%=o.getDefinition()%>"  placeholder="Insert the Definition of the game"> 
    </div>
  </div>
  

  
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
    </div>
  <div class="col-75">
  	<select id="idCategory" name="idCategory" required>
  		<option value=''>Choose one...</option>
  		<%
  			for(CategoryDTO c: categoryList){
  		//valore id categoria, etichetta titolo categoria
  		%>
  				<option value=<%= c.getId() %> <%= Long.valueOf(c.getId()) == o.getCategory().getId() ? "selected" : "" %>>
  				<%= c.getTitle()  %></option>
  		<% 
  			}
  		%>
  		</select>
 
  	 </div>
	</div>
	
	<div class="row">
    <div class="col-25">
      <label for="idLevel">Level</label>
    </div>
  <div class="col-75">
  	<select id="idLevel" name="idLevel" required>
  		<option value=''>Choose one...</option>
  		<%
  			for(LevelDTO level: levelList){
  		//valore id categoria, etichetta titolo categoria
  		%>
  				<option value=<%= level.getId() %> <%= Long.valueOf(level.getId()) == o.getLevel().getId() ? "selected" : "" %>>
  				<%= level.getName()  %></option>
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