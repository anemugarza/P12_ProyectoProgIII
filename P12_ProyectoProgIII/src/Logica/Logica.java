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
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import Clases.Compra;
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
	private static Usuario usuario;
	public static List<Producto> productosHistoricos = new ArrayList<>();
	private Logger log;

	
	public Logica(Usuario usuario) {
		super();
		Logica.usuario = usuario;
	}

	public Logica() {
		super();
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		Logica.usuario = usuario;
	}

	public List<Producto> getProductosHistoricos() {
		return productosHistoricos;
	}

	public void setProductosHistoricos(List<Producto> productosHistoricos) {
		Logica.productosHistoricos = productosHistoricos;
	}

	@Override
	public String toString() {
		return "Logica [usuario=" + usuario + "]";
	}
	
	public static boolean existeUsuario(String email) {
		if(BaseDeDatos.getUsuarios().containsKey(email)) return true;
		else return false;
	}
	
	public static Usuario usuarioCorrecto(String email, String contrasenya) {
		if(BaseDeDatos.getUsuarios().get(email).getContrasenya().equals(contrasenya)){
			Logica.usuario=BaseDeDatos.getUsuarios().get(email);
			return BaseDeDatos.getUsuarios().get(email);
		}else return null;
	}
	
	public static boolean UsuarioComprador(String email) {
		if(BaseDeDatos.getUsuarios().get(email) instanceof Comprador ) return true;
		else return false;

	}

	
	public static void crearUsuario(String nombre, String email, String contrasenya) {
		Comprador c1= new Comprador(nombre, email,contrasenya, 0); 
		BaseDeDatos.getUsuarios().put(c1.getEmail(),c1);
	}

	public static void guardarProductos(String nombreFic) {
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
	
	public static void cargarProductos(String nombreFic) {
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

	public static double getMenorPrecio(List<Producto> lProductos) {
		double menor = lProductos.get(0).getPrecio();
		for(int i=1;i<lProductos.size();i++) {
			if(lProductos.get(i).getPrecio()<menor) {
				menor = lProductos.get(i).getPrecio();
			}
		}
		return menor;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void quePuedoComprar(double saldo, ArrayList<Producto> lProductos, ArrayList<ArrayList<Producto>> lPuedoComprar, ArrayList<Double> saldos){
		if(saldo==0 || saldo < Logica.getMenorPrecio(Logica.productosHistoricos)) {
			lPuedoComprar.add(lProductos);
			saldos.add(saldo);
			return;
		}
		for (Producto p : Logica.productosHistoricos) {
			if(saldo-p.getPrecio()>0) {
				lProductos.add(p);
				ArrayList<Producto> ps = new ArrayList<>(lProductos);
				quePuedoComprar(saldo - p.getPrecio(), ps, lPuedoComprar, saldos);
				lProductos.remove(p);
			}
		}
	}
	
	public static Producto productoMasVendido(long fecha1, long fecha2) {
		HashMap<Integer,Compra> comprasEntreFechas = BaseDeDatos.getCompras(1, fecha1, fecha2);
		HashMap<Producto, Integer> contador = new HashMap<Producto, Integer>();
		for(Integer i :  comprasEntreFechas.keySet()) {
			recursivaContador(comprasEntreFechas.get(i).getProductosComprados(), contador);
		}
		Producto max = null;
		int maxI=0;
		for(Producto p : contador.keySet()) if(contador.get(p)>maxI) max=p;
		return max;
		
	}
	public static void recursivaContador(ArrayList<Producto> prs, HashMap<Producto, Integer> contador) {
		if(prs.size()>0) {
			for(Producto p: prs) {
				if(!contador.containsKey(p)) contador.put(p, 1);
				else contador.put(p, contador.get(p)+1);
				prs.remove(p);
				recursivaContador(prs, contador);
				break;
			}
		}
	}
}
