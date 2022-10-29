package Clases;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario {
	double monedero;
	public Cesta cesta;
	public List<Articulo> wl = new ArrayList<>();
	
	public Comprador(int codigoUsuario, String nomUsuario, String email, String contrasenya, double monedero,
			Cesta cesta, List<Articulo> wl) {
		super(codigoUsuario, nomUsuario, email, contrasenya);
		this.monedero = monedero;
		this.cesta = cesta;
		this.wl = wl;
	}

	public Comprador(int codigoUsuario, String nomUsuario, String email, String contrasenya) {
		super(codigoUsuario, nomUsuario, email, contrasenya);
	}

	public double getMonedero() {
		return monedero;
	}

	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}

	public Cesta getCesta() {
		return cesta;
	}

	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}

	public List<Articulo> getWl() {
		return wl;
	}

	public void setWl(List<Articulo> wl) {
		this.wl = wl;
	}

	@Override
	public String toString() {
		return "Comprador [monedero=" + monedero + ", cesta=" + cesta + ", wl=" + wl + "]";
	}
	
	
}
