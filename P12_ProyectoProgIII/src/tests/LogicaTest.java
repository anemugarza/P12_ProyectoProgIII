package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Usuario;
import Logica.BaseDeDatos;
import Logica.Logica;
public class LogicaTest {

	@Before
	public void setUp() throws Exception {
		BaseDeDatos.abrirConexion("MiBD.db", false);
		Logica.cargarProductos("Productos.dat");
	}

	@After
	public void tearDown() throws Exception {
		BaseDeDatos.cerrarConexion();
	}

	@Test
	public void testExisteUsuario() {
		boolean resul = Logica.existeUsuario("a@a.com");
		assertTrue(resul);
		resul = Logica.existeUsuario("b@b.com");
		assertFalse(resul);
	}

	@Test
	public void testUsuarioCorrecto() {
		Usuario u = Logica.usuarioCorrecto("a@a.com", "a");
		assertNotNull(u);
		u = Logica.usuarioCorrecto("a@a.com", "b");
		assertNull(u);
	}

}
