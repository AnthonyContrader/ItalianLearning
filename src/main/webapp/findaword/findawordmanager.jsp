<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.FindAWordDTO"
	import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Find a Word Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
  	<a href="GameServlet">Back</a>
  	<a class="active" href="FindAWordServlet?mode=gamelist">Find a Word</a>
  	<a href="LogoutServlet" id="logout">Logout</a>
</div>
	<%
	//list contiene tutti gli elementi del gioco che verrenno stampati nella tabella
		List<FindAWordDTO> list = (List<FindAWordDTO>) request.getAttribute("list");
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
	%>

<br>
<!-- stempo gli elementi contenuti in list nella tabella -->
	<table>
		<tr>
			<th>Id</th>
			<th>Solution</th>
			<th>Sentence</th>
			<th>Definition</th>
			<th>Score</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
		//per ogni elemento contenuto nella lista lo metto nella variabile u e lo stampo
			for (FindAWordDTO u : list) {
		%>
		<tr>
			<td><a href=FindAWordServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getId()%>
			</a></td>
			
			<td><%=u.getSolution()%></td>
			<td><%=u.getSentence()%></td>
			<td><%=u.getDefinition()%></td>
			<td><%=u.getScore()%></td>
			<td><%=u.getCategory()%></td>
			
			<td><a href=FindAWordServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=FindAWordServlet?mode=read&delete=true&id=<%=u.getId()%> style="text-decoration: underline;">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="FindAWordServlet?mode=insert" method="post">

  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input type="text" id="solution" name="solution" required placeholder="Insert Solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="sentence">Sentence</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input type="text" id="sentence" name="sentence" required placeholder="Insert Sentence">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="definition">Definition</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input type="text" id="definition" name="definition" placeholder="Insert Definition">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25"><!--si prende il 25% dello spazio  -->
      <label for="score">Score</label>
    </div>
    <div class="col-75">
    <!-- input crea campo di inserimento -->
      <input type="number" id="score" name="score" required placeholder="Insert Score">
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
     
      <option value=<%= c.getId() %>> <%=c.getTitle() %> </option>
      
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
