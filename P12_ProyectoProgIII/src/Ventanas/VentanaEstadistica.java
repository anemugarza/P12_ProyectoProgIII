package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Producto;
import Logica.Logica;


public class VentanaEstadistica extends JFrame{
	//Componentes de la ventana
	private static final long serialVersionUID = 1L;
	private JButton bvolver;
	private JButton bnuevaE;
	private JButton bcrearEstadis;
	private JTextField txtatr1;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField txtatr2;
	private JLabel atr1;
	private JLabel atr2;
	private JLabel result;
	private JPanel pCentral;
	private JPanel p1;
	private JPanel p2;
	private JPanel pResultado;
	private JPanel pfechas;
	private JPanel pNorte;
	private JComboBox<String> jcbProd;
	DecimalFormat df = new DecimalFormat("#.00");

    //Inicio de ventana
	public VentanaEstadistica(String op) throws ParseException  {
		inicializar(op);
	}
	 /**
     * Para inicializar la ventana                   
     * @param op parametro con el que indicaremos que tipo de estadística 
     * queremos que se nos muestre por pantalla.
     * @throws ParseException señala que se ha producido un error inesperado durante el análisis.
     */
	private void inicializar(String op) throws ParseException {
		// Atributos generales
		bvolver = new JButton("VOLVER");
		bcrearEstadis= new JButton("CREAR ESTADISTICA");
		pCentral = new JPanel();
		bnuevaE = new JButton("NUEVA ESTADÍSTICA");
		pfechas = new JPanel();
		pNorte = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		pResultado = new JPanel();
		result = new JLabel();
		result.setBackground(new Color(0, 0, 255));
		
		//Dependiendo de la estadistica se crea un panel central u otro
		switch (op) {
		case "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS":
			atr1 = new JLabel("Introduzca la fecha inicial: ");
			atr2 = new JLabel("Introduzca la fecha final: ");
			txtatr1 = new JTextField(sdf.format(System.currentTimeMillis()-24*3600000L));
			txtatr2 = new JTextField(sdf.format((System.currentTimeMillis()+24*3600000L)));
			break;
		case "GASTO MEDIO DE CLIENTES EN UN MES":
			atr1 = new JLabel("Introduzca el año: ");
			atr2 = new JLabel("Introduzca el mes: ");
			txtatr1 = new JTextField("2023");
			txtatr2 = new JTextField("01");
			break;
		default:
			atr1 = new JLabel("Introduzca el año: ");
			atr2 = new JLabel("Introduzca el mes: ");
			txtatr1 = new JTextField("2023");
			txtatr2 = new JTextField("01");
			jcbProd = new JComboBox<String>();
			for(Producto p : Logica.productosHistoricos) jcbProd.addItem(p.getNomP());
			break;
		}
		//Se añaden los componentes a los paneles
		this.add(bvolver, BorderLayout.NORTH);
		this.add(bnuevaE, BorderLayout.SOUTH);
		pfechas.add(atr1);
		pfechas.add(txtatr1);
		pfechas.add(atr2);
		pfechas.add(txtatr2);
		pfechas.setLayout(new GridLayout(2,2));
		pNorte.add(pfechas);
		if(op.equals("CANTIDAD VENDIDA DE UN PRODUCTO EN UN MES"))pNorte.add(jcbProd);
		pNorte.add(bcrearEstadis);
		pResultado.add(p1);
		pResultado.add(result);
		pResultado.add(p2);
		pCentral.setLayout(new GridLayout(2,1));
		pCentral.add(pNorte);
		pCentral.add( pResultado, BorderLayout.CENTER );
		
		this.add(pCentral, BorderLayout.CENTER);
		
		setSize(650,300);
		setLocationRelativeTo(null);
		setTitle("ESTADÍSTICA: " + op);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando");
				System.exit(0);
			}
		});
		//Para retroceder a la página principal
		bvolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalAdmin ventana= new VentanaPrincipalAdmin(); 
				dispose();	
			}
		});
		
		//Para mostrar un tipo de  estadistica de entre los tres que hay
		//(producto más vendido entre dos fechas, gasto medio de clientes en un mes y la cantidad vendida de un prod en un mes) 
		//según el op indicado en el metodo inicializar().
		bcrearEstadis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (op) {
				case "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS":
					try {
						result.setText(Logica.productoMasVendido((sdf.parse(txtatr1.getText())).getTime(), (sdf.parse(txtatr2.getText())).getTime()).getNomP().toUpperCase());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "GASTO MEDIO DE CLIENTES EN UN MES":
					result.setText(String.valueOf(df.format(Logica.gastoMedio(txtatr1.getText(), txtatr2.getText())))+ "€");
					break;
				case "CANTIDAD VENDIDA DE UN PRODUCTO EN UN MES":
					result.setText(String.valueOf(Logica.cantProdEnUnMes(txtatr1.getText(), txtatr2.getText(), (String)jcbProd.getSelectedItem()) + " unidades vendidas"));
					break;
				default:
					break;
				}
			}
		});
		//Para que muestre por pantalla otra estadistica del tipo que hayamos escogido nosotros en la OptionPane.
		bnuevaE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono = new ImageIcon("");
				String op = (String) JOptionPane.showInputDialog(null, "OPCIONES DE ESTADÍSTICAS",
		                "OPCIONES DE ESTADÍSTICAS", JOptionPane.QUESTION_MESSAGE,
		                icono, new Object[] { "PRODUCTO MÁS VENDIDO ENTRE DOS FECHAS","GASTO MEDIO DE CLIENTES EN UN MES", "CANTIDAD VENDIDA DE UN PRODUCTO EN UN MES" },
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
