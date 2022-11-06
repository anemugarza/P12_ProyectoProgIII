package Ventanas;

import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.JFrame;

public class VentanaInicial {
	
	public static void main (String[] args) {
		EmbeddedMediaPlayerComponent component = new EmbeddedMediaPlayerComponent();
		
		JFrame f = new JFrame();
		f.setContentPane(component);
		f.setBounds(new Rectangle(800,600,200,200));
		f.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
			component.release();
			System.exit(0);
			}
		});
		f.setVisible(true);
		
		component.mediaPlayer().media().play("/home/mark/demo.mp4");
	}
	
	
	
	
	
	

	
}
