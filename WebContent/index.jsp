<%@page import="model.Carrello"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
	if(cart == null){
		response.sendRedirect("./start");
		return;
	}
%>
<!DOCTYPE html>
<html>
	<%@include file="./head_foot/header.jsp" %>
	<!-- BANNER PER I PRODOTTI IN SALDO -->
	    <div class="header">
            <div class="container">
                <div class="row">
                    <div class="col-2">
                        <h1>Acquista  nuovi prodotti</h1>
                        <p>Ogni giorno nuovi prodotti in saldo</p>
                        <a href="products.jsp" class="btn">Esplora &#8594;</a>
                    </div>
                    <div class="col-2">
                        <img src="./img/ragazzachebeve.png">
                    </div>
                </div>
            </div>      
        </div>
	<!-- CATEGORIE -->
        <div class="categorie">
            <div class="small-container">
                <h2 class="title">Categorie</h2>
                <div class="row">
                 	 <div class="col-3">
                        <a href="proteine.jsp"><img src="./img/sacchetto_proteine.png">
                        <h4> Proteine</h4>
                    </div><!-- FINE COLONNA -->
                    
                     <div class="col-3">
                        <a href="abbigliamento.jsp"><img src="./img/maglietta_nera.png">
                        <h4> Abbigliamento/Accessori</h4>
                    </div><!-- FINE COLONNA -->
                    
                     <div class="col-3">
                        <a href="vitamine.jsp"><img src="./img/sacchetto_vitamine.png">
                        <h4> Vitamine</h4>
                    </div><!-- FINE COLONNA -->
                    
                     <div class="col-3">
                        <a href="alimentazione.jsp"><img src="./img/alimenti.png">
                        <h4> Alimentazione</h4>
                    </div><!-- FINE COLONNA -->
                    
                    <div class="col-3">
                        <a href="vegan.jsp"><img src="./img/vegan.png">
                        <h4> Vegane</h4>
                    </div><!-- FINE COLONNA -->

                </div><!-- FINE RIGA -->
            </div><!-- FINE CONTAINER PICCOLO -->
        </div><!-- FINE CATEGORIA -->

        <!-- PRODOTTI PIU VENDUTI -->
        <div class="small-container">
            <h2 class="title">I più venduti</h2>
            <div class="row">

                <!-- COLONNE PER I PROFOTTI -->
                <div class="col-4">
                    <a href="proteine.jsp"> <img src="./img/sacchetto_proteine.png">
	                    <h4> Proteine in polvere</h4>
	                     <!-- RATING CON STELLE -->
	           
	                </a>
                  
                </div><!-- FINE COLONNA -->

                <div class="col-4">
                    <a href="alimentazione.jsp"> <img src="./img/alimenti.png">
	                    <h4> Alimenti</h4>
	                     <!-- RATING CON STELLE -->
	         
	                
	                </a>
                   
                </div><!-- FINE COLONNA -->

                <div class="col-4">
                    <a href="abbigliamento.jsp"> <img src="./img/maglietta_nera.png">
	                    <h4> Abbigliamento</h4>
	                     <!-- RATING CON STELLE -->
	              
	             
	                </a>
                  
                </div><!-- FINE COLONNA -->
                
            </div><!-- FINE ROW -->

            <!-- PRODOTTI INSERITI PER ULTIMO -->
        <h2 class="title">Nuovi Proddotti</h2>
        <div class="row">

                <div class="col-4">
                    <a href="abbigliamento.jsp"> <img src="./img/shaker.png">
	                    <h4> Shaker</h4>
	             
	     
	                </a>
                </div><!-- FINE COLONNA -->

                <div class="col-4">
                    <a href="abbigliamento.jsp"> <img src="./img/maglietta_bianca.png">
	                    <h4> T-shirt bianca</h4>
	                    
	                </a>
                </div><!-- FINE COLONNA -->


              
        </div><!-- FINE RIGA -->
        </div><!-- FINE PICCOLO CONTAINER -->

        <!-- BANNER PER L'OFFERTA DEL GIORNO -->   
        <div class="offer">
            <div class="small-container">
                <div class="row">
                    <div class="col-2">
                        <img src="./img/sacchetto_proteine.png"class="offer-img">
                    </div><!-- FINE COLONNA -->

                    <div class="col-2">
                        <p>Offerta esclusiva!!</p>
                        <h1>Proteine al cioccolato</h1>
                        <small>
                     
                        </small>
                        <br>
                        <a href="proteine.jsp" class="btn">Compra ora &#8594;</a>
                    </div><!-- FINE COLONNA -->
                </div><!-- FINE RIGA -->
            </div><!-- FINE PICCOLO CONTAINER -->
        </div><!-- FINE OFFERTA -->

      
	<%@include file="./head_foot/footer.jsp" %>
</body>
</html>