<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.awt.font.ImageGraphicAttribute"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.ProductBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
                <a href="VisualizzaProdottiAdmin.jsp">
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
                <a href="AddProdotto.jsp" class="active">
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
            <h1>Inserisci Prodotto</h1>
            <div class="row">
            	<div class="col-4">
            		<%
            			Boolean inserted = (Boolean) session.getAttribute("inserted");
            			if(inserted != null && inserted == true){%>
            				<h1>Prodotto Inserito</h1>
            		<%}
            		%>
            		
            	</div>
            </div>
            <form action="addProdotto" method="get" id="form">
            	<div class="small-container">
            		<div class="row">
            			<div class="col-3">
            				<div class="input-group">
            					<label for="categoria">Categoria</label>
            					
            						<select name="categoria" id="categoria">
										<option value="0">Scegli Opzione</option>
										<option value="1">Proteine</option>
										<option value="2">Vitamine</option>
										<option value="3">Alimenti</option> 
										<option value="4">Abbigliamento</option> 
										<option value="5">Accessori</option> 
									</select>
  								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
		            		</div>
            			</div>
            		</div>
            		<div class="row">
            			<div class="col-3">
            				<div class="input-group">
								<label for="nome">Formato</label>
								<div id="pre-formati"></div>
								<br>
								<div id="formati"></div>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
            			</div>
            			<div class="col-3">
            				<div class="input-group">
								<label for="cognome">Gusto/Colore</label>
								<div id="gusto">
								</div>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
            			</div>
            		</div>
	            	
					<div class="row">
						<div class="col-3">
							<div class="input-group">
								<label for="codice">Codice</label>
								<input type="text" placeholder="123..." name="codice" id="codice"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
						<div class="col-3">
							<div class="input-group">
								<label for="telefono">Nome</label>
								<input type="text" placeholder="nome prodotto..." name="nome" id="nome"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-3">
							<div class="input-group">
								<label for="descrizione">Descrizione</label>
								<textarea style="background-color:white" id="descrizione" name="descrizione"></textarea>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
						<div class="col-3">
							<div class="input-group">
								<label for="quantita">Quantità in magazzino</label>
								<input type="number" name="quantita" id="quantita"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-3">
							<div class="input-group">
								<label for="prezzo">Prezzo</label>
								<input type="text" placeholder="es. 1.2" name="prezzo" id="prezzo"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
						<div class="col-3">
							<div class="input-group">
								<label for="iva">IVA</label>
								<input type="text" placeholder="X%" name="iva" id="iva"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-3">
							<div class="input-group">
								<label for="vegan">Vegan</label>
								<input type="checkbox" name="vegan" id="vegan"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
						<div class="col-3">
							<div class="input-group">
								<label for="immagine">Immagine</label>
								<input type="file" name="immagine" id="immagine"/>
								<i class="fas fa-check-circle"></i>
								<i class="fas fa-exclamation-circle"></i>
								<small>Error message</small>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-3">
							<input type="submit" class="btnadd" value="Inserisci">
						</div>
					</div>
	            </div>
            </form>      
        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="new-product1.js"></script>
	<script type="text/javascript" src="validate-addProduct.js"></script>
</body>
</html>