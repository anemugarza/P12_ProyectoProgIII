package Clases;
/**
 * Clase para la gestión de productos que pertenecen al apartado de ropa
 */
public class Ropa extends Producto{
	
	private static final long serialVersionUID = 1L;
	String descripcion;
	Talla talla;
	/**
	 * 
	 * @param descripcion breve descripción de la prenda
	 * @param talla la talla de la prenda
	 */
	
	public Ropa(String nomP, double precio, String descripcion, Talla talla,String foto) {
		super(nomP, precio, foto);
		this.descripcion = descripcion;
		this.talla = talla;
	}

	public Ropa(String nomP, double precio, String foto) {
		super(nomP, precio,foto);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Ropa [descripcion=" + descripcion + ", talla=" + talla + "precio= " + precio +" € ]";
	}
	
	
}
