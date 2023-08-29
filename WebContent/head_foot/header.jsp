
<%@page import="model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserBean user = (UserBean) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
       	<meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Hub Protein</title>
        <link rel="icon" type="image/x-icon" href="./logo_small.png">
        <link rel="stylesheet" href="./css/style.css" type="text/css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&family=Poppins:ital,wght@0,400;1,400;1,600&family=Roboto+Slab:wght@600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
   		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
   		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>

        <!-- BARRA DI NAVIGAZIONE -->
        <div class="header">
        	
	        <div class="navbar">
	            <div class="logo">
	            	<a href="index.jsp"><img src="./logo/logo_white_large.png"> </a>
		        </div>
		        <div class="search-field">
		        	<form action="byName" method="get">
			        	<input type="search" class="search-data" placeholder="Search" id="search-bar" name="param" autocomplete="off">
			            <button class="search-button" id="search-button"><i class="fa-solid fa-magnifying-glass" aria-hidden="true"></i></button>
		            </form>
		            <div class="result">
		            	<ul>
		            	</ul>
		            </div>
		         </div>
            	<nav>
		         <div class="nav-items">
			         <ul id="Menuitems">
			         	<li><a href="index.jsp">Home</a></li>
			            <li><a href="products.jsp">Prodotti</a></li>
			            <li><a href="contattaci.jsp">Contattaci</a></li>
			            <li><a href="carrello.jsp" class="carrello-account">Carrello</a></li>
			            <li><a href="account.jsp" class="carrello-account">Account</a></li>
			            <%
			            	if(user != null && user.isAdmin()){%>
			            		<li><a href="VisualizzaProdottiAdmin.jsp" class="carrello-account">Admin Panel</a></li>
			            <%	}
			            %>
			         </ul> 
		         </div>  
			     </nav>
			     <div class="utils">
			    	<a href="account.jsp"><i class="fa fa-user" aria-hidden="true"></i></a>
			    	<%if(user != null && user.isAdmin()== true){%>
			 		   <a href="VisualizzaProdottiAdmin.jsp">
			 		   	<span class="material-symbols-outlined">admin_panel_settings</span>  	
			 		   </a>
			    	<%} %>
			     	<a href="carrello.jsp"><i class="fa-solid fa-cart-shopping" id="cart"></i></a>
			     </div>
			     
			     <i class="fa-solid fa-bars" id="menu" onclick="menutoggle()"></i><!-- FUNZIONE PER FAR SCOMPARIRE E COMPARE IL SIMBOLO DEL MENU -->
	        </div>
	        
	    </div>
		        
    <!-- SCRIPT PER L'ITEM MENU -->
    <script>
        var Menuitems = document.getElementById("Menuitems");
        Menuitems.style.maxHeight = "0px";

        function menutoggle(){
            if(Menuitems.style.maxHeight == "0px")
            {
                Menuitems.style.maxHeight = "200px";
            }
            else{
                Menuitems.style.maxHeight = "0px";
             }
        }
    </script>
    <script src="searchbar_script.js"></script>
    <script src="searchbar_script.js"></script>