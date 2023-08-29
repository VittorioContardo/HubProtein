<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/panel2.css" type="text/css">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
</head>
<body>
	<%@include file="./head_foot/header.jsp"%>
	<%
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
	%>
		<div class="container-panel">
        <aside>
            <div class="top-panel">
                <div class="close" id="close-btn">
                    <span class="material-icons-sharp">close</span>
                </div>
            </div>
            <div class="sidebar-panel">
                <a href="AddProdotto.jsp" class="">
                    <span class="material-symbols-outlined">add</span>
                    <h3>Inserisci Prodotto</h3>
                </a>
                <a href="VisualizzaProdottiAdmin.jsp">
                	<span class="material-symbols-outlined">inventory_2</span>
                	<h3>Visualizza Prodotti</h3>
                </a>
                <a href="">
                    <span class="material-symbols-outlined">format_list_bulleted</span>
                    <h3>Visualizzi Ordini</h3>
                </a>
                <a>
                	<span class="material-symbols-outlined">group</span>
                	<h3>Visualizza Utenti</h3>
                </a>
                <a href="logout">
                    <span class="material-symbols-sharp">logout</span>
                    <h3>Logout</h3>
                </a>
            </div>
        </aside>
        <main>
            <h1>Inserisci Prodotto</h1>
            <div class="insights">
            	
            </div>
        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="validate-modificaAccount.js"> </script>
</body>
</html>