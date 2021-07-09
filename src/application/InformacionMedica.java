package application;

import java.sql.Timestamp;

public class InformacionMedica {

	private String titulo;
	private Timestamp fechaCreacion;
	private String descripcion;
	private String tratamiento;
	
	public InformacionMedica(String titulo, Timestamp fecha, String descripcion, String tratamiento) {
		this.titulo = titulo;
		this.fechaCreacion = fecha;
		this.descripcion = descripcion;
		this.tratamiento = tratamiento;
	}
	
	//Constructor para tabla 
	public InformacionMedica(String titulo, Timestamp fecha) {
		this.titulo = titulo;
		this.fechaCreacion = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
}
