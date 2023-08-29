<%@page import="java.util.Iterator"%>
<%@page import="model.IndirizzoDAO"%>
<%@page import="model.IndirizzoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="stylesheet" href="./css/panel2.css" type="text/css">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">

</head>
<body>
	<%@include file="./head_foot/header.jsp"%>
	<%@include file="./head_foot/panel.jsp" %>
	<%
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		List<IndirizzoBean> indirizzi = IndirizzoDAO.doRetrieveByUser(user.getEmail());
		if(indirizzi == null){
			session.setAttribute("errore", "Errore nel caricare gli indirizzi dell'utente");
			response.sendRedirect(request.getContextPath() + "/error.jsp");
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
                <a href="account.jsp" >
                    <span class="material-symbols-sharp">person</span>
                    <h3>Il mio profilo</h3>
                </a>
                <a href="miei-ordini.jsp">
                    <span class="material-symbols-sharp">receipt_long</span>
                    <h3>I miei ordini</h3>
                </a>
                <a href="indirizzi.jsp" class="active">
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
        	<table>
        		<%
        			Iterator<IndirizzoBean> it = indirizzi.iterator();
        			while(it.hasNext()){ 
        				IndirizzoBean ind = it.next();	
        		%>
        				<tr>
        					<td>
        						<%=ind.getVia() %><br>
        						<%=ind.getCitta() %>, <%=ind.getStato_provincia() %><br>
        						<%=ind.getCap() %><br>
        						<%=ind.getPaese() %>
        					</td>
        					<td><a href="modifica-indirizzo.jsp?codice=<%=ind.getCodice() %>">Modifica</a></td>
        				</tr>
        			<%}
        		%>
        		<tr>
        			<td colspan="2" style="text-align:center;"><a href="new-indirizzo.jsp" class="btn">Aggiungi Indirizzo</a></td>
        		</tr>
        	</table>
        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="validate-modificaAccount.js"> </script>
</body>
</html>