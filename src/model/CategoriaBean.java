package model;

import java.io.Serializable;

public class CategoriaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codice;
	private String nome;
	private String descrizione;
	public CategoriaBean(int codice, String nome, String descrizione) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	public CategoriaBean() {
		super();
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
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
	@Override
	public String toString() {
		return "CategoriaBean [codice=" + codice + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}
}
