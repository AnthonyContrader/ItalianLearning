<!-- Created By @Alessandro Alfieri -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.HangmanDTO"
    import="it.contrader.dto.CategoryDTO"
    import="it.contrader.dto.LevelDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Hangman</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/hangman/getall">Back</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
	HangmanDTO h = (HangmanDTO) request.getSession().getAttribute("dto");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
	List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");
%>


<form id="floatleft" action="/hangman/update?id=<%=h.getId()%>" method="post">
 <div class="row">
    <div class="col-25">
      <label for="solution">Soluzione</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Inserisci la soluzione" value="<%=h.getSolution()%>">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Indizio</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="Inserisci l'indizio" value="<%=h.getSentence()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definizione</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" placeholder="Inserisci la definizione" value="<%=h.getDefinition()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
    	<label for="idLevel">Punteggio</label>
    </div>
    <div class="col-75">
		<select id="idLevel" name="idLevel">
 			<%
				for (LevelDTO level : levelList) {
			%>
			<option value=<%= level.getId() %> <%= level.getId() == h.getLevel().getId() ? "selected" : ""%> >
				<%= level.getName() %>
			</option>
			<%
				}
			%>

		</select>
   	</div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Categoria</label>
    </div>
   		 <div class="col-75">
 			<select id="idCategory" name="idCategory">
	 			<%
					for (CategoryDTO category : categoryList) {
				%>
  				<option value=<%= category.getId() %> <%= category.getId() == h.getCategory().getId() ? "selected" : ""%> >
  					<%= category.getTitle() %>
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