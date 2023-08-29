<%@page import="model.FatturaDAO"%>
<%@page import="model.ProductCart"%>
<%@page import="model.ProductBean"%>
<%@page import="model.OrdineDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.OrdineBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/panel2.css" type="text/css">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
</head>
<body>
	<%@include file="./head_foot/header.jsp"%>
	<%@include file="./head_foot/panel.jsp"%>
	<%
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
	%>
		<div class="container-panel">
        <aside>
            
            <div class="sidebar-panel">
                <a href="account.jsp">
                    <span class="material-symbols-sharp">person</span>
                    <h3>Il mio profilo</h3>
                </a>
                <a href="miei-ordini.jsp" class="active">
                    <span class="material-symbols-sharp">receipt_long</span>
                    <h3>I miei ordini</h3>
                </a>
                <a href="indirizzi.jsp">
                    <span class="material-symbols-sharp">home</span>
                    <h3>I miei indirizzi</h3>
                </a>
                <a href="logout">
                    <span class="material-symbols-sharp">logout</span>
                    <h3>Logout</h3>
                </a>
            </div>
        </aside>
        <main>
        	<div class="dropdown">
			  <button class="dropbtn">Menu</button>
			  <div class="dropdown-content">
			    <a href="account.jsp">Il mio profilo</a>
			    <a href="miei-ordini.jsp">I miei ordini</a>
			    <a href="indirizzi.jsp">I miei indirizzi</a>
			  </div>
			</div>
            <h1>Il miei ordini</h1>
            <div class="insights">
            		<%
	               		OrdineBean ordine = (OrdineBean) session.getAttribute("ordine");
	               		if(ordine == null){
	               			session.setAttribute("errore", "errore caricamento ordine");
	               			response.sendRedirect(request.getContextPath() + "/error.jsp");
	               			return;
	               	 	} else{
	               	 		for(ProductCart bean : ordine.getProduct()){%>
	               	 			<div class="row">
	               	 				<div class="col-4">
	               	 					<img src="<%=bean.getProduct().getImmagine() %>" alt="Immagine Prodotto" class="dettaglioOrdine">
	               	 				</div>
	               	 				<div class="col-4">
	               	 					<%=bean.getProduct().getNome() %><br>
	               	 					<%=bean.getProduct().getGusto_colore() %>
	               	 					<%=bean.getProduct().getFormato() %>
	               	 				</div>
	               	 			</div>
	               	 			
	               	 		<%}		
	               	}%>
	               	<div class="row">
     	 				<a href="fatturaPDF?codiceFattura=<%=FatturaDAO.getIdFatturaByIdOrdine(ordine.getId()) %>&idOrdine=<%=ordine.getId() %>"  target="_blank">Stampa fattura</a>
       	 			</div>
            </div>
        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="validate-modificaAccount.js"> </script>
</body>
</html>