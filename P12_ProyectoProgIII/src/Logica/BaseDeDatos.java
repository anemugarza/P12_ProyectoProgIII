package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clases.Administrador;
import Clases.Compra;
import Clases.Comprador;
import Clases.Producto;
import Clases.Usuario;


public class BaseDeDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDeDatos" );
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static HashMap<String,Usuario> users = new HashMap<String, Usuario>();
	
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
				String sent = "CREATE TABLE IF NOT EXISTS usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), email varchar(25), contrasenya varchar(25), admin int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
			
				sent = "CREATE TABLE IF NOT EXISTS wl (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS cestas (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );

				sent = "CREATE TABLE IF NOT EXISTS  compra (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario(id), fecha bigint, precio float);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS  compraP (id INTEGER REFERENCES compra(id), idProducto int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
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
				if(rs.getInt("admin")==1) {
					Administrador a =new Administrador(nombre, email, constrasenya );
					a.setCodigoUsuario(id);
					users.put(email,  a);		
				}
				else {
					Comprador c = new Comprador(nombre, email, constrasenya, rs.getInt("admin")) ;
					c.setCodigoUsuario(id);
					users.put(email,c);
				}
			}
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	public static Usuario getUsuarioId(int id) {
		try (Statement statement = conexion.createStatement()){
			String sent = "select * from usuario where id = "+ id+";";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String constrasenya = rs.getString("contrasenya");
				if(rs.getInt("admin")==1) {
					Administrador a =new Administrador(nombre, email, constrasenya );
					a.setCodigoUsuario(id);
					return a;		
				}
				else {
					Comprador c = new Comprador(nombre, email, constrasenya, rs.getInt("admin")) ;
					c.setCodigoUsuario(id);
					return c;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
		return null;
	}
	
	public static void añadirUsuario(String nombre, String email, String contrasenya) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into usuario (nombre, email, contrasenya, admin) values ('" + nombre +"', '" + email + "', '" + contrasenya + "', 0);";
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
	//La función está hecha pero en el log in solo se pueden crear usuarios compradores, los administradores tendrán que ser añadidos a mano
	public static void añadirAdmin(String nombre, String email, String contrasenya) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into usuario (nombre, email, contrasenya, admin) values ('" + nombre +"', '" + email + "', '" + contrasenya + "', 1);";
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
	public static List<Producto> getWLoCesta(int id, int tipo){
		String sent;
		try (Statement statement = conexion.createStatement()){
			switch (tipo) {
			case 0:
				sent = "select idProducto from wl where idUsuario = "+ id+ ";";
				break;
			case 1:
				sent = "select idProducto from cestas where idUsuario = "+ id+ ";";
				break;
			default:
				throw new SQLException("type not defined");
			}
			List<Producto> pl = new ArrayList<>();
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int idP = rs.getInt("idProducto");
				for(Producto p : Logica.productosHistoricos) {
					if(idP==p.getCodigoP()) {
						pl.add(p);
						break;
					}
				}
			}
			return pl;
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static void añadirProducto(int idUsuario, int idProducto, int tipo) throws SQLException {
		String sent="";
		switch (tipo) {
		case 0:
			sent = "insert into wl (idUsuario, idProducto) values ("+idUsuario + ", " + idProducto + ");";
			break;
		case 1:
			sent = "insert into cestas (idProducto, idUsuario) values ("+idUsuario + ", " + idProducto + ");";
			break;
		default:
			throw new SQLException("type not defined");
		}
		try(Statement statement = conexion.createStatement()) {
			
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
	public static void eliminarProducto(int idUsuario, int idProducto, int tipo) throws SQLException {
		String sent="";
		switch (tipo) {
		case 0:
			sent = "delete from wl where idProducto = " + idProducto + " and idUsuario = " + idUsuario + ";";
			break;
		case 1:
			sent = "delete from cestas where idProducto = " + idProducto + " and idUsuario = " + idUsuario + ";";
			break;
		default:
			throw new SQLException("type not defined");
		}
		try(Statement statement = conexion.createStatement()) {
			
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Eliminada " + val + " fila de base de datos\t" + sent );
			
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en eliminación de base de datos\t" + e );
		}
	}
	
	public static int añadirCompra(int idUsuario, long fecha, double precio) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into compra (idUsuario, fecha, precio ) values (" + idUsuario + ",'" + fecha + "', " + (float) precio + ");";
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			sent = "select id from compra order by id desc;";
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int id = rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
		return 0;
	}
	
	public static void añadirCompraP(int idCompra, int idProducto) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into compraP (id, idProducto ) values (" + idCompra + "," + idProducto + ");";
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
	public static HashMap<Integer,Compra> getCompras(int type, long fecha1, long fecha2){
		HashMap<Integer,Compra> mapaCompras = new HashMap<>();
		ArrayList<Producto> ps = new ArrayList<Producto>();
		String sent="";
		try (Statement statement = conexion.createStatement()){
			if(type ==1) sent = "select * from compra where fecha between "+ fecha1+ " and " + fecha2 +";";
			else sent = "select * from compra;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				int idUsuario = rs.getInt("idUsuario");
				long fecha = rs.getLong("fecha");
				float precio = rs.getFloat("precio");
				sent = "select idProducto from compraP where id = "+id + ";";
				logger.log( Level.INFO, "Statement: " + sent );
				ResultSet rs2 = statement.executeQuery( sent );
				while(rs2.next()) {
					int idProducto=rs2.getInt("idProducto");
					for(Producto p : Logica.productosHistoricos) {
						if(idProducto==p.getCodigoP()) {
							ps.add(p);
							break;
						}					}
				}
				Compra c = new Compra(id, getUsuarioId(idUsuario), ps, fecha,(double) precio);
				mapaCompras.put(id, c);
			}
			return mapaCompras;
		} catch (Exception e) {
			// TODO: handle exception
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	
}
