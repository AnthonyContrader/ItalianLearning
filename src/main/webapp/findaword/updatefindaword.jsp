<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.List"
    import="it.contrader.dto.FindAWordDTO"
    import="it.contrader.dto.CategoryDTO"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Find a word</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="FindAWordServlet?mode=gamelist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
FindAWordDTO u = (FindAWordDTO) request.getAttribute("dto");
List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>

<form id="floatleft" action="FindAWordServlet?mode=update&id=<%=u.getId()%>" method="post">

  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
   	 <input value="<%=u.getSolution() %>" type="text" id="solution" name="solution" required placeholder="Inserisci la soluzione">  
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="sentence">Sentence</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input value="<%=u.getSentence() %>"type="text" id="sentence" name="sentence" required placeholder="Inserisci l'indizio">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="definition">Definition</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input value="<%=u.getDefinition() %>"type="text" id="definition" name="definition" placeholder="Inserisci la definizione">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="score">Score</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input value="<%=u.getScore() %>"type="number" id="score" name="score" required placeholder="Inserisci il punteggio">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="idCategory">Category</label>
    </div>
    <div class="col-75">
    <!-- select campo a tendina formato dalle option cioè le opzioni ke si visualizzano quando esce il menù a tendina -->
      <select id="idCategory" name="idCategory" required>
      <option value=''> Choose one...</option>
      
      <!-- per ogni categ in catlist crea option con valori id categ e come etichetta il titolo categ -->
      <%
      for(CategoryDTO c : categoryList) {
      %>
     <!-- per menu a tendina sia selezionato sul valore presente nel db grazie all'attributo selected l'operatore ternario è un if-->
      <option value=<%= c.getId() %> <%= Integer.valueOf(c.getId()) == u.getIdCategory() ? "selected" : ""%>> <!-- è un if -->
  					<%= c.getTitle() %>
  					 </option>
      
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