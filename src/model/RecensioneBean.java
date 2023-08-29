package model;

import java.io.Serializable;

public class RecensioneBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codiceProdotto;
	private String email;
	private double valore;
	private String commento;
	public RecensioneBean(int codiceProdotto, String email, double valore, String commento) {
		super();
		this.codiceProdotto = codiceProdotto;
		this.email = email;
		this.valore = valore;
		this.commento = commento;
	}
	public RecensioneBean() {
		super();
	}
	public int getCodiceProdotto() {
		return codiceProdotto;
	}
	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	@Override
	public String toString() {
		return "RecensioneBean [codiceProdotto=" + codiceProdotto + ", email=" + email + ", valore=" + valore
				+ ", commento=" + commento + "]";
	}
}
