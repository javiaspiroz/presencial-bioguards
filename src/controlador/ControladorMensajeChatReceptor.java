package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import application.MensajeChat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ControladorMensajeChatReceptor {

	@FXML
    private Label lblFechaMensaje;
    @FXML
    private Label lblMensaje;
    
    private Node ihm;
    
    public ControladorMensajeChatReceptor(MensajeChat mensaje) throws IOException {
    
    	URL url= new URL(this.getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "vista/MensajeChatReceptor.fxml");
		FXMLLoader loader = new FXMLLoader (url);
		loader.setController(this);
		this.ihm = (Node) loader.load();
		
		lblFechaMensaje.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(mensaje.getFechaMensaje()));
		lblMensaje.setText(mensaje.getMensaje());
    }
    
	public Node getIhm() {
		return this.ihm;
	}
}