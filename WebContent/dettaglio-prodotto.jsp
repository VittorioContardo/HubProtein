<%@page import="model.UserDAO"%>
<%@page import="model.RecensioneDAO"%>
<%@page import="model.RecensioneBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<?> formati = (List<?>) session.getAttribute("formati");
	List<?> gusti_colori = (List<?>) session.getAttribute("gusti_colori");
	
	ProductBean product = (ProductBean) session.getAttribute("product");
	//List<RecensioneBean> recensioni = (List<RecensioneBean>) session.getAttribute("recensioni");
	if(formati == null || gusti_colori == null || product == null){
		session.setAttribute("errore", "errore dettaglio prodotto");
		response.sendRedirect(request.getContextPath() + "/error.jsp" );
	}
	
	//System.out.println(formati);
	//System.out.println(gusti_colori);
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<meta charset="UTF-8">
</head>
<body>
	<%@include file="./head_foot/header.jsp"%>
	<!-- DETTAGLIO PRODOTTO -->
            <div class="small-container single-product">
                <!-- IMMAGINI PICCOLE -->
                <div class="row">
                    <div class="col-2">
                    <img src="<%=product.getImmagine()%>" alt="IMMAGINE">
                    </div><!-- FINE COLONNA -->
                   <!-- INFORMAZIONI SUL PRODOTTO -->
                    
                    <div class="col-2">
                    <form action="addToCart" method="get">
					<%		String categoria = null;
	                       	switch(product.getCategoria()){
	                        	case 1: categoria = "Proteine";break;
	                        	case 2: categoria = "Vitamine";break;
	                        	case 3: categoria = "Alimenti";break;
	                        	case 4: categoria = "Proteine";break;
	                        	case 5: categoria = "Proteine";break;
	                        }%>
	                      	<p>Home/<%=categoria%></p>
	                        <h1><%=product.getNome()%></h1>
	                        <h4><%=product.getPrezzo()%></h4>
	                        <input type="hidden" name="codice" value="<%=product.getCodice()%>">
                        	<%
                        		if(product.getCategoria() == 1 || product.getCategoria() == 3){%>
                        			<h4>Gusto</h4>	
                        	<% 	} else if(product.getCategoria() >= 4){%>
                        			<h4>Colore</h4>
                        	<%	}%>
               				
               				<%
               					if(product.getCategoria() != 2){%>
               						<select name="gusto_colore" id="gusto_colore" onchange="myFunct(<%=product.getCodice()%>, <%=product.getCategoria()%>)">
			                        <%
			                        	Iterator<?> it2 = gusti_colori.iterator();
			                       		while(it2.hasNext()){
			                       			String optionG = (String)it2.next();
			                        %>
			                        		<option <%if(optionG.equals(product.getGusto_colore())){%> selected="selected" <%}%>><%=optionG%></option>                       	
		                        	<%}%>
		                        	</select>
               				<%	} else {%>
               						<input type="hidden" name="gusto_colore" value="NOGusto">
               				<%}%>
                        	
                        	
                        	<h4>Formato</h4>
                        	<select name="formato" id="formato" onchange="myFunct(<%=product.getCodice()%>, <%=product.getCategoria()%>)">
	                        <%
	                        	Iterator<?> it = formati.iterator();
	                       		while(it.hasNext()){
	                       			String optionF = (String)it.next();
	                        %>
	                        		<option <%if(optionF.equals(product.getFormato())){%> selected="selected" <%}%>><%=optionF%></option>                       	
                        	<%}%>
                        	</select>
                        <br>
                        <input type="number" value="1" name="quantita" min="1">
                        <br>
                        <br>
                        <input type="submit" class="btnadd" value="Aggiungi al carrello">
                        </form>
                        <%
                        	if(user != null && user.isAdmin()){%>
                        		<a href="" class="btn">Modifica Prodotto</a>
                        <%}%>
                        
                        <h3>Dattagli Prodotto  <i class="fa fa-indent"></i></h3>
                        <p><%=product.getDescrizione()%></p>
                    </div><!-- FINE COLONNA -->
                </div><!-- FINE RIGA-->
            </div><!-- FINE CONTAINER-->

            <!-- PRODOTTI RELATIVI-->
            <div class="small-container">
                <div class="row row-2">
                    <h2>Prodotti Relativi</h2>
                </div>
            </div>

            <!-- IMMAGINI PRODOTTI RELATIVI-->
            <div class="small-container">
                <div class="row">
                    <!-- COLONNE PER I PRODOTTI -->
                    <div class="col-4">
                        <a href="proteine.jsp"> <img src="./img/sacchetto_proteine.png" alt="IMG">
                            <h4> Proteine in polvere</h4> 

                        </a>
                    </div><!-- FINE COLONNA -->

                    <div class="col-4">
                        <a href="alimentazione.jsp"> <img src="./img/alimenti.png" alt="IMG"></a> 
                    <h4> Alimentazione</h4>  </a> 

                    </div><!-- FINE COLONNA -->


                 </div><!-- FINE RIGA -->
            </div><!-- FINE CONTAINER PICCOLO-->
            
            
            <!-- RECENSIONI-->
            <div class="small-container">
                <div class="row row-2">
                    <h2>Recensioni</h2>
                </div>
            </div>
            <div class="small-container">
            	<div id="error-message" class="error-message"></div>
            	<form  action="addRecensione" method="get" id="form">
					<fieldset class="rating">
						<%
							String emailValue;
							if(user == null){
								emailValue = "";
							} else{
								emailValue = user.getEmail();
							}
						%>
						<input type="text" style="hidden" name="email" value="<%=emailValue %>" id="email">
						<input type="text" value="<%= product.getCodice() %>" style="hidden" name="codiceProdotto" id="codiceProdotto">
						
						<input type="radio" id="star5" name="rating" value="5" id="valore"/><label for="star5" class="full" title="Awesome"></label>
						<input type="radio" id="star4.5" name="rating" value="4.5"/><label for="star4.5" class="half"></label>
						<input type="radio" id="star4" name="rating" value="4" id="valore"/><label for="star4" class="full"></label>
						<input type="radio" id="star3.5" name="rating" value="3.5"/><label for="star3.5" class="half"></label>
						<input type="radio" id="star3" name="rating" value="3" id="valore"/><label for="star3" class="full"></label>
						<input type="radio" id="star2.5" name="rating" value="2.5"/><label for="star2.5" class="half"></label>
						<input type="radio" id="star2" name="rating" value="2" id="valore"/><label for="star2" class="full"></label>
						<input type="radio" id="star1.5" name="rating" value="1.5"/><label for="star1.5" class="half"></label>
						<input type="radio" id="star1" name="rating" value="1" id="valore"/><label for="star1" class="full"></label>
						<input type="radio" id="star0.5" name="rating" value="0.5"/><label for="star0.5" class="half"></label>
					</fieldset>
					<h4 id="rating-value"></h4>
					<textarea name="commento" cols="30" rows="5" placeholder="Your opinion..." id="commento"></textarea>
					<div class="btn-group">
						<button type="submit" class="btn submit">Submit</button>
						<!--  <button class="btn cancel">Cancel</button> -->
					</div>
				</form>
			</div>
			
			<div class="small-container">
			<%	List<RecensioneBean> recensioni = RecensioneDAO.doRetrieveByCode(product.getCodice());
				if(recensioni != null){
					//System.out.println(recensioni);
					for(int i = 0; i < 5; i++){
						if(i >= recensioni.size()) break;
						RecensioneBean currentBean = recensioni.get(i);
						UserBean userRecensione = UserDAO.findByUsername(currentBean.getEmail());
					%>
						<section class="recensioni">
						<div class="recensioni-box-container">
							<div class="recensioni-box">
								<div class="box-top">
									<div class="profile">
										<div class="profile-img">
											<img  src="./img/utente.jpg" alt="PROF">
										</div>
										<div class="user-name">
											<strong><%=userRecensione.getNome() %></strong>
											<span><%=userRecensione.getEmail() %></span>
										</div>
									</div>	
									<div class="recensione">
									<%
										for(int j = 0; j < (int) currentBean.getValore() ; j++){%>
											<i class="far fa-star"></i>
									<% 	}
										if((currentBean.getValore() - (int) currentBean.getValore()) != 0){%>
											<i class="far fa-star-half"></i>
									<% 	}
									%>	
									</div>
								</div>	
								<div class="commento">
									<p><%=currentBean.getCommento() %></p>
								</div>	
							</div>
						</div>
					</section>
				<% 	}	
				} else{%>
					<p>Errore caricamento recensioni</p>
				<%}
				%>
				
			</div>  
	<%@include file="./head_foot/footer.jsp"%>
	<script type="text/javascript">
		function myFunct(codice, categoria){
			var selForm = document.getElementById("formato");
			var selGust = document.getElementById("gusto_colore");
			var formato = selForm.options[selForm.selectedIndex].value;
			var gusto_colore = selGust.options[selGust.selectedIndex].value;
			
			if(categoria == 2){
				gusto_colore = "NOGusto";
			}
			
			var url = "dettaglio?codice="+codice+"&formato="+formato+"&gusto_colore="+gusto_colore;
			console.log(url);
			var xhttp = new XMLHttpRequest();
            xhttp.open("get", url, false);
			xhttp.send();
			window.location.reload();
		}
	</script>
	
	<script type="text/javascript">
		const form = document.getElementById('form');
		const email = document.getElementById('email');
		const codice = document.getElementById('codiceProdotto');
		const valore = document.querySelectorAll('input[name="rating"]');
		const commento = document.getElementById('commento');
		const error_message = document.getElementById('error-message');
		
		
		form.addEventListener('submit', e =>{
		        e.preventDefault();
		        checkInput();
		});
	
		function checkInput(){
			let valoreValue = 0;
			for(const radio of valore){
				if(radio.checked){
					valoreValue = radio.value;
				}
			}
			
		    const emailValue = email.value;
		    const codiceValue = codice.value;
		    const commentoValue = commento.value;
			var valid = true;
			
			console.log(email+codice+valore+commento);
			console.log(valoreValue);
		    if(emailValue === ''){
		        error_message.innerText = 'Effettuare login';
		        valid = false;
		    }
	
		    if(codiceValue === ''){
		    	error_message.innerText = 'Errore codice prodotto';
		        valid = false;
		    }
	
		    if(commentoValue === ''){
		    	error_message.innerText = 'Inserire commento';
		        valid = false;
		    }
		    
		    if(valoreValue <= 0){
		    	error_message.innerText = 'Selezionare stelle';
		    	valid = false;
		    }
		    
		    if(valid){
		    	error_message.innerText = '';
				form.submit();
			}  
		}
	</script>
</body>
</html>