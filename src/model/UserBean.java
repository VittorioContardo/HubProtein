package model;

public class UserBean {
	private String email;
	private String password;
	private String cf;
	private String nome;
	private String cognome;
	private String telefono;
	private boolean isAdmin;
	
	public UserBean(String email, String password, String cf, String nome, String cognome, String telefono, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.isAdmin = isAdmin;
	}
	public UserBean() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "UserBean [email=" + email + ", password=" + password + ", cf=" + cf + ", nome=" + nome + ", cognome="
				+ cognome + ", telefono=" + telefono + ", isAdmin=" + isAdmin + "]";
	}
}
