package Clases;

public class Ropa extends Producto{
	String descripcion;
	Talla talla;
	
	public Ropa(String nomP, double precio, String descripcion, Talla talla) {
		super(nomP, precio);
		this.descripcion = descripcion;
		this.talla = talla;
	}

	public Ropa(String nomP, double precio) {
		super(nomP, precio);
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
		return "Ropa [descripcion=" + descripcion + ", talla=" + talla + "]";
	}
	
	
}
