package Clases;

import Logica.BaseDeDatos;
import Logica.Logica;
import Ventanas.VentanaInicial;
import Ventanas.VentanaLogIn;
import Ventanas.VentanaPersonal;
import Ventanas.VentanaPrincipal;
import Ventanas.VentanaPrincipalAdmin;

public class Main {

	public static void main(String[] args) {
		Logica log = new Logica();
		log.cargarProductos("Productos.dat");
		log.setUsuario(new Comprador(0, "a", "a", "a"));
		BaseDeDatos.abrirConexion("MiBD.db", false);
		VentanaPrincipal vl = new VentanaPrincipal();
		
		//VentanaPrincipalAdmin v = new VentanaPrincipalAdmin();
		//VentanaInicial v3 = new VentanaInicial();
	}

}
