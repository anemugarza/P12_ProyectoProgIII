package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clases.Administrador;
import Clases.Comprador;
import Clases.Producto;
import Clases.Usuario;


public class BaseDeDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDeDatos" );
	private static HashMap<String,Usuario> users = new HashMap<String, Usuario>();
	private static HashMap<String,Producto> prods = new HashMap<String, Producto>();
	
	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return	true si la conexión ha sido correcta, false en caso contrario
	 */
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXISTS producto";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, clase varchar(100));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS usuario";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), email varchar(25), contrasenya varchar(25), admin int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS wl";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE wl (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto INTEGER REFERENCES producto(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS analisis";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE analisis (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha bigint, datos varchar(100), idUsuario KEY REFERENCES usuario (id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS cestas";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE cestas (id INTEGER PRIMARY KEY AUTOINCREMENT, idProducto INTEGER REFERENCES producto (id), idUsuario INTEGER REFERENCES usuario(id), totalPrecio int(10));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				/*try {
					Scanner scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("productos-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into producto (id, nombre, precio) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("compras-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into compra (id, idProducto, cliente, fecha, cantidad) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + "'," + datos[3] + "," + datos[4] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
				} catch(Exception e) {
					logger.log( Level.SEVERE, "Excepción", e );
				}*/
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}	
	
	/** Cierra la conexión abierta de base de datos ({@link #abrirConexion(String)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	public static HashMap<String,Usuario> getUsuarios(){
		try (Statement statement = conexion.createStatement()){
			
			String sent = "select * from usuario;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String constrasenya = rs.getString("contrasenya");
				if(rs.getInt("admin")==1) users.put(email, new Administrador( id, nombre, email, constrasenya ) );		
				else users.put(email,new Comprador(id, nombre, email, constrasenya ) );
				}
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}

	
	/*PRODUCTO COMO VAMOS A GUARDARLO EN UN .dat o csv NOSE SI HACE FALTA ESTO
	 * public static HashMap<String,Producto> getProductos(){
		try (Statement statement = conexion.createStatement()){
			
			String sent = "select * from producto;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				String clase = rs.getString("clase");
			}
			return prods;
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}*/
}
