package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
	private int idCompra;
	private Usuario user;
	private ArrayList<Producto> productosComprados;
	private long fecha;
	private double precio;
	
	public Compra(int idCompra, Usuario user, ArrayList<Producto> productosComprados, long fecha, double precio) {
		super();
		this.idCompra = idCompra;
		this.user = user;
		this.productosComprados = productosComprados;
		this.fecha = fecha;
		this.precio = precio;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public ArrayList<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(ArrayList<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", user=" + user + ", productosComprados=" + productosComprados
				+ ", fecha=" + fecha + ", precio=" + precio + "]";
	}
}
