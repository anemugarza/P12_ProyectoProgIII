package Clases;
/**
 * Clase para la gestion de los productos que pertenecen al apartado de material escolar
 */

public class MaterialEscolar extends Producto {
	
	private static final long serialVersionUID = 1L;

	public MaterialEscolar(String nomP, double precio, String foto) {
		super(nomP, precio, foto);
	}

	@Override
	public String toString() {
		return "MaterialEscolar [nomP=" + nomP + ", precio=" + precio + "€ ]";
	}
}
