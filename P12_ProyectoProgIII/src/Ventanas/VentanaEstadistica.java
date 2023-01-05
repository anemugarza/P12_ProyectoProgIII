package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class VentanaEstadistica extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bvolver;
	private JButton bnuevaE;
	
	public VentanaEstadistica(String op)  {
		inicializar(op);
	}

	private void inicializar(String op) {
		// TODO Auto-generated method stub
		bvolver = new JButton("VOLVER");
		bnuevaE = new JButton("NUEVA ESTADÍSTICA");
		
		this.add(bvolver, BorderLayout.NORTH);
		this.add(bnuevaE, BorderLayout.SOUTH);
		
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("ESTADISTICAS");
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
				VentanaPrincipalAdmin ventana= new VentanaPrincipalAdmin(); 
				dispose();	
			}
		});
		
		bnuevaE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono = new ImageIcon("");
				String op = (String) JOptionPane.showInputDialog(null, "OPCIONES DE ESTADÍSTICAS",
		                "OPCIONES DE ESTADÍSTICAS", JOptionPane.QUESTION_MESSAGE,
		                icono, new Object[] { "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS","GASTO MEDIO DE CLIENTES EN UN MES", "DÍA DE LA SEMANA QUE MÁS SE COMPRA" },
		                "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS");
				VentanaEstadistica ventana= new VentanaEstadistica(op); 
				dispose();
			}
		});
	}
}
