package Clases;

import java.io.Serializable;
/**
 * Clase para la gestión de los productos en tienda
 */
public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int codigoP;
	static int cont=1;
	String nomP;
	double precio;
	private String foto;
	
	/**
	 * 
	 * @param nomP nombre del producto
	 * @param precio el precio al que se vende
	 * @param foto una imagen del producto
	 */
	public Producto(String nomP, double precio, String foto) {
		super();
		this.codigoP = cont++;
		this.nomP = nomP;
		this.precio = precio;
		this.foto= foto;
	}

	public Producto() {
		super();
	}

	public int getCodigoP() {
		return codigoP;
	}
	

	public void setCodigoP(int codigoP) {
		this.codigoP = codigoP;
	}

	public static int getCont() {
		return cont;
	}

	public static void setCont(int cont) {
		Producto.cont = cont;
	}

	public String getNomP() {
		return nomP;
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Producto [codigoP=" + codigoP + ", nomP=" + nomP + ", precio=" + precio + "]";
	}
}
