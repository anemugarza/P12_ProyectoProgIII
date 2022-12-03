package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Producto;
import Logica.Logica;

public class VentanaCambioFoto extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Componentes del Log In
	private JLabel fotoN;
	private JTextField txtfoto;
	private JButton bcancelar;
	private JButton bguardarDatos;
	private JPanel pbotonera;
	

	
	public VentanaCambioFoto(Producto p)  {
		inicializar(p);
	}
	
	private void inicializar(Producto p) {
		//Inicializamos elementos 
		fotoN= new JLabel("Introduzca el nombre del archivo de la nueva foto: ");
		txtfoto= new JTextField(60);
		bguardarDatos= new JButton("GUARDAR CAMBIOS");
		bcancelar= new JButton("CANCELAR");
		pbotonera = new JPanel();
		
		//Añadimos a la ventana
		pbotonera.add(bcancelar);
		pbotonera.add(bguardarDatos);
		this.setLayout(new BorderLayout());
		this.add(fotoN, BorderLayout.NORTH);
		this.add(txtfoto, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
		
		
		//Caracteristicas de la ventana
		setSize(500,200);
		setLocationRelativeTo(null);
		setTitle("CAMBIO DE FOTO");
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		bcancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaModificarProd ventana= new VentanaModificarProd(p); 
				dispose();	
			}
		});
		
		bguardarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFoto("Media/"+txtfoto.getText());
				Logica.guardarProductos("Productos.dat");
				VentanaModificarProd ventana= new VentanaModificarProd(p); 
				dispose();	
			}
		});

	}
}
