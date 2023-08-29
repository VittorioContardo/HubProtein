package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FatturaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idFattura;
	private double importo;
	private String metodo_pagamento;
	private Date data;
	private int idOrdine;
	private List<ProductCart> prodotti_fatturati = new ArrayList<>();
	public FatturaBean(int idFattura, double importo, String metodo_pagamento, Date data, int idOrdine,
			List<ProductCart> prodotti_fatturati) {
		super();
		this.idFattura = idFattura;
		this.importo = importo;
		this.metodo_pagamento = metodo_pagamento;
		this.data = data;
		this.idOrdine = idOrdine;
		this.prodotti_fatturati = prodotti_fatturati;
	}
	public FatturaBean(int idFattura, double importo, String metodo_pagamento, Date data, int idOrdine) {
		super();
		this.idFattura = idFattura;
		this.importo = importo;
		this.metodo_pagamento = metodo_pagamento;
		this.data = data;
		this.idOrdine = idOrdine;
	}
	public FatturaBean() {
		super();
	}
	public int getIdFattura() {
		return idFattura;
	}
	public void setIdFattura(int idFattura) {
		this.idFattura = idFattura;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}
	public void setMetodo_pagamento(String metodo_pagamento) {
		this.metodo_pagamento = metodo_pagamento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public List<ProductCart> getProdotti_fatturati() {
		return prodotti_fatturati;
	}
	public void setProdotti_fatturati(List<ProductCart> prodotti_fatturati) {
		this.prodotti_fatturati = prodotti_fatturati;
	}
	@Override
	public String toString() {
		return "FatturaBean [idFattura=" + idFattura + ", importo=" + importo + ", metodo_pagamento=" + metodo_pagamento
				+ ", data=" + data + ", idOrdine=" + idOrdine + ", prodotti_fatturati=" + prodotti_fatturati + "]";
	}
}
