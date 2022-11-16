package Clases;

import Logica.BaseDeDatos;
import Ventanas.VentanaLogIn;
import Ventanas.VentanaPersonal;
import Ventanas.VentanaPrincipal;
import Ventanas.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseDeDatos.abrirConexion("MiBD.bd", true);
		VentanaPersonal vl = new VentanaPersonal();
		
	}

}
