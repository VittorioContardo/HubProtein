package model;

import java.io.Serializable;
import java.util.Objects;

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private String formato;
	private String gusto_colore;
	private String nome;
	private String descrizione;
	private int quantit‡Magazzino;
	private double prezzo;
	private double iva;
	private boolean vegan;
	private int categoria;
	private String immagine;
	public ProductBean(int codice, String formato, String gusto_colore, String nome, String descrizione,
			int quantit‡Magazzino, double prezzo, double iva, boolean vegan, int categoria, String immagine) {
		super();
		this.codice = codice;
		this.formato = formato;
		this.gusto_colore = gusto_colore;
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantit‡Magazzino = quantit‡Magazzino;
		this.prezzo = prezzo;
		this.iva = iva;
		this.vegan = vegan;
		this.categoria = categoria;
		this.immagine = immagine;
	}
	public ProductBean() {
		this.codice = -1;
		this.formato = "";
		this.gusto_colore = "";
		this.nome = "";
		this.descrizione = "";
		this.quantit‡Magazzino = -1;
		this.prezzo = -1;
		this.iva = -1;
		this.vegan = false;
		this.categoria = 0;
		this.immagine = "";
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getGusto_colore() {
		return gusto_colore;
	}
	public void setGusto_colore(String gusto_colore) {
		this.gusto_colore = gusto_colore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getQuantitaMagazzino() {
		return quantit‡Magazzino;
	}
	public void setQuantitaMagazzino(int quantit‡Magazzino) {
		this.quantit‡Magazzino = quantit‡Magazzino;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public boolean isVegan() {
		return vegan;
	}
	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	@Override
	public String toString() {
		return "ProductBean [codice=" + codice + ", formato=" + formato + ", gusto_colore=" + gusto_colore + ", nome="
				+ nome + ", descrizione=" + descrizione + ", quantit‡Magazzino=" + quantit‡Magazzino + ", prezzo="
				+ prezzo + ", iva=" + iva + ", vegan=" + vegan + ", categoria=" + categoria + ", immagine=" + immagine
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBean other = (ProductBean) obj;
		return codice == other.codice && Objects.equals(formato, other.formato) && Objects.equals(gusto_colore, other.gusto_colore);
	}
	
}
