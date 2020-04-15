<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.HangmanDTO"
    import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Modifica Impiccato</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="HangmanServlet?mode=gamelist">Indietro</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
	HangmanDTO h = (HangmanDTO) request.getAttribute("dto");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>


<form id="floatleft" action="HangmanServlet?mode=update&id=<%=h.getId()%>" method="post">
 <div class="row">
    <div class="col-25">
      <label for="solution">Soluzione</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" value="<%=h.getSolution()%>">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Indizio</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" value="<%=h.getSentence()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definizione</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" value="<%=h.getDefinition()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="score">Punteggio</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" value="<%=h.getScore()%>"> 
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
  				<option value=<%= c.getId() %> <%= Integer.valueOf(c.getId()) == h.getIdCategory() ? "selected" : ""%>>
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