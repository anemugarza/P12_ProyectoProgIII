package Clases;

import java.util.ArrayList;

import java.util.List;
/**
 * 
 * Clase para la gestión de usuarios compradores
 *
 */

public class Comprador extends Usuario {
	double monedero;
	public List<Producto> cesta = new ArrayList<>();
	public List<Producto> wl = new ArrayList<>();
	
	/**
	 * Constructor clase comprador
	 * @param nomUsuario nombre del usuario comprador
	 * @param email direccion de correo del usuario comprador
	 * @param contrasenya  codigo con el que el usuario accede a su cuenta
	 * @param monedero dinero del que dispone para hacer sus compras
	 * @param cesta lista de productos que el usuario ha añadido a su cesta personal
	 * @param wl lista de productos que el usuario ha añadido a su wishlist(productos que querría comprar algún dia)
	 * 
	 */
	
	public Comprador(String nomUsuario, String email, String contrasenya, double monedero,
			List<Producto> cesta, List<Producto> wl, int admin) {
		super(nomUsuario, email, contrasenya, admin);
		this.monedero = monedero;
		this.cesta = cesta;
		this.wl = wl;
	}

	public Comprador(String nomUsuario, String email, String contrasenya, int admin) {
		super(nomUsuario, email, contrasenya, admin);
	}

	public double getMonedero() {
		return monedero;
	}

	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}

	public List<Producto> getCesta() {
		return cesta;
	}

	public void setCesta(List<Producto> cesta) {
		this.cesta = cesta;
	}

	public List<Producto> getWl() {
		return wl;
	}

	public void setWl(List<Producto> wl) {
		this.wl = wl;
	}

	@Override
	public String toString() {
		return "Comprador [monedero=" + monedero + ", cesta=" + cesta + ", wl=" + wl + "]";
	}
	
	//para añadir un producto a la wish list
	public void anyadirWL(Producto p) {
		wl.add(p);
	}
	//para añadir un producto a la wish list
	public void anyadirCesta(Producto p) {
		cesta.add(p);
	}
}
