package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Clases.Producto;
import Logica.BaseDeDatos;
import Logica.Logica;

public class VentanaCambioNombre extends JFrame implements VentanaCambio{
	
	private static final long serialVersionUID = 1L;
	
		//Componentes de la ventana
		private JLabel nombreN;
		private JTextField txtNombre;
		private JButton bcancelar;
		private JButton bguardarDatos;
		private JPanel pbotonera;
		private static Logger logger = Logger.getLogger( "CambioNombre" );

		public VentanaCambioNombre(Producto p)  {
			inicializar(p);
		}
		
		public void inicializar(Producto p) {
			//Inicializamos elementos 
			nombreN= new JLabel("Introduzca el nuevo nombre: ");
			txtNombre= new JTextField(25);
			bguardarDatos= new JButton("GUARDAR CAMBIOS");
			bcancelar= new JButton("CANCELAR");
			pbotonera = new JPanel();
			
			//Añadimos a la ventana
			pbotonera.add(bcancelar);
			pbotonera.add(bguardarDatos);
			this.setLayout(new BorderLayout());
			this.add(nombreN, BorderLayout.NORTH);
			this.add(txtNombre, BorderLayout.CENTER);
			this.add(pbotonera, BorderLayout.SOUTH);
			
			
			//Caracteristicas de la ventana
			setSize(500,200);
			setLocationRelativeTo(null);
			setTitle("CAMBIO DE NOMBRE");
			setVisible(true);
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.out.println("Cerrando");
					dispose();
				}
			});
			//Para cancelar la acción de cambio de nombre
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
					p.setNomP(txtNombre.getText());
					logger.log( Level.INFO, "Cambio de nombre efectuado correctamente:  "+ p.getNomP());
					Logica.guardarProductos("Productos.dat");
					VentanaModificarProd ventana= new VentanaModificarProd(p); 
					dispose();	
				}
			});
	
		}
		
}