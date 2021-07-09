package controlador;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.Administrador;
import application.Clinico;
import application.Cuidador_Familiar;
import application.InformacionMedica;
import application.MensajeChat;
import application.Paciente;
import application.Perfil;
import application.Sensor;
import application.Sistema;
import application.Usuario;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class FuncionesAuxiliares {

	public static void getAlertaError(String tittle, String msg) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setHeaderText(null);
		alerta.initStyle(StageStyle.DECORATED);
		alerta.setTitle(tittle);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}

	public static void getAlertaInformacion(String tittle, String msg) {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setHeaderText(null);
		alerta.initStyle(StageStyle.DECORATED);
		alerta.setTitle(tittle);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
	
	
	
// ------------------ VALIDAR DATOS------------------
	public static String TimeStampToString(Timestamp timestamp) {
	        String stringTime = new SimpleDateFormat("dd/MM/yyyy").format(timestamp);
	        return stringTime;
	}
	
	public static Timestamp StringToTimestamp(String str_date, String mensaje) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date parsedDate = dateFormat.parse(str_date);
	        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	        return timestamp;
	    } catch (ParseException e) {
	    	if (mensaje.equalsIgnoreCase("guardar")) {
	    		getAlertaError("Error", "Error al guardar la fecha");
	    	} else if (mensaje.equalsIgnoreCase("eliminar")){
	    		getAlertaError("Error", "Error al eliminar");
	    	}
	      return null;
	    }    
    }
	
	//Valida que los datos introducidos son numericos
	public static boolean esNumerico(String valor){
        try {
        	Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
	
	//Valida que el DNI es correcto (úumeros y letra)
	public static boolean validarDNI(String dni){
		Pattern dniPattern = Pattern.compile("[0-9]{7,8}[A-Z]");
		Matcher m = dniPattern.matcher(dni);
		if(m.matches()){
			return true;
		}
		else {
			return false;
		}
	}

	// Comprueba si un valor  es numérico y que tiene una longitud determinada
	public static boolean validarValorNumerico(String valor, int longitud){
		if (valor!= "" && valor.length()==longitud && esNumerico(valor)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validarEmail(String email){
		// Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
        								  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            return true;
        } else {
        	return false;
        }
    }

	public static String validarPassword (String password) {	
		int length = 0; // Se almacena el numero de caracteres en el password
		int numCount = 0; // Variable usada para almacenar numeros en el password
		int capCount = 0; // Variable usada para almacenar mayusculas en el password
		int capSignos = 0; // Variable  usada para almacenar los signos

		String infoSeguridad = "";
		for (int x = 0; x < password.length(); x++) {
			if ((password.charAt(x) >= 47 && password.charAt(x) <= 58) // numeros
					|| (password.charAt(x) >= 64 && password.charAt(x) <= 91) // mayusculas
					|| (password.charAt(x) >= 63 && password.charAt(x) <= 65) // Arroba
					|| (password.charAt(x) >= 32 && password.charAt(x) <= 44) // signos
					|| (password.charAt(x) >= 97 && password.charAt(x) <= 122)) { // minusculas
			}
			if ((password.charAt(x) > 32 && password.charAt(x) < 44)) { // Cuenta la cantidad signos
				capSignos++;
			}
			if ((password.charAt(x) > 47 && password.charAt(x) < 58)) { // Cuenta la cantidad de numero
				numCount++;
			}
			if ((password.charAt(x) > 64 && password.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
				capCount++;
			}
			length = (x + 1); // Cuenta la longitud del password
		} 

		if (capSignos < 1 || capCount < 1 || numCount < 1 || length < 8) { // Revisa la longitud minima de 8 caracteres del password
			infoSeguridad = "La contraseña debe tener al menos una mayúscula, un número, "
						  + "caracteres especiales como [! # $ % & ' ( ) + - +] y una longitud mínima de 8 caracteres";
		} else {
			infoSeguridad = "Contraseña segura";
		}
		return infoSeguridad;
	}

	public static boolean validarCampovacio (String valor){
        if (!valor.equals("")) {
            return true;
        } else {
        	return false;
        }
    }
	
	
	public static Clinico getClinicoPorDNI (String id) {
		Clinico resultado = null;
		Vector<Clinico> clinicos = Sistema.getUnico().getfListaClinicos();
		for (Clinico c : clinicos) {
			if (id.equals(c.getDni())) {
				resultado = c;
				break;
			}
		}
		return resultado;
	}

	public static Cuidador_Familiar getCuidadorPorDNI (String id) {
		Cuidador_Familiar resultado = null;
		Vector<Cuidador_Familiar> cuidadores_familiares = Sistema.getUnico().getfListaCuidadores_Familiares();
		for (Cuidador_Familiar c_f : cuidadores_familiares) {
			if (id.equals(c_f.getDni())) {
				resultado = c_f;
				break;
			}
		}
		return resultado;
	}
	
	public static Paciente getPacientePorDNI (String id) {
		Paciente resultado = null;
		Vector<Paciente> pacientes = Sistema.getUnico().getfListaPacientes();
		for (Paciente p : pacientes) {
			if (id.equals(p.getDni())) {
				resultado = p;
				break;
			}
		}
		return resultado;
	}
	
// ------------------ GUARDAR Y EDITAR DATOS PERFIL ------------------
	
	public static void mostrarElementosNoEditables(Label label, JFXTextField textField, Button btnOcultar, Button btnMostrar) {
	    textField.setVisible(false);
	    btnOcultar.setVisible(false);
	    label.setVisible(true);
	    btnMostrar.setVisible(true);
	}
	    
	public static void mostrarElementosEditables(Label label, JFXTextField textField, Button btnOcultar, Button btnMostrar) {
		label.setVisible(false);
		btnOcultar.setVisible(false);
		textField.setVisible(true);
		btnMostrar.setVisible(true);
    }
	
	// Email
	public static void editarEmail(Perfil perfilLogueado, Label emailPerfil, JFXTextField emailEditable, 
									      Button btnOcultar, Button btnMostrar ) {
		emailEditable.setText(perfilLogueado.getEmail());
    	mostrarElementosEditables(emailPerfil, emailEditable, btnOcultar, btnMostrar);
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	emailEditable.requestFocus();
    	    }
    	});
	}

	public static boolean guardarEmail(Perfil perfilLogueado, Label emailPerfil, JFXTextField emailEditable, 
										   Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (validarEmail(emailEditable.getText())) {
    		perfilLogueado.setEmail(emailEditable.getText());
    		emailPerfil.setText(perfilLogueado.getEmail());
    		valido = true;
    	} else {
    		getAlertaError("Error", "El email no es válido.");
    	}
    	mostrarElementosNoEditables(emailPerfil, emailEditable, btnOcultar, btnMostrar);
    	return valido;
	}  
	
	// Telefono
	public static void editarTelefono(Perfil perfilLogueado, Label telefonoPerfil, JFXTextField telefonoEditable, 
									      Button btnOcultar, Button btnMostrar ) {
		telefonoEditable.setText(Integer.toString(perfilLogueado.getTelefono()));
    	mostrarElementosEditables(telefonoPerfil, telefonoEditable, btnOcultar, btnMostrar);
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	telefonoEditable.requestFocus();
    	    }
    	});
	}

	public static boolean guardarTelefono(Perfil perfilLogueado, Label telefonoPerfil, JFXTextField telefonoEditable, 
										   Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (validarValorNumerico(telefonoEditable.getText(), 9)) {
			int telefono = Integer.parseInt(telefonoEditable.getText());
			perfilLogueado.setTelefono(telefono);
			telefonoPerfil.setText(Integer.toString(perfilLogueado.getTelefono()));
			valido = true;
		} else {
			getAlertaError("Error", "El teléfono no es válido.");
		}
		mostrarElementosNoEditables(telefonoPerfil, telefonoEditable, btnOcultar, btnMostrar);
		return valido;
	}    

	// Direccion
	public static void editarDireccion(Perfil perfilLogueado, Label direccionPerfil, JFXTextField direccionEditable, 
									   Button btnOcultar, Button btnMostrar ) {
		direccionEditable.setText(perfilLogueado.getDireccion());
		mostrarElementosEditables(direccionPerfil, direccionEditable, btnOcultar, btnMostrar);
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	direccionEditable.requestFocus();
    	    }
    	});
	}
	
	public static boolean guardarDireccion(Perfil perfilLogueado, Label direccionPerfil, JFXTextField direccionEditable, 
										Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (!direccionEditable.getText().trim().equalsIgnoreCase("")) {
			perfilLogueado.setDireccion(direccionEditable.getText());
			direccionPerfil.setText(perfilLogueado.getDireccion());
			valido = true;
		} else {
			getAlertaError("Error", "La dirección no es válida.");
		}
		
		mostrarElementosNoEditables(direccionPerfil, direccionEditable, btnOcultar, btnMostrar);
		return valido;
	}

	// Ciudad
	public static void editarCiudad(Perfil perfilLogueado, Label ciudadPerfil, JFXTextField ciudadEditable, 
									Button btnOcultar, Button btnMostrar ) {
		ciudadEditable.setText(perfilLogueado.getCiudad());
		mostrarElementosEditables(ciudadPerfil, ciudadEditable, btnOcultar, btnMostrar);
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	ciudadEditable.requestFocus();
    	    }
    	});
	}
	
	public static boolean guardarCiudad(Perfil perfilLogueado, Label ciudadPerfil, JFXTextField ciudadEditable, 
										Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (!ciudadEditable.getText().trim().equals("")) {
			perfilLogueado.setCiudad(ciudadEditable.getText());
			ciudadPerfil.setText(perfilLogueado.getCiudad());
			valido = true;
		} else {
			getAlertaError("Error", "La ciudad no es válida.");
		}
		mostrarElementosNoEditables(ciudadPerfil, ciudadEditable, btnOcultar, btnMostrar);
		return valido;
	}
	
	// Codigo Postal
	public static void editarCodigoPostal(Perfil perfilLogueado, Label codigoPostalPerfil, JFXTextField codigoPostalEditable, 
										  Button btnOcultar, Button btnMostrar ) {
		codigoPostalEditable.setText(Integer.toString(perfilLogueado.getCodigoPostal()));
		mostrarElementosEditables(codigoPostalPerfil, codigoPostalEditable, btnOcultar, btnMostrar);
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	codigoPostalEditable.requestFocus();
    	    }
    	});
	}

	public static boolean guardarCodigoPostal(Perfil perfilLogueado, Label codigoPostalPerfil, JFXTextField codigoPostalEditable, 
										   Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (validarValorNumerico(codigoPostalEditable.getText(), 5)) {
			int codigoPostal = Integer.parseInt(codigoPostalEditable.getText());
			perfilLogueado.setCodigoPostal(codigoPostal);
			codigoPostalPerfil.setText(Integer.toString(perfilLogueado.getCodigoPostal()));
			valido = true;
		} else {
			getAlertaError("Error", "El código postal no es válido, debe contener 5 dígitos.");
		}
		mostrarElementosNoEditables(codigoPostalPerfil, codigoPostalEditable, btnOcultar, btnMostrar);
		return valido;
	}
	
	// Provincia
	public static void editarProvincia(Perfil perfilLogueado, Label provinciaPerfil, JFXTextField provinciaEditable, 
									   Button btnOcultar, Button btnMostrar ) {
		provinciaEditable.setText(perfilLogueado.getProvincia());
		mostrarElementosEditables(provinciaPerfil, provinciaEditable, btnOcultar, btnMostrar);
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	provinciaEditable.requestFocus();
    	    }
    	});
	}
	
	public static boolean guardarProvincia(Perfil perfilLogueado, Label provinciaPerfil, JFXTextField provinciaEditable, 
										Button btnOcultar, Button btnMostrar) {
		boolean valido = false;
		if (!provinciaEditable.getText().trim().equals("")) {
			perfilLogueado.setProvincia(provinciaEditable.getText());
			provinciaPerfil.setText(perfilLogueado.getProvincia());
			valido = true;
		} else {
			getAlertaError("Error", "La provincia no es válida.");
		}
		mostrarElementosNoEditables(provinciaPerfil, provinciaEditable, btnOcultar, btnMostrar);
		return valido;
	}
	
	// Contraseña
	public static void editarContrasena(JFXPasswordField contrasena, Button btnOcultar, Button btnMostrar) {
    	contrasena.setEditable(true);
    	btnMostrar.setVisible(true);
    	btnOcultar.setVisible(false);
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	contrasena.requestFocus();
    	    }
    	});
    }   
	
	public static void guardarContrasena(JFXPasswordField contrasena, Button btnOcultar, Button btnMostrar) {
    	String informacion = validarPassword(contrasena.getText());
    	if (informacion.equalsIgnoreCase("Contraseña segura")) {
    		Sistema.getUnico().getUsuarioLogueado().setContrasena(contrasena.getText());
    		serializarArrayAJsonUsuario();
    		getAlertaInformacion("Guardado", "Dato actualizado correctamente.");
    	} else {
			FuncionesAuxiliares.getAlertaError("Error", informacion);
			contrasena.setText(Sistema.getUnico().getUsuarioLogueado().getContrasena());
		}
    	contrasena.setEditable(false);
    	btnOcultar.setVisible(false);
    	btnMostrar.setVisible(true);
    }
	    

    
// ------------------ GUARDAR DATOS DE UN JSON AL SISTEMA ------------------
	// USUARIOS 
	public static Vector<Usuario> desserializarJsonAArrayUsuarios() {
		Vector<Usuario> usuarios = new Vector<Usuario>();
		try (Reader reader = new FileReader("ficheros\\Usuarios.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Usuario>>(){}.getType();
			usuarios = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"usuarios\"");
		}
		return usuarios;
	}
	
	// ADMINISTRADORES 
	public static Vector<Administrador> desserializarJsonAArrayAdmin() {
		Vector<Administrador> administradores = new Vector<Administrador>();
		try (Reader reader = new FileReader("ficheros\\Administradores.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Administrador>>(){}.getType();
			administradores = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"administradores\"");
		}
		return administradores;
	}
	
	// CLINICOS 
	public static Vector<Clinico> desserializarJsonAArrayClinicos() {
		Vector<Clinico> clinicos = new Vector<Clinico>();
		try (Reader reader = new FileReader("ficheros\\Clinicos.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Clinico>>(){}.getType();
			clinicos = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"clÃ­nicos\"");
		}
		return clinicos;
	}
		
	// PACIENTES  
	public static Vector<Paciente> desserializarJsonAArrayPacientes() {
		Vector<Paciente> pacientes = new Vector<Paciente>();
		try (Reader reader = new FileReader("ficheros\\Pacientes.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Paciente>>(){}.getType();
			pacientes = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"pacientes\"");
		}
		return pacientes;
	}
	
	// CUIDADORES/FAMILIARES  
	public static Vector<Cuidador_Familiar> desserializarJsonAArrayCuidadores_Familiares() {
		Vector<Cuidador_Familiar> cuidadores_familiares = new Vector<Cuidador_Familiar>();
		try (Reader reader = new FileReader("ficheros\\Cuidadores_Familiares.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Cuidador_Familiar>>(){}.getType();
			cuidadores_familiares = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"cuidadores y familiares\"");
		}
		return cuidadores_familiares;
	}
	
	// SENSORES 
	public static Vector<Sensor> desserializarJsonAArraySensores() {
		Vector<Sensor> sensores = new Vector<Sensor>();
		try (Reader reader = new FileReader("ficheros\\Sensores.json")) {
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<Vector<Sensor>>(){}.getType();
			sensores = gson.fromJson(reader, tipoLista);
		} catch (IOException e) {
			getAlertaError("Error al cargar los datos", "No se ha podido cargar el fichero de \"sensores\"");
		}
		return sensores;
	}
	
	// INFORMACION MEDICA 
	public static Vector<InformacionMedica> desserializarJsonAArrayInfoMedica(String identificadorPaciente) {
		Vector<InformacionMedica> infoMedica = new Vector<InformacionMedica>();
		File file = new File("ficheros_InformacionMedica\\InformacionMedica_" + identificadorPaciente + ".json");
		if (file.exists()) {
			try (Reader reader = new FileReader(file)) {
				Gson gson = new Gson();
				Type tipoLista = new TypeToken<Vector<InformacionMedica>>(){}.getType();
				infoMedica = gson.fromJson(reader, tipoLista);
			} catch (IOException e) {
				getAlertaError("Error al cargar los datos", "No se ha podido cargar información médica");
			}
		}
		return infoMedica;
	}
	
	// CHAT 
	public static Vector<MensajeChat> desserializarJsonAArrayChats() {
		Vector<MensajeChat> chats = new Vector<MensajeChat>();
		File file = new File("ficheros\\chats.json");
		if (file.exists()) {
			try (Reader reader = new FileReader(file)) {
				Gson gson = new Gson();
				Type tipoLista = new TypeToken<Vector<MensajeChat>>(){}.getType();
				chats = gson.fromJson(reader, tipoLista);
			} catch (IOException e) {
				getAlertaError("Error al cargar los datos", "No se ha podido cargar los chats");
			}
		}
		return chats;
	}
	
	
// ------------------ GUARDAR DATOS DEL SISTEMA A UN JSON ------------------
	// USUARIOS 
	public static boolean serializarArrayAJsonUsuario () {		
		Vector <Usuario> usuarios = Sistema.getUnico().getFListaUsuarios();	
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Usuarios.json"); 	
			gson.toJson(usuarios, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de usuario.");
		}
		return guardado;
	}
	
	// ADMINISTRADORES 
	public static boolean serializarArrayAJsonAdministradores() {		
		Vector <Administrador> administradores = Sistema.getUnico().getfListaAdministradores();	
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Administradores.json"); 	
			gson.toJson(administradores, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de administrador.");
		}
		return guardado;
	}
	
	// CLINICOS 
	public static boolean serializarArrayAJsonClinicos() {		
		Vector <Clinico> clinicos = Sistema.getUnico().getfListaClinicos();
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Clinicos.json"); 	
			gson.toJson(clinicos, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de clínico.");
		}
		return guardado;
	}
	
	// PACIENTES  
	public static boolean serializarArrayAJsonPacientes() {		
		Vector <Paciente> pacientes = Sistema.getUnico().getfListaPacientes();
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Pacientes.json"); 	
			gson.toJson(pacientes, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de paciente.");
		}
		return guardado;
	}
	
	// CUIDADORES/FAMILIARES  
	public static boolean serializarArrayAJsonCuidadores_Familiares() {		
		Vector <Cuidador_Familiar> cuidadores_familiares = Sistema.getUnico().getfListaCuidadores_Familiares();	
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Cuidadores_Familiares.json"); 	
			gson.toJson(cuidadores_familiares, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de cuidador/familiar.");
		}
		return guardado;
	}
	
	// SENSORES 
	public static boolean serializarArrayAJsonSensores() {		
		Vector <Sensor> sensores = Sistema.getUnico().getfListaSensores();
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros\\Sensores.json"); 	
			gson.toJson(sensores, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de los sensores");
		}
		return guardado;
	}
	
	// INFORMACION MEDICA 
	public static boolean serializarArrayAJsonInfoMedica(Vector <InformacionMedica> listaInfoMedica, String identificadorPaciente) {
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros_InformacionMedica\\InformacionMedica_" + identificadorPaciente + ".json"); 	
			gson.toJson(listaInfoMedica, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de información médica");
		}
		return guardado;
	}
	
	
	
// ------------------ MOSTRAR SENSORES ------------------
	public static void mostrarSensores(String dni, LineChart<String, Double> graficalineal,  String[] sensoresPaciente,
									   String fecha, String tipo, String nombreSensor) {
		if (fecha!=null) {
			Vector<Sensor> sensores = Sistema.getUnico().getfListaSensores();
			XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
			
			for (int i = 0; i < sensoresPaciente.length; i++) {
				for (Sensor s : sensores) {
		        	if(sensoresPaciente[i].equals(s.getId()) && tipo.equals(s.getTipo()) && fecha.equals(s.getFecha())) {
		        		series.getData().add(new XYChart.Data<String, Double>(s.getHora(), s.getValor()));
					}
		        }
			}
			if(series.getData().size()>0) {
				graficalineal.getData().clear();
				series.setName(nombreSensor);
		        graficalineal.getData().add(series);
			} else {
				getAlertaError("Error", "No hay datos de " + nombreSensor +" en esta fecha");
			}
		} else {
			getAlertaError("Error", "No hay ninguna fecha seleccionada");
		}
	}
	
	public static void mostrarTodosSensores(String dni, LineChart<String, Double> graficalineal, String[] sensoresPaciente, String fecha, String Tipo, String Nombre) {

		XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		Vector<Sensor> sensores = Sistema.getUnico().getfListaSensores();
		
		for (int i = 0; i < sensoresPaciente.length; i++) {
			for (Sensor s : sensores) {
				if(sensoresPaciente[i].equals(s.getId()) && Tipo.equals(s.getTipo()) && fecha.equals(s.getFecha())) {
					series.getData().add(new XYChart.Data<String, Double>(s.getHora(), s.getValor()));
				}
			}
		}
		if(series.getData().size()<=0) {
			getAlertaError("Error", "No hay datos de "+Nombre+" en esta fecha");
		} else {
			series.setName(Nombre);
	        graficalineal.getData().add(series);
		}
	}
}