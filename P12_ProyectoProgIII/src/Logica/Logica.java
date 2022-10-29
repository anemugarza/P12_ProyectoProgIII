package Logica;

import java.io.Serializable;
import java.util.HashMap;

import Clases.Usuario;

public class Logica implements Serializable{
	private Usuario usuario;
	public static HashMap<String,Usuario> usuariosRegistrados = new HashMap<>();
	
	public Logica(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Logica() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static HashMap<String, Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}

	public static void setUsuariosRegistrados(HashMap<String, Usuario> usuariosRegistrados) {
		usuariosRegistrados = usuariosRegistrados;
	}

	@Override
	public String toString() {
		return "Logica [usuario=" + usuario + "]";
	}
	
	public boolean existeUsuario(String email) {
		if(Logica.usuariosRegistrados.containsKey(email)) return true;
		else return false;
	}
	
	public Usuario usuarioCorrecto(String email, String contrasenya) {
		if(Logica.usuariosRegistrados.get(email).getContrasenya().equals(contrasenya)){
			this.usuario=Logica.usuariosRegistrados.get(email);
			return Logica.usuariosRegistrados.get(email);
		}else return null;
	}

}
