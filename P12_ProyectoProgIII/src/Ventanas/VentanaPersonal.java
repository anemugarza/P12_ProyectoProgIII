package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

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

import Clases.Comprador;
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
	private JButton bvolver;
	private JPanel pbotonera;

	Comprador c1 = (Comprador) Logica.getUsuario();
	private DefaultTableModel mProductos = new DefaultTableModel(
			new Object[] { "Nombre", "Código", "Tipo", "Precio", "Foto" }, 0
		);
	private JTable tproductos;

	
	public VentanaPersonal()  {
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		info = new JLabel("LISTA: Wish List");
		totalPrecio = new JLabel("PRECIO: "); //falta hacer la funcion del precio total de la lista
		bwl = new JButton("WISHLIST");
		bcesta = new JButton("CESTA");
		bvolver = new JButton("VOLVER");
		bcompra = new JButton("COMPRAR");
		pNorte = new JPanel();
		pbotonera = new JPanel();
		tproductos = new JTable(mProductos);
		
		actualizarLista(1);
		tproductos.setModel(mProductos);
		pbotonera.add(bwl);
		pbotonera.add(bcesta);
		pbotonera.add(bcompra);
		bcompra.setVisible(false);
		pNorte.add(bvolver, BorderLayout.WEST);
		pNorte.add(info, BorderLayout.WEST);
		pNorte.add(totalPrecio, BorderLayout.EAST);
		this.add(pNorte,BorderLayout.NORTH);
		this.add(tproductos, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
		
		pNorte.setBounds(100, 100, 100, 30);
		pNorte.setLayout(new GridLayout(1,3));
		pbotonera.setBounds(100, 100, 100, 30);
				
		//Caracteristicas de la ventana
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("TU VENTANA PERSONAL");
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
				VentanaPrincipal ventana= new VentanaPrincipal(); 
				dispose();	
			}
		});
		
		bcesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(true);
				info.setText("LISTA: Cesta");
				actualizarLista(0);
			}
		});
		
		bwl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(false);
				info.setText("LISTA: WishList");
				actualizarLista(1);
			}
		});
		
		bcompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tu compra ha sido registrada");

				
			}
		});
	}
	public void actualizarLista(int type) {
		mProductos = new DefaultTableModel(
				new Object[] { "Nombre", "Código", "Tipo", "Precio", "Foto" }, 0
			);
		switch (type) {
		case 0:
			for(Producto p : c1.getCesta()) {
				 mProductos.addRow(new Object[] {p.getNomP(),p.getCodigoP(),p.getClass(),p.getPrecio(), p.getFoto()});
			}
			break;

		default:
			for(Producto p : c1.getWl()) {
				 mProductos.addRow(new Object[] {p.getNomP(),p.getCodigoP(),p.getClass(),p.getPrecio(), p.getFoto()});
			}
			break;
		}
		tproductos.setModel(mProductos);
	}
}
