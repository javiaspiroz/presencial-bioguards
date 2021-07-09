package controlador;

import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import com.jfoenix.controls.JFXTextField;
import application.Administrador;
import application.Clinico;
import application.Cuidador_Familiar;
import application.Paciente;
import application.Sistema;
import application.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorValidacion1 {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXTextField email;

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	private String dniPerfil;

	@FXML
	void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				email.requestFocus();
			}
		});
		btnAceptar.setDefaultButton(true);
	}

	@FXML
	void volverLogin(ActionEvent event) {
		ControladorMostrarVentana.mostrarLogin();
	}

	@FXML
	void validarEmail(ActionEvent event) {

		String emailVerificar = email.getText();

		if (!buscarEmail(emailVerificar)) {
			FuncionesAuxiliares.getAlertaError("Reestablecimiento de contraseï¿½a",
					"El correo electrï¿½nico introducido no estï¿½ registrado.");
		} else {

			String codigoGenerado = generarCodigo();
			if (enviarMail(emailVerificar, codigoGenerado)) {
				asignarUsuarioEmail();
				ControladorMostrarVentana.mostrarRecuperarPassword2(codigoGenerado);
			} else {
				ControladorMostrarVentana.mostrarLogin();
			}
		}
	}

	public boolean buscarEmail(String emailVerificar) {
		boolean encontrado = false;

		Vector<Administrador> administrdores = Sistema.getUnico().getfListaAdministradores();
		for (Administrador a : administrdores) {
			if (emailVerificar.equals(a.getEmail())) {
				dniPerfil = a.getDni();
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			Vector<Clinico> clinicos = Sistema.getUnico().getfListaClinicos();
			for (Clinico c : clinicos) {
				if (emailVerificar.equals(c.getEmail())) {
					dniPerfil = c.getDni();
					encontrado = true;
					break;
				}
			}
		}

		if (!encontrado) {
			Vector<Paciente> pacientes = Sistema.getUnico().getfListaPacientes();
			for (Paciente p : pacientes) {
				if (emailVerificar.equals(p.getEmail())) {
					dniPerfil = p.getDni();
					encontrado = true;
					break;
				}
			}
		}

		if (!encontrado) {
			Vector<Cuidador_Familiar> cuidadores_familiares = Sistema.getUnico().getfListaCuidadores_Familiares();
			for (Cuidador_Familiar c_f : cuidadores_familiares) {
				if (emailVerificar.equals(c_f.getEmail())) {
					dniPerfil = c_f.getDni();
					encontrado = true;
					break;
				}
			}
		}
		return encontrado;
	}

	private String generarCodigo() {
		Random r = new Random();
		int n = r.nextInt(9999 - 1) + 1;
		return String.format("%04d", n);
	}

	public boolean enviarMail(String toEmail, String valor) {
		// Origen
		final String fromEmail = "companythenas";
		final String password = "contrasena123";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", fromEmail);
		props.put("mail.smtp.clave", password);
		props.put("mail.smtp.auth", "true"); // Usar autenticaciï¿½n mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);

			// Se rellena el From y los destinatarios
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			// Se rellena el subject
			message.setSubject("Recuperaciï¿½n de contraseï¿½a BioGuards");

			message.setText("Se modificara tu cotraseña al introducir el siguiente codigo de verificacion:\n" + valor);
			
			// Envio
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", fromEmail, password);
			Transport.send(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException e1) {
			FuncionesAuxiliares.getAlertaError("Error", "El email no se ha podido enviar");
			return false;
		}
	}

	private void asignarUsuarioEmail() {
		Vector<Usuario> usr = Sistema.getUnico().getFListaUsuarios();
		for (Usuario u : usr) {
			if (dniPerfil.equals(u.getUsuario())) {
				Sistema.getUnico().setfUsuarioLogueado(u);
				break;
			}
		}
	}
}
