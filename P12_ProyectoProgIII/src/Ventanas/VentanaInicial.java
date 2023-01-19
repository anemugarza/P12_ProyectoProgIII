package Ventanas;


import java.awt.GraphicsEnvironment;
import java.awt.event.*;

import javax.swing.*;

import Logica.BaseDeDatos;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;


public class VentanaInicial extends JFrame{

	/**
	 * Ventana en la que se nos muestra un video como entrada a la página web.  Una vez terminado el video, se abrirá la ventana login. 
	 */
	private static final long serialVersionUID = 1L;
	EmbeddedMediaPlayerComponent comp;
	public VentanaInicial() {
		BaseDeDatos.abrirConexion("MiBD.db", true);
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		//this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("BIENVENIDO A DEUSTOSHOP");
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} 
		});
		JFrame v = this;
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * En caso de no querer esperar a que el video se termine podremos pulsar la barra de espacio.
			 * @param e el evento de teclado.
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					//v.dispose();
					VentanaLogIn vl = new VentanaLogIn();
					vl.setVisible(true);
				}
			}
		});
	}
	
}