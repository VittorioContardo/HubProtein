<%@page import="model.ProductCart"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Carrello"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%
	Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
	if(cart == null){
		response.sendRedirect("./start");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
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
        	<%
        		if(cart.getProducts().size() <= 0){%>
        			<h1><a href="index.jsp">Continua gli acquisti</a></h1>
        		<%} else{%>
            <table>

                <tr>
                    <th>  Prodotti </th>
                    <th>  Quantità </th>
                    <th>  Totale </th>
                </tr> <!-- FINE RIGA TABELLA-->

                <!-- PRODOTTI DEL CARRELLO-->
                <%
                	Iterator<ProductCart> it = cart.getProducts().iterator();
                	while(it.hasNext()){
                		ProductCart productCart = it.next();%>
                		<tr>
		                    <td>
		                        <div class="cart-info">
		                            <img src="<%=productCart.getProduct().getImmagine() %>">
		                            <div>
		                                <p><%=productCart.getProduct().getNome() %></p>
		                                <small><%=productCart.getProduct().getFormato() + " " + productCart.getProduct().getGusto_colore() %></small><br>
		                                <small><%=productCart.getProduct().getPrezzo() %></small>
		                                <br>
		                                <a onclick="confirmRemove()" href="removeToCart?codice=<%=productCart.getProduct().getCodice()%>&formato=<%=productCart.getProduct().getFormato()%>&gusto_colore=<%=productCart.getProduct().getGusto_colore()%>">Remove</a>
		                            </div>
		                        </div>
		                    </td>
		                    <td>
		                    	<input type="number" value="<%=productCart.getQuantita()%>" readonly>
		                    	<a class="btn" href="quantityCart?action=add&codice=<%=productCart.getProduct().getCodice()%>&formato=<%=productCart.getProduct().getFormato()%>&gusto_colore=<%=productCart.getProduct().getGusto_colore()%>">+</a> 
		                    	<a class="btn" href="quantityCart?action=remove&codice=<%=productCart.getProduct().getCodice()%>&formato=<%=productCart.getProduct().getFormato()%>&gusto_colore=<%=productCart.getProduct().getGusto_colore()%>">-</a>
		                    </td>
		                    <td><%=productCart.getProduct().getPrezzo() * productCart.getQuantita()%></td>
		                </tr><!-- FINE RIGA TABELLA-->
                	<%}%>
            </table><!-- FINE  TABELLA-->
            
            <!-- TABELLA PREZZO-->
            <div class="total-price">
                <table>
                    <tr>
                        <td> Totale </td>
                        <%
                        	if(cart.getTotal() < 50){%>
                        		<td> <%=cart.getTotal() %> </td>		
                        <%} else{%>
                        	<td> <%=cart.getTotal() %> </td>
                        <%}%>
                    </tr>
                    <tr>
                    	<td colspan="2"><a class="btn" href="checkout.jsp">Completa acquisto</a></td>
                    </tr>
                </table><!-- FINE TABELLA-->
            </div> <!-- FINE PREZZO-->
            <%}%> 
        </div> <!-- FINE CARRELLO-->
	<%@include file="./head_foot/footer.jsp" %>
</body>
</html>