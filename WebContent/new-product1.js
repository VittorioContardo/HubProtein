const categorie = document.getElementById('categoria');
const formato = document.getElementById('formati');
const pre_formato = document.getElementById('pre-formati');
const gusto = document.getElementById('gusto');

categorie.addEventListener('change' , function(){
	const cat = this.value;
	
	if(cat == 1){
		pre_formato.innerHTML = '';
		formato.innerHTML = '<select name="formato" id="formato"><option value="500gr">500gr</option><option value="1kg">1kg</option><option value="2kg">2kg</option></select>';
		gusto.innerHTML = '<input type="text" name="gusto_colore" id="gusto">'
	} else if(cat == 2){
		formato.innerHTML = '';
		pre_formato.innerHTML = '<select id="pre-formato"><option value="0">Seleziona opzione</option><option value="1">Sacchetto</option><option value="2">Capsule</option></select>';
		gusto.innerHTML = '<input type="hidden" name="gusto_colore" value="NOGusto">'
		const forVit = document.getElementById('pre-formato');
		forVit.addEventListener('change' , function(){
			const forVitValue = this.value;
			if(forVitValue == 1){
				formato.innerHTML = '<select name="formato" id="formato"><option value="500gr">500gr</option><option value="1kg">1kg</option><option value="2kg">2kg</option></select>';
			} else{
				formato.innerHTML = '<select name="formato" id="formato"><option value="90 capsule">90 capsule</option><option value="270 capsule">270 capsule</option></select>';
			}
		});
		
	} else if(cat == 3){
		formato.innerHTML = '';
		pre_formato.innerHTML = '<select id="pre-formato"><option value="0">Seleziona opzione</option><option value="1">Sacchetto</option><option value="2">Confezione</option><option value="3">Barattolo</option></select>';
		gusto.innerHTML = '<input type="text" name="gusto_colore" id="gusto">'
		const forAli = document.getElementById('pre-formato');
		forAli.addEventListener('change' , function(){
			const value = this.value;
			if(value == 1){
				formato.innerHTML = '<select name="formato" id="formato"><option value="500gr">500gr</option><option value="250gr">250gr</option></select>';
			} else if(value == 2){
				formato.innerHTML = '<select name="formato" id="formato"><option value="1x24gr">1x24gr</option><option value="16x24gr">16x24gr</option></select>';
			} else if (value == 3){
				formato.innerHTML = '<select name="formato" id="formato"><option value="500gr">500gr</option><option value="250gr">250gr</option><option value="150gr">150gr</option></select>';
			}
		});
	} else if(cat == 4){
		pre_formato.innerHTML = '';
		formato.innerHTML = '<select name="formato" id="formato"><option value="S">S</option><option value="M">M</option><option value="L">L</option><option value="XL">XL</option><option value="tutti">Tutti</option></select>';
		gusto.innerHTML = '<input type="text" name="gusto_colore" id="gusto">'
	} else if(cat == 5){
		pre_formato.innerHTML = '';
		formato.innerHTML = '<select name="formato" id="formato"><option value="S">S</option><option value="M">M</option><option value="L">L</option><option value="XL">XL</option><option value="tutti">Tutti</option><option value="noTaglie">NO Taglie</option></select>';
		gusto.innerHTML = '<input type="text" name="gusto_colore" id="gusto">'
	}
	
});