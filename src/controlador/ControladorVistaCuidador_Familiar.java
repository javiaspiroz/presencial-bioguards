//Vista cf
package controlador;

import java.util.Vector;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.Cuidador_Familiar;
import application.Paciente;
import application.Sistema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


public class ControladorVistaCuidador_Familiar {


	@FXML
	private AnchorPane CuidadorPane;
	@FXML
	private Button btnCuidadorCerrarSesion;
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


	// ----------- PACIENTES -----------
	@FXML
	private Button btnPacientes;
	@FXML
	private BorderPane vistaPacientes;    

	@FXML
	private TableView<Paciente> tablaPacienteDoc;
	@FXML
	private TableColumn<Paciente, String> nombrePacienteDoc;
	@FXML
	private TableColumn<Paciente, String> apellidosPacienteDoc;
	@FXML
	private TableColumn<Paciente, String> direccionPacienteDoc;
	@FXML
	private TableColumn<Paciente, String> botonPacienteDoc ;
	@FXML
	private Label lblNombrePaciente;
	@FXML
	private Label lblApellidosPaciente;
	@FXML
	private Label lblEstado;

	@FXML
	private Button btnConsultarPacienteDoc;	
	@FXML
	private Button btnActualizarPacienteDoc;

	
	private Cuidador_Familiar cuidadorLogueado;

	public String pacienteElegido;

	private ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();
	private ObservableList<String> listaDNIsPacientes = FXCollections.observableArrayList();

	public int submenu = 1;




	@FXML
	void initialize() {
		buscarCuidadorLogueado();

		identidad.setText("Bienvenido, " + cuidadorLogueado.getNombre() + " " + cuidadorLogueado.getApellidos());

		switch (submenu) {
		case 1:
			mostrarPerfil(null);
			break;
		case 2:
			mostrarPacientes(null);
			break;
		}  
	}

	// ------------------ PANEL PERFIL ------------------
	@FXML
	void mostrarPerfil(ActionEvent event) {
		setDatosPerfil();
		vistaPerfil.setVisible(true);
		vistaPacientes.setVisible(false);
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
		FuncionesAuxiliares.editarEmail(cuidadorLogueado, emailPerfil, emailEditable, btnEditarEmail, btnGuardarEmail);
	}	
	@FXML
	void guardarEmail(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarEmail(cuidadorLogueado, emailPerfil, emailEditable, btnGuardarEmail, btnEditarEmail));
	} 

	@FXML
	void editarTelefono(ActionEvent event) {
		FuncionesAuxiliares.editarTelefono(cuidadorLogueado, telefonoPerfil, telefonoEditable, btnEditarTelefono, btnGuardarTelefono);
	}        
	@FXML
	void guardarTelefono(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarTelefono(cuidadorLogueado, telefonoPerfil, telefonoEditable, btnGuardarTelefono, btnEditarTelefono));
	}

	@FXML
	void editarDireccion(ActionEvent event) {
		FuncionesAuxiliares.editarDireccion(cuidadorLogueado, direccionPerfil, direccionEditable, btnEditarDireccion, btnGuardarDireccion);
	}
	@FXML
	void guardarDireccion(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarDireccion(cuidadorLogueado, direccionPerfil, direccionEditable, btnGuardarDireccion, btnEditarDireccion));
	}

	@FXML
	void editarCiudad(ActionEvent event) {
		FuncionesAuxiliares.editarCiudad(cuidadorLogueado, ciudadPerfil, ciudadEditable, btnEditarCiudad, btnGuardarCiudad);
	}   
	@FXML
	void guardarCiudad(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarCiudad(cuidadorLogueado, ciudadPerfil, ciudadEditable, btnGuardarCiudad, btnEditarCiudad));
	}

	@FXML
	void editarCodigoPostal(ActionEvent event) {
		FuncionesAuxiliares.editarCodigoPostal(cuidadorLogueado, codigoPostalPerfil, codigoPostalEditable, btnEditarCodigoPostal, btnGuardarCodigoPostal);
	} 
	@FXML
	void guardarCodigoPostal(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarCodigoPostal(cuidadorLogueado, codigoPostalPerfil, codigoPostalEditable, btnGuardarCodigoPostal, btnEditarCodigoPostal));
	}

	@FXML
	void editarProvincia(ActionEvent event) {
		FuncionesAuxiliares.editarProvincia(cuidadorLogueado, provinciaPerfil, provinciaEditable, btnEditarProvincia, btnGuardarProvincia);
	}
	@FXML
	void guardarProvincia(ActionEvent event) {
		guardarDatosCuidador(FuncionesAuxiliares.guardarProvincia(cuidadorLogueado, provinciaPerfil, provinciaEditable, btnGuardarProvincia, btnEditarProvincia));
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


	// ------------------ PANEL PACIENTES ------------------
	@FXML
	void mostrarPacientes(ActionEvent event) {
		vistaPerfil.setVisible(false);
		vistaPacientes.setVisible(true);
		
		botonPacienteDoc.setCellValueFactory(new PropertyValueFactory<>("Contactar"));
		Callback<TableColumn<Paciente, String>, TableCell<Paciente, String>> cellFactory
		=  new Callback<TableColumn<Paciente, String>, TableCell<Paciente, String>>() {
			@Override
			public TableCell call(final TableColumn<Paciente, String> param) {
				final TableCell<Paciente, String> cell = new TableCell<Paciente, String>() {
					final Button btn = new Button();
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setPrefSize(30, 30);
                        	btn.getStyleClass().add("btnChat");
							btn.setOnAction(event -> {
								btn.isPressed();
								Paciente paciente = getTableView().getItems().get(getIndex());

								ControladorMostrarVentana.mostrarVentanaPacienteParaCF(paciente.getDni(),true);
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		botonPacienteDoc.setCellFactory(cellFactory);

		//Se inicializan las columnas
		nombrePacienteDoc.setCellValueFactory(new PropertyValueFactory</*Paciente, String*/>("Nombre"));
		apellidosPacienteDoc.setCellValueFactory(new PropertyValueFactory</*Paciente, String*/>("Apellidos"));
		direccionPacienteDoc.setCellValueFactory(new PropertyValueFactory</*Paciente, String*/>("Direccion"));

		//Se cargan los datos
		tablaPacienteDoc.setItems(listaPacientes);
	}

	//Sale el nombre y los apellidos de la persona seleccionada en la tabla
	@FXML
	void actualizar(ActionEvent event) {
		Paciente paciente = tablaPacienteDoc.getSelectionModel().getSelectedItem();
		if (paciente !=null) {
			lblNombrePaciente.setText(paciente.getNombre());
			lblApellidosPaciente.setText(paciente.getApellidos());
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ning�n paciente seleccionado");
		}		
	}


	@FXML
	void consultarPaciente(ActionEvent event) {
		Paciente paciente = tablaPacienteDoc.getSelectionModel().getSelectedItem();
		if (paciente!=null) {
			ControladorMostrarVentana.mostrarVentanaPacienteParaCF(paciente.getDni(),false);
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "No hay ning�n paciente seleccionado");
		}
	}


	@FXML
	void cerrarSesionCuidador(ActionEvent event) {
		Sistema.getUnico().logoutUsuario();
		ControladorMostrarVentana.mostrarLogin();
	}


	// ------------------ Funciones auxiliares ------------------
	private void buscarCuidadorLogueado() {
		// Se busca el clinico en funcion del usuario logueado;
		Vector<Cuidador_Familiar> cuidador = Sistema.getUnico().getfListaCuidadores_Familiares();
		for(Cuidador_Familiar c: cuidador) {
			if (Sistema.getUnico().getUsuarioLogueado().getUsuario().equals(c.getDni())) {
				cuidadorLogueado = c;
				break;
			}
		}

		// Se busca los pacientes del cuidador para guardarlos en la listaPacientes
		String[] pacientesCuidador = cuidadorLogueado.getPacientes();
		Vector<Paciente> pacientesSistema = Sistema.getUnico().getfListaPacientes();
		for(String pCuidador : pacientesCuidador) {
			for (Paciente pSistema : pacientesSistema) {
				if (pCuidador.equals(pSistema.getDni())) {
					listaPacientes.add(new Paciente(pSistema.getNombre(), pSistema.getApellidos(),pSistema.getDni(),pSistema.getDireccion()));
					listaDNIsPacientes.add(pSistema.getDni());
				}
			}
		}
	}
	
	private void setDatosPerfil() {    	
		nombrePerfil.setText(cuidadorLogueado.getNombre());
		apellidosPerfil.setText(cuidadorLogueado.getApellidos());
		dniPerfil.setText(cuidadorLogueado.getDni());
		fechaNacimientoPerfil.setText(FuncionesAuxiliares.TimeStampToString(cuidadorLogueado.getFechaNacimiento()));

		emailPerfil.setText(cuidadorLogueado.getEmail());
		FuncionesAuxiliares.mostrarElementosNoEditables(emailPerfil, emailEditable, btnGuardarEmail, btnEditarEmail);

		telefonoPerfil.setText(Integer.toString(cuidadorLogueado.getTelefono()));
		FuncionesAuxiliares.mostrarElementosNoEditables(telefonoPerfil, telefonoEditable, btnGuardarTelefono, btnEditarTelefono);

		direccionPerfil.setText(cuidadorLogueado.getDireccion());
		FuncionesAuxiliares.mostrarElementosNoEditables(direccionPerfil, direccionEditable, btnGuardarDireccion, btnEditarDireccion);

		ciudadPerfil.setText(cuidadorLogueado.getCiudad());
		FuncionesAuxiliares.mostrarElementosNoEditables(ciudadPerfil, ciudadEditable, btnGuardarCiudad, btnEditarCiudad);

		codigoPostalPerfil.setText(Integer.toString(cuidadorLogueado.getCodigoPostal()));
		FuncionesAuxiliares.mostrarElementosNoEditables(codigoPostalPerfil, codigoPostalEditable, btnGuardarCodigoPostal, btnEditarCodigoPostal);

		provinciaPerfil.setText(cuidadorLogueado.getProvincia());
		FuncionesAuxiliares.mostrarElementosNoEditables(provinciaPerfil, provinciaEditable, btnGuardarProvincia, btnEditarProvincia);

		usuario.setText(Sistema.getUnico().getUsuarioLogueado().getUsuario());
		usuario.setEditable(false);

		contrasena.setText(Sistema.getUnico().getUsuarioLogueado().getContrasena());
		contrasena.setEditable(false);
		btnGuardarContrasena.setVisible(false);
		btnEditarContrasena.setVisible(true);
	}

	private void guardarDatosCuidador(boolean valido) {
		if (valido && FuncionesAuxiliares.serializarArrayAJsonCuidadores_Familiares()) {
			FuncionesAuxiliares.getAlertaInformacion("Guardado", "Dato actualizado correctamente.");
		} 
	}


}
