package Ventanas;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Comprador;
import Clases.Producto;
import Logica.BaseDeDatos;
import Logica.Logica;

public class VentanaRecursiva extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String NULL = null;
	private JLabel info;
	private JButton banterior;
	private JButton bsiguiente;
	private JPanel pNorte;
	private JButton bvolver;
	private JPanel pbotonera;
	private JLabel s;
	private JList lista;
	private DefaultListModel<Producto> mlista;
	private ArrayList<ArrayList<Producto>> lPuedoComprar = new ArrayList<>();
	private ArrayList<Double> saldos =new ArrayList<Double>();
	private int cont=0;
	Comprador c1 = (Comprador) Logica.getUsuario();

	public VentanaRecursiva(double saldo, ArrayList<ArrayList<Producto>> lPuedoComprar, ArrayList<Double> saldos)  {
		inicializar(saldo);
		this.lPuedoComprar=lPuedoComprar;
		this.saldos=saldos;
	}

	private void inicializar(double saldo) {
		// TODO Auto-generated method stub
		info = new JLabel("OPCIÓN "+ cont);
		s = new JLabel("SALDO RESTANTE: " + saldos.get(cont));
		banterior = new JButton("OPCIÓN ANTERIOR");
		bsiguiente = new JButton("SIGUIENTE OPCIÓN");
		bvolver = new JButton("VOLVER");
		pNorte = new JPanel();
		lista = new JList();
		mlista = new DefaultListModel<Producto>();
		pbotonera = new JPanel();
		
		actualizarLista(cont);
		pbotonera.add(banterior);
		pbotonera.add(bsiguiente);
		pNorte.add(bvolver, BorderLayout.WEST);
		pNorte.add(info, BorderLayout.WEST);
		this.add(pNorte,BorderLayout.NORTH);
		this.add(lista, BorderLayout.CENTER);
		this.add(s, BorderLayout.CENTER);
		this.add(pbotonera, BorderLayout.SOUTH);
		
		pNorte.setBounds(100, 100, 100, 30);
		pNorte.setLayout(new GridLayout(1,3));
		pbotonera.setBounds(100, 100, 100, 30);
				
		//Caracteristicas de la ventana
		//setSize(700,600);
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		setLocationRelativeTo(null);
		setTitle("OPCIONES DE COMPRA");
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
		
		banterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cont==0) JOptionPane.showMessageDialog(null, "No hay opción anterior");
				else actualizarLista(cont-1);
			}
		});
		
		bsiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cont==lPuedoComprar.size()) JOptionPane.showMessageDialog(null, "No hay siguiente opción");
				else actualizarLista(cont+1);
			}
		});			
	}
	
	public void actualizarLista(int cont) {
		for(Producto p: lPuedoComprar.get(cont)) {
			mlista.addElement(p);
		}
		lista.setModel(mlista);
	}
}
