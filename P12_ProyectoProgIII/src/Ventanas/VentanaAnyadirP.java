package Ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Electronica;
import Clases.MaterialEscolar;
import Clases.Producto;
import Clases.Ropa;
import Clases.TipoProducto;
import Logica.Logica;

public class VentanaAnyadirP extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Componentes del Log In
	private JLabel nombreP;
	private JLabel precioP;
	private JLabel fotoP;
	private JPanel panelN;
	private JPanel panelP;
	private JPanel panelF;
	private JComboBox<TipoProducto> tipoP;
	private JTextField txtnombre;
	private JTextField txtprecio;
	private JTextField txtfoto;
	private JButton bcancelar;
	private JButton bguardarDatos;
	private JPanel pbotonera;
	

	public VentanaAnyadirP() {
		// TODO Auto-generated constructor stub
		inicializar();
	}
	
	private void inicializar() {
		//Inicializamos elementos 
		nombreP= new JLabel("Introduzca el nombre del producto: ");
		precioP= new JLabel("Introduzca el precio del producto: ");
		fotoP= new JLabel("Introduzca el nombre del archivo de la foto: ");
		txtnombre= new JTextField(25);
		txtprecio= new JTextField(10);
		txtfoto= new JTextField(60);
		tipoP = new JComboBox<>();
		tipoP.setModel(new DefaultComboBoxModel<TipoProducto>(TipoProducto.values()));
		panelN = new JPanel();
		panelP = new JPanel();
		panelF = new JPanel();
		bguardarDatos= new JButton("GUARDAR PRODUCTO");
		bcancelar= new JButton("CANCELAR");
		pbotonera = new JPanel();
		
		//Añadimos a la ventana
		panelN.setLayout(new GridLayout(1,2));
		panelP.setLayout(new GridLayout(1,2));
		panelF.setLayout(new GridLayout(1,2));
		panelN.add(nombreP);
		panelN.add(txtnombre);
		panelP.add(precioP);
		panelP.add(txtprecio);
		panelF.add(fotoP);
		panelF.add(txtfoto);
		pbotonera.add(bcancelar);
		pbotonera.add(bguardarDatos);
		this.setLayout(new GridLayout(5,1));
		
		this.add(panelN);
		this.add(panelP);
		this.add(tipoP);
		this.add(panelF);
		this.add(pbotonera);
		
		//Caracteristicas de la ventana
		setSize(500,400);
		setLocationRelativeTo(null);
		setTitle("NUEVO PRODUCTO");
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				dispose();
			}
		});
		
		bcancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		bguardarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Producto p;
				if(txtfoto.getText()!=null || txtnombre.getText()!=null|| txtprecio.getText()!=null ) {
					if(tipoP.getSelectedItem() != null) {
						if(tipoP.getSelectedItem() == TipoProducto.ELECTRONICA) {
							p = new Electronica(txtnombre.getText(), Double.parseDouble(txtprecio.getText()),"Media/" +txtfoto.getText());
						}else if(tipoP.getSelectedItem() == TipoProducto.ROPA){
							p = new Ropa(txtnombre.getText(), Double.parseDouble(txtprecio.getText()),"Media/" +txtfoto.getText());
						}else {
							p = new MaterialEscolar(txtnombre.getText(), Double.parseDouble(txtprecio.getText()),"Media/" +txtfoto.getText());
						}
						Logica.productosHistoricos.add(p);
						Logica.guardarProductos("Productos.dat");
						for(Producto t : Logica.productosHistoricos) {
							VentanaPrincipalAdmin.crearVentana(t);
						}
						dispose();
				}else JOptionPane.showMessageDialog(null, "Debes indicar de qué tipo es el producto");
				}else JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
				dispose();
			}
		});

	}
}
