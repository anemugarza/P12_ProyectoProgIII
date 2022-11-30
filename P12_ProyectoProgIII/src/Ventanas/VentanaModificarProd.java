package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import Clases.Producto;

public class VentanaModificarProd extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bvolver;
	
	public VentanaModificarProd(Producto p)  {
		inicializar(p);
	}

	private void inicializar(Producto p) {
		// TODO Auto-generated method stub
		bvolver = new JButton("VOLVER");
		
		this.add(bvolver, BorderLayout.NORTH);
		
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
		
		bvolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalAdmin ventana= new VentanaPrincipalAdmin(); 
				dispose();	
			}
		});
	}
	
}
