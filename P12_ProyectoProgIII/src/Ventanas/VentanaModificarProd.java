package Ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Clases.Producto;

public class VentanaModificarProd extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaModificarProd(Producto p)  {
		inicializar(p);
	}

	private void inicializar(Producto p) {
		// TODO Auto-generated method stub
		
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("MODIFICAR PRODUCTO: " + p.getNomP());
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
	}
	
}
