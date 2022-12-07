package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Clases.Comprador;
import Clases.Producto;
import Clases.TipoProducto;
import Clases.Usuario;
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
	private boolean tipolista; //false es wl y true es cesta

	Comprador c1 = (Comprador) Logica.getUsuario();
	Vector <String> cabecera = new Vector <String> (Arrays.asList("NOMBRE","CÓDIDO","TIPO PRODUCTO", "PRECIO", "CANTIDAD"));
	private DefaultTableModel mProductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
	private JTable tproductos;


	
	public VentanaPersonal()  {
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		tipolista = false;
		info = new JLabel("LISTA: Wish List");
		bwl = new JButton("WISHLIST");
		bcesta = new JButton("CESTA");
		bvolver = new JButton("VOLVER");
		bcompra = new JButton("COMPRAR");
		pNorte = new JPanel();
		pbotonera = new JPanel();
		tproductos = new JTable(mProductos);
		
		actualizarLista(1);
		totalPrecio = new JLabel("PRECIO TOTAL: " + actualizarPrecio(c1.getWl())+ "€");
		tproductos.setModel(mProductos);
		pbotonera.add(bwl);
		pbotonera.add(bcesta);
		pbotonera.add(bcompra);
		bcompra.setVisible(false);
		pNorte.add(bvolver, BorderLayout.WEST);
		pNorte.add(info, BorderLayout.WEST);
		pNorte.add(totalPrecio, BorderLayout.EAST);
		this.add(pNorte,BorderLayout.NORTH);
		this.add(new JScrollPane(tproductos), BorderLayout.CENTER);
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
				totalPrecio.setText("PRECIO TOTAL: " + actualizarPrecio(c1.getCesta())+ "€");
				tipolista = true;
			}
		});
		
		bwl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(false);
				info.setText("LISTA: WishList");
				actualizarLista(1);
				totalPrecio.setText("PRECIO TOTAL: " + actualizarPrecio(c1.getWl()) + "€");
				tipolista = false;
			}
		});
		
		bcompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tu compra ha sido registrada");
				//Hay que registrar la compra
				
				
			}
		});
		
		KeyListener kl = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					int pos= tproductos.getSelectedRow();
					if(tipolista) {
						c1.getCesta().remove(pos);
						actualizarLista(0);
					}else {
						c1.getWl().remove(pos);
						actualizarLista(1);
					}
					
				}
			}
		};
		tproductos.addKeyListener( kl );
	}
	public void actualizarLista(int type) {
		Vector <String> cabecera = new Vector <String> (Arrays.asList("NOMBRE","CÓDIDO","TIPO PRODUCTO", "PRECIO", "CANTIDAD"));
		mProductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		switch (type) {
		case 0:
			for(Producto p : c1.getCesta()) {
				int cont=1;
				 for (int i = 0; i < mProductos.getRowCount(); i++) {
					 if(mProductos.getValueAt(i, 0).toString().equals(p.getNomP())){
						cont= cont+1;
						mProductos.removeRow(i);
					 } 
				 }
				mProductos.addRow(new Object[] {p.getNomP(),p.getCodigoP(),p.getClass().getSimpleName(),p.getPrecio()+ "€",cont });
			
				
				
			}
			break;

		default:
			for(Producto p : c1.getWl()) {
					int cont=1;
					 for (int i = 0; i < mProductos.getRowCount(); i++) {
						 if(mProductos.getValueAt(i, 0).toString().equals(p.getNomP())){
							cont= cont+1;
							mProductos.removeRow(i);
						 }
					 }
				 mProductos.addRow(new Object[] {p.getNomP(),"Co:" +p.getCodigoP(),p.getClass().getSimpleName(),p.getPrecio()+ "€", "Cantidad:"+cont});
			}
			break;
		}
		tproductos.setModel(mProductos);
	}
	
	public String actualizarPrecio(List<Producto> lista) {
		double precioT = 0.0;
		DecimalFormat df = new DecimalFormat("#.00");
		for(Producto p : lista) {
			precioT += p.getPrecio();
		}
		return df.format(precioT);
	}
	
	
}
