package Clases;

/**
 * Clase para la gestión de productos electronicos
 */
public class Electronica extends Producto {
	
	private static final long serialVersionUID = 1L;
	String descripcion;

	public Electronica(String nomP, double precio, String foto, String descripcion) {
		super(nomP, precio, foto);
		this.descripcion = descripcion;
	}
	
	public Electronica(String nomP, double precio, String foto) {
		super(nomP, precio, foto);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Electronica [descripcion=" + descripcion + "precio= " + precio +" €]";
	}
}
