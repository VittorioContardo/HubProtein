package model;

import java.io.Serializable;

public class IndirizzoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private String via;
	private String citta;
	private String stato_provincia;
	private String cap;
	private String paese;
	private String utente_mail;
	public IndirizzoBean(int codice, String via, String citta, String stato_provincia, String cap, String paese,
			String utente_mail) {
		super();
		this.codice = codice;
		this.via = via;
		this.citta = citta;
		this.stato_provincia = stato_provincia;
		this.cap = cap;
		this.paese = paese;
		this.utente_mail = utente_mail;
	}
	public IndirizzoBean() {
		super();
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getStato_provincia() {
		return stato_provincia;
	}
	public void setStato_provincia(String stato_provincia) {
		this.stato_provincia = stato_provincia;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public String getUtente_mail() {
		return utente_mail;
	}
	public void setUtente_mail(String utente_mail) {
		this.utente_mail = utente_mail;
	}
	@Override
	public String toString() {
		return "IndirizzoBean [codice=" + codice + ", via=" + via + ", citta=" + citta + ", stato_provincia="
				+ stato_provincia + ", cap=" + cap + ", paese=" + paese + ", utente_mail=" + utente_mail + "]";
	}
}
