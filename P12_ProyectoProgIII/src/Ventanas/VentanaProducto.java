package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Clases.Comprador;
import Logica.Logica;

public class VentanaProducto extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Componentes del Log In
	private JLabel nombreProd;
	private JLabel precioProd;
	private JLabel descripcionProd;
	//falta foto del producto
	private JButton bvolver;
	private JButton bañadirWL;
	private JButton bañadirCESTA;
	private JPanel pNorte;
	private JPanel pCentral;
	private JPanel pbotonera;
	private Logica logica;

	
	public VentanaProducto()  {
		inicializar();
	}
	
	private void inicializar() {
		//Inicializamos elementos 
		nombreProd= new JLabel("Nombre");
		precioProd= new JLabel("Precio");
		descripcionProd= new JLabel("Descrip");
		pNorte= new JPanel();
		pCentral= new JPanel();
		pbotonera= new JPanel();
		bañadirCESTA = new JButton("AÑADIR CESTA");
		bañadirWL = new JButton("AÑADIR WL");
		bvolver = new JButton("ATRAS");
		logica= new Logica();
		Comprador c1 = (Comprador) logica.getUsuario();

		
		//Añadimos a la ventana
		pCentral.add(nombreProd);
		pCentral.add(precioProd);
		pCentral.add(descripcionProd);
		pNorte.add(bvolver, BorderLayout.WEST);
		pbotonera.add(bañadirCESTA, BorderLayout.SOUTH);
		pbotonera.add(bañadirWL, BorderLayout.SOUTH);
		
		//Caracteristicas de la ventana
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("PRODUCTO");
		pNorte.setBounds(100, 100, 30, 30);
		pbotonera.setBounds(100, 100, 30, 30);
		getContentPane().setLayout(new GridLayout(3,1));
		pCentral.setLayout(new GridLayout(3,1));
		
		this.add(pNorte);
		this.add(pCentral);
		this.add(pbotonera);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		bvolver.addActionListener(new ActionListener() {
			//si es admin entre a la ventana del admin
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana= new VentanaPrincipal(); //aqui falta pasarle la logica a la ventana principal
				dispose();	
			}
		});
		
		bañadirCESTA.addActionListener(new ActionListener() {
			//si es admin entre a la ventana del admin
			@Override
			public void actionPerformed(ActionEvent e) {
				c1.anyadirCesta(p);
			}
		});
		
		bañadirWL.addActionListener(new ActionListener() {
			//si es admin entre a la ventana del admin
			@Override
			public void actionPerformed(ActionEvent e) {
				c1.anyadirWL(p);
			}
		});
	}
}
