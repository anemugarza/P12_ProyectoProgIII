package Clases;

public abstract class Usuario {
	int codigoUsuario;
	static int cont=0;
	String nomUsuario;
	String email;
	String contrasenya;
	
	public Usuario(int cod,String nomUsuario, String email, String contrasenya) {
		super();
		if(cod!=0) {
			this.codigoUsuario=cod;
		}else this.codigoUsuario = cont++;
		this.nomUsuario = nomUsuario;
		this.email = email;
		this.contrasenya = contrasenya;
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
