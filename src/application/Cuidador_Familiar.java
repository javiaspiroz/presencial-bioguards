package application;

import java.sql.Timestamp;

public class Cuidador_Familiar extends Perfil{
     
	private String [] pacientes;

	public Cuidador_Familiar(String nombre, String apellido, String dni, String email, Timestamp fecha, String direccion,  
			String ciudad, String provincia, int codigoPostal, int tel, String[] pacientes) {
		super(nombre, apellido, dni, email, fecha, direccion, ciudad, provincia, codigoPostal, tel);
		this.pacientes = pacientes;
	}

	public String[] getPacientes() {
		return pacientes;
	}

	public void setPaciente(String[] pacientes) {
		this.pacientes = pacientes;
	}
}
