package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Clases.Comprador;
import Clases.Producto;
import Clases.Usuario;

public class Logica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	public List<Producto> productosHistoricos = new ArrayList<>();

	
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

	public static void setUsuariosRegistrados(HashMap<String, Usuario> usuariosRegistrados) {
		usuariosRegistrados = usuariosRegistrados;
	}

	@Override
	public String toString() {
		return "Logica [usuario=" + usuario + "]";
	}
	
	public boolean existeUsuario(String email) {
		if(BaseDeDatos.getUsuarios().containsKey(email)) return true;
		else return false;
	}
	
	public Usuario usuarioCorrecto(String email, String contrasenya) {
		if(BaseDeDatos.getUsuarios().get(email).getContrasenya().equals(contrasenya)){
			this.usuario=BaseDeDatos.getUsuarios().get(email);
			return BaseDeDatos.getUsuarios().get(email);
		}else return null;
	}
	
	public void crearUsuario(String nombre, String email, String contrasenya) {
		Comprador c1= new Comprador(0, nombre, email,contrasenya ); 
		BaseDeDatos.getUsuarios().put(c1.getEmail(),c1);
	}

	public void guardarProductos(String nombreFic) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFic));
			oos.writeObject(productosHistoricos);
			System.out.println("guardar");
			oos.close();
		}catch(IOException e){
			System.out.println("ERROR EN ESCRITURA de fichero: " + nombreFic);
			System.out.println(e);
		}
	}
	
	public void cargarProductos(String nombreFic) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFic));
			@SuppressWarnings("unchecked")
			List<Producto> cCargado = (ArrayList<Producto>) ois.readObject();
			System.out.println("cargado");
			productosHistoricos=cCargado;
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("ERROR EN LA CARGA de fichero: " + nombreFic);
			System.out.println(e);
		}
	}
	
}
