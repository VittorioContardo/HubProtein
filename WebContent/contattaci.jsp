<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%@include file="./head_foot/header.jsp" %>
  <div class="container-contattaci">
    <div class="content">
      <div class="left-side">
        <div class="address details">
          <i class="fas fa-map-marker-alt"></i>
          <div class="indirizzo">Indirizzo</div>
          <div class="testo">Fisciano</div>
        </div>
        <div class="phone details">
          <i class="fas fa-phone-alt"></i>
          <div class="topic">Telefono</div>
          <div class="testo">+39 1232341224</div>
          <div class="testo">+39 1223214323</div>
        </div>
        <div class="email details">
          <i class="fas fa-envelope"></i>
          <div class="indirizzo">Email</div>
          <div class="testo">hubprotein@gmail.com</div>
          <div class="testo">info.hubprotein@gmail.com</div>
        </div>
      </div>
      <div class="right-side">
        <div class="messaggio">Inviaci un messaggio</div>
        <p>Se hai bisogno di qualche informazione su un prodotto o servizio,contanttaci al più presto.</p>
      <form action="richiestainviata.jsp " method="get">
        <div class="input-box">
          <input type="text" placeholder="Inserisci nome">
        </div>
        <div class="input-box">
          <input type="text" placeholder="Inserisci email">
        </div>
        <div class="input-box message-box">
          <textarea placeholder="Inserisci messaggio"></textarea>
        </div>
        <div class="button">
          <input type="submit" value="Invia adesso" >
        </div>
      </form>
    </div>
    </div>
  </div>
   <%@include file="./head_foot/footer.jsp" %>
</body>
</html>