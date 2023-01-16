package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import Clases.Compra;
import Clases.Comprador;
import Clases.Producto;
import Clases.Usuario;

public class Logica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Usuario usuario;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static List<Producto> productosHistoricos = new ArrayList<>();
	private static Logger logger = Logger.getLogger( "Logica" );

	
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
	/**
	 * comprueba que un usuario existe en la base de datps
	 * @param email la direccion de correo que buscaremos en la base de datos
	 * @return true si la direccion ya existia, false en caso contrario
	 */
	
	public static boolean existeUsuario(String email) {
		if(BaseDeDatos.getUsuarios().containsKey(email)) return true;
		else return false;
	}
	
	/**
	 * para comprobar que la direccion de correo y la contraseña coinciden
	 * @param email la direccion de correo del usuario
	 * @param contrasenya la contraseña con la que se debería de permitir el acceso a dicha direccion de correo
	 * @return true si los dos parametros coinciden, false en caso contrario
	 */
	public static Usuario usuarioCorrecto(String email, String contrasenya) {
		if(BaseDeDatos.getUsuarios().get(email).getContrasenya().equals(contrasenya)){
			Logica.usuario=BaseDeDatos.getUsuarios().get(email);
			logger.log( Level.INFO, "Existe usuario en la BD");
			return BaseDeDatos.getUsuarios().get(email);
		}else return null;
	}
	/**
	 * comprueba si el usuario se trata de un comprador
	 * @param email la direccion de correo del usuario
	 * @return true si es un comprador, false en caso de ser administrador
	 */
	public static boolean UsuarioComprador(String email) {
		if(BaseDeDatos.getUsuarios().get(email) instanceof Comprador ) return true;
		else return false;

	}
	/**
	 * crea un nuevo usuario 
	 * @param nombre nombre del usuario
	 * @param email la direccion de correo del usuario
	 * @param contrasenya codigo con el que el usuario accede a su cuenta personal
	 */
	
	public static void crearUsuario(String nombre, String email, String contrasenya) {
		Comprador c1= new Comprador(nombre, email,contrasenya, 0); 
		BaseDeDatos.getUsuarios().put(c1.getEmail(),c1);
	}
	/**
	 * Guarda los productos en ficheros
	 * @param nombreFic nombre fichero en el que deseamos guardar los productos
	 */
	
	public static void guardarProductos(String nombreFic) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFic));
			oos.writeObject(productosHistoricos);
			logger.log( Level.INFO, "Productos guardados correctamente en: " + nombreFic );
			oos.close();
		}catch(IOException e){
			logger.log( Level.INFO, "ERROR EN ESCRITURA de fichero: " + nombreFic + e);
		}
	}
	/**
	 * Carga los productos desde el fichero
	 * @param nombreFic nombre del fichero del que deseamos cargar los productos
	 */
	
	public static void cargarProductos(String nombreFic) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFic));
			@SuppressWarnings("unchecked")
			List<Producto> cCargado = (ArrayList<Producto>) ois.readObject();
			productosHistoricos=cCargado;
			ois.close();
			logger.log( Level.INFO, "Productos cargados correctamente desde: " + nombreFic );
		} catch (IOException | ClassNotFoundException e) {
			logger.log( Level.INFO, "ERROR EN LA CARGA de fichero: " + nombreFic + e);
		}
	}
	/**
	 * obtiene el proucto con menor precio 
	 * @param lProductos la lista de productos en la que tendra que buscar el que menos valga
	 * @return el precio del producto de menor costo
	 */
	public static double getMenorPrecio(List<Producto> lProductos) {
		double menor = lProductos.get(0).getPrecio();
		for(int i=1;i<lProductos.size();i++) {
			if(lProductos.get(i).getPrecio()<menor) {
				menor = lProductos.get(i).getPrecio();
			}
		}
		return menor;
	}
	/**
	 * funcion recursiva en la que obtendremos una lista con productos que podremos comprar con un limite de dinero.
	 * @param saldo el limite de dinero que ponemos para las diferentes opciones de compra
	 * @param lProductos lista de todos los productos
	 * @param lPuedoComprar  lista en la que se guardaran los que se pueden comprar 
	 * @param saldos lista de diferentes saldos
	 */
	
	
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
	/**
	 * para obtener el producto más vendido entre dos fechas
	 * @param fecha1 la fecha que indica el principio del periodo de tiempo en el que buscamos el producto
	 * @param fecha2 la fecha que indica el final del periodo de tiempo en el que buscamos el producto
	 * @return el producto mas vendido entre las fechas indicadas.
	 */
	
	public static Producto productoMasVendido(long fecha1, long fecha2) {
		HashMap<Integer,Compra> comprasEntreFechas = BaseDeDatos.getCompras(1, fecha1, fecha2);
		if(comprasEntreFechas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay compras entre estas fechas");
			return null;
		}else {
		HashMap<Producto, Integer> contador = new HashMap<Producto, Integer>();
		for(Integer i :  comprasEntreFechas.keySet()) {
			recursivaContador(comprasEntreFechas.get(i).getProductosComprados(), contador);
		}
		Producto max = null;
		int maxI=0;
		for(Producto p : contador.keySet()) if(contador.get(p)>maxI) max=p;
		return max;
		}
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
	/**
	 * Obtiene el gasto medio de compras realizadas en un mes 
	 * @param anyo  el año del que queremos obtener dicho dato
	 * @param mes el mes  del que queremos obtener dicho dato
	 * @return la media de todos los gastos de nuestros clientes entre el numero de compras
	 */
	
	
	
	public static double gastoMedio(String anyo, String mes) {
		long fecha1 = parsear(anyo, mes, "01");
		long fecha2 = parsear(anyo, mes, "31");
		HashMap<Integer,Compra> comprasEntreFechas = BaseDeDatos.getCompras(1, fecha1, fecha2);
		if(comprasEntreFechas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay compras entre estas fechas");
			return 0;
		}else {
			double gastoTotal = 0.0;
			for(Compra c : comprasEntreFechas.values()) {
				gastoTotal += c.getPrecio();
			}
			double media = gastoTotal/comprasEntreFechas.size();
			return media;
		}
	}
	/**
	 * para parsear el año, mes y dia indicado  a un formato especifico de fecha
	 * @param anyo  
	 * @param mes 
	 * @param dia 
	 * @return
	 */
	public static long parsear(String anyo, String mes, String dia) {
		String fecha = dia + "/" + mes + "/" + anyo;
		try {
			return sdf.parse(fecha).getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * obtiene el numero de veces que se ha vendido un producto en un mes
	 * @param anyo  el año  del que queremos obtener dicho dato
	 * @param mes el mes  del que queremos obtener dicho dato
	 * @param prod el producto del cual queremos obtener los datos
	 * @return la cantidad de productos vendidos
	 */
	
	public static int cantProdEnUnMes(String anyo, String mes, String prod) {
		long fecha1 = parsear(anyo, mes, "01");
		long fecha2 = parsear(anyo, mes, "31");
		HashMap<Integer,Compra> comprasEntreFechas = BaseDeDatos.getCompras(1, fecha1, fecha2);
		if(comprasEntreFechas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay compras entre estas fechas");
			return 0;
		}else {
			int cant = 0;
			for(Compra c : comprasEntreFechas.values()) {
				for(Producto p : c.getProductosComprados()) {
					if(prod.equals(p.getNomP())) cant++;
				}
			}
			return cant;
		}
	}
}
