package Clases;

public class Usuario {
	int codigoUsuario;
	String nomUsuario;
	String email;
	String contrasenya;
	
	public Usuario(int codigoUsuario, String nomUsuario, String email, String contrasenya) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nomUsuario = nomUsuario;
		this.email = email;
		this.contrasenya = contrasenya;
	}

	public Usuario(int codigoUsuario, String nomUsuario) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nomUsuario = nomUsuario;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public String toString() {
		return "Usuario [codigoUsuario=" + codigoUsuario + ", nomUsuario=" + nomUsuario + ", email=" + email
				+ ", contrasenya=" + contrasenya + "]";
	}
	
	
}
