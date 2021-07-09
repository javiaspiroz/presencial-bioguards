package controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.Cuidador_Familiar;
import application.IButtonChatListener;
import application.InformacionMedica;
import application.MensajeChat;
import application.Paciente;
import application.Perfil;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ControladorVistaPacienteParaClinico implements IButtonChatListener {	
	@FXML
    private AnchorPane PacienteParaClinicoPane;
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
    @FXML
    private Button btnAddInfo;
    
    // General
    @FXML
    private BorderPane vistaInfoMedicaGeneral;
    @FXML
    private TableView<InformacionMedica> tablaInfoMedica;
    @FXML
    private TableColumn<InformacionMedica, String> infoMedicaPacienteDoc;
    @FXML
    private TableColumn<InformacionMedica, Timestamp> fechaInfoMedicaPacienteDoc;
    
    // Especifica
    @FXML
    private BorderPane vistaInfoMedicaDetalles;
    @FXML
    private JFXTextField tituloInfoMedica;
    @FXML
    private Label fechaInfoMedica;
    @FXML
    private JFXDatePicker fechaElegirInfoMedica;
    @FXML
    private Button btnEditarInfoMedica;
    @FXML
    private Button btnGuardarInfoMedica;
    @FXML
    private Button btnEliminarInfoMedica;
    @FXML
    private Button btnVolverInfoMedica;
    @FXML
    private JFXTextArea textDescripcion;
    @FXML
    private JFXTextArea textTratamiento;
    
    
// ----------- CONTACTAR -----------
    @FXML
    private Button btnContactar;
    @FXML
    private BorderPane vistaContactar;
   
    @FXML
    private VBox VBoxChatPaciente;
    @FXML
    private VBox VBoxChatFamiliares;
    @FXML
    private VBox VBoxChat;
    @FXML
    private ScrollPane scrollChat;
    @FXML
    private TextField newMsg;
    @FXML
    private Button sendMsg;
    @FXML
    private Label lblNombreChatAbierto;
    @FXML
    private ImageView imgRolChat;
    
   
    private Paciente pacienteAMostrar;
    private String dniPacienteElegido;
    private boolean activarChat;
    private String fechaSensores= DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    
    private Vector<Paciente> listaPacientes = Sistema.getUnico().getfListaPacientes();
    private Vector<InformacionMedica> infoMedicaPaciente;
    private ObservableList<InformacionMedica> listaInformacionMedica = FXCollections.observableArrayList();
    private boolean nuevaInfoMedica = false;
 

    
    
    @FXML
    void initialize() throws IOException {
    	pacienteAMostrar = buscarPacienteActual();
    	identidad.setText("Paciente: " + pacienteAMostrar.getNombre() + " " + pacienteAMostrar.getApellidos());
    	setDatosPerfil();
    	if(activarChat==true) {
    		mostrarContactar(null);
    	} else {
    		vistaPerfil.setVisible(true);
        	vistaSensores.setVisible(false);
        	vistaInfoMedica.setVisible(false);
        	vistaContactar.setVisible(false);
    	}
	}
   
// ------------------ PANEL PERFIL ------------------
	@FXML
    void mostrarPerfil(ActionEvent event) {
		vistaPerfil.setVisible(true);
    	vistaSensores.setVisible(false);
    	vistaInfoMedica.setVisible(false);
    	vistaContactar.setVisible(false);
    }

    
// ------------------ PANEL SENSORES ------------------
	@FXML
    void mostrarSensores(ActionEvent event) {
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(true);
    	vistaInfoMedica.setVisible(false);	
    	vistaContactar.setVisible(false);
    	
//    	graficalineal.getData().clear();
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
    	FuncionesAuxiliares.mostrarSensores(dniPacienteElegido, graficalineal, pacienteAMostrar.getSensores(), fechaSensores, "Sensor 3", "pulsioximetro");
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
//			FuncionesAuxiliares.mostrarSensoresFecha(dniPacienteElegido,graficalineal, listaPacientes, sensores, fecha, Tipo[i], Nombre[i]);
			FuncionesAuxiliares.mostrarTodosSensores(dniPacienteElegido, graficalineal, pacienteAMostrar.getSensores(), fechaSensores, Tipo[i], Nombre[i]);
		}
    }
	
// ------------------ PANEL INFORMACION MEDICA ------------------
	@FXML
    void mostrarInfoMedica(ActionEvent event) {
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaInfoMedica.setVisible(true);
    	vistaContactar.setVisible(false);
    	
    	mostrarVistaInfoMedicaGeneral(true);
		cargarInfoMedica();
	}

	@FXML
    void ampliarInfo(ActionEvent event) {
		InformacionMedica informacionSeleccionada = tablaInfoMedica.getSelectionModel().getSelectedItem();
		if (informacionSeleccionada!=null) {
			for(InformacionMedica i : infoMedicaPaciente) {
				if(informacionSeleccionada.getTitulo().equals(i.getTitulo()) && informacionSeleccionada.getFechaCreacion().equals(i.getFechaCreacion())) {
					tituloInfoMedica.setText(i.getTitulo());
					fechaInfoMedica.setText(FuncionesAuxiliares.TimeStampToString(i.getFechaCreacion()));
					textDescripcion.setText(i.getDescripcion());
					textTratamiento.setText(i.getTratamiento());
					break;
				}
			}
			cambiarEstadoCamposInfoMedica(false);
			mostrarVistaInfoMedicaGeneral(false);
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ningun elemento seleccionado");
		}
    }
	
    @FXML
    void editarInfoMedica(ActionEvent event) {
    	InformacionMedica informacionSeleccionada = tablaInfoMedica.getSelectionModel().getSelectedItem();
    	if (informacionSeleccionada!=null) {
			for(InformacionMedica i : infoMedicaPaciente) {
				if(informacionSeleccionada.getTitulo().equals(i.getTitulo()) && informacionSeleccionada.getFechaCreacion().equals(i.getFechaCreacion())) {
					tituloInfoMedica.setText(i.getTitulo());
					LocalDate fecha = i.getFechaCreacion().toLocalDateTime().toLocalDate();
					fechaElegirInfoMedica.setValue(fecha);
					textDescripcion.setText(i.getDescripcion());
					textTratamiento.setText(i.getTratamiento());
					break;
				}
			}
			nuevaInfoMedica = false;
			cambiarEstadoCamposInfoMedica(true);
			mostrarVistaInfoMedicaGeneral(false);
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ningun elemento seleccionado");
		}
    }
   
    @FXML
    void addInfo(ActionEvent event) {
    	cambiarEstadoCamposInfoMedica(true);
		mostrarVistaInfoMedicaGeneral(false);
		nuevaInfoMedica = true;
		// Se asegura que los campos estan vacios
    }
	
	@FXML
    void eliminarInfoMedica(ActionEvent event) {
		InformacionMedica informacionSeleccionada = tablaInfoMedica.getSelectionModel().getSelectedItem();
		if (informacionSeleccionada!=null) {
			for(InformacionMedica i : infoMedicaPaciente) {
				if(informacionSeleccionada.getTitulo().equals(i.getTitulo()) && informacionSeleccionada.getFechaCreacion().equals(i.getFechaCreacion())) {
					infoMedicaPaciente.remove(i);
					break;
				} 
			}
			if (FuncionesAuxiliares.serializarArrayAJsonInfoMedica(infoMedicaPaciente, dniPacienteElegido)) {
				FuncionesAuxiliares.getAlertaInformacion("Eliminado", "Se han eliminado correctamente los datos.");
				cargarInfoMedica();
				
			}
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ningun elemento seleccionado");
		}
    }
    
    @FXML
    void guardarInfoMedica(ActionEvent event) {
		if (!tituloInfoMedica.getText().trim().equals("") && fechaElegirInfoMedica.getValue()!= null && 
    		!textDescripcion.getText().trim().equals("") && !textTratamiento.getText().trim().equals("")) {
			if (tituloRepetido()) {
				FuncionesAuxiliares.getAlertaError("Error", "El titulo ya existe");
			} else {
				guardarInfoMedica();
			}
		} else {
    			FuncionesAuxiliares.getAlertaError("Error", "Campos vacios o valores no validos.");
		}
    }
    
	@FXML
    void volverInfoMedica(ActionEvent event) {
		cargarInfoMedica();
		mostrarVistaInfoMedicaGeneral(true);
		tituloInfoMedica.setText(null);
		fechaElegirInfoMedica.setPromptText(null);
		textDescripcion.setText(null);
		textTratamiento.setText(null);
		nuevaInfoMedica = true;
    }
    
		
	
// ------------------ PANEL CONTACTAR ------------------
	@FXML
    void mostrarContactar(ActionEvent event) throws IOException {
		VBoxChatPaciente.getChildren().clear();
		ControladorBtnPerfilChat btnChatPaciente = new ControladorBtnPerfilChat(pacienteAMostrar, "btnChatPaciente.fxml", this);
		VBoxChatPaciente.getChildren().add(btnChatPaciente.getIhm());
		
		VBoxChatFamiliares.getChildren().clear();
		String[] dniCuidadoresPaciente = pacienteAMostrar.getCuidador_familiar();
		for (String dniCuidador: dniCuidadoresPaciente) {
			Cuidador_Familiar c_f = FuncionesAuxiliares.getCuidadorPorDNI(dniCuidador);
			ControladorBtnPerfilChat btnChatCuidador = new ControladorBtnPerfilChat(c_f, "btnChatCuidador_Familiar.fxml", this);
			VBoxChatFamiliares.getChildren().add(btnChatCuidador.getIhm());
		}
		
		cargarChatPaciente(pacienteAMostrar);
		
		vistaPerfil.setVisible(false);
    	vistaSensores.setVisible(false);
    	vistaInfoMedica.setVisible(false);
    	vistaContactar.setVisible(true);
    }
	
	@FXML
    void enviarMensaje(ActionEvent event) {
//		if (comboNombres.getValue()!=null) {//evito enviar mensajes sin chat chat seleecionado
//			String newM = newMsg.getText();
//			//Se da formato al mensaje dependiendo del usuario que escribe
//			newM = "C: "+newM;
//			//Se actualizan los mensajes en datos del paciente
//			Vector<Vector<String>> mensajes = pacienteAMostrar.getChat();
//			mensajes.get(chatActivo).add(newM);			
//			pacienteAMostrar.setChat(mensajes);
//			//Se exportan los datos nuevos
//			FuncionesAuxiliares.serializarArrayAJsonPacientes();
//			//Se actualiza la interfaz
//			newMsg.setText("");
//			vBoxChat.getChildren().clear();
//			cargarMensajes(chatActivo);
//		}
    }

	@Override
	public void onClick(Perfil perfil) {
		if (perfil instanceof Paciente) {
			cargarChatPaciente((Paciente) perfil);
		} else if (perfil instanceof Cuidador_Familiar) {
			cargarChatCuidador((Cuidador_Familiar) perfil) ;
		}
	}
	
	private void cargarChatPaciente (Paciente paciente) {
		Vector<MensajeChat> chatPaciente = new Vector <MensajeChat>();
		Vector<MensajeChat> chats = Sistema.getUnico().getfChats();
		String dniClinicoLogueado = Sistema.getUnico().getUsuarioLogueado().getUsuario();
		for (MensajeChat mensajeChat: chats) {
			if ((mensajeChat.getDniEmisor().equalsIgnoreCase(dniClinicoLogueado) && mensajeChat.getDniReceptor().equalsIgnoreCase(paciente.getDni())) ||
			    (mensajeChat.getDniReceptor().equalsIgnoreCase(dniClinicoLogueado) && mensajeChat.getDniEmisor().equalsIgnoreCase(paciente.getDni()))) {
				chatPaciente.add(mensajeChat);
			} 
		}
		VBoxChat.getChildren().clear();
		for (MensajeChat mensajeChat: chatPaciente) {
			if (mensajeChat.getDniEmisor().equalsIgnoreCase(dniClinicoLogueado)) {
				ControladorMensajeChatEmisor entradaMensajeEmisor;
				try {
					entradaMensajeEmisor = new ControladorMensajeChatEmisor(mensajeChat);
					VBoxChat.getChildren().add(entradaMensajeEmisor.getIhm());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				ControladorMensajeChatReceptor entradaMensajeReceptor;
				try {
					entradaMensajeReceptor = new ControladorMensajeChatReceptor(mensajeChat);
					VBoxChat.getChildren().add(entradaMensajeReceptor.getIhm());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void cargarChatCuidador (Cuidador_Familiar cuidador_familiar) {
		FuncionesAuxiliares.getAlertaInformacion("cuidador", cuidador_familiar.getDni());
	}
	
	
	
	
	
	
    @FXML
	void volverVistaClinico(ActionEvent event) {
    	ControladorMostrarVentana.mostrarVentanaClinico(2);
	}
    
    
    
 // ------------------ FUNCIONES AUXILIARES ------------------
    public Paciente buscarPacienteActual() {
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

    public void setChat(boolean activar) { 
    	activarChat = activar; 
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
    
    private void cargarInfoMedica() {
    	infoMedicaPaciente= FuncionesAuxiliares.desserializarJsonAArrayInfoMedica(dniPacienteElegido);
    	listaInformacionMedica.clear();
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
		mostrarVistaInfoMedicaGeneral(true);
		
	}  
    
    private void mostrarVistaInfoMedicaGeneral(boolean mostrar) {
    	if (mostrar) {
			vistaInfoMedicaGeneral.setVisible(true);
			vistaInfoMedicaDetalles.setVisible(false);
    	} else {
			vistaInfoMedicaGeneral.setVisible(false);
			vistaInfoMedicaDetalles.setVisible(true);
    	}
	}
    
    private boolean tituloRepetido() {
		int j = 0;
		for(InformacionMedica i : infoMedicaPaciente) {
			if(tituloInfoMedica.getText().equals(i.getTitulo())) {
				j++;
			} 
		}
		if (j>1) {
			return true;
		}
		return false;
	}

    private void guardarInfoMedica() {
    	String fecha = fechaElegirInfoMedica.getEditor().getText();
		Timestamp fechaAGuardar = FuncionesAuxiliares.StringToTimestamp(fecha, "guardar");
    	if (nuevaInfoMedica) {
			infoMedicaPaciente.add(new InformacionMedica (tituloInfoMedica.getText(), fechaAGuardar,
														textDescripcion.getText(), textTratamiento.getText()));
		} else {
	    	InformacionMedica informacionSeleccionada = tablaInfoMedica.getSelectionModel().getSelectedItem();
			for(InformacionMedica i : infoMedicaPaciente) {
				if(informacionSeleccionada.getTitulo().equals(i.getTitulo()) && informacionSeleccionada.getFechaCreacion().equals(i.getFechaCreacion())) {
					i.setTitulo(tituloInfoMedica.getText());
					i.setFechaCreacion(fechaAGuardar);
					i.setDescripcion(textDescripcion.getText());
					i.setTratamiento(textTratamiento.getText());
					break;
				} 
			}
		}
		
		if (FuncionesAuxiliares.serializarArrayAJsonInfoMedica(infoMedicaPaciente, dniPacienteElegido)) {
			FuncionesAuxiliares.getAlertaInformacion("Guardado", "Se han guardado correctamente los datos.");
			nuevaInfoMedica = false;
			
		}
	}
    
    private void cambiarEstadoCamposInfoMedica(boolean editar) {
    	// Si es true se ponen visibles los datos para editar
		if (editar) {
			tituloInfoMedica.setEditable(true);
			
			fechaInfoMedica.setVisible(false);
			fechaElegirInfoMedica.setVisible(true);
			
			textDescripcion.setEditable(true);
			textTratamiento.setEditable(true);
			
			btnGuardarInfoMedica.setVisible(true);
		} else {
			tituloInfoMedica.setEditable(false);
			
			fechaInfoMedica.setVisible(true);
			fechaElegirInfoMedica.setVisible(false);
			
			textDescripcion.setEditable(false);
			textTratamiento.setEditable(false);
			
			btnGuardarInfoMedica.setVisible(false);
		}
	}
}