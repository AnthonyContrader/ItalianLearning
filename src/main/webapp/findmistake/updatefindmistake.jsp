<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.FindMistakeDTO"
    import="it.contrader.dto.CategoryDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit FindMistake</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="FindMistakeServlet?mode=gamelist">Back</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%
	FindMistakeDTO f = (FindMistakeDTO) request.getAttribute("dto");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
%>


<form id="floatleft" action="FindMistakeServlet?mode=update&id=<%=f.getId()%>" method="post">
 <div class="row">
    <div class="col-25">
      <label for="solution">Solution</label>
    </div>
    <div class="col-75">
      <input type="text" id="solution" name="solution" required placeholder="Insert the solution" value="<%=f.getSolution()%>">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Sentence</label>
    </div>
    <div class="col-75">
      <input type="text" id="sentence" name="sentence" required placeholder="Insert the sentence" value="<%=f.getSentence()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="definition">Definition</label>
    </div>
    <div class="col-75">
      <input type="text" id="definition" name="definition" placeholder="Insert the definition" value="<%=f.getDefinition()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="optionA">OptionA</label>
    </div>
    <div class="col-75">
      <input type="text" id="optionA" name="optionA" placeholder="Insert the first option" value="<%=f.getOptionA()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="optionB">Option B</label>
    </div>
    <div class="col-75">
      <input type="text" id=optionB name="optionB" placeholder="Insert the second option" value="<%=f.getOptionB()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="optionC">Option C</label>
    </div>
    <div class="col-75">
      <input type="text" id="optionC" name="optionC" placeholder="Insert the third option" value="<%=f.getOptionC()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="score">Score</label>
    </div>
    <div class="col-75">
      <input type="number" id="score" name="score" placeholder="Insert the score" value="<%=f.getScore()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="idCategory">Category</label>
    </div>
   		 <div class="col-75">
 			<select id="idCategory" name="idCategory">
	 			<%
					for (CategoryDTO category : categoryList) {
				%>
  				<option value=<%= category.getId() %> <%= Integer.valueOf(category.getId()) == f.getIdCategory() ? "selected" : ""%> >
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