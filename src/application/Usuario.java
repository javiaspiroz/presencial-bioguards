package application;

public class Usuario {

	private String usuario;
	private String contrasena;
	private String rol;

	public Usuario(String usuario, String contrasena, String rol) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;		
	}
	
	// GETTERS Y SETTERS
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}