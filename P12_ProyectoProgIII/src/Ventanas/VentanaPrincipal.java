package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import Logica.Logica;
import Properties.PropertiesProyecto;

public class VentanaPrincipal extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	//Componentes de la ventana
	private JComboBox<TipoProducto> seleccion;
	private JButton bpersonal;
	private JButton brecursiva;
	private JButton bProperties;
	private JPanel pCentral;
	private JPanel botonera;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JScrollPane scroll;
	private VentanaPrincipal v;
	public VentanaPrincipal()  {
		inicializar();
	}
	
	/**
	 * Inicializa la ventana principal con todos los productos disponibles en tienda creando un JPanel para cada uno.
	 * Al clickar dos veces en alguna de los JPanel se abrirá la ventana del producto en cuestión.
	 */
	private void inicializar() {
		// TODO Auto-generated method stub
		v=this;
		seleccion = new JComboBox<>();
		seleccion.setModel(new DefaultComboBoxModel<TipoProducto>(TipoProducto.values()));
		bpersonal = new JButton("PERSONAL");
		bProperties = new JButton("HOJA DE RECLAMACIONES");
		brecursiva = new JButton("OPCIONES COMPRA");
		pCentral = new JPanel();
		botonera = new JPanel();
		scroll = new JScrollPane(pCentral);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(50, 30, 300, 50);
		for(Producto p : Logica.productosHistoricos) {
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
						VentanaProducto vp = new VentanaProducto(p);
					}
				}
			});
		}
		
		this.add(seleccion, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		botonera.add(bProperties);
		botonera.add(brecursiva);
		botonera.add(bpersonal);
		this.add(botonera, BorderLayout.SOUTH);
		
		//Caracteristicas de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		setLocationRelativeTo(null);
		setTitle("DEUSTOSHOP");
		setVisible(true); 
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		
		/**
		 * Con la combobox selección se mostrarán las tres opciones para filtrar todos los productos en tienda.
		 * Se dividen en las siguientes categorias: electronica, ropa y material escolar. Al seleccionar alguna de estas
		 * se volverá a cargar la ventana principal pero solamente con productos que pertenezcan a la categoria especificada.
		 * En todo lo demás la ventana funcionará exactamente igual.
		 */
		seleccion.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(seleccion.getSelectedItem()!=null) {
					pCentral.removeAll();
					TipoProducto tp = (TipoProducto) seleccion.getSelectedItem();
					if(tp.equals(TipoProducto.ELECTRONICA)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof Electronica) {
								JPanel pProducto = new JPanel();
								JPanel pfoto = new JPanel();
								JLabel lNom = new JLabel(p.getNomP());
								ImageIcon foto = new ImageIcon((String) p.getFoto());
								JLabelAjustado lfoto = new JLabelAjustado(foto);
								lfoto.setPreferredSize( new Dimension( 200, 200 ) );
								pfoto.add(lfoto);
								pProducto.add(pfoto, BorderLayout.NORTH);
								pProducto.add(lNom, BorderLayout.SOUTH);
								pCentral.add(pProducto);
								pCentral.setLayout(new GridLayout((int) Math.ceil(Logica.productosHistoricos.size()/2) , 2));
								
								pProducto.addMouseListener(new MouseAdapter() {
						
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										if (e.getClickCount() == 2) {
											VentanaProducto vp = new VentanaProducto(p);
										}
									}
								});
							}
						}
					}
					else if(tp.equals(TipoProducto.ROPA)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof Ropa) {
								JPanel pProducto = new JPanel();
								JPanel pfoto = new JPanel();
								JLabel lNom = new JLabel(p.getNomP());
								ImageIcon foto = new ImageIcon((String) p.getFoto());
								JLabelAjustado lfoto = new JLabelAjustado(foto);
								lfoto.setPreferredSize( new Dimension( 200, 200 ) );
								pfoto.add(lfoto);
								pProducto.add(pfoto, BorderLayout.NORTH);
								pProducto.add(lNom, BorderLayout.SOUTH);
								pCentral.add(pProducto);
								pCentral.setLayout(new GridLayout((int) Math.ceil(Logica.productosHistoricos.size()/2) , 2));
								
								pProducto.addMouseListener(new MouseAdapter() {
								
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										if (e.getClickCount() == 2) {
											VentanaProducto vp = new VentanaProducto(p);
										}
									}
								});
							}
						}
					}
					else if(tp.equals(TipoProducto.MATERIAL_ESCOLAR)) {
						for (Producto p : Logica.productosHistoricos) {
							if(p instanceof MaterialEscolar) {
								JPanel pProducto = new JPanel();
								JPanel pfoto = new JPanel();
								JLabel lNom = new JLabel(p.getNomP());
								ImageIcon foto = new ImageIcon((String) p.getFoto());
								JLabelAjustado lfoto = new JLabelAjustado(foto);
								lfoto.setPreferredSize( new Dimension( 200, 200 ) );
								pfoto.add(lfoto);
								pProducto.add(pfoto, BorderLayout.NORTH);
								pProducto.add(lNom, BorderLayout.SOUTH);
								pCentral.add(pProducto);
								pCentral.setLayout(new GridLayout((int) Math.ceil(Logica.productosHistoricos.size()/2) , 2));
								pProducto.addMouseListener(new MouseAdapter() {
								
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										if (e.getClickCount() == 2) {
											VentanaProducto vp = new VentanaProducto(p);
										}
									}
								});
							}
						}
					}
					pCentral.revalidate();
				}
			}
		});
		
		/**
		 * Botón para abrir la ventana personal
		 */
		bpersonal.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPersonal ventana= new VentanaPersonal(); 
				dispose();
			}
		});
		
		/**
		 * Botón para crear una nueva clase de Properties, la cual permite crear una ventana de diálogo 
		 * que gestiona valores de configuración
		 */
		bProperties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] props = { "Usuario", "Fecha", "Descripción", "Número de teléfono" };
				String[] mensajes = { "Usuario que reclama: ", "Fecha de reclamación: ", "Motivo resumido de la reclamación: ", "Número de teléfono: " };
				String[] defecto = { "a@gmail.com", sdf.format(new Date(System.currentTimeMillis())), ".", ""};
				String[] carpetas = { "", "", "", ""};
				try {
					PropertiesProyecto dialogo = new PropertiesProyecto( "properties.xml", props, mensajes, defecto, carpetas );
					dialogo.setVisible(true);  // Edición interactiva de configuración (hasta que no confirma o cancela el usuario no se devuelve el control)
					dialogo.dispose();  // Cierra la ventana para que swing acabe
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		/**
		 * Botón para abrir la ventana de recursividad, mostrando primero por pantalla un JOptionPane 
		 * donde hay que indicar el saldo del que se dispone para que en la función logica quePuedoComprar  pueda funcionar.
		 */
		brecursiva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double saldo =Double.parseDouble(JOptionPane.showInputDialog(v, "SALDO?", "0"));
				ArrayList<ArrayList<Producto>> lPuedoComprar = new ArrayList<>();
				ArrayList<Double> saldos = new ArrayList<Double>();
 				Logica.quePuedoComprar(saldo, new ArrayList<Producto>(), lPuedoComprar, saldos);
				VentanaRecursiva ventana= new VentanaRecursiva(saldo, lPuedoComprar, saldos); 
				dispose();
			}
		});
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
		/**
		 * se encarga del re-pintado o re-dibujado de la interfaz en diferentes situaciones
		 */
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