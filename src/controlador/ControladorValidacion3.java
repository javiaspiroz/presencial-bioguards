package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXPasswordField;
import application.Sistema;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorValidacion3 {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;

	@FXML
	private JFXPasswordField contrasenaNueva;
	@FXML
	private JFXPasswordField confirmacionContrasenaNueva;

	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;


	@FXML
	void initialize() {
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	contrasenaNueva.requestFocus();
    	    }
    	});
    	btnAceptar.setDefaultButton(true);
	}

	@FXML
	void volverLogin(ActionEvent event) {
		ControladorMostrarVentana.mostrarLogin();
	} 

	@FXML
	void modificarContrasena(ActionEvent event) {
		if (contrasenaNueva.getText().equals(confirmacionContrasenaNueva.getText())) {
			String infoSeguridad = FuncionesAuxiliares.validarPassword(contrasenaNueva.getText());
			if (infoSeguridad.equals("Contraseña segura")) {
				Sistema.getUnico().getUsuarioLogueado().setContrasena(contrasenaNueva.getText());
				FuncionesAuxiliares.getAlertaInformacion("Actualización contraseña", "¡Contraseña actualizada con éxito!");
				FuncionesAuxiliares.serializarArrayAJsonUsuario();
				Sistema.getUnico().logoutUsuario();
				ControladorMostrarVentana.mostrarLogin();
			} else {
				FuncionesAuxiliares.getAlertaInformacion("Seguridad", infoSeguridad);
			}
		} else {
			FuncionesAuxiliares.getAlertaError("Error", "Las contraseñas no coinciden");
		}
	}
}