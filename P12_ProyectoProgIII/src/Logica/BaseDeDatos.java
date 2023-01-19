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
	 * @param reiniciaBD true si se quiere reiniciar la base de datos 
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
	
	/** Cierra la conexión de la base de datos ({@link #abrirConexion(String)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	/**
	 * Método para obtener todos los usuarios guardados en la base de datos, tanto compradores como usuarios.
	 * @return devuelve un mapa con la direccion de correo del usuario como clave.
	 */
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
	
	/**
	 * Obtiene un usuario sabiendo su id
	 * @param id el codigo de identificacion por el que buscamos al usuario
	 * @return un usuario administrador o comprador
	 */
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
	
	/**
	 * Añade un usuario comprador nuevo a la base de datos
	 * @param nombre el nombre del usuario 
	 * @param email la direccion de correo del usuario
	 * @param contrasenya la contraseña con la que acceder a dicha direccion de correo
	 */
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
	
	/**
	 * Para añadir nuevos administradores a la base de datos
	 * @param nombre el nombre del administrador
	 * @param email  la direccion de correo del administrador
	 * @param contrasenya a contraseña con la que acceder a dicha direccion de correo
	 * La función está hecha pero en el log in solo se pueden crear usuarios compradores, los administradores tendrán que ser añadidos a mano.
	 */
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
	
	/**
	 * Para obtener la lista de productos que forman la wishlist o cesta del comprador
	 * @param id el código de identificación del usuario
	 * @param tipo el parametro con el que indicaremos que queremos obtener, la wishlist(0) o la compra(1).
	 * @return la lista de los productos actualmente en cesta o wishlist
	 */
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
	
	/**
	 * Para añadir un nuevo producto a la cesta o la wishlist dentro de la base de datos
	 * @param idUsuario el código de identificación del usuario a cuya cesta/wishlist se añadirá
	 * @param idProducto  el código de identificación del producto 
	 * @param tipo el parametro con el que indicaremos donde queremos guardar el producto, la wishlist(0) o la compra(1).
	 * @throws SQLException indicará error en caso de no haber podido guardar correctamente el producto
	 */
	public static void añadirProducto(int idUsuario, int idProducto, int tipo) throws SQLException {
		String sent="";
		switch (tipo) {
		case 0:
			sent = "insert into wl (idUsuario, idProducto) values ("+idUsuario + ", " + idProducto + ");";
			break;
		case 1:
			sent = "insert into cestas (idUsuario, idProducto) values ("+idUsuario + ", " + idProducto + ");";
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
	
	/**
	 * Para borrar un producto de la cesta o la wishlist
	 * @param idUsuario el código de identificación del usuario de cuya cesta/wishlist se eliminará el producto
	 * @param idProducto  el código de identificación del producto
	 * @param tipo el parametro con el que indicaremos de donde queremos borrar el producto, la wishlist(0) o la compra(1).
	 * @throws SQLException indicará error en caso de no haber podido borrar correctamente el producto
	 */
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
	
	/**
	 * Método para añadir una nueva compra a la base de datos una vez esta es confirmada por el usuario.
	 * @param idUsuario el codigo de identificacion del usuario cuya compra se añadirá a la base de datos
	 * @param fecha la fecha del dia en que esta compra es realizada
	 * @param precio el coste total de la compra
	 * @return valor 0
	 */
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
	
	/**
	 * Para añadir los productos que se han adquirido en una compra.
	 * @param idCompra codigo de identificacion de la compra, que servirá para relacionar los productos de una misma compra.
	 * @param idProducto el codigo de identificacion del producto
	 */
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
	
	/**
	 * Obtener las compras registradas en la base de datos.
	 * @param type parametro que indicará si queremos obtener una fecha hecha entre ciertas fechas(1) o no.
	 * @param fecha1 la fecha que indica el principio del periodo de tiempo en el que buscamos una compra
	 * @param fecha2 la fecha que indica el final del periodo de tiempo en el que buscamos una compra
	 * @return un mapa de las compras con el codigo de identificacion (id) de cada compra como clave.
	 */
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
