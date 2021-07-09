package application;

import java.sql.Timestamp;

public class Administrador extends Perfil {

    private int tipo; // 1- empresa; 2- particular
	

	public Administrador(String nombre, String apellido, String dni, String email, Timestamp fecha, 
						 String direccion, String ciudad, String provincia, int codigoPostal,int tel, int tipo) {
		super(nombre, apellido, dni, email, fecha, direccion, ciudad, provincia, codigoPostal, tel);
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int t) {
		tipo=t;
	}
	public String toString() {
    	String salida = "Administrador: ( "+super.getApellidos()+", "+ super.getFechaNacimiento();
    	return salida;
    }
}
