package Clases;

import java.util.HashMap;
import java.util.Map;

public class Administrador extends Usuario {
	int salario;
	Map<Long, Analisis> analisis = new HashMap<>();

	public Administrador(String nomUsuario, String email, String contrasenya, int salario) {
		super(nomUsuario, email, contrasenya, 1);
		this.salario = salario;
	}

	public Administrador(String nomUsuario, String email, String contrasenya) {
		super(nomUsuario, email, contrasenya, 1);
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public Map<Long, Analisis> getAnalisis() {
		return analisis;
	}

	public void setAnalisis(HashMap<Long, Analisis> analisis) {
		this.analisis = analisis;
	}

	@Override
	public String toString() {
		return "Administrador [salario=" + salario + ", analisis=" + analisis + "]";
	}
	
	
}
