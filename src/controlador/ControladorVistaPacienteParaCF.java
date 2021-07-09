package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import application.InformacionMedica;
import application.Paciente;
import application.Sistema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class ControladorVistaPacienteParaCF {	
	
	@FXML
    private AnchorPane PacienteParaCuidadorPane;
    @FXML
    private Button btnVolver;
    @FXML
    private Label identidad;

// ----------- PERFIL -----------
    @FXML
    private Button btnPerfil;
    @FXML
    private BorderPane vistaPerfil;

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
    private Label telefonoPerfil;
    @FXML
    private Label direccionPerfil; 
    @FXML
    private Label ciudadPerfil;
    @FXML
    private Label codigoPostalPerfil;
    @FXML
    private Label provinciaPerfil;

    
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
    
    
 // ----------- INFORMACION MEDICA -----------
    @FXML
    private Button btnInfoMedica;
    @FXML
    private BorderPane vistaInfoMedica;
   
    @FXML
    private TextField buscarInfo;
    @FXML
    private Button btnAmpliarInfo;
    
    // General
    @FXML
    private BorderPane vistaInfoMedicaGeneral;
    @FXML
    private TableView<InformacionMedica> tablaInfoMedica;
    @FXML
    private TableColumn<InformacionMedica, String> infoMedicaPacienteDoc;
    @FXML
    private TableColumn<InformacionMedica, String> fechaInfoMedicaPacienteDoc;
    
    @FXML
    private BorderPane vistaInfoMedicaDetalles;

    @FXML
    private JFXTextField tituloInfoMedica;
    @FXML
    private Label fechaInfoMedica;
    @FXML
    private Button btnVolverInfoMedica;
    @FXML
    private JFXTextArea textDescripcion;
    @FXML
    private JFXTextArea textTratamiento;
    
    
// ----------- CALENDARIO -----------
    @FXML
    private Button btnCalendario;
    @FXML
    private AnchorPane vistaCalendario;
   
    
// ----------- CONTACTAR -----------
    @FXML
    private Button btnContactar;
    @FXML
    private BorderPane vistaContactar;
    private boolean activarChat;
    
    @FXML
    private ScrollPane scrollChat;
    @FXML
    private TextField newMsg;
    @FXML
    private Button sendMsg;
    @FXML
    private Label lblNombreClinicoChat;
    
    private Paciente pacienteAMostrar;
    private String dniPacienteElegido;
    private Vector<Paciente> listaPacientes = Sistema.getUnico().getfListaPacientes();
    private Vector<InformacionMedica> infoMedicaPaciente;
    private ObservableList<InformacionMedica> listaInformacionMedica = FXCollections.observableArrayList();
    private String fechaSensores= DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    
    
    
    @FXML
    void initialize() throws IOException {
    	pacienteAMostrar = pacienteActual();
    	setDatosPerfil();
    	identidad.setText("Paciente: " + pacienteAMostrar.getNombre() + " " + pacienteAMostrar.getApellidos());
    	if(activarChat==true) {
    		mostrarContactar(null);
    	} else {
    		vistaPerfil.setVisible(true);
        	vistaSensores.setVisible(false);
        	vistaCalendario.setVisible(false);
        	vistaContactar.setVisible(false);
        	vistaInfoMedica.setVisible(false);
    	}
	}
   
// ------------------ PANEL PERFIL ------------------
	@FXML
    void mostrarPerfil(ActionEvent event) {
		vistaPerfil.setVisible(true);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(false);
    	vistaInfoMedica.setVisible(false);
    }

    
// ------------------ PANEL SENSORES ------------------
	@FXML
    void mostrarSensores(ActionEvent event) {
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(true);
    	vistaCalendario.setVisible(false);	
    	vistaContactar.setVisible(false);
        vistaInfoMedica.setVisible(false);
        
        graficalineal.getData().clear();
        datePickerSensores.setValue(LocalDate.now());
		seleccionarFecha(null);
    }

	@FXML
    void mostrarSensorTemperatura(ActionEvent event) {
		FuncionesAuxiliares.mostrarSensores(dniPacienteElegido, graficalineal, pacienteAMostrar.getSensores(), fechaSensores, "Sensor 1", "temperatura");
    }

    @FXML
    void mostrarSensorPresion(ActionEvent event) {
    	FuncionesAuxiliares.mostrarSensores(dniPacienteElegido, graficalineal, pacienteAMostrar.getSensores(), fechaSensores, "Sensor 2", "presion");
    }

    @FXML
    void mostrarSensorPulsioximetro(ActionEvent event) {
    	FuncionesAuxiliares.mostrarSensores(dniPacienteElegido,graficalineal, pacienteAMostrar.getSensores(), fechaSensores, "Sensor 3", "pulsioximetro");
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
			FuncionesAuxiliares.mostrarTodosSensores(dniPacienteElegido, graficalineal, pacienteAMostrar.getSensores(), fechaSensores, Tipo[i], Nombre[i]);
		}
    }

    
    
// ------------------ PANEL INFORMACION MEDICA ------------------
	@FXML
    void mostrarInfoMedica(ActionEvent event) {
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(false);
    	vistaInfoMedica.setVisible(true);
    	vistaInfoMedicaGeneral.setVisible(true);
		vistaInfoMedicaDetalles.setVisible(false);
		cargarInfoMedica();
    }
    
	@FXML
    void ampliarInfo(ActionEvent event) {
    	InformacionMedica informacion = tablaInfoMedica.getSelectionModel().getSelectedItem();
		if (informacion!=null) {
			cambiarEstadoCamposInfoMedica(false);
			for(InformacionMedica i : infoMedicaPaciente) {
				if(informacion.getTitulo().equals(i.getTitulo())) {
					tituloInfoMedica.setText(i.getTitulo());
					fechaInfoMedica.setText(FuncionesAuxiliares.TimeStampToString(i.getFechaCreacion()));
					textDescripcion.setText(i.getDescripcion());
					textTratamiento.setText(i.getTratamiento());
				}
			}
			vistaInfoMedicaGeneral.setVisible(false);
			vistaInfoMedicaDetalles.setVisible(true);
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ningun elemento seleccionado");
		}
    }
    
	@FXML
    void volverInfoMedica(ActionEvent event) {
    	vistaInfoMedicaGeneral.setVisible(true);
		vistaInfoMedicaDetalles.setVisible(false);
    }
    
    
// ------------------ PANEL CALENDARIO ------------------
	@FXML
    void mostrarCalendario(ActionEvent event) {
    	// Se crea y se muestra el calendario
		ManejadorCalendario manejadorCalendario = new ManejadorCalendario();
    	Control cal = manejadorCalendario.CrearCalendario(dniPacienteElegido, false);
    	AnchorPane.setTopAnchor(cal, 0.0);
    	AnchorPane.setLeftAnchor(cal, 0.0);
    	AnchorPane.setRightAnchor(cal, 0.0);
    	AnchorPane.setBottomAnchor(cal, 0.0);
    	vistaCalendario.getChildren().add(cal);
    	
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(true);
    	vistaContactar.setVisible(false);
    	vistaInfoMedica.setVisible(false);
    }
	
	
// ------------------ PANEL CONTACTAR ------------------
	@FXML
    void mostrarContactar(ActionEvent event) throws IOException{
    	vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaCalendario.setVisible(false);
    	vistaContactar.setVisible(true);
    	vistaInfoMedica.setVisible(false);
    	
//        //Se cargan los mensajes en el chat
//        cargarMensajes(); 
    }
	
	@FXML
    void enviarMensaje(ActionEvent event) {
//		//Encontrar familiar activo
//        Usuario usr = Sistema.getUnico().getUsuarioLogueado();
//        int posCF=-1;
//        String[] cuidadores = pacienteAMostrar.getCuidador_familiar();
//        for (int i=0; i<cuidadores.length; i++) {
//        	if (cuidadores[i].equals(usr.getUsuario())) {
//        		posCF=i;
//        	}
//        }
// 		String newM = newMsg.getText();
// 		//Se da formato al mensaje dependiendo del usuario que escribe
//    	newM = "F: "+newM;
// 		//Se actualizan los mensajes en datos del paciente
// 		Vector<Vector<String>> mensajes = pacienteAMostrar.getChat();
// 		mensajes.get(posCF).add(newM);
// 		pacienteAMostrar.setChat(mensajes);
// 		//Se exportan los datos nuevos
// 		FuncionesAuxiliares.serializarArrayAJsonPacientes();
// 		//Se actualiza la interfaz
// 		newMsg.setText("");
// 		vBoxChat.getChildren().clear();
// 		cargarMensajes();
    }
	
	
    @FXML
	void volverVistaCuidador(ActionEvent event) {
    	ControladorMostrarVentana.mostrarVentanaCuidador_Familiar(2);
	}
    
    
 // ------------------ Funciones auxiliares ------------------
    public Paciente pacienteActual() {
    	Paciente pacienteMostrar = null;
    	for(Paciente p : listaPacientes) {
			if(dniPacienteElegido.equals(p.getDni())) {
				pacienteMostrar= p;
			}
		}
    	return pacienteMostrar;
    }
    
    public String dniActual(String dni) {
    	dniPacienteElegido = dni;
    	return dniPacienteElegido;
    }
    
    private void setDatosPerfil() {    	
    	nombrePerfil.setText(pacienteAMostrar.getNombre());
		apellidosPerfil.setText(pacienteAMostrar.getApellidos());
		dniPerfil.setText(pacienteAMostrar.getDni());
		fechaNacimientoPerfil.setText(FuncionesAuxiliares.TimeStampToString(pacienteAMostrar.getFechaNacimiento()));
		emailPerfil.setText(pacienteAMostrar.getEmail());
		telefonoPerfil.setText(Integer.toString(pacienteAMostrar.getTelefono()));
		direccionPerfil.setText(pacienteAMostrar.getDireccion());
		ciudadPerfil.setText(pacienteAMostrar.getCiudad());
		codigoPostalPerfil.setText(Integer.toString(pacienteAMostrar.getCodigoPostal()));
		provinciaPerfil.setText(pacienteAMostrar.getProvincia());
    }
    
    public void setChat(boolean activar) { 
    	activarChat = activar; 
    }
    
    public void cargarMensajes() {
// 		//Encontrar familiar activo
//        Usuario usr = Sistema.getUnico().getUsuarioLogueado();
//        int posCF=-1;
//        String[] cuidadores = pacienteAMostrar.getCuidador_familiar();
//        for (int i=0; i<cuidadores.length; i++) {
//        	if (cuidadores[i].equals(usr.getUsuario())) {
//        		posCF=i;
//        	}
//        }
// 		
//        Vector<Vector<String>> mensajes = pacienteAMostrar.getChat();
//        vBoxChat.setSpacing(12);
//        for (int i=0; i<mensajes.get(posCF).size(); i++) {
//        	Label label = new Label(mensajes.get(posCF).get(i));
//        	label.setTextFill(Color.WHITE);
//        	label.setBackground((new Background(new BackgroundFill(Color.rgb(49,68,96), new CornerRadii(5.0), new Insets(-5.0)))));
//        	//muestro labels a un lado y a otro dependiendo del emisor
//        	if (mensajes.get(posCF).get(i).charAt(0)=='F') {
//        		label.setAlignment(Pos.TOP_RIGHT);
//        	}
//        	else {
//        		label.setAlignment(Pos.TOP_LEFT);
//        	}
//        	vBoxChat.getChildren().add(label);
//        }
//        scrollChat.setContent(vBoxChat);
 	}
    
    private void cargarInfoMedica() {
    	infoMedicaPaciente= FuncionesAuxiliares.desserializarJsonAArrayInfoMedica(dniPacienteElegido);
    	// Se guarda la informacion medica del paciente en la lista
		for(InformacionMedica info : infoMedicaPaciente) {
			listaInformacionMedica.add(new InformacionMedica(info.getTitulo(),info.getFechaCreacion()));
		}
		//Se inicializan las columnas
    	infoMedicaPacienteDoc.setCellValueFactory(new PropertyValueFactory</*InformacionMedica, String*/>("Titulo"));
    	fechaInfoMedicaPacienteDoc.setCellValueFactory(new PropertyValueFactory</*InformacionMedica, String*/>("FechaCreacion"));
    	
    	//Se cargan los datos
    	tablaInfoMedica.setItems(listaInformacionMedica);
    	
    	//Busqueda de pacientes
    	busqueda(buscarInfo.getText());
    }
    
    public void busqueda(String  busqueda) {
		FilteredList<InformacionMedica> filteredData = new FilteredList<>(listaInformacionMedica, p -> true);
		tablaInfoMedica.setItems(filteredData);
		
		buscarInfo.textProperty().addListener((prop, old, text) -> {
		    filteredData.setPredicate(informacion -> {
		        if(text == null || text.isEmpty()) return true;
		        
		        String titulo = informacion.getTitulo().toLowerCase();  
		        String fecha = FuncionesAuxiliares.TimeStampToString(informacion.getFechaCreacion());
		        return titulo.contains(text.toLowerCase())||fecha.contains(text);
		    });
		});
		
		SortedList<InformacionMedica> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablaInfoMedica.comparatorProperty());

		tablaInfoMedica.setItems(sortedData);
	}  
    
    private void cambiarEstadoCamposInfoMedica(boolean editar) {
		tituloInfoMedica.setEditable(false);
		fechaInfoMedica.setVisible(true);
		textDescripcion.setEditable(false);
		textTratamiento.setEditable(false);
	}
}
