<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.awt.font.ImageGraphicAttribute"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.ProductBean"%>
<%@page import="java.util.List"%>
<%
	@SuppressWarnings("unchecked")
	List<ProductBean> products = (List<ProductBean>) request.getSession().getAttribute("dettaglio_prodotti");
	if( products == null || products.size() == 0 ){
		session.setAttribute("errore", "errore caricamento prodotti");
		response.sendRedirect(request.getContextPath() + "/error.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/panel2.css" type="text/css">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
</head>
<body>
	<%@include file="./head_foot/header.jsp"%>
	<%@include file="./head_foot/panel.jsp" %>
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
                <a href="VisualizzaProdottiAdmin.jsp" class="active">
                	<span class="material-symbols-outlined">inventory_2</span>
                	<h3>Visualizza Prodotti</h3>
                </a>
                <a href="visualizzaOrdiniAdmin.jsp">
                    <span class="material-symbols-outlined">format_list_bulleted</span>
                    <h3>Visualizzi Ordini</h3>
                </a>
                <a href="visualizzaUtentiAdmin.jsp">
                	<span class="material-symbols-outlined">group</span>
                	<h3>Visualizza Utenti</h3>
                </a>
                <a href="AddProdotto.jsp">
                    <span class="material-symbols-outlined">add</span>
                    <h3>Inserisci Prodotto</h3>
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
			    <a href="VisualizzaProdottiAdmin.jsp">Tutti i prodotti</a>
			    <a href="visualizzaOrdiniAdmin.jsp">Tutti gli ordini</a>
			    <a href="visualizzaUtentiAdmin.jsp">Tutti gli utenti</a>
			    <a href="logout.jsp">Logout</a>
			  </div>
            <h1>Dettaglio Prodotto</h1>
           	<div class="row">
            <%
    		if(products != null && products.size() != 0){
    			Iterator<?> it = products.iterator();
    			while(it.hasNext()){
    				ProductBean product = (ProductBean) it.next();
    		%>	
    		<div class="col-4">
          		<img src="<%=product.getImmagine()%>" alt="IMMAGINE"><br>
            	Codice: <%=product.getCodice() %><br>
            	Formato: <%=product.getFormato() %><br>
            	Gusto o Colore: <%=product.getGusto_colore() %><br>
            	Nome: <%=product.getNome() %><br>
            	Descrizione: <%=product.getDescrizione() %><br>
            	Prezzo: €<%=product.getPrezzo() %><br>
            	Iva: %<%=product.getIva() %><br>
            	Quantità: <%=product.getQuantitaMagazzino() %><br>
            	Vegan: <%=product.isVegan() %>	<br>
            	<a href="caricaProdotto?codice=<%=product.getCodice()%>&formato=<%=product.getFormato()%>&gusto_colore=<%=product.getGusto_colore()%>">
            		<span class="material-symbols-outlined">edit_square</span>
            	</a>		
            	<a href="deleteProduct?codice=<%=product.getCodice()%>&formato=<%=product.getFormato()%>&gusto_colore=<%=product.getGusto_colore()%>">
            		<span class="material-symbols-outlined">delete</span>
            	</a>
       		</div>
    	<%		}
           }%>
    	
  </div>

        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="validate-modificaAccount.js"> </script>
</body>
</html>