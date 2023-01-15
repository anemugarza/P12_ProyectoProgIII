package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Clases.Comprador;
import Logica.BaseDeDatos;
import Logica.Logica;

public class VentanaLogIn extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
		//Componentes del Log In
		private JLabel email;
		private JTextField txtemail;
		private JLabel contrasenya;
		private JPasswordField txtcontrasenya;
		private JButton registrarse;
		private JButton entrar;
		private JPanel panelCentral;
		private JPanel botonera;
		
		//Componentes del registro, de momento ocultos
		private JLabel infoCuenta;
		private JLabel nombreRegistro;
		private JTextField txtnombreRegistro;
		private JLabel emailRegistro;
		private JTextField txtemailRegistro;
		private JLabel contrasenyaRegistro;
		private JTextField txtcontrasenyaRegistro;
		private JPanel datosCuenta;
		private JButton guardarDatos;
		
		private JPanel botoneraRegistro;
		
		public VentanaLogIn()  {
			inicializar();
		}
		
		private void inicializar() {
			JFrame v = this;
			//Inicializamos elementos 
			email= new JLabel("Email: ");
			contrasenya= new JLabel("Contraseña: ");
			txtemail= new JTextField(25);
			txtcontrasenya= new JPasswordField(25);
			registrarse= new JButton("Registrarse");
			entrar= new JButton("Entrar");
			panelCentral= new JPanel();
			botonera= new JPanel();
			registrarse.setFont(new Font("Serif", Font.PLAIN, 20));
			entrar.setFont(new Font("Serif", Font.PLAIN, 20));
			infoCuenta=new JLabel("DATOS DE LA CUENTA COMPRADOR");
			nombreRegistro= new JLabel("Nombre: ");
			txtnombreRegistro= new JTextField(25);
			emailRegistro= new JLabel("Email: ");
			txtemailRegistro= new JTextField(25);
			contrasenyaRegistro= new JLabel("Contraseña: ");
			txtcontrasenyaRegistro= new JTextField(25);
			datosCuenta= new JPanel();
			botoneraRegistro= new JPanel();
			guardarDatos= new JButton("Registrarse");
			
			//Añadimos a la ventana
			panelCentral.add(email);
			panelCentral.add(txtemail);
			panelCentral.add(contrasenya);
			panelCentral.add(txtcontrasenya);
			botonera.add(registrarse);
			botonera.add(entrar);
			
			//Dejamos añadidos los elementos a los paneles que visibilizaremos después
			datosCuenta.add(nombreRegistro);
			datosCuenta.add(txtnombreRegistro);
			datosCuenta.add(emailRegistro);
			datosCuenta.add(txtemailRegistro);
			datosCuenta.add(contrasenyaRegistro);
			datosCuenta.add(txtcontrasenyaRegistro);
			datosCuenta.setLayout(new GridLayout(3,2));
			
			botoneraRegistro.add(guardarDatos);
			botoneraRegistro.setLayout(new GridLayout(1,2));
			
			//Caracteristicas de la ventana
			setSize(500,200);
			setLocationRelativeTo(null);
			setTitle("DEUSTOSHOP LOG IN");
			getContentPane().setLayout(new GridLayout(2,1));
			panelCentral.setLayout(new GridLayout(2,2));
			this.add(panelCentral);
			this.add(botonera);
			setVisible(true);
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.out.println("Cerrando");
					System.exit(0);
				}
			});
			
			registrarse.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					txtemail.setText("");
					txtcontrasenya.setText("");
					setSize(700,200);
					setTitle("REGISTRO");
					getContentPane().removeAll();
					getContentPane().add(infoCuenta,BorderLayout.CENTER);
					getContentPane().add(datosCuenta);
					getContentPane().add(botoneraRegistro);
					getContentPane().setLayout(new FlowLayout());
				}
			});
			
			entrar.addActionListener(new ActionListener() {
				//si es admin entre a la ventana del admin
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!txtemail.getText().equals("") && !txtcontrasenya.getText().equals("")) {
						if(Logica.existeUsuario(txtemail.getText())) {
							if(Logica.usuarioCorrecto(txtemail.getText(), txtcontrasenya.getText())!=null){
								if(Logica.UsuarioComprador(txtemail.getText())){
									((Comprador) Logica.getUsuario()).setWl(BaseDeDatos.getWLoCesta(Logica.getUsuario().getCodigoUsuario(), 0));
									((Comprador) Logica.getUsuario()).setCesta(BaseDeDatos.getWLoCesta(Logica.getUsuario().getCodigoUsuario(), 1));
									VentanaLoading vl =  new VentanaLoading(v);
									dispose();
									vl.setVisible(true);
								}else {
									VentanaPrincipalAdmin ventana = new VentanaPrincipalAdmin();
									dispose();
								}
								}else JOptionPane.showMessageDialog(null, "ERROR: Contraseña incorrecta. Vuelva a intentarlo");
							}else JOptionPane.showMessageDialog(null, "ERROR: No existe ninguna cuenta con ese email. REGISTRESE");
						}else JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los datos");
					txtemail.setText("");
					txtcontrasenya.setText("");
				}
			});
			
			guardarDatos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(Logica.existeUsuario(txtemail.getText())) {
						JOptionPane.showMessageDialog(null, "ERROR: Ya existe una cuenta con ese email. Utilice otro");
						txtemailRegistro.setText("");
					}
					if (!txtemailRegistro.getText().equals("")  && !txtcontrasenyaRegistro.getText().equals("") ){
						String er = "[a-zA-Z]{1,}.{0,}[a-zA-Z]{0,}@[a-zA-Z]{1,}.[a-z]{2,}";
						String email = txtemailRegistro.getText();
						if(Pattern.matches(er, email)) {
							Logica.crearUsuario(txtnombreRegistro.getText(),txtemailRegistro.getText(), txtcontrasenyaRegistro.getText()); 
							//aqui hay que añadir el codigo tanto arriba como abajo
							BaseDeDatos.añadirUsuario(txtnombreRegistro.getText(),txtemailRegistro.getText(), txtcontrasenyaRegistro.getText());
							//Después de que se guarden todos los datos, vuelve a la ventana del Log In para poder entrar con la cuenta creada
							txtemailRegistro.setText("");
							txtcontrasenyaRegistro.setText("");
							getContentPane().removeAll();
							setSize(400,200);
							setTitle("DEUSTOSHOP LOG IN");
							getContentPane().setLayout(new GridLayout(2,1));
							panelCentral.setLayout(new GridLayout(2,2));
							getContentPane().add(panelCentral);
							getContentPane().add(botonera);
							setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "ERROR: El formato del email no es correcto");
					}else JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos");
				}
			});
			setVisible(true);
		}
		
}
