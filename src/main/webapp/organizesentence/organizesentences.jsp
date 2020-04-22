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
<title>Organize Sentence</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="../homeadmin.jsp">Home</a>
  	<a href="../game/getAll">Back</a>
  	<a class="active" href="#">Organize Sentence</a>
  	<a href="/user/logout" id="logout">Logout</a>
</div>
<div class="main">
	<%
		//lista che contiene tutti gli elementi del gioco che verranno stampati nella tabella
		List<OrganizeSentenceDTO> list = (List<OrganizeSentenceDTO>) request.getSession().getAttribute("list");
		List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getSession().getAttribute("categoryList");
		List<LevelDTO> levelList = (List<LevelDTO>) request.getSession().getAttribute("levelList");
		
		String ans = null;
		try{
			ans = request.getSession().getAttribute("ans").toString();
			request.getSession().removeAttribute("ans");
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
			
			<th>Solution</th>
			<th>OrganizeSentence</th>
			<th>Definition</th>
			<th>Level</th>
			<th>Category</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (OrganizeSentenceDTO o : list) {
		%>
		<tr>
			<td><a href="/organizesentence/read?id=<%=o.getId()%>">
					<%=o.getSolution()%>
			</a></td>
			<td><%=o.getSentence()%></td>
			<td><%=o.getDefinition()%></td>
			<td><%=o.getLevel().getName()%></td>
			<td><%=o.getCategory().getTitle()%></td>
			<td><a href="/organizesentence/preupdate?id=<%=o.getId()%>">Edit</a>
			</td>
			<td><a href="/organizesentence/predelete?id=<%=o.getId()%>">Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="/organizesentence/insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Insert Solution">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
      <label for="sentence">Organize Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="Insert Organize Sentence ">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" placeholder="Insert the Definition of the game"> 
    </div>
  </div>
  
 <!-- <div class="row">
    <div class="col-25">
      <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" min=1 required placeholder="Insert the score ">
    </div>
  </div> messo a commento -->
  
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
  				<option value=<%= c.getId() %>><%= c.getTitle()  %></option>
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
  				<option value=<%= level.getId() %>><%= level.getName()  %></option>
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