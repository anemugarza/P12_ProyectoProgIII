package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Clases.Producto;

public class VentanaModificarProd extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Componentes del Log In
	private JLabel nombreProd;
	private JLabel precioProd;
	private ImageIcon foto;
	private JLabelAjustado lfoto;
	private JButton bvolver;
	private JButton bPrecio;
	private JButton bNombre;
	private JButton bFoto;
	private JPanel pCentral;
	private JPanel pInfo;
	private JPanel pbotonera;
	
	public VentanaModificarProd(Producto p)  {
		inicializar(p);
	}
	
	private void inicializar(Producto p) {
		nombreProd= new JLabel("Nombre: " + p.getNomP());
		precioProd= new JLabel("Precio: " + p.getPrecio() + "€");
		foto = new ImageIcon(p.getFoto());
		lfoto= new JLabelAjustado(foto);
		pCentral= new JPanel();
		pInfo= new JPanel();
		pbotonera= new JPanel();
		bPrecio = new JButton("MODIFICAR PRECIO");
		bNombre = new JButton("MODIFICAR NOMBRE");
		bFoto = new JButton("MODIFICAR FOTO");
		bvolver = new JButton("VOLVER");

		pCentral.setLayout(new BorderLayout());
		pCentral.add(lfoto, BorderLayout.CENTER);
		pInfo.setLayout(new BorderLayout());
		pInfo.add(nombreProd, BorderLayout.NORTH);
		pInfo.add(precioProd, BorderLayout.SOUTH);
		pCentral.add(pInfo, BorderLayout.SOUTH);
		pbotonera.add(bNombre);
		pbotonera.add(bPrecio);
		pbotonera.add(bFoto);
		
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("PRODUCTO: " + p.getNomP());
		
		pbotonera.setBounds(100, 100, 30, 30);
		
		this.add(bvolver, BorderLayout.NORTH);
		this.add(pCentral, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
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
				VentanaPrincipalAdmin v = new VentanaPrincipalAdmin();
				dispose();	
			}
		});
		
		bNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambioNombre vn= new VentanaCambioNombre(p); 
			}
		});
		
		bPrecio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambioPrecio vp= new VentanaCambioPrecio(p); 
			}
		});
		
		bFoto.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambioFoto vf= new VentanaCambioFoto(p); 
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
		 * @param imagen	Imagen a visualizar en el label
		 */
		public JLabelAjustado( ImageIcon imagen ) {
			setImagen( imagen );
		}
		/** Modifica la imagen
		 * @param imagen	Nueva imagen a visualizar en el label
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
