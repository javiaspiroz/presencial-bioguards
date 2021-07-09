package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.Paciente;
import application.Sistema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ControladorVistaPaciente {	
	
	@FXML
    private AnchorPane PacientePane;
    @FXML
    private Button btnPacienteCerrarSesion;
    @FXML
    private Label identidad;

// ----------- PERFIL -----------
    @FXML
    private Button btnPerfil;
    @FXML
    private BorderPane vistaPerfil;
    
  // DATOS DE PERFIL
    @FXML
    private Button btnPerfilGeneral;
    @FXML
    private GridPane gridPanePerfilGeneral;
    
    @FXML
    private Label clinicoPerfil;    
    @FXML
    private Label nombrePerfil;
    @FXML
    private Label apellidosPerfil;
    @FXML
    private Label dniPerfil;
    @FXML
    private Label fechaNacimientoPerfil;
    
    @FXML
    private Label emailPerfil; 
    @FXML
    private JFXTextField emailEditable;
    @FXML
    private Button btnEditarEmail;  
    @FXML
    private Button btnGuardarEmail;

    @FXML
    private Label telefonoPerfil;
    @FXML
    private JFXTextField telefonoEditable;
    @FXML
    private Button btnEditarTelefono; 
    @FXML
    private Button btnGuardarTelefono;

    @FXML
    private Label direccionPerfil;
    @FXML
    private JFXTextField direccionEditable;
    @FXML
    private Button btnEditarDireccion;
    @FXML
    private Button btnGuardarDireccion;
    
    @FXML
    private Label ciudadPerfil;
    @FXML
    private JFXTextField ciudadEditable;
    @FXML
    private Button btnEditarCiudad;
    @FXML
    private Button btnGuardarCiudad;
    
    @FXML
    private Label codigoPostalPerfil;
    @FXML
    private JFXTextField codigoPostalEditable;
    @FXML
    private Button btnEditarCodigoPostal;
    @FXML
    private Button btnGuardarCodigoPostal;

    @FXML
    private Label provinciaPerfil;
    @FXML
    private JFXTextField provinciaEditable;
    @FXML
    private Button btnEditarProvincia;
    @FXML
    private Button btnGuardarProvincia;
    
  // DATOS PERFIL DE SEGURIDAD
    @FXML
    private Button btnPerfilSeguridad;
    @FXML
    private GridPane gridPaneSeguridad;

    @FXML
    private JFXTextField usuario;
    
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private Button btnEditarContrasena;  
    @FXML
    private Button btnGuardarContrasena;

    
// ----------- SENSORES -----------
    @FXML
    private Button btnSensores;
    @FXML
    private BorderPane vistaSensores;
 
    @FXML
    private LineChart<String, Double> graficalineal;
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Button btnSensorTemperatura;
    @FXML
    private Button btnSensorPresion;
    @FXML
    private Button btnSensorPulsioximetro;
    @FXML
    private Button btnTodosSensores;

    @FXML
    private JFXDatePicker datePickerSensores;
    
// ----------- CALENDARIO -----------
    @FXML
    private Button btnCalendario;
    @FXML
    private AnchorPane vistaCalendario;
   
    private Paciente pacienteLogueado;
//    private Vector<Paciente> pacientes = Sistema.getUnico().getfListaPacientes();
    private String fechaSensores= DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

 // ----------- CONTACTAR -----------
    @FXML
    private Button btnContactar;
    @FXML
    private BorderPane vistaContactar;
   
    @FXML
    private ScrollPane scrollChat;
    @FXML
    private TextField newMsg;
    @FXML
    private Button sendMsg;
    @FXML
    private Label lblNombreClinicoChat;
    
    
    
    @FXML
    void initialize() {
    	buscarPacienteLogueado();
    	setDatosPerfil();
    	identidad.setText("Bienvenido, " + pacienteLogueado.getNombre() + " " + pacienteLogueado.getApellidos());
    	vistaPerfil.setVisible(true);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(false);
//    	vistaRecordatorios.getChildren().add();
    	
	}
   
// ------------------ PANEL PERFIL ------------------
	@FXML
    void mostrarPerfil(ActionEvent event) {
		vistaPerfil.setVisible(true);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(false);
		gridPaneSeguridad.setVisible(false);
		gridPanePerfilGeneral.setVisible(true);
    }
	
	// Perfil general
	@FXML
    void mostrarPerfilGeneral(ActionEvent event) {
		 gridPaneSeguridad.setVisible(false);
		 gridPanePerfilGeneral.setVisible(true);
    }
	 	
	@FXML
    void editarEmail(ActionEvent event) {
    	FuncionesAuxiliares.editarEmail(pacienteLogueado, emailPerfil, emailEditable, btnEditarEmail, btnGuardarEmail);
    }	
    @FXML
    void guardarEmail(ActionEvent event) {
		guardarDatosPaciente(FuncionesAuxiliares.guardarEmail(pacienteLogueado, emailPerfil, emailEditable, btnGuardarEmail, btnEditarEmail));
    } 
    
    @FXML
    void editarTelefono(ActionEvent event) {
    	FuncionesAuxiliares.editarTelefono(pacienteLogueado, telefonoPerfil, telefonoEditable, btnEditarTelefono, btnGuardarTelefono);
    }        
	@FXML
    void guardarTelefono(ActionEvent event) {
		guardarDatosPaciente(FuncionesAuxiliares.guardarTelefono(pacienteLogueado, telefonoPerfil, telefonoEditable, btnGuardarTelefono, btnEditarTelefono));
    }

    @FXML
    void editarDireccion(ActionEvent event) {
    	FuncionesAuxiliares.editarDireccion(pacienteLogueado, direccionPerfil, direccionEditable, btnEditarDireccion, btnGuardarDireccion);
    }
    @FXML
    void guardarDireccion(ActionEvent event) {
    	guardarDatosPaciente(FuncionesAuxiliares.guardarDireccion(pacienteLogueado, direccionPerfil, direccionEditable, btnGuardarDireccion, btnEditarDireccion));
    }

    @FXML
    void editarCiudad(ActionEvent event) {
    	FuncionesAuxiliares.editarCiudad(pacienteLogueado, ciudadPerfil, ciudadEditable, btnEditarCiudad, btnGuardarCiudad);
    }   
    @FXML
    void guardarCiudad(ActionEvent event) {
    	guardarDatosPaciente(FuncionesAuxiliares.guardarCiudad(pacienteLogueado, ciudadPerfil, ciudadEditable, btnGuardarCiudad, btnEditarCiudad));
    }

    @FXML
    void editarCodigoPostal(ActionEvent event) {
    	FuncionesAuxiliares.editarCodigoPostal(pacienteLogueado, codigoPostalPerfil, codigoPostalEditable, btnEditarCodigoPostal, btnGuardarCodigoPostal);
    } 
    @FXML
    void guardarCodigoPostal(ActionEvent event) {
    	guardarDatosPaciente(FuncionesAuxiliares.guardarCodigoPostal(pacienteLogueado, codigoPostalPerfil, codigoPostalEditable, btnGuardarCodigoPostal, btnEditarCodigoPostal));
    }

    @FXML
    void editarProvincia(ActionEvent event) {
    	FuncionesAuxiliares.editarProvincia(pacienteLogueado, provinciaPerfil, provinciaEditable, btnEditarProvincia, btnGuardarProvincia);
    }
    @FXML
    void guardarProvincia(ActionEvent event) {
    	guardarDatosPaciente(FuncionesAuxiliares.guardarProvincia(pacienteLogueado, provinciaPerfil, provinciaEditable, btnGuardarProvincia, btnEditarProvincia));
    }
    
    // Perfil Seguridad
    @FXML
    void mostrarPerfilSeguridad(ActionEvent event) {
    	gridPanePerfilGeneral.setVisible(false);
    	gridPaneSeguridad.setVisible(true);	 
    }
    
    @FXML
    void editarContrasena(ActionEvent event) {
    	FuncionesAuxiliares.editarContrasena(contrasena, btnEditarContrasena, btnGuardarContrasena);
    }   
    @FXML
    void guardarContrasena(ActionEvent event) {
    	FuncionesAuxiliares.guardarContrasena(contrasena, btnGuardarContrasena, btnEditarContrasena);
    }
    
    
// ------------------ PANEL SENSORES ------------------
	@FXML
    void mostrarSensores(ActionEvent event) {
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(true);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(false);
    	
    	graficalineal.getData().clear();
		datePickerSensores.setValue(LocalDate.now());
		seleccionarFecha(null);
    }
	
	@FXML
    void mostrarSensorTemperatura(ActionEvent event) {
		 FuncionesAuxiliares.mostrarSensores(pacienteLogueado.getDni(),graficalineal, pacienteLogueado.getSensores(), fechaSensores, "Sensor 1", "temperatura");
    }

    @FXML
    void mostrarSensorPresion(ActionEvent event) {
    	FuncionesAuxiliares.mostrarSensores(pacienteLogueado.getDni(), graficalineal, pacienteLogueado.getSensores(), fechaSensores, "Sensor 2", "presion");
    }

    @FXML
    void mostrarSensorPulsioximetro(ActionEvent event) {
    	FuncionesAuxiliares.mostrarSensores(pacienteLogueado.getDni(),graficalineal, pacienteLogueado.getSensores(), fechaSensores, "Sensor 3", "pulsioximetro");
    }
    
    @FXML
    void mostrarTodosSensores(ActionEvent event) {
    	seleccionarFecha(null);
    }
    
    @FXML
    void seleccionarFecha(ActionEvent event) {
    	LocalDate fechaElegidaSensores = datePickerSensores.getValue();
    	fechaSensores = fechaElegidaSensores.toString();
    	graficalineal.getData().clear();
//		xAxis.setLabel("Horas");
		String [] Tipo = {"Sensor 1", "Sensor 2", "Sensor 3"};
		String [] Nombre = {"Temperatura", "Presion", "Pulsioximetro"};
		for (int i = 0; i < Nombre.length; i++) {
			FuncionesAuxiliares.mostrarTodosSensores(pacienteLogueado.getDni(), graficalineal, pacienteLogueado.getSensores(), fechaSensores, Tipo[i], Nombre[i]);
		}
    }
	
	
// ------------------ PANEL CALENDARIO ------------------
	@FXML
    void mostrarCalendario(ActionEvent event) {
		// Se crea y se muestra el calendario
		ManejadorCalendario manejadorCalendario = new ManejadorCalendario();
    	Control cal = manejadorCalendario.CrearCalendario(pacienteLogueado.getDni(), true);
    	AnchorPane.setTopAnchor(cal, 0.0);
    	AnchorPane.setLeftAnchor(cal, 0.0);
    	AnchorPane.setRightAnchor(cal, 0.0);
    	AnchorPane.setBottomAnchor(cal, 0.0);
    	vistaCalendario.getChildren().add(cal);
    	
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(true);
    	vistaContactar.setVisible(false);
    }
	
	
	// ------------------ PANEL CONTACTAR ------------------
		@FXML
	    void mostrarContactar(ActionEvent event) {
			vistaPerfil.setVisible(false);
	    	vistaSensores.setVisible(false);
	    	vistaCalendario.setVisible(true);
	    	vistaContactar.setVisible(true);
	    	
//	        //Se cargan los familiares disponibles
//	        String[] cuidadores = pacienteAMostrar.getCuidador_familiar();
//	    	for(int i = 0; i<cuidadores.length; i++) {
//				listaDNIsCuidadores.add(cuidadores[i]);
//			}
//	    	comboNombres.setItems(listaDNIsCuidadores);
	    }
	    
		
		@FXML
	    void enviarMensaje(ActionEvent event) {
	    }
	
	
	
	
    @FXML
	void cerrarSesionPaciente(ActionEvent event) {
		Sistema.getUnico().logoutUsuario();
		ControladorMostrarVentana.mostrarLogin();
	}    
    
    
// ------------------ Funciones auxiliares ------------------
	private void buscarPacienteLogueado() {
		// Se busca el clinico en funcion del usuario logueado;
		Vector<Paciente> pacientes = Sistema.getUnico().getfListaPacientes();
		for(Paciente p: pacientes) {
			if (Sistema.getUnico().getUsuarioLogueado().getUsuario().equals(p.getDni())) {
				pacienteLogueado = p;
				break;
			}
		}
	}
	
    private void setDatosPerfil() {    	
    	clinicoPerfil.setText(pacienteLogueado.getClinico());
    	nombrePerfil.setText(pacienteLogueado.getNombre());
		apellidosPerfil.setText(pacienteLogueado.getApellidos());
		dniPerfil.setText(pacienteLogueado.getDni());
		fechaNacimientoPerfil.setText(FuncionesAuxiliares.TimeStampToString(pacienteLogueado.getFechaNacimiento()));
    	
		emailPerfil.setText(pacienteLogueado.getEmail());
		FuncionesAuxiliares.mostrarElementosNoEditables(emailPerfil, emailEditable, btnGuardarEmail, btnEditarEmail);
		
		telefonoPerfil.setText(Integer.toString(pacienteLogueado.getTelefono()));
		FuncionesAuxiliares.mostrarElementosNoEditables(telefonoPerfil, telefonoEditable, btnGuardarTelefono, btnEditarTelefono);

		direccionPerfil.setText(pacienteLogueado.getDireccion());
		FuncionesAuxiliares.mostrarElementosNoEditables(direccionPerfil, direccionEditable, btnGuardarDireccion, btnEditarDireccion);

		ciudadPerfil.setText(pacienteLogueado.getCiudad());
		FuncionesAuxiliares.mostrarElementosNoEditables(ciudadPerfil, ciudadEditable, btnGuardarCiudad, btnEditarCiudad);

		codigoPostalPerfil.setText(Integer.toString(pacienteLogueado.getCodigoPostal()));
		FuncionesAuxiliares.mostrarElementosNoEditables(codigoPostalPerfil, codigoPostalEditable, btnGuardarCodigoPostal, btnEditarCodigoPostal);

		provinciaPerfil.setText(pacienteLogueado.getProvincia());
		FuncionesAuxiliares.mostrarElementosNoEditables(provinciaPerfil, provinciaEditable, btnGuardarProvincia, btnEditarProvincia);
		
		usuario.setText(Sistema.getUnico().getUsuarioLogueado().getUsuario());
		usuario.setEditable(false);
		
		contrasena.setText(Sistema.getUnico().getUsuarioLogueado().getContrasena());
		contrasena.setEditable(false);
		btnGuardarContrasena.setVisible(false);
		btnEditarContrasena.setVisible(true);
    }
    
    private void guardarDatosPaciente(boolean valido) {
    	if (valido && FuncionesAuxiliares.serializarArrayAJsonPacientes()) {
    		FuncionesAuxiliares.getAlertaInformacion("Guardado", "Dato actualizado correctamente.");
    	} 
    }
}