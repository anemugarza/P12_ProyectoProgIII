package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class VentanaCambioPrecio extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
		//Componentes del Log In
		private JLabel PrecioAhora;
		private JLabel Precio;
		private JTextField txtPrecio;
		private JButton guardarDatos;
		private JPanel panelCentral;
		private JPanel botonera;
		private Logica logica;
		private BaseDeDatos bd;
		private Producto p;
	

		
		public VentanaCambioPrecio(Producto p)  {
			inicializar(p);
		}
		
		private void inicializar(Producto p) {
			//Inicializamos elementos 
			PrecioAhora= new JLabel("El precio en estos momentos es de " + p.getPrecio() + "€.");
			Precio= new JLabel("Introduzca el nuevo precio: ");
			txtPrecio= new JTextField(25);
			guardarDatos= new JButton("Guardar cambios");
			panelCentral= new JPanel();
			botonera= new JPanel();
			guardarDatos.setFont(new Font("Serif", Font.PLAIN, 20));
			logica= new Logica();
			bd = new BaseDeDatos();
			
			//Añadimos a la ventana
			panelCentral.add(PrecioAhora);
			panelCentral.add(Precio);
			panelCentral.add(txtPrecio);
			botonera.add(guardarDatos);
			

			
			//Caracteristicas de la ventana
			setSize(500,200);
			setLocationRelativeTo(null);
			setTitle("CAMBIO DE PRECIO");
			getContentPane().setLayout(new GridLayout(2,1));
			panelCentral.setLayout(new GridLayout(2,2));
			this.add(panelCentral);
			this.add(botonera);
			setVisible(true);
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.out.println("Cerrando");
					System.exit(0);
				}
			});
		
			
		
			
			setVisible(true);
		}
		
}