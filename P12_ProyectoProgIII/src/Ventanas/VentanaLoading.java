package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class VentanaLoading extends JFrame{
	private JPanel pTitulo,pCentro;
	private JLabel lblTitulo;
	private JProgressBar pb;
	public VentanaLoading(JFrame va) {
		super();
		setBounds(va.getX()+10,va.getY()+10,200, 100);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
