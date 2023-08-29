<%@page import="model.CategoriaDAO"%>
<%@page import="java.awt.font.ImageGraphicAttribute"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     <% ProductBean product = (ProductBean) request.getSession().getAttribute("modifica_prodotto");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza Prodotti</title>
<link rel="stylesheet" href="./css/panel4.css" type="text/css">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
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
       	    <h1>Modifica Prodotto</h1>
       	    <div class="insight">
	       	    <form action="update" method = "get">
		       	    <div class="form-panel">
			       	    <div class=input-group>
				       	    <div class="row">
				       	   		<div class=col-4> 
				       	    		<img src="<%=product.getImmagine()%>" alt="IMMAGINE">
				       	    		<input type="hidden" name="oldImg" value="<%=product.getImmagine()%>">
				       	    		<input type="file" name="newImg" id="newImg">
								</div>				       	   	
				       	    </div>
			       	    </div>
			       	    <div class="row">
			       	    	<div class=input-group>
				       	    	<div class="col-4">
				       	    		<label>Codice</label>
				       	    		<input type="text" value = "<%=product.getCodice() %>" readonly class="readonly" name="codice" id="codice">
				       	    	</div>
			       	    	</div>
			       	    	<div class=input-group>
				       	    	<div class="col-4">
				       	    		<label>Formato</label>
				       	    		<input type="text" value = "<%=product.getFormato() %>" readonly class="readonly" name="formato" id="formato">
				       	    	</div>
			       	    	</div>
			       	    	<div class=input-group>
				       	    	<div class="col-4">
				       	    		<label>Gusto/colore</label>
				       	    		<input type="text" value = "<%=product.getGusto_colore() %>" readonly class="readonly" name="gusto_colore" id="gusto_colore">
				       	    	</div>
			       	    	</div>
			       	    </div>
			       	    <div class="row">
				       	    <div class="input-group">
				       	    	<div class="col-4">
				       	    		<label>Nome</label>
					       	    	<input type="text" value = "<%=product.getNome() %>" name="nome" id="nome">
				       	    	</div>
				       	   	</div>
				       	   	<div class="input-group">	
				       	    	<div class="col-4">
				       	    		<label>Descrizione</label>
					       	    	<textarea style="background-color:white" id="descrizione" name="descrizione"><%=product.getDescrizione() %></textarea>
				       	    	</div>
				       	    </div>	
				       	   	<div class="input-group">	
				       	    	<div class="col-4">
				       	    		<label>Quantità in magazzino</label>
					       	    	<input type="number" value = "<%=product.getQuantitaMagazzino() %>" name="quantita_magazzino" id="quantita_magazzino">
				       	    	</div>
				       	    </div>	
			       	    </div>
			       	    <div class="row">
			       	    	<div class="input-group">
			       	   			<div class="col-4">	
				       	    		<label for="prezzo">Prezzo</label>
				       	    		<input type="text" value="<%=product.getPrezzo() %>" name="prezzo" id="prezzo">
			       	    		</div>
			       	    	</div>
			       	    	<div class="input-group">
			       	   			<div class="col-4">	
				       	    		<label for="prezzo">Iva</label>
				       	    		<input type="text" value="<%=product.getIva() %>" name="iva" id="iva">
			       	    		</div>
			       	    	</div>
				       	    <div class="col-4">
								<div class="input-group">
									<label for="vegan">Vegan</label>
									<input type="checkbox" <%if(product.isVegan()) {%>checked<%} %> name="vegan" id="vegan">
									<small>Error message</small>
								</div>
							</div>
							<div class="col-4">
								<div class="input-group">
									<label>Nome Categoria</label>
									<input type="text" value=<%=CategoriaDAO.doRetrieveByKey(product.getCategoria()).getNome() %> readonly class="readonly">
								</div>
							</div>
							<div class="col-4">
							<div class="input-group">
									<label>Codice Categoria</label>
									<input type="text" value=<%=product.getCategoria() %> readonly class="readonly" name = "categoria" id="categoria">
								</div>
							</div>
			       	    </div>
			       	    <div class="row">
			       	    	<div class="col-4">
			       	    		<input type="submit" class="btn" value="Inserisci">
			       	    	</div>
			       	    </div>
		       	    </div>
	       	    </form>
       	    </div>
    	</main>
 	    </div>      
  <%@include file="./head_foot/footer.jsp" %>
  <script type="text/javascript" src="validate-addProduct.js"></script>
</body>
</html>