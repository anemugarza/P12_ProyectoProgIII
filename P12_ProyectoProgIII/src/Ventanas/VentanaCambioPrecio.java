package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Producto;
import Logica.Logica;

public class VentanaCambioPrecio extends JFrame implements VentanaCambio{
	
	private static final long serialVersionUID = 1L;
	
	//Componentes de la ventana
	private JLabel precioN;
	private JTextField txtPrecio;
	private JButton bcancelar;
	private JButton bguardarDatos;
	private JPanel pbotonera;
	private static Logger logger = Logger.getLogger( "CambioPrecio" );
	
	public VentanaCambioPrecio(Producto p)  {
		inicializar(p);
	}
	
	public void inicializar(Producto p) {
		//Inicializamos elementos 
		precioN= new JLabel("Introduzca el nuevo precio: ");
		txtPrecio= new JTextField(10);
		bguardarDatos= new JButton("GUARDAR CAMBIOS");
		bcancelar= new JButton("CANCELAR");
		pbotonera = new JPanel();
		
		//Añadimos a la ventana
		pbotonera.add(bcancelar);
		pbotonera.add(bguardarDatos);
		this.setLayout(new BorderLayout());
		this.add(precioN, BorderLayout.NORTH);
		this.add(txtPrecio, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
		
		
		//Caracteristicas de la ventana
		setSize(500,200);
		setLocationRelativeTo(null);
		setTitle("CAMBIO DE PRECIO");
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				dispose();
			}
		});
		//Para cancelar la acción de cambio de precio
		bcancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		//Para registrar el cambio en el fichero de productos.
		//También retrocede a la página anterior.Esto es, la ventana para modificar productos.
		bguardarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setPrecio(Double.parseDouble(txtPrecio.getText()));
				logger.log( Level.INFO, "Cambio de precio efectuado correctamente:  "+ p.getNomP());
				Logica.guardarProductos("Productos.dat");
				VentanaModificarProd ventana= new VentanaModificarProd(p); 
				dispose();	
			}
		});
		
	}
}