package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Clases.Electronica;
import Clases.MaterialEscolar;
import Clases.Producto;
import Clases.Ropa;
import Clases.TipoProducto;
import Logica.BaseDeDatos;
import Logica.Logica;

public class VentanaPrincipalAdmin extends JFrame{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private JComboBox<TipoProducto> seleccion;
	private JButton banyadirProd;
	private JButton bestadistica;
	private JPanel pCentral;
	private JPanel pbotonera;
	private JScrollPane scroll;
	private boolean borrando = false;
	public VentanaPrincipalAdmin()  {
		inicializar();
	}
	
	
	private void inicializar() {
		// TODO Auto-generated method stub
		seleccion = new JComboBox<>();
		seleccion.setModel(new DefaultComboBoxModel<TipoProducto>(TipoProducto.values()));
		banyadirProd = new JButton("AÑADIR PRODUCTO");
		bestadistica = new JButton("ESTADISTICAS");
		pCentral = new JPanel();
		pbotonera = new JPanel();
		scroll = new JScrollPane(pCentral);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(50, 30, 300, 50);
		for(Producto p: Logica.productosHistoricos)crearVentana(p);
		
		pbotonera.add(banyadirProd);
		pbotonera.add(bestadistica);
		this.add(seleccion, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
		
		
		
		//Caracteristicas de la ventana
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("DEUSTOSHOP ADMINISTRADOR");
		setVisible(true); 
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		seleccion.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(seleccion.getSelectedItem()!=null) {
					pCentral.removeAll();
					TipoProducto tp = (TipoProducto) seleccion.getSelectedItem();
					if(tp.equals(TipoProducto.ELECTRONICA)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof Electronica) {
								crearVentana(p);
							}
						}
					}
					else if(tp.equals(TipoProducto.ROPA)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof Ropa) {
								crearVentana(p);
							}
						}
					}
					else if(tp.equals(TipoProducto.MATERIAL_ESCOLAR)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof MaterialEscolar) {
								crearVentana(p);
								
							}
						}
					}
					pCentral.revalidate();

				}
			}
		});
		
		
		bestadistica.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEstadistica ventana= new VentanaEstadistica(); 
				dispose();
			}
		});
		
		banyadirProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAnyadirP vap = new VentanaAnyadirP();
			}
		});
		
	}
	
	public void crearVentana(Producto p) {
		JPanel pProducto = new JPanel();
		JPanel pfoto = new JPanel();
		JPanel pnom = new JPanel();
		JLabel lNom = new JLabel(p.getNomP());
		ImageIcon foto = new ImageIcon((String) p.getFoto());
		JLabelAjustado lfoto = new JLabelAjustado(foto);
		lfoto.setPreferredSize( new Dimension( 200, 200 ) );
		pfoto.add(lfoto);
		pnom.add(lNom);
		pProducto.add(pfoto, BorderLayout.NORTH);
		pProducto.add(pnom, BorderLayout.SOUTH);
		pProducto.setLayout(new GridLayout(2,1));
		pCentral.add(pProducto);
		pCentral.setLayout(new GridLayout((int) Math.ceil(Logica.productosHistoricos.size()/2) , 2));
		
		pProducto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					VentanaModificarProd v = new VentanaModificarProd(p);
				}
				if (e.getClickCount() == 1 && e.isControlDown()) {
					Logica.productosHistoricos.remove(p);
					Logica.guardarProductos("Productos.dat");
					crearVentana(p);
				}
				
			}
		});
		pCentral.revalidate();
	}
	
	private static class JLabelAjustado extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ImageIcon imagen; 
		private int tamX;
		private int tamY;
		/** Crea un jlabel que ajusta una imagen cualquiera con fondo blanco a su tamaño (a la que ajuste más de las dos escalas, horizontal o vertical)
		* @param imagen Imagen a visualizar en el label
		*/
		public JLabelAjustado( ImageIcon imagen ) {
			setImagen( imagen );
		}
		/** Modifica la imagen
		* @param imagen Nueva imagen a visualizar en el label
		*/
		public void setImagen( ImageIcon imagen ) {
			this.imagen = imagen;
			if (imagen==null) {
				tamX = 0;
				tamY = 0;
			} else {
				this.tamX = imagen.getIconWidth();
				this.tamY = imagen.getIconHeight();
			}
		}
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			g2.setColor( Color.WHITE );
			g2.fillRect( 0, 0, getWidth(), getHeight() );
			if (imagen!=null && tamX>0 && tamY>0) {
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
				double escalaX = 1.0 * getWidth() / tamX;
				double escalaY = 1.0 * getHeight() / tamY;
				double escala = escalaX;
				int x = 0;
				int y = 0;
				if (escalaY < escala) {
					escala = escalaY;
					x = (int) ((getWidth() - (tamX * escala)) / 2);
				} else {
					y = (int) ((getHeight() - (tamY * escala)) / 2);
				}
				g2.drawImage( imagen.getImage(), x, y, (int) (tamX*escala), (int) (tamY*escala), null );
			}
		}
	}
}
