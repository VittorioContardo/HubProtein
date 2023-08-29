<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@include file="./head_foot/header.jsp" %>
	<%
		if(user != null){
			response.sendRedirect(request.getContextPath() + "/account.jsp");
			return;
		}
	%>
		<!-- ACCOUNT-->
        <div class="account-page">
            <div class="container">
                <div class="row">
                    <div class="col-2">
                        <div class="form-container">
								<div class="header-form">
									<h2>Registrazione</h2>
								</div>
								<form action="registrazione" class="form" id="form" method="post">
									<div class="input-group">
										<label for="email">Email</label>
										<input type="text" placeholder="email" name="email" id="email"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="nome">Nome</label>
										<input type="text" placeholder="nome" name="nome" id="nome"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="cognome">Cognome</label>
										<input type="text" placeholder="cognome" name="cognome" id="cognome"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="telefono">Telefono</label>
										<input type="text" placeholder="123456789" name="telefono" id="telefono"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="cf">CF</label>
										<input type="text" placeholder="codice fiscale" name="cf" id="cf"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="password">Password</label>
										<input type="password" placeholder="password" name="password" id="password"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
							
									<div class="input-group">
										<label for="password2">Ripeti password</label>
										<input type="password" placeholder="ripeti password" name="password2" id="password2"/>
										<i class="fas fa-check-circle"></i>
										<i class="fas fa-exclamation-circle"></i>
										<small>Error message</small>
									</div>
									<small>* campi obbligatori</small>
								<button>Registrati</button>
							</form>
                        </div> <!-- FINE CONTAINER FORM-->
                    </div> <!-- FINE COLONNA-->
                </div> <!-- FINE RIGA-->
            </div> <!-- FINE CONTAINER-->
        </div> <!-- FINE ACCOUNT-->
	<%@include file="./head_foot/footer.jsp" %>
        <script type="text/javascript" src="validate-register.js"></script>
        <script>
        	$("#email").keyup(function(event){
        		const value = event.target.value;
        		console.log(value);
        		
        		$.get('checkRegisteredUser', {"emailValue" : value},
        			function(resp){
        				if(resp.registered){
        					const div = $("#email").parent();
        					const small = div.children('small');
        					//console.log(small);
        					div.addClass('error');
        					small.text('Utente già registrato');
        				} else {
        					const div = $("#email").parent();
        					const small = div.children('small');
        					//console.log(small);
        					div.removeClass('error');
        				}
        			}
        		).fail(function(){
        			console.log('errore');
        		});
        	});
        </script>
</body>
</html>