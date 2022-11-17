package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.TipoProducto;
import Logica.Logica;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<TipoProducto> seleccion;
	private JButton bpersonal;
	private JPanel pNorte;
	private JPanel pCentral;
	private JPanel pbotonera;
	private Logica logica;
	private DefaultTableModel mProductos = new DefaultTableModel(
			new Object[] { "Código", "Categoría", "Fecha" }, 0
		);
	private JTable tproductos;

	
	public VentanaPrincipal()  {
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		seleccion = new JComboBox<>();
		seleccion.setModel(new DefaultComboBoxModel<TipoProducto>(TipoProducto.values()));
		bpersonal = new JButton("PERSONAL");
		logica = new Logica();
		pCentral = new JPanel();
		pNorte = new JPanel();
		pbotonera = new JPanel();
		tproductos = new JTable(mProductos);
		
		pbotonera.add(bpersonal);
		pNorte.add(seleccion);
		pCentral.add(tproductos);
		this.add(pNorte);
		this.add(pCentral);
		this.add(pbotonera);
		
	
				
		//Caracteristicas de la ventana
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("DEUSTOSHOP");
		getContentPane().setLayout(new GridLayout(3,1));
		pCentral.setLayout(new GridLayout(1,1));
		
		setVisible(true);		
				
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		
		bpersonal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPersonal ventana= new VentanaPersonal(); //aqui falta pasarle la logica a la ventana principal
				dispose();
			}
		});
	}
}
