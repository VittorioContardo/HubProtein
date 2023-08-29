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

function checkCAP(inputtxt) {
	console.log(inputtxt);
    var re = /^(\d{5})+$/;
    
    return re.test(inputtxt);
}
const form = document.getElementById('form');
const via = document.getElementById('via');
const citta = document.getElementById('citta');
const stato = document.getElementById('stato_provincia');
const cap = document.getElementById('cap');
const paese = document.getElementById('paese');


form.addEventListener('submit', e =>{
        e.preventDefault();
        checkInput();
})

function checkInput(){
    const viaValue = via.value.trim();
    const cittaValue = citta.value.trim();
    const statoValue = stato.value.trim();
    const capValue = cap.value.trim();
    const paeseValue = paese.value.trim();
	var valid = true;
	console.log("formm");
    if(viaValue === ''){
        setErrorFor(via, 'Via non può essere vuota');
        valid = false;
    } else{
        setSuccesFor(via);
    }

    if(cittaValue === ''){
        setErrorFor(citta, 'Città non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(citta);
    }

    if(statoValue === ''){
        setErrorFor(stato, 'Stato/Provincia non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(stato);
    }

    if(capValue === ''){
        setErrorFor(cap, 'CAP non può essere vuoto');
        valid = false;
    } else if(!checkCAP(capValue)){
        setErrorFor(cap, 'CAP non valido');
        valid = false;
    } else{
        setSuccesFor(cap);
    }

    if(paeseValue === ''){
        setErrorFor(paese, 'Paese non può essere vuoto');
        valid = false;
    } else{
        setSuccesFor(paese);
    }
    if(valid){
		form.submit();
	}  
}