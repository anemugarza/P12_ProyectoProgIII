package Clases;

public class Electronica extends Producto {
	String descripcion;



	public Electronica(String nomP, double precio, String foto, String descripcion) {
		super(nomP, precio, foto);
		this.descripcion = descripcion;
	}




	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
