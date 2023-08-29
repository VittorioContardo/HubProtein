<%@page import="java.util.Iterator"%>
<%@page import="model.ProductDAO"%>
<%@page import="model.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	@SuppressWarnings("unchecked")
	List<ProductBean> products = (List<ProductBean>) request.getSession().getAttribute("products");
	if( products == null || products.size() == 0 ){
		session.setAttribute("errore", "errore caricamento prodotti");
		response.sendRedirect(request.getContextPath() + "/error.jsp");
		return;
		
	}
	System.out.println(products);
%>
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
    	<%@include file="./head_foot/header.jsp" %>
        <!-- TUTTI I PRODOTTI -->
        <div class="small-container">
            <div class="row row-2">
                <h2>Proteine</h2>       
            </div>
			
			
            <div class="row">
            	<%
				Iterator<ProductBean> it = products.iterator();
				while(it.hasNext()){
					
					ProductBean product = it.next();
					if(product.getCategoria() == 1){
						
					
				%>	
	                <!-- COLONNE PER I PROFOTTI -->
	                <div class="col-4">
	                    <a href="dettaglio?codice=<%=product.getCodice()%>&formato=<%=product.getFormato()%>&gusto_colore=<%=product.getGusto_colore()%>"> 
	                    	<img src="<%=product.getImmagine()%>" alt="IMMAGINE">
		                   	<h4><%=product.getNome()%></h4> 
		                    <!-- RATING CON STELLE -->
		                    <div class="rating">
		                        <i class="fa-solid fa-star"></i>
		                        <i class="fa-solid fa-star"></i>
		                        <i class="fa-solid fa-star"></i>
		                        <i class="fa-solid fa-star-half-stroke"></i>
		                    </div>
		                    <br>
		                    <p>€<%=product.getPrezzo()%></p>
	                  	</a>
	                </div><!-- FINE COLONNA -->
				<%}}%>
            </div><!-- FINE RIGA -->
        </div><!-- FINE CONTAINER -->
        <%@include file="./head_foot/footer.jsp" %>
    </body>
</html>