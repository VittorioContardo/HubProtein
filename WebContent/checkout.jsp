<%@page import="model.SpedizioneDAO"%>
<%@page import="model.SpedizioneBean"%>
<%@page import="model.IndirizzoDAO"%>
<%@page import="model.IndirizzoBean"%>
<%@page import="java.util.List"%>
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
<title>Insert title here</title>
</head>
<body>
<%@include file="./head_foot/header.jsp" %>
<form action="completaAcquisto" method="get">
<table class="tabella-principale">
<tbody>
<tr class="tr1">
  <td class="td1"></td>
  <td class="td2">
    <table class="tabella-interna">
      <tr class="tr1-ti">
        <td class="td1-ti">
          <p><b>Indirizzo di consegna</b></p>
        </td>
        <td class="td2-ti">
        <%
        	if(user == null){
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return;
			}%>
        <input type="text" id= "email" name="user" value="<%=user.getEmail()%>">
	        <%
			List<IndirizzoBean> indirizzi = IndirizzoDAO.doRetrieveByUser(user.getEmail());
	        if(indirizzi.size() <= 0){
	        	response.sendRedirect(request.getContextPath()+"/new-indirizzo.jsp");
	        	return;
	        }
			Iterator<IndirizzoBean> it =  indirizzi.iterator();
			while(it.hasNext()){
			IndirizzoBean indirizzo= it.next();
			%>
           <div class="info">
            <p><input type="radio" name ="indirizzo" value="<%= indirizzo.getCodice() %>" required> <%= indirizzo.getVia()%></p>
            <p><%= indirizzo.getCitta()%>,<%= indirizzo.getStato_provincia()%> <%= indirizzo.getCap() %></p>
            <p><%= indirizzo.getPaese() %></p>
              </div>
             <br>
          <%}%>
            <p><a href="indirizzi.jsp">Aggiungi istruzioni per la consegna</a></p>
        </td>
      </tr>
    </table>
  </td>
  <td class="td3" rowspan="3">
    <div class="conferma-acquisto">
        <button class="btn-acquisto">Aquista ora</button>
      <p>Confermando il tuo ordine accetti integralmente 
      le nostre Condizioni generali di uso e vendita. 
      L'acquisto sar� completato solo con la conferma di spedizione.
      Prendi visione della nostra Informativa sulla privacy, della nostra Informativa
      sui Cookie e della nostra Informativa sulla Pubblicit� definita in base agli interessi.</p>
    </div>
    <table class="riepilogo-ordine">
    <tr class="tr1-to">
      <td class="td1-to" colspan="2">
        <p><b>Riepilogo ordine</b></p>
      </td>
    </tr>
    <tr class="tr2-to">
      <td class="td1-to">
        <div class="info">
          <p><small>articoli:</small></p>
          <p><small>costo spedizione:</small> </p>
        </div>
      </td>
      <td class="td2-to">
        <div class="info">
          <p><small id="articoli"><%= cart.getTotal() %></small></p>
          <p><small id="costo-spedizione">-</small></p>
        </div>
      </td>
    </tr>
    <tr class="tr3-to">
      <td class="td1-to">
        <div class="info">
          <p><b>Costo totale:</b></p>
        </div>
      </td>
      <td class="td2-to">
        <div class="info">
          <p><b id="totale">-</b></p>
        </div>
      </td>
    </tr>
    </table>
  </td>
  <td class="td4"></td>
</tr><!---FINE PRIMA RIGA-->
<tr class="tr2">
  <td class="td1"></td>
  <td class="td2">
    <table class="tabella-interna">
    <tr class="tr1-ti">
      <td class="td1-ti">
        <p><b>Metodi di pagamento</b></p>
      </td>
      <td class="td2-ti">
        <div class="info">
          <p><input type="radio" name="metodo_pagamento" value="Visa" required><i class="fa-brands fa-cc-visa"></i>  Visa</p>
          <p><input type="radio"  name="metodo_pagamento" value="Mastercard"><i class="fa-brands fa-cc-mastercard"></i> Mastercard</p>
          <p><input type="radio" name="metodo_pagamento" value="Payapal"><i class="fa-brands fa-paypal"></i>  Payapal</p>
          <p><input type="radio"  name="metodo_pagamento" value="Apple-pay"><i class="fa-brands fa-apple-pay"></i>  Apple pay</p>
          <p><input type="radio"  name="metodo_pagamento" value="Google-pay"><i class="fa-brands fa-google-pay"></i>  Google pay</p>
        </div>
      </td>
    </tr>
    </table>
  </td>
  <!---td3 non c'� per via del rowspan-->
  <td class="td4"></td>
</tr>
<tr class="tr3">
  <td class="td1"></td>
  <td class="td2">
    <table class="tabella-interna">
    <tr class="tr1-ti">
      <td class="td1-ti">
        <p><b>Metodi di spedizione</b></p>
      </td>
      <td class="td2-ti">
        <div class="info">
       		<p><b>Scegli modalita di spedizione</b></p>
			<%
			List<SpedizioneBean> spedizioni = SpedizioneDAO.doRetrieveAll();
			if(spedizioni == null){
				session.setAttribute("errore", "errore nel caricamento delle spedizioni");
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
			Iterator<SpedizioneBean> spedIt = spedizioni.iterator();
			while(spedIt.hasNext()){
				SpedizioneBean spedizione = spedIt.next();
			%>		
			<p><input type="radio" name="spedizione" value="<%=spedizione.getCodice()%>" required>
			<input type="hidden" value="<%=spedizione.getCosto() %>" id="<%=spedizione.getCodice()%>">
			<%= spedizione.getTipo()%></p>
			<p><small><%=spedizione.getTempo() %></small></p>
			<p><small>Costo:<%= spedizione.getCosto()%> euro</small></p>
        	<%} %>
        </div>
      </td>
    </tr>
    </table>
  </td>
  <!---td3 non c'� per via del rowspan-->
  <td class="td4"></td>
</tr>
<tr class="tr5">
  <td class="td1"></td>
  <td class="td2">
    <table class="tabella-interna">
    <tr class="tr1-ti">
      <td class="td1-ti" colspan="3">
        <small>Confermando il tuo ordine accetti integralmente 
          le nostre Condizioni generali di uso e vendita. 
          L'acquisto sarà completato solo con la conferma di spedizione.
          Prendi visione della nostra Informativa sulla privacy, della nostra Informativa
          sui Cookie e della nostra Informativa sulla Pubblicità definita in base agli interessi.
        </small>
      </td>
    </tr>
    <tr class="tr2-ti">
      <td class="td1-ti" colspan="3"><!--- td4-1-->
        <div class="forbutton">
           <button class="btn-acquisto">Aquista ora</button>
        </div>
      </td>
    </tr>
    <tr class="tr3-ti">
      <td class="td1-ti" colspan="2">
        <div class="info">
          <p><small>articoli:</small></p>
          <p><small>costo spedizione:</small> </p>
        </div>
      </td>
      <td class="td3-ti">
        <div class="info">
          <p><small id="articoli2"><%= cart.getTotal() %></small></p>
          <p><small id="costo-spedizione2">-</small></p>
        </div>
      </td>
    </tr>
    <tr class="tr4-ti">
      <td class="td1-ti" colspan="2">
        <p><b>Totale ordine:</b></p>
      </td>
      <td class="td3-ti">
        <div class="info">
          <p><b id="totale2">-</b></p>
        </div>
      </td>
    </tr>
    </table>
  </td>
  <td class="td3"></td>
  <td class="td4"></td>
</tr>
</tbody>
</table>
</form>
<%@include file="./head_foot/footer.jsp"%>
</body>

<script type="text/javascript">
	const spedizione = document.querySelectorAll('input[name="spedizione"]');
	const small = document.getElementById('costo-spedizione');
	const small2 = document.getElementById('costo-spedizione2');
	const b = document.getElementById('totale');
	const b2 = document.getElementById('totale2');
	const articoli = document.getElementById('articoli');	

	for(const radio of spedizione){
		radio.addEventListener('change', e =>{
	        e.preventDefault();
	        check(radio);
		});
	}
	
	function check(radio){
		const costo = document.getElementById(radio.value);
		small.innerText = costo.value;
		small2.innerText = costo.value;
		var totale = parseFloat(costo.value) + parseFloat(articoli.textContent);
		console.log(totale);
		b.innerText = totale;
		b2.innerText = totale;
	}
</script>
</html>