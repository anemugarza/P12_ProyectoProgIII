package Clases;

import java.util.HashMap;
import java.util.Map;

public class Articulo {
	int codigoArticulo;
	String nomArticulo;
	double precio;
	public Map<Articulo, Integer> articulosHistoricos = new HashMap<>();
	//si no hacemos almacen (pedidos de tienda), lo hacemos con una lista
}
