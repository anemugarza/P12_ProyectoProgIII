package Clases;

import java.util.HashMap;
import java.util.Map;
/**
 * Clase para la gestión de usuarios administradores
 */
public class Administrador extends Usuario {
	int salario;
	

	/**
	 * 
	 * @param nomUsuario mombre del usuario administrador
	 * @param email direccion de correo del usuario administrador
	 * @param contrasenya codigo con el que el usuario accede a su cuenta
	 * @param salario dinero que gana al mes como administrador
	 */
	public Administrador(String nomUsuario, String email, String contrasenya, int salario) {
		super(nomUsuario, email, contrasenya, 1);
		this.salario = salario;
	}

	public Administrador(String nomUsuario, String email, String contrasenya) {
		super(nomUsuario, email, contrasenya, 1);
	}

	
	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}


	@Override
	public String toString() {
		return "Administrador [salario=" + salario + "]";
	}
	
	
}
