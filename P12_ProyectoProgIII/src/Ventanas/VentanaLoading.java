package Ventanas;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class VentanaLoading extends JFrame{
	/**
	 * Ventana en la que se nos mostrará una progressbar indicando que la página web se esta cargando.
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pTitulo,pCentro;
	private JLabel lblTitulo;
	private JProgressBar pb;
	/**
	 * Genera la ventana loading
	 * @param va es la JFrame creada a base de la ventanaLogin
	 */
	public VentanaLoading(JFrame va) {
		super();
		setBounds(va.getX()+10,va.getY()+10,200, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		pTitulo = new JPanel();
		pCentro = new JPanel();
		getContentPane().add(pTitulo,BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		lblTitulo = new JLabel("LOADING...");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		pTitulo.add(lblTitulo);
		JFrame v = this;
		pb = new JProgressBar(0, 100);
		pb.setValue(0);
		pCentro.add(pb);
		Runnable r = new Runnable() {
		/**
		 * Este metodo  constituye el cuerpo del hilo que necesitamos 
		 * 		para que la progressbar funcione, en ejecución.
		 */

			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					pb.setValue(pb.getValue()+1);
					try {
						Thread.sleep((int) Math.random()*(100-50+1)+50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				v.dispose();
				VentanaPrincipal ventana= new VentanaPrincipal();
			}
		};
		Thread t =  new Thread(r);
		t.start();
	}
	
}
