<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
	</head>
	<%@include file="./head_foot/header.jsp" %>
	<%
		if(user != null){
			response.sendRedirect(request.getContextPath() + "/account.jsp");
			return;
		}
		Boolean isLogged = (Boolean) session.getAttribute("isLogged");
	%>
		<!-- ACCOUNT-->
        <div class="account-page">
            <div class="container">
                <div class="row">
                    <div class="col-2">
                        <div class="form-container">
                        	<%
                        		if(isLogged != null && !isLogged){%>
                        			<h3 class="error">Email o password errati!!</h3>
                        	<%	}%>
                        	<div class="header-form">
                        		<h2>Login</h2>
                        	</div>
                            <form action="login" method="post" class="form" id="form">
                                <div class="input-group">
									<label for="email">Email</label>
									<input type="text" placeholder="email" name="email" id="email"/>
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
                                <button> Accedi </button>
                                oppure
                                <a href="register.jsp">Registrati</a>
                            </form> <!-- FINE FORM DI LOGIN-->
                        </div> <!-- FINE CONTAINER FORM-->
                    </div> <!-- FINE COLONNA-->
                </div> <!-- FINE RIGA-->
            </div> <!-- FINE CONTAINER-->
        </div> <!-- FINE ACCOUNT-->
        <script type="text/javascript">
	        function setErrorFor(input, message){
	            const formControl = input.parentElement;
	            const small = formControl.querySelector('small');
	            formControl.className = 'input-group error';
	            small.innerText = message;
		    }
		
		    function setSuccesFor(input){
		        const formControl = input.parentElement;
		        formControl.className = 'input-group success';
		    }
	        function checkEmail(inputtxt) {
	        	console.log(inputtxt);
	            var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	            if(inputtxt.match(email)) 
	                return true;
	            
	            return false;	
	        }
	        
	        
	        const email = document.getElementById('email');
	        const password = document.getElementById('password');
	        
	        form.addEventListener('submit', e =>{
	            e.preventDefault();
	            checkInput();
	    	})
	    	
	    	function checkInput(){
			    const emailValue = email.value.trim();
			    const passwordValue = password.value.trim();
				var valid = true;
				
			    if(emailValue === ''){
			        setErrorFor(email, 'Email non può essere vuota');
			        valid = false;
			    } else if(!checkEmail(emailValue)){
			        setErrorFor(email, 'Email non valida');
			        valid = false;
			    } else{
			        setSuccesFor(email);
			    }			
			   			
			    if(passwordValue === ''){
			        setErrorFor(password, 'Password non può essere vuota');
			        valid = false;
			    } else{
			        setSuccesFor(password);
			    }
			    
			    if(valid){
					form.submit();
				}  
			}
        </script>
        <script type="text/javascript" src=""></script>
	<%@include file="./head_foot/footer.jsp" %>
</body>
</html>