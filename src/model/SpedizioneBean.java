package model;

import java.io.Serializable;

public class SpedizioneBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private String tipo;
	private double costo;
	private String tempo;
	private String corriere;
	public SpedizioneBean(int codice, String tipo, double costo, String tempo, String corriere) {
		super();
		this.codice = codice;
		this.tipo = tipo;
		this.costo = costo;
		this.tempo = tempo;
		this.corriere = corriere;
	}
	public SpedizioneBean() {
		super();
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getCorriere() {
		return corriere;
	}
	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}
	@Override
	public String toString() {
		return "SpedizioneBean [codice=" + codice + ", tipo=" + tipo + ", costo=" + costo + ", tempo=" + tempo
				+ ", corriere=" + corriere + "]";
	}
}
