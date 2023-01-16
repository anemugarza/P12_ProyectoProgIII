package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Comprador;
import Clases.Producto;
import Logica.BaseDeDatos;
import Logica.Logica;

public class VentanaPersonal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Componentes de la ventana personal
	private JLabel info;
	private JLabel totalPrecio;
	private JButton bwl;
	private JButton bcesta;
	private JButton bcompra;
	private JPanel pNorte;
	private JButton bvolver;
	private JPanel pbotonera;
	private boolean tipolista; //false es wl y true es cesta
	
	
	//
	Comprador c1 = (Comprador) Logica.getUsuario();
	Vector <String> cabecera = new Vector <String> (Arrays.asList("NOMBRE","CÓDIDO","TIPO PRODUCTO", "PRECIO", "CANTIDAD"));
	private DefaultTableModel mProductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
	private JTable tproductos;

	private int filaMouseOver = -1;
	private int colMouseOver = -1;
	
	public VentanaPersonal()  {
		inicializar();
	}
	/**
	 * Inicializa la ventana personal mostrando la wishlist por defecto.
	 */

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
		totalPrecio = new JLabel("PRECIO TOTAL: " + String.format("%.2f", actualizarPrecio(c1.getWl()))+ "€");
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
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		setLocationRelativeTo(null);
		setTitle("TU VENTANA PERSONAL");
		setVisible(true);		
				
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		//Botón para volver a la página anterior, esto es , la ventana principal.
		bvolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana= new VentanaPrincipal(); 
				dispose();	
			}
		});
		//Botón para que la ventana muestre los productos en cesta y el precio total de esta.
		bcesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(true);
				info.setText("LISTA: Cesta");
				actualizarLista(0);
				totalPrecio.setText("PRECIO TOTAL: " + String.format("%.2f", actualizarPrecio(c1.getCesta()))+ "€");
				tipolista = true;
			}
		});
		//Botón para que la ventana muestre los productos de la wishlist y el precio total de esta.
		
		bwl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bcompra.setVisible(false);
				info.setText("LISTA: WishList");
				actualizarLista(1);
				totalPrecio.setText("PRECIO TOTAL: " + String.format("%.2f", actualizarPrecio(c1.getWl())) + "€");
				tipolista = false;
			}
		});
		/**
		 * Botón para relizar la compra de los productos en cesta.
		 * La compra será registrada en la base de datos y la cesta volverá a estar 
		 * vacía y el precio total a cero.
		 * El botón falla si en la cesta no hay ningun producto.
		 */
		
		
		bcompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!c1.getCesta().isEmpty()) {
					long fecha = System.currentTimeMillis();
					int id = BaseDeDatos.añadirCompra(c1.getCodigoUsuario() , fecha, actualizarPrecio(c1.getCesta()));
					for(Producto p : c1.getCesta()) {
						BaseDeDatos.añadirCompraP(id, p.getCodigoP());
					}
					totalPrecio.setText("PRECIO TOTAL: 0.00 €");
					JOptionPane.showMessageDialog(null, "Tu compra ha sido registrada");
					c1.getCesta().removeAll(c1.getCesta());
					actualizarLista(0);
				}else JOptionPane.showMessageDialog(null, "ERROR: Cesta vacia");
			}
		});
		
		/**
		 * Se pueden borrar los productos tanto de la wishlist como de la cesta. Solo hay que poner el ratón encima del 
		 * producto que queremos borrar y darle al ctrl + supr. Se actualiza tanto la lista en ventanta como de la base de datos.
		 */
		
		KeyListener kl = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					int pos= tproductos.getSelectedRow();
					if(tipolista) {
						try {
							BaseDeDatos.eliminarProducto(c1.getCodigoUsuario(), c1.cesta.get(pos).getCodigoP(), 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						c1.getCesta().remove(pos);
						actualizarLista(0);
						totalPrecio.setText("PRECIO TOTAL: " + String.format("%.2f", actualizarPrecio(c1.getCesta())) + "€");

					}else {
						try {
							BaseDeDatos.eliminarProducto(c1.getCodigoUsuario(), c1.wl.get(pos).getCodigoP(), 0);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						c1.getWl().remove(pos);
						actualizarLista(1);
						totalPrecio.setText("PRECIO TOTAL: " + String.format("%.2f", actualizarPrecio(c1.getWl())) + "€");

					}
					
				}
			}
		};
		tproductos.addKeyListener( kl );
		

		/**
		 * Renderer que muestra el producto en color verde si pasamos el ratón por encima.
		 */

		tproductos.setDefaultRenderer( Object.class, (table, value, isSelected, hasFocus, row, column) -> {
			JLabel label = new JLabel( value + "" );
			label.setFont( new Font( "Arial", Font.PLAIN, 14 ) );
			label.setOpaque( true );
			if (filaMouseOver>=0 && colMouseOver>=0 && row==filaMouseOver && column==colMouseOver) {
				label.setBackground( Color.GREEN );
			} else {
				label.setBackground( Color.WHITE );
			}
			return label;
		} );
		tproductos.addMouseListener( new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				filaMouseOver = -1;
				colMouseOver = -1;
				tproductos.repaint();
			}
		});
		tproductos.addMouseMotionListener( new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				filaMouseOver = tproductos.rowAtPoint(e.getPoint());
				colMouseOver = tproductos.columnAtPoint(e.getPoint());
				if (filaMouseOver>=0 && colMouseOver>=0) {
					tproductos.repaint();
				}
			}
		});
			
	}
	/**
	 * Actualiza la lista de productos
	 * @param type: indica que lista queremos que se cargue. Valor 0 para cargar la cesta y en cualquier otro 
	 * caso la lista que se mostrará por defecto es la wishlist.
	 */
	public void actualizarLista(int type) {
		Vector <String> cabecera = new Vector <String> (Arrays.asList("NOMBRE","CÓDIDO","TIPO PRODUCTO", "PRECIO", "CANTIDAD"));
		mProductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		List<Producto> añadidos = new ArrayList<Producto>();
		switch (type) {
		case 0:
			for(Producto p : c1.getCesta()) {
				int cont=0;
				for(Producto p2 : c1.getCesta()) {
					if(p.equals(p2)) cont++;
				}
				if(!añadidos.contains(p)) {
					mProductos.addRow(new Object[] {p.getNomP(), p.getCodigoP(),p.getClass().getSimpleName(),p.getPrecio()+ "€", cont});
					añadidos.add(p);
				}
			}
			break;

		default:
			for(Producto p : c1.getWl()) {
				int cont=0;
				for(Producto p2 : c1.getWl()) {
					if(p.equals(p2)) cont++;
				}
				if(!añadidos.contains(p)) {
					mProductos.addRow(new Object[] {p.getNomP(), p.getCodigoP(),p.getClass().getSimpleName(),p.getPrecio()+ "€", cont});
					añadidos.add(p);
				}
			}
			break;
		}
		tproductos.setModel(mProductos);
	}
	/**
	 * actualiza el precio total de las listas
	 * @param lista: es la lista de prouctos del que hay que obtener un precio total
	 * @return devuelve lo que habría que pagar para hacerse con todos los articulos de la lista en pantalla.
	 */
	
	public double actualizarPrecio(List<Producto> lista) {
		double precioT = 0.0;
		DecimalFormat df = new DecimalFormat("#.00");
		for(Producto p : lista) {
			precioT += p.getPrecio();
		}
		return precioT;
	}
	
	
}
