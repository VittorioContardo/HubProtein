<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String errore =(String) request.getSession().getAttribute("errore");%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Hub Protein</title>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
		<%@include file="./head_foot/header.jsp"%>
		<% for(int i=0; i<5; i++){
				%><br><% 
			}
			%>
				<h1 align="center">ERRORE</h1>	
			<div id="div2" align="center">
				<h4><a href="" onclick="showError();">Mostra dettagli↓</a></h4>
			</div>
			<div id="error" hidden="hidden" align="center">
				<h4><a href="" onclick="showError();">Nascondi dettagli↑</a></h4>
				<p><h4 align="center"><%=errore %></h4>
			</div>	
			
			<% for(int i=0; i<10; i++){
				%><br><% 
			}
			%>
		
		<%@include file="./head_foot/footer.jsp" %>
</body>

<script>
	function showError(){
		event.preventDefault();
		
		var divError = document.getElementById("error");
		if(divError.hidden == true){
			divError.hidden = false;	
			document.getElementById("div2").hidden = true;
		}else{
			divError.hidden = true;	
			document.getElementById("div2").hidden = false;
		}	
	}
</script>
</html>