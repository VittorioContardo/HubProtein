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

function isNumber(inputtxt){
	if(isNaN(inputtxt)){
		return false;
	}
	return typeof inputtxt === 'number';
}

const form = document.getElementById('form');
const categoria = document.getElementById('categoria');
const codice = document.getElementById('codice');
const nome = document.getElementById('nome');
const descrizione = document.getElementById('descrizione');
const quantita = document.getElementById('quantita');
const prezzo = document.getElementById('prezzo');
const iva = document.getElementById('iva');
const immagine = document.getElementById('immagine');

form.addEventListener('submit', e =>{
	e.preventDefault();
	checkInput();
})

function checkInput(){
	const categoriaValue = categoria.value;
	const codiceValue = codice.value.trim();
	const nomeValue = nome.value.trim();
	const descrizioneValue = descrizione.value.trim();
	const quantitaValue = quantita.value.trim();
	const prezzoValue = prezzo.value.trim();
	const ivaValue = iva.value.trim();
	const immagineValue = immagine.value;
	
	var valid = true;
	
	if(categoriaValue == 0){
		setErrorFor(categoria, 'Scegliere opzione');
		valid = false;
	} else {
		/*if(categoriaValue == 2 || categoriaValue == 3){
			const pre_formato = document.getElementById('pre-formato');
			const pre_formatoValue = pre_formato.value;
			console.log(pre_formatoValue);
			
			if(pre_formatoValue == 0){
				setErrorFor(formato, 'Scegliere formato');
				valid = false;
			} else {
				const formato = document.getElementById('formato');
				const gusto = document.getElementById('gusto');
				const formatoValue = formato.value;
				const gustoValue = gusto.value;
				if(formatoValue == 0){
					setErrorFor(formato, 'Scegliere 7878 formato');
					valid = false;
				} else {
					setSuccesFor(formato);
				}
				
				if(gustoValue === ''){
					setErrorFor(formato, 'Scegliere formato');
					valid = false;
				} else{
					setSuccesFor(gusto);
				}
			}
		}*/
		setSuccesFor(categoria);
	}
	
	console.log(parseInt(codiceValue));
	if(codiceValue === ''){
		setErrorFor(codice, 'Codice non può essere vuoto');
		valid = false;
	} else if(!isNumber(parseInt(codiceValue))){
		setErrorFor(codice, 'Codice è un numero');
		valid = false;
	} else {
		setSuccesFor(codice);
	}
	
	if(nomeValue === ''){
		setErrorFor(nome, 'Nome non può essere vuoto');
		valid = false;
	} else {
		setSuccesFor(nome);
	}
	
	if(descrizioneValue === ''){
		setErrorFor(descrizione, 'Descrizione non può essere vuota');
		valid = false;
	} else {
		setSuccesFor(descrizione);
	}
	
	if(quantitaValue === ''){
		setErrorFor(quantita, 'Quantità non può essere vuota');
		valid = false;
	} else {
		setSuccesFor(quantita);
	}
	
	if(prezzoValue === ''){
		setErrorFor(prezzo, 'Prezzo non può essere vuoto');
		valid = false;
	} else if(!isNumber(parseFloat(prezzoValue))){
		setErrorFor(prezzo, 'Prezzo è un numero');
		valid = false;
	} else {
		setSuccesFor(prezzo);
	}
	
	if(ivaValue === ''){
		setErrorFor(iva, 'IVA non può essere vuoto');
		valid = false;
	} else if(!isNumber(parseFloat(ivaValue))){
		setErrorFor(iva, 'IVA è un numero');
		valid = false;
	} else {
		setSuccesFor(iva);
	}
	
	if(immagineValue === ''){
		setErrorFor(immagine, 'Scegli immagine');
		valid = false;
	} else {
		setSuccesFor(immagine);
	}
	
	if(valid == true){
		console.log('ok');
		form.submit();
	}
}
