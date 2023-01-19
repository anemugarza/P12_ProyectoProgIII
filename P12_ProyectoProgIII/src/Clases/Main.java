 package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Logica.BaseDeDatos;
import Logica.Logica;
import Ventanas.VentanaInicial;
import Ventanas.VentanaLogIn;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class Main {

	public static void main(String[] args) {
		//Conexión a la base de datos
		BaseDeDatos.abrirConexion("MiBD.db", true);
		
		//Carga de productos desde el fichero donde se almacenan sus datos.
		Logica.cargarProductos("Productos.dat");
		
		//Creación de ventana inicial e inicialización del video que se muestra en esta.
		VentanaInicial v = new VentanaInicial();
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
		//Opción para saltarse el video pulsando la barra de espacio y abrir directamente la ventana LogIn
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
				comp.mediaPlayer().controls().pause();
				v.dispose();
				VentanaLogIn vl = new VentanaLogIn();
				vl.setVisible(true);
			} 
			}
		});
		
	}

}
