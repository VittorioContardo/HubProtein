<%@page import="model.ProductCart"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Carrello"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
	<script>
		function confirmRemove(){
			confirm("Vuoi rimuovere il prodotto dal carrello?");
		}
	</script>
</head>
<body>
	<%@include file="./head_foot/header.jsp" %>
	<!-- CARRELLO-->
        <div class="small-container carrello">
        	<h1>Richiesta inviata!!</h1>
        	<h3>Vi contatteremo via email.</h3>
        	<a href="index.jsp"><h3>Torna alla home</h3></a>
        </div>
	<%@include file="./head_foot/footer.jsp" %>
</body>
</html>