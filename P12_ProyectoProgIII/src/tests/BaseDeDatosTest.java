package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logica.BaseDeDatos;

public class BaseDeDatosTest {
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.abrirConexion("MiBD.db", false);
	}

	@After
	public void tearDown() throws Exception {
		BaseDeDatos.cerrarConexion();
	}

	@Test
	public void testAbrirConexion() {
	}

	@Test
	public void testCerrarConexion() {
	}

	@Test
	public void testGetUsuarios() {
		assertTrue(BaseDeDatos.getUsuarios().keySet().size() > 0);
	}

	@Test
	public void testGetUsuarioId() {
		assertNotNull(BaseDeDatos.getUsuarioId(2));
		assertNull(BaseDeDatos.getUsuarioId(99));
	}

	@Test
	public void testAñadirUsuario() {
		/*int numUsu = BaseDeDatos.getUsuarios().keySet().size();
		BaseDeDatos.añadirUsuario("prueba", "prueba@prueba.com", "prueba");
		assertTrue(BaseDeDatos.getUsuarios().keySet().size() == numUsu+1);
		*/
	}

	@Test
	public void testAñadirAdmin() {
		/*int numUsu = BaseDeDatos.getUsuarios().keySet().size();
		BaseDeDatos.añadirAdmin("prueba", "prueba@prueba.com", "prueba");
		assertTrue(BaseDeDatos.getUsuarios().keySet().size() == numUsu+1);
		*/
	}

	@Test
	public void testGetWLoCesta() {
		assertTrue(BaseDeDatos.getWLoCesta(99, 0).size() == 0);
		assertTrue(BaseDeDatos.getWLoCesta(99, 1).size() == 0);
	}

	@Test
	public void testAñadirProducto() {
		assertThrows(SQLException.class, ()->{BaseDeDatos.añadirProducto(1, 1, 8);});
	}

	@Test
	public void testEliminarProducto() {
		assertThrows(SQLException.class, ()->{BaseDeDatos.eliminarProducto(1, 1, 8);});
	}

	@Test
	public void testAñadirCompra() {
		assertNotEquals(0, BaseDeDatos.añadirCompra(1, System.currentTimeMillis(), 1));
	}

	@Test
	public void testAñadirCompraP() {
		
	}

	@Test
	public void testGetCompras() {
		
	}

}
