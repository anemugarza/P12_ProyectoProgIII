package Ventanas;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Clases.Comprador;
import Clases.Producto;
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
	private JButton bcrearCesta;
	private JPanel pbotonera;
	private JLabel s;
	private JList<Producto> lista;
	private DefaultListModel<Producto> mlista;
	private ArrayList<ArrayList<Producto>> lPuedoComprar;
	private ArrayList<Double> saldos;
	private int cont=0;
	Comprador c1 = (Comprador) Logica.getUsuario();

	public VentanaRecursiva(double saldo, ArrayList<ArrayList<Producto>> lPuedoComprar, ArrayList<Double> saldos)  {
		this.lPuedoComprar=lPuedoComprar;
		this.saldos=saldos;
		inicializar(saldo);
	}

	private void inicializar(double saldo) {
		// TODO Auto-generated method stub
		info = new JLabel("OPCIÓN: "+ (cont+1));
		s = new JLabel("");
		banterior = new JButton("OPCIÓN ANTERIOR");
		bsiguiente = new JButton("SIGUIENTE OPCIÓN");
		bvolver = new JButton("VOLVER");
		bcrearCesta = new JButton("CREAR CESTA");
		pNorte = new JPanel();
		lista = new JList<Producto>();
		mlista = new DefaultListModel<Producto>();
		pbotonera = new JPanel();
		
		actualizarLista(cont);
		pbotonera.add(banterior);
		pbotonera.add(bsiguiente);
		pbotonera.add(bcrearCesta);
		pNorte.add(bvolver, BorderLayout.WEST);
		pNorte.add(info, BorderLayout.WEST);
		pNorte.add(s, BorderLayout.WEST );
		pbotonera.add(s, BorderLayout.NORTH);
		ScrollPane panelLista = new ScrollPane();
		panelLista.add(lista);
		getContentPane().add(pNorte,BorderLayout.NORTH);
		getContentPane().add(panelLista, BorderLayout.CENTER);
		getContentPane().add(pbotonera, BorderLayout.SOUTH);
		
		setSize(800,600);
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
				if(cont<=0) JOptionPane.showMessageDialog(null, "No hay opción anterior");
				else {
					cont--;
					info.setText("OPCIÓN:"+(cont+1));
					actualizarLista(cont);
				}
			}
		});
		
		bsiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cont>=lPuedoComprar.size()-1) JOptionPane.showMessageDialog(null, "No hay siguiente opción");
				else {
					cont++;
					info.setText("OPCIÓN:"+(cont+1));
					actualizarLista(cont);
				}
			}
		});	
		
		bcrearCesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Se creará una cesta con esta opción. ¿Desea realizar esta operación?");
				switch (op) {
				case 0:
					c1.getCesta().removeAll(c1.getCesta());
					for( Producto p: lPuedoComprar.get(cont)) {
						c1.anyadirCesta(p);
					}
					break;
				default:
					break;
				}
			}
		});	
	}
	
	public void actualizarLista(int cont) {
		mlista.removeAllElements();
		for(Producto p: lPuedoComprar.get(cont)) {
			mlista.addElement(p);
		}
		DecimalFormat df = new DecimalFormat("0.00");
		s.setText("SALDO RESTANTE: " + df.format(saldos.get(cont)));
		lista = new JList(mlista);
	}
}
