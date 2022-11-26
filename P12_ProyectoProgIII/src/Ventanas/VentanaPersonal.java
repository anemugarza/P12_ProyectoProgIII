package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Clases.Producto;
import Clases.TipoProducto;
import Logica.Logica;

public class VentanaPersonal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel info;
	private JLabel totalPrecio;
	private JButton bwl;
	private JButton bcesta;
	private JButton bcompra;
	private JPanel pNorte;
	private JPanel pCentral;
	private JPanel pbotonera;
	private Logica logica;
	private DefaultTableModel mProductos = new DefaultTableModel(
			new Object[] { "Nombre", "Código", "Tipo", "Precio" }, 0
		);
	private JTable tproductos;

	
	public VentanaPersonal()  {
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		info = new JLabel("LISTA");
		totalPrecio = new JLabel("PRECIO");
		bwl = new JButton("WISHLIST");
		bcesta = new JButton("CESTA");
		bcompra = new JButton("COMPRAR");
		logica = new Logica();
		pCentral = new JPanel();
		pNorte = new JPanel();
		pbotonera = new JPanel();
		tproductos = new JTable(mProductos);
		
		for(Producto p : logica.getUsuario().getWL()) {
			mProductos.addRow(p);
		}
		
		pbotonera.add(bwl);
		pbotonera.add(bcesta);
		pbotonera.add(bcompra);
		bcompra.setVisible(false);
		pNorte.add(info, BorderLayout.WEST);
		pNorte.add(totalPrecio, BorderLayout.EAST);
		pCentral.add(tproductos);
		this.add(pNorte);
		this.add(pCentral);
		this.add(pbotonera);
		
		pNorte.setBounds(100, 100, 100, 30);
		pNorte.setLayout(new GridLayout(1,2));
		pbotonera.setBounds(100, 100, 100, 30);
				
		//Caracteristicas de la ventana
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("TU VENTANA PERSONAL");
		getContentPane().setLayout(new GridLayout(3,1));
		setVisible(true);		
				
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		bcesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(true);
				
			}
		});
	}

}
