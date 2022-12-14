package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		//VentanaLogIn v = new VentanaLogIn();
		VentanaInicial v = new VentanaInicial();
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
		v.setVisible(true);
	}

}
