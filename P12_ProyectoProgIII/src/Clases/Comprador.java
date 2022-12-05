package Clases;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario {
	double monedero;
	public List<Producto> cesta = new ArrayList<>();
	public List<Producto> wl = new ArrayList<>();
	
	public Comprador(int id, String nomUsuario, String email, String contrasenya, double monedero,
			List<Producto> cesta, List<Producto> wl, int admin) {
		super(id, nomUsuario, email, contrasenya, admin);
		this.monedero = monedero;
		this.cesta = cesta;
		this.wl = wl;
	}

	public Comprador(int id, String nomUsuario, String email, String contrasenya, int admin) {
		super(id, nomUsuario, email, contrasenya, admin);
	}

	public Comprador( String nomUsuario, String email, String contrasenya, int admin) {
		super( nomUsuario, email, contrasenya, admin);
		
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
	
	public void anyadirWL(Producto p) {
		wl.add(p);
	}
	
	public void anyadirCesta(Producto p) {
		cesta.add(p);
	}
}
