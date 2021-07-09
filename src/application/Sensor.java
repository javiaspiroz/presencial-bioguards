package application;


public class Sensor {
	private String Id;
	private String Tipo;
	private String Fecha;
	private String Hora;
	private double Valor;
	
	// GETTERS Y SETTERS
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public double getValor() {
		return Valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}
	
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public String toString() {
        return "Sensores [id=" + Id + ", Tipo=" + Tipo +", Fecha=" + Fecha + "Hola=" + Hora + ", Valor=" + Valor+ "]";
    }
}