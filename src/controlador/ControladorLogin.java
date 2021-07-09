package controlador;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import application.Sistema;

public class ControladorLogin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginMainPane;

    @FXML
    private ColumnConstraints gridImages;

    @FXML
    private GridPane loginPaneBotones;

    @FXML
    private GridPane loginGridRegistro;

    @FXML
    private Label loginResText;

    @FXML
    private Button loginBotonResg;

    @FXML
    private JFXTextField loginUsuario;

    @FXML
    private JFXPasswordField loginPass;

    @FXML
    private Button loginEntrar;

    @FXML
    private Button loginRecuperar;
    
    @FXML
    void initialize() {
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	loginUsuario.requestFocus();
    	    }
    	});
    	loginEntrar.setDefaultButton(true);
    }
	
    @FXML
    void iniciarSesion(ActionEvent event) {
       	//Se coge el texto de inputs de usuario
		String user = loginUsuario.getText();
		String pass = loginPass.getText();
		
		//Entra en el perfil correcto dependiendo del rol que inicia sesion
		if (Sistema.getUnico().loginUsuario(user, pass)) {
			Usuario usr = Sistema.getUnico().getUsuarioLogueado();
			if (usr.getRol().equals("paciente") && usr.getContrasena().equals(pass) && usr.getUsuario().equals(user)) {		
				ControladorMostrarVentana.mostrarVentanaPaciente();
			} else if (usr.getRol().equals("administrador") && usr.getContrasena().equals(pass) && usr.getUsuario().equals(user)) {
				ControladorMostrarVentana.mostrarVentanaAdministrador();
			} else if (usr.getRol().equals("clinico") && usr.getContrasena().equals(pass) && usr.getUsuario().equals(user)) {
				ControladorMostrarVentana.mostrarVentanaClinico(1);
			} else if (usr.getRol().equals("cuidador") && usr.getContrasena().equals(pass) && usr.getUsuario().equals(user)) {
				ControladorMostrarVentana.mostrarVentanaCuidador_Familiar(1);
			} 
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "El usuario o la contraseña no son correctas");
		}
	}
    
    @FXML
    void recuperarPass(ActionEvent event) {
    	ControladorMostrarVentana.mostrarRecuperarPassword1();
    }

    @FXML
    void registrar(ActionEvent event) {
	   ControladorMostrarVentana.mostrarVentanaRegistrar();
    }
}