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

function checkClass(input){
	const formControl = input.parentElement;
	console.log(formControl.className);
	return !(formControl.className.includes("error"));
}

function checkPhonenumber(inputtxt) {
	//console.log(inputtxt);
    var re = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
    
    return re.test(inputtxt);
}

function checkEmail(inputtxt) {
	//console.log(inputtxt);
    var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    
    return email.test(inputtxt);
}
const form = document.getElementById('form');
const email = document.getElementById('email');
const nome = document.getElementById('nome');
const cognome = document.getElementById('cognome');
const telefono = document.getElementById('telefono');
const cf = document.getElementById('cf');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');


form.addEventListener('submit', e =>{
        e.preventDefault();
        checkInput();
});

function checkInput(){
    const emailValue = email.value.trim();
    const nomeValue = nome.value.trim();
    const cognomeValue = cognome.value.trim();
    const telefonoValue = telefono.value.trim();
    const cfValue = cf.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();
	var valid = true;
	var passOk = true;
	
    if(emailValue === ''){
        setErrorFor(email, 'Email non può essere vuota');
        valid = false;
    } else if(!checkClass(email)){
		valid = false;
	} else if(!checkEmail(emailValue)){
        setErrorFor(email, 'Email non valida');
        valid = false;
    } else{
		setSuccesFor(email);
	}

    if(nomeValue === ''){
        setErrorFor(nome, 'Nome non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(nome);
    }

    if(cognomeValue === ''){
        setErrorFor(cognome, 'Cognome non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(cognome);
    }

    if(telefonoValue === ''){
        setErrorFor(telefono, 'Telefono non può essere vuoto');
        valid = false;
    } else if(!checkPhonenumber(telefonoValue)){
        setErrorFor(telefono, 'Telefono non valido');
        valid = false;
    } else{
        setSuccesFor(telefono);
    }

    if(cfValue === ''){
        setErrorFor(cf, 'CF non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(cf);
    }

    if(passwordValue === ''){
        setErrorFor(password, 'Password non può essere vuota');
        valid = false;
    } else{
        setSuccesFor(password);
    }

    if(password2Value === ''){
        setErrorFor(password2, 'Password non può essere vuota');
        valid = false;
    } else if(password2Value !== passwordValue){
        setErrorFor(password2, 'Le password non corrispondono');
        valid = false;
        passOk = false;
    }
    else{
        setSuccesFor(password2);
    }
    if(valid && passOk){
		form.submit();
	}  
}