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
<style>
	table{
		border: 1px solid red;
		text-align: center;
	}
</style>

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
                <a href="account.jsp" >
                    <span class="material-symbols-sharp">person</span>
                    <h3>Il mio profilo</h3>
                </a>
                <a href="#">
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
        	<form action="addIndirizzo" method="post" id="form">
        		<div class="form-panel">
               			<div class="input-group">
	               			<label>Email</label>
	               			<input type="text" name="email" value="<%=user.getEmail()%>" readonly class="readonly" id="email">
	               		</div>
               			<div class="input-group">
	               			<label>Nome</label>
	               			<input type="text" name="nome" value="<%=user.getNome()%>" id="nome" readonly class="readonly">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
               			</div>
	               		<div class="input-group">
	               			<label>Cognome</label>
	               			<input type="text" name="cognome" value="<%=user.getCognome()%>" id="cognome" readonly class="readonly">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>Telefono</label>
	               			<input type="text" name="telefono" value="<%=user.getTelefono() %>" id="telefono" readonly class="readonly">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>Via*</label>
	               			<input type="text" name="via" id="via">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>Città*</label>
	               			<input type="text" name="citta" id="citta">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>Stato/Provincia*</label>
	               			<input type="text" name="stato_provincia" id="stato_provincia">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>CAP*</label>
	               			<input type="text" name="cap" id="cap">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<div class="input-group">
	               			<label>Paese*</label>
	               			<input type="text" name="paese" id="paese">
	               			<i class="fas fa-check-circle"></i>
							<i class="fas fa-exclamation-circle"></i>
							<small>Error message</small>
	               		</div>
	               		<small>* Campi obbligatori</small><br>
	               		<input type="submit" value="Aggiungi" class="btn">
               		</div>	
        	</form>
        </main>
    </div>
	<%@include file="./head_foot/footer.jsp" %>
	<script type="text/javascript" src="validate-indirizzo1.js"> </script>
</body>
</html>