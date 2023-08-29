package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class OrdineBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private Date data_ordine;
	private String stato;
	private int spedizione;
	private String utente;
	private int indirizzo;
	private List<ProductCart> product;
	public OrdineBean(int id, Date data_ordine, String stato, int spedizione, String utente, int indirizzo,
			List<ProductCart> product) {
		super();
		this.id = id;
		this.data_ordine = data_ordine;
		this.stato = stato;
		this.spedizione = spedizione;
		this.utente = utente;
		this.indirizzo = indirizzo;
		this.product = product;
	}
	public OrdineBean(int id, Date data_ordine, String stato, int spedizione, String utente, int indirizzo) {
		super();
		this.id = id;
		this.data_ordine = data_ordine;
		this.stato = stato;
		this.spedizione = spedizione;
		this.utente = utente;
		this.indirizzo = indirizzo;
	}
	public OrdineBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_ordine() {
		return data_ordine;
	}
	public void setData_ordine(Date data_ordine) {
		this.data_ordine = data_ordine;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getSpedizione() {
		return spedizione;
	}
	public void setSpedizione(int spedizione) {
		this.spedizione = spedizione;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public List<ProductCart> getProduct() {
		return product;
	}
	public void setProduct(List<ProductCart> product) {
		this.product = product;
	}
	public int getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(int indirizzo) {
		this.indirizzo = indirizzo;
	}
	@Override
	public String toString() {
		return "OrdineBean [id=" + id + ", data_ordine=" + data_ordine + ", stato=" + stato + ", spedizione="
				+ spedizione + ", utente=" + utente + ", indirizzo=" + indirizzo + ", product=" + product + "]";
	}
}
