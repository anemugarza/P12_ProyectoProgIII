package Ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class VentanaEstadistica extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaEstadistica()  {
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("ESTADISTICAS");
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
	}
}
