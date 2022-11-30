package Ventanas;


import java.awt.event.*;

import javax.swing.*;

import Logica.BaseDeDatos;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;


public class VentanaInicial extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmbeddedMediaPlayerComponent comp;
	public VentanaInicial() {
		BaseDeDatos.abrirConexion("MiBD.db", true);
		this.setSize(1000, 600);
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
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					v.dispose();
					VentanaLogIn vl = new VentanaLogIn();
					vl.setVisible(true);
				}
			}
		});
	}
	
	public static void main(String[] args) {
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
		v.setVisible(true);
		
	}
}