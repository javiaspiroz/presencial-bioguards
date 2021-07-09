package application;

import java.sql.Timestamp;

public class Clinico extends Perfil{

    private String especialidad;
	private String[] pacientes;
  
	public Clinico(String nombre, String apellido, String dni, String email, Timestamp fecha, String direccion, String ciudad, 
				   String provincia, int codigoPostal, int tel, String especialidad, String[] pacientes) {
    	super(nombre, apellido, dni, email, fecha, direccion, ciudad, provincia, codigoPostal, tel);
		this.especialidad = especialidad;
		this.pacientes = pacientes;
	}

	
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String[] getPacientes() {
		return pacientes;
	}

	public void setPacientes(String[] pacientes) {
		this.pacientes = pacientes;
	}
}
