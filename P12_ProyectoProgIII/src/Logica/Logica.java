package Logica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import Clases.Comprador;
import Clases.Electronica;
import Clases.MaterialEscolar;
import Clases.Producto;
import Clases.Ropa;
import Clases.Usuario;

public class Logica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	public static List<Producto> productosHistoricos = new ArrayList<>();
	
	/*if( usuario instanceof Comprador) {
		Comprador c1 = (Comprador) usuario;
	}else {
		Administrador a1 = (Administrador) usuario;
	}*/
	

	
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

	public List<Producto> getProductosHistoricos() {
		return productosHistoricos;
	}

	public void setProductosHistoricos(List<Producto> productosHistoricos) {
		this.productosHistoricos = productosHistoricos;
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
			productosHistoricos=cCargado;
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("ERROR EN LA CARGA de fichero: " + nombreFic);
			System.out.println(e);
		}
	}
}
