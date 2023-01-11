package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Logica.Logica;


public class VentanaEstadistica extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bvolver;
	private JButton bnuevaE;
	private JButton bcrearEstadis;
	private JTextField txtatr1;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField txtatr2;
	private JLabel atr1;
	private JLabel atr2;
	private JLabel prodMasVend;
	private JPanel pCentral;
	private JPanel pfechas;
	private JPanel pNorte;
                               
	public VentanaEstadistica(String op) throws ParseException  {
		inicializar(op);
	}

	private void inicializar(String op) throws ParseException {
		// Atributos generales
		bvolver = new JButton("VOLVER");
		bcrearEstadis= new JButton("CREAR ESTADISTICA");
		pCentral = new JPanel();
		bnuevaE = new JButton("NUEVA ESTADÍSTICA");
		pfechas = new JPanel();
		pNorte = new JPanel();
		
		//Dependiendo de la estadistica se crea un panel central u otro
		switch (op) {
		case "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS":
			atr1 = new JLabel("Introduzca la fecha inicial: ");
			atr2 = new JLabel("Introduzca la fecha final: ");
			txtatr1 = new JTextField(sdf.format(System.currentTimeMillis()-24*3600000L));
			txtatr2 = new JTextField(sdf.format((System.currentTimeMillis()+24*3600000L)));
			prodMasVend = new JLabel();
			//esto hay que ponerlo en el medio
			pCentral.add(prodMasVend);
			break;
		case "GASTO MEDIO DE CLIENTES EN UN MES":
			atr1 = new JLabel("Introduzca el año: ");
			atr2 = new JLabel("Introduzca el mes: ");
			txtatr1 = new JTextField("2023");
			txtatr2 = new JTextField("02");
			break;
		default:
			atr1 = new JLabel("Introduzca el año: ");
			atr2 = new JLabel("Introduzca el mes: ");
			txtatr1 = new JTextField("2023");
			txtatr2 = new JTextField("02");
			break;
		}
		this.add(bvolver, BorderLayout.NORTH);
		this.add(bnuevaE, BorderLayout.SOUTH);
		pfechas.add(atr1);
		pfechas.add(txtatr1);
		pfechas.add(atr2);
		pfechas.add(txtatr2);
		pfechas.setLayout(new GridLayout(2,2));
		pNorte.add(pfechas);
		pNorte.add(bcrearEstadis);
		pCentral.setLayout(new BorderLayout());
		pCentral.add(pNorte, BorderLayout.NORTH);
		this.add(pCentral, BorderLayout.CENTER);
		
		setSize(600,400);
		setLocationRelativeTo(null);
		setTitle("ESTADÍSTICA: " + op);
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
		
		bcrearEstadis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (op) {
				case "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS":
					try {
						prodMasVend.setText(Logica.productoMasVendido((sdf.parse(txtatr1.getText())).getTime(), (sdf.parse(txtatr2.getText())).getTime()).getNomP());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "GASTO MEDIO DE CLIENTES EN UN MES":
					
					break;
				default:
					
					break;
				}
				
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
				try {
					VentanaEstadistica ventana= new VentanaEstadistica(op);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				dispose();
			}
		});
	}
}
