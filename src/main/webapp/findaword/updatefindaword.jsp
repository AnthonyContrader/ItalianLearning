<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.List"
    import="it.contrader.dto.FindAWordDTO"
    import="it.contrader.dto.CategoryDTO"
    import="it.contrader.dto.LevelDTO"%>
    
    <!-- created by Gabriella Brunetto -->
    
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
  <a href="../homeadmin.jsp">Home</a>
  <a class="active" href="/findaword/getall">Back</a><!-- il tasto back ti riporta al link -->
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
FindAWordDTO u = (FindAWordDTO) request.getSession().getAttribute("dto");
List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");
%>

<form id="floatleft" action="/findaword/update" method="post">

 <input type="hidden" name="id" value =<%=u.getId() %>> 
 
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
      <label for="idCategory">Category</label>
    </div>
    <div class="col-75">
    <!-- select campo a tendina formato dalle option cio� le opzioni ke si visualizzano quando esce il men� a tendina -->
      <select id="idCategory" name="idCategory" required>
      <option value=''> Choose one...</option>
      
      <!-- per ogni categ in catlist crea option con valori id categ e come etichetta il titolo categ -->
      <%
      for(CategoryDTO c : categoryList) {
      %>
     <!-- per menu a tendina sia selezionato sul valore presente nel db grazie all'attributo selected l'operatore ternario � un if-->
      <option value=<%= c.getId() %> <%= Long.valueOf(c.getId()) == u.getCategory().getId() ? "selected" : ""%>> <!-- � un if -->
  					<%= c.getTitle() %>
  					 </option>
      
      <%
      }
      %>
      
      </select>
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="idLevel">Level</label>
    </div>
    <div class="col-75">
    <!-- select campo a tendina formato dalle option cio� le opzioni ke si visualizzano quando esce il men� a tendina -->
      <select id="idLevel" name="idLevel" required>
      <option value=''> Choose one...</option>
      
      <!-- per ogni categ in catlist crea option con valori id categ e come etichetta il titolo categ -->
      <%
      for(LevelDTO c : levelList) {
      %>
     <!-- per menu a tendina sia selezionato sul valore presente nel db grazie all'attributo selected l'operatore ternario � un if-->
      <option value=<%= c.getId() %> <%= Long.valueOf(c.getId()) == u.getLevel().getId() ? "selected" : ""%>> <!-- � un if -->
  					<%= c.getName() %>
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