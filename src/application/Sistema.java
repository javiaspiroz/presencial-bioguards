package application;

import java.util.Vector;
import controlador.FuncionesAuxiliares;

public class Sistema {
	
	private static Sistema fUnico;
	private Usuario fUsuarioLogueado;
	private Vector<Usuario> fListaUsuarios;
	private Vector<Administrador> fListaAdministradores;
	private Vector<Clinico> fListaClinicos;
	private Vector<Paciente> fListaPacientes;
	private Vector<Cuidador_Familiar> fListaCuidadores_Familiares;
	private Vector<Sensor> fListaSensores;
	private Vector<MensajeChat> fChats;

	/**
    * Constructor de la clase.
    * Nota: Es privado porque se instancia al inicio de la aplicación y solo puede haber 
    * 		un objeto Sistema único en todo el contexto de la aplicación.
    */
	private Sistema() {	}
	
	/**
	* M�todo para acceder al objeto Ãºnico de sistema.
	* @return objeto único de sistema
	*/
	public static Sistema getUnico() {
		if (fUnico == null)
		{
			fUnico = new Sistema();
		}
		return fUnico;
	}
	
	

	public void inicializar() {
		cargarDatos();
	}
	
	public boolean loginUsuario(String login, String password) {
		boolean resultado = false;
		Vector<Usuario> usr = Sistema.getUnico().fListaUsuarios;
		for(Usuario u: usr) {
			if (login.equals(u.getUsuario()) && password.equals(u.getContrasena())) {
				resultado = true;
				Sistema.getUnico().fUsuarioLogueado = u;
				break;
			}
		}
		return resultado;
	}
	
	public void logoutUsuario() {
		Sistema.getUnico().fUsuarioLogueado = null;
	}
	
	
	// ------------------ CARGAR FICHEROS ------------------
	private boolean cargarDatos() {
		fListaUsuarios= FuncionesAuxiliares.desserializarJsonAArrayUsuarios();
		fListaAdministradores= FuncionesAuxiliares.desserializarJsonAArrayAdmin();
		fListaClinicos= FuncionesAuxiliares.desserializarJsonAArrayClinicos();
		fListaPacientes= FuncionesAuxiliares.desserializarJsonAArrayPacientes();
		fListaCuidadores_Familiares= FuncionesAuxiliares.desserializarJsonAArrayCuidadores_Familiares();
		fListaSensores= FuncionesAuxiliares.desserializarJsonAArraySensores();
		fChats = FuncionesAuxiliares.desserializarJsonAArrayChats();
		return fListaUsuarios != null && fListaAdministradores != null && fListaClinicos != null && fListaPacientes != null
			   && fListaCuidadores_Familiares != null && fListaSensores != null && fChats != null;
	}
	
	// ------------------ GETTERS Y SETTERS ------------------
	public Usuario getUsuarioLogueado() {
		return fUsuarioLogueado;
	}

	public void setfUsuarioLogueado(Usuario fUsuarioLogeado) {
		this.fUsuarioLogueado = fUsuarioLogeado;
	}
	
	public Vector<Usuario> getFListaUsuarios() {
		return fListaUsuarios;
	}
	
	public Vector<Administrador> getfListaAdministradores() {
		return fListaAdministradores;
	}

	public void setfListaAdministradores(Vector<Administrador> fListaAdministradores) {
		this.fListaAdministradores = fListaAdministradores;
	}

	public Vector<Clinico> getfListaClinicos() {
		return fListaClinicos;
	}

	public void setfListaClinicos(Vector<Clinico> fListaClinicos) {
		this.fListaClinicos = fListaClinicos;
	}

	public Vector<Paciente> getfListaPacientes() {
		return fListaPacientes;
	}

	public void setfListaPacientes(Vector<Paciente> fListaPacientes) {
		this.fListaPacientes = fListaPacientes;
	}

	public Vector<Cuidador_Familiar> getfListaCuidadores_Familiares() {
		return fListaCuidadores_Familiares;
	}

	public void setfListaCuidadores_Familiares(Vector<Cuidador_Familiar> fListaCuidadores_Familiares) {
		this.fListaCuidadores_Familiares = fListaCuidadores_Familiares;
	}
	
	public Vector<Sensor> getfListaSensores() {
		return fListaSensores;
	}

	public void setfListaSensores(Vector<Sensor> fListaSensores) {
		this.fListaSensores = fListaSensores;
	}
	
	public Vector<MensajeChat> getfChats() {
		return fChats;
	}

	public void setfChats(Vector<MensajeChat> fChats) {
		this.fChats = fChats;
	}
}