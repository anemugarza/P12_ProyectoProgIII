package Clases;
/**
 * Clase para la gestión de usuarios de la tienda.
 *
 */
public abstract class Usuario {
	int codigoUsuario =0;
	String nomUsuario;
	String email;
	String contrasenya;
	int admin;
	/**
	 * 
	 * @param nomUsuario nombre del usuario
	 * @param email direccion de correo del usuario
	 * @param contrasenya codigo con el que el usuario accede a su cuenta
	 * @param admin parametro con el que indicamos de que tipo de usuario se trata, de comprador(0) o administrador(1).
	 */
	public Usuario(String nomUsuario, String email, String contrasenya, int admin) {
		super();
		this.nomUsuario = nomUsuario;
		this.email = email;
		this.contrasenya = contrasenya;
		this.admin = admin;
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
