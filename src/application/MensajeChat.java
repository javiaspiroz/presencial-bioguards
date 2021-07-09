package application;

import java.sql.Timestamp;

public class MensajeChat {

	String dniEmisor;
	String dniReceptor;
	Timestamp fechaMensaje;
	String mensaje;
	
	public MensajeChat(String dniEmisor, String dniReceptor, Timestamp fechaMensaje, String mensaje) {
		super();
		this.dniEmisor = dniEmisor;
		this.dniReceptor = dniReceptor;
		this.fechaMensaje = fechaMensaje;
		this.mensaje = mensaje;
	}
	
	public String getDniEmisor() {
		return dniEmisor;
	}
	public void setDniEmisor(String dniEmisor) {
		this.dniEmisor = dniEmisor;
	}
	public String getDniReceptor() {
		return dniReceptor;
	}
	public void setDniReceptor(String dniReceptor) {
		this.dniReceptor = dniReceptor;
	}
	public Timestamp getFechaMensaje() {
		return fechaMensaje;
	}
	public void setFechaMensaje(Timestamp fechaMensaje) {
		this.fechaMensaje = fechaMensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
