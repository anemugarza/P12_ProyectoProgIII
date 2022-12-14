package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Logica.BaseDeDatos;
import Logica.Logica;
import Ventanas.VentanaInicial;
import Ventanas.VentanaLogIn;
import Ventanas.VentanaModificarProd;
import Ventanas.VentanaModificarProd;
import Ventanas.VentanaPersonal;
import Ventanas.VentanaPrincipal;
import Ventanas.VentanaPrincipalAdmin;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class Main {

	public static void main(String[] args) {
		BaseDeDatos.abrirConexion("MiBD.db", true);
		Logica.cargarProductos("Productos.dat");
		ArrayList<Producto> lPuedoComprar = new ArrayList<>();
		double saldo = 50;
		if(saldo>=Logica.getMenorPrecio(Logica.productosHistoricos)) {
			int num = (int) (Math.floor(Math.random()*(Logica.productosHistoricos.size()-1-0+1)+0));
			saldo = Logica.quePuedoComprar(saldo, Logica.productosHistoricos, lPuedoComprar,num,1);
			for(Producto p: lPuedoComprar) {
				System.out.println(p);
			}
			System.out.println("SALDO RESTANTE: "+saldo+" €");
		}
		//VentanaLogIn v = new VentanaLogIn();
		//VentanaInicial v = new VentanaInicial();
		/*VentanaInicial v = new VentanaInicial();
		EmbeddedMediaPlayerComponent comp = new EmbeddedMediaPlayerComponent() {
			private static final long serialVersionUID = 1L;

			public void finished(MediaPlayer mediaPlayer) {
				v.dispose();
				VentanaLogIn vl = new VentanaLogIn();
				vl.setVisible(true);
				}
			};
		v.setContentPane(comp);
		v.setVisible(true);
		comp.mediaPlayer().media().play("Media/VideoInicial.MP4");
		v.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
				comp.mediaPlayer().controls().pause();
				v.dispose();
				VentanaLogIn vl = new VentanaLogIn();
				vl.setVisible(true);
			} 
			}
		});*/
		//v.setVisible(true);
	}

}
