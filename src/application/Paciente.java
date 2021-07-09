package application;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Vector;

public class Paciente extends Perfil {

    private int numero_paciente;
    private String[] sensores;
    private String clinico; 
    private String[] cuidador_familiar;
    private Vector<Vector<String>> chat; 

	public Paciente(String nombre, String apellido, String dni, String email, Timestamp fecha, String direccion, String ciudad,
			 		String provincia, int codigoPostal, int tel, int numero_paciente, String[] sensores, String clinico, String[] cuidador_familiar, 
			 		Vector<Vector<String>> chat) {
		super(nombre, apellido, dni, email, fecha, direccion, ciudad, provincia, codigoPostal, tel);
		this.numero_paciente = numero_paciente;
		this.sensores = sensores;
		this.clinico = clinico;
		this.cuidador_familiar = cuidador_familiar;
		this.chat = chat;
	}

	//Constructor para tabla pacientes
	public Paciente(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}


	public Paciente(String nombre, String apellidos, String dni,String direccion) {
		super (nombre, apellidos, dni, direccion);
	}

	// Getters y setters
	public int getNumero_paciente() {
		return numero_paciente;
	}

	public void setNumero_paciente(int numero_paciente) {
		this.numero_paciente = numero_paciente;
	}
	
	public String[] getSensores() {
		return sensores;
	}

	public void setSensores(String[] sensores) {
		this.sensores = sensores;
	}

	public String getClinico() {
		return clinico;
	}

	public void setClinico(String clinico) {
		this.clinico = clinico;
	}
	
	public String[] getCuidador_familiar() {
		return cuidador_familiar;
	}

	public void setCuidador_familiar(String[] cuidador_familiar) {
		this.cuidador_familiar = cuidador_familiar;
	}
	
	public Vector<Vector<String>> getChat() {
		return chat;
	}

	public void setChat(Vector<Vector<String>> chat) {
		this.chat = chat;
	}

	@Override
	public String toString() {
		return "Paciente [numero_paciente=" + numero_paciente + ", sensores=" + Arrays.toString(sensores) + ", clinico="
				+ clinico + ", cuidador_familiar=" + Arrays.toString(cuidador_familiar) + ", chat=" + chat + "]";
	}
}
