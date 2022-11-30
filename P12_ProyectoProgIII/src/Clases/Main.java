package Clases;

import Logica.Logica;
import Ventanas.VentanaLogIn;
import Ventanas.VentanaPersonal;
import Ventanas.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		Logica log = new Logica();
		log.cargarProductos("Productos.dat");
		VentanaPrincipal vl = new VentanaPrincipal();
	}

}
