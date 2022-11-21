package Clases;

import java.util.ArrayList;

import Logica.BaseDeDatos;
import Logica.Logica;
import Ventanas.VentanaLogIn;
import Ventanas.VentanaPersonal;
import Ventanas.VentanaPrincipal;
import Ventanas.VentanaProducto;
import Ventanas.VentanaPrincipal;

public class Main {
	public static void main(String[] args) {
		Logica log = new Logica();
		/*Ropa ropa1 = new Ropa("Camiseta blanca hombre", 12.95 , "Camiseta blanca de algodón con pequeño logo de Deusto", Talla.M,"CamisetaBlancaHombre.JPEG");
		Ropa ropa2 = new Ropa("Camiseta blanca mujer", 12.95 , "Camiseta blanca de algodón con pequeño logo de Deusto", Talla.M,"CamisetaBlancaMujer.JPEG");
		Ropa ropa3 = new Ropa("sudadera blanca ", 17.95 , "Sudadera blanca de algodón  con pequeño logo de Deusto", Talla.M, "SudaderaBlanca.JPEG");
		Ropa ropa4 = new Ropa("sudadera gris letras", 17.95 , "Sudadera gris de algodón con Deusto escrito", Talla.M,"SudaderaGris.JPEG");
		Ropa ropa5 = new Ropa("sudadera negra", 17.95 , "Sudadera negra de algodón con Deusto escrito", Talla.M, "SudaderaNegra.JPEG");
		Ropa ropa6 = new Ropa("sudadera azul ", 17.95 , "Sudadera azul marino de algodón  con logo de Deusto", Talla.M, "SudaderaAzul.JPEG");
		Ropa ropa7 = new Ropa("sudadera gris logo", 17.95 , "Sudadera gris de algodón  con logo de Deusto", Talla.M,"SudaderaGris2.JPEG");
		Ropa ropa8 = new Ropa("Camiseta azul hombre", 12.95 , "Camiseta azul marino de algodón con logo de Deusto", Talla.M,"CamisetaAzulHombre.JPEG");
		Ropa ropa9 = new Ropa("Camiseta azul mujer", 12.95 , "Camiseta azul marino de algodón con logo de Deusto", Talla.M,"CamisetaAzulMujer.JPEG");
		Ropa ropa10 = new Ropa("Camiseta gris hombre", 12.95 , "Camiseta gris de algodón con UD", Talla.M, "CamisetaGrisHombre.JPEG");
		Ropa ropa11 = new Ropa("Camiseta gris mujer", 12.95 , "Camiseta gris de algodón con UD", Talla.M, "CamisetaGrisHombre.JPEG");
		MaterialEscolar material1 = new MaterialEscolar("Libro Electronica", 25.75, "ElectronicaDigital.JPEG" );
		MaterialEscolar material2 = new MaterialEscolar("Libro Calculo", 44.99, "Calculo.jpeg" );
		MaterialEscolar material3 = new MaterialEscolar("Libro Algoritmia", 28.89, "Algoritmia.jpeg" );
		MaterialEscolar material4 = new MaterialEscolar("Libro Circuitos", 23, "Circuitos.jpeg" );
		MaterialEscolar material5 = new MaterialEscolar("Cuaderno deusto", 5.96, "Cuaderno.JPEG" );
		MaterialEscolar material6 = new MaterialEscolar("Boligrafo deusto", 2, "Boligrafo.JPEG" );
		MaterialEscolar material7 = new MaterialEscolar("Agenda deusto", 4.78, "AgendaDeusto.JPEG" );
		MaterialEscolar material8 = new MaterialEscolar("Estuche deusto", 5.96, "Estuche.JPEG" );
		Electronica electronica1 = new Electronica("Teclado", 14.99, "teclado.jpeg", "Teclado 100, Conexión USB, Negro");
		Electronica electronica2 = new Electronica("Auriculares", 16.99, "auriculares.png", "Auriculares apple earpods");
		Electronica electronica3 = new Electronica("Ipad", 779, "ipad.png", "Apple ipad air 2022 128GB wifi");
		Electronica electronica4 = new Electronica("Adaptador", 25.99, "adaptador.jpeg", " Adaptador de la marca UGREEN, fino,compacto y con 6 salidas que incluyen USB-C, HDMI a 4K, tres puertos USB 3.0 y lector de tarjetas.");
		Electronica electronica5 = new Electronica("Disco duro", 45, "DiscoDuro.jpeg", "Disco duro externo 1TB 3.0");
		Electronica electronica6 = new Electronica("Raton", 29.25, "raton.jpeg", "Microsoft Bluetooth Mobile Mouse 3600 Negro");
		Electronica electronica7 = new Electronica("Pendrive", 25.99, "raton.jpeg", "Memoria Externa USB 256GB Almacenamiento de Datos Extern Flash Drive para PC,Tabletas y Otros Dispositivos.");
		
		log.getProductosHistoricos().add(ropa1);
		log.getProductosHistoricos().add(ropa2);
		log.getProductosHistoricos().add(ropa3);
		log.getProductosHistoricos().add(ropa4);
		log.getProductosHistoricos().add(ropa5);
		log.getProductosHistoricos().add(ropa6);
		log.getProductosHistoricos().add(ropa7);
		log.getProductosHistoricos().add(ropa8);
		log.getProductosHistoricos().add(ropa9);
		log.getProductosHistoricos().add(ropa10);
		log.getProductosHistoricos().add(ropa11);
		log.getProductosHistoricos().add(material1);
		log.getProductosHistoricos().add(material2);
		log.getProductosHistoricos().add(material3);
		log.getProductosHistoricos().add(material4);
		log.getProductosHistoricos().add(material5);
		log.getProductosHistoricos().add(material6);
		log.getProductosHistoricos().add(material7);
		log.getProductosHistoricos().add(material8);
		log.getProductosHistoricos().add(electronica1);
		log.getProductosHistoricos().add(electronica2);
		log.getProductosHistoricos().add(electronica3);
		log.getProductosHistoricos().add(electronica4);
		log.getProductosHistoricos().add(electronica5);
		log.getProductosHistoricos().add(electronica6);
		log.getProductosHistoricos().add(electronica7);*/
		
		log.cargarProductos("Productos.dat");
		System.out.println(log.productosHistoricos);

		
	}

}
