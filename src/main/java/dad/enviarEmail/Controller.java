package dad.enviarEmail;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

//David Alejandro Hernández Alonso 2º DAM A
public class Controller implements Initializable {

	private Model model = new Model();

	public Controller() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PuertoNumero.textProperty().set(""); // Por defecto se inicializa a "0", de esta manera se evita eso
	}

	@FXML
	private Label AsuntoLabel;

	@FXML
	private TextField AsuntoTexto;

	@FXML
	private VBox BotonesVBox;

	@FXML
	private Button CerrarBoton;

	@FXML
	private Label ConexionLabel;

	@FXML
	private TextField ContrasenaTexto;

	@FXML
	private Label DestinatarioLabel;

	@FXML
	private TextField EmailDESTexto;

	@FXML
	private TextField EmailREMTexto;

	@FXML
	private Button EnviarBoton;

	@FXML
	private GridPane EnviarView;

	@FXML
	private TextArea MensajeTextArea;

	@FXML
	private TextField NombreTexto;

	@FXML
	private TextField PuertoNumero;

	@FXML
	private Label RemitenteLabel;

	@FXML
	private CheckBox sslCheckBox;

	@FXML
	private HBox ServidorHBox;

	@FXML
	private Label ServidorLabel;

	@FXML
	private Button VaciarBoton;
	
	private ArrayList<TextField> listaTextField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings
		
		NombreTexto.textProperty().bindBidirectional(model.nombreHostTextoProperty()); //model.nombreHostTextoProperty().bind(NombreTexto.textProperty());
		PuertoNumero.textProperty().bindBidirectional(model.puertoNumeroProperty(), new NumberStringConverter());
		sslCheckBox.selectedProperty().bindBidirectional(model.sslProperty());
		EmailREMTexto.textProperty().bindBidirectional(model.remitenteTextoProperty());
		ContrasenaTexto.textProperty().bindBidirectional(model.contrasenaTextoProperty());
		EmailDESTexto.textProperty().bindBidirectional(model.destinataroTextoProperty());
		AsuntoTexto.textProperty().bindBidirectional(model.asuntoTextoProperty());
		MensajeTextArea.textProperty().bindBidirectional(model.mensajeTextoProperty());
		
		// load data
		
		listaTextField = new ArrayList<TextField>(Arrays.asList(NombreTexto, PuertoNumero, EmailREMTexto, ContrasenaTexto, EmailDESTexto, AsuntoTexto));
		
		EnviarBoton.disableProperty().bind(Bindings.when(model.puertoNumeroProperty().lessThan(1)
														.or((model.puertoNumeroProperty().greaterThan(99999)))
														.or(model.remitenteTextoProperty().isEmpty())
														.or(model.destinataroTextoProperty().isEmpty()))
															.then(true)
															.otherwise(false));
		
		EmailREMTexto.styleProperty().bind(Bindings.when(EmailREMTexto.textProperty().isEmpty())
												.then("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;")
												.otherwise("")); // Si está vacío el TextField se pone en rojo
		
		EmailDESTexto.styleProperty().bind(Bindings.when(EmailDESTexto.textProperty().isEmpty())
												.then("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;")
												.otherwise("")); // Si está vacío el TextField se pone en rojo
		
		PuertoNumero.styleProperty().bind(Bindings.when(model.puertoNumeroProperty().greaterThan(99999)
														.or(PuertoNumero.textProperty().isEmpty()))
															.then("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;")
															.otherwise("")); // Si está vacío el TextField se pone en rojo y permite como máximo el valor a 99999
		
		ServidorLabel.textFillProperty().bind(Bindings.when(PuertoNumero.textProperty().isEmpty())
																.then(Color.color(1, 0, 0)).otherwise(Color.BLACK));
		
		RemitenteLabel.textFillProperty().bind(Bindings.when(EmailREMTexto.textProperty().isEmpty())
																.then(Color.color(1, 0, 0)).otherwise(Color.BLACK));
		
		DestinatarioLabel.textFillProperty().bind(Bindings.when(EmailDESTexto.textProperty().isEmpty())
																	.then(Color.color(1, 0, 0)).otherwise(Color.BLACK));
		
		PuertoNumero.addEventFilter(KeyEvent.KEY_TYPED, e -> {
			if (e.getCharacter().charAt(0)<'0' || e.getCharacter().charAt(0)>'9') {		// De esta forma el KeyEvent si funciona
	            e.consume();
	        }
			// Esto lo probé añadiendo un evento KeyEvent (On Key Typed) en el scene builder y luego
			// cargándolo aquí, pero no funcionaba de ninguna manera (al menos el "e.consume()")
		});
	}
	
	public GridPane getView() {
		return EnviarView;
	}
	
	@FXML
    void puertoNumeroAdd(KeyEvent event) {
		/*if (e.getCharacter().charAt(0)<'0' || e.getCharacter().charAt(0)>'9') {		// Esto por algún motivo no funciona
            e.consume();
        }*/
    }

	@FXML
	void OnCerrarAction(ActionEvent event) {
		Stage appVentana = (Stage) CerrarBoton.getScene().getWindow();
		appVentana.close();
	}

	@FXML
	void OnEnviarAction(ActionEvent event) {
		
		boolean enviado = false;
		
		String exception = "";
		try {
			Email email = new SimpleEmail();
			email.setHostName(model.getNombreHostTexto());	// host
			email.setSmtpPort(model.getPuertoNumero());		// puerto
			email.setSSLOnConnect(model.isSsl());		// Usar ssl
			email.setAuthenticator(new DefaultAuthenticator(model.getRemitenteTexto(), model.getContrasenaTexto()));	// Email remitente y contraseña
			email.setFrom(model.getRemitenteTexto());	// Remitente
			email.addTo(model.getDestinataroTexto());	// Destinatario
			email.setSubject(model.getAsuntoTexto());	// Asunto
			email.setMsg(model.getMensajeTexto());		// Mensaje
			email.send();		// Enviar el email
			
			enviado = true;
		} catch (EmailException e) {
			exception = e.getMessage();
		}
		
		Alert dialogo = enviado ? new Alert(AlertType.INFORMATION) : new Alert(AlertType.ERROR);
    	dialogo.initOwner(EnviarApp.primaryStage);
    	dialogo.setTitle(enviado ? "Mensaje enviado" : "Error");
    	dialogo.setHeaderText(enviado ? "Mensaje enviado con éxito a '" + model.getRemitenteTexto() + "'." : "No se pudo enviar el email.");
    	dialogo.setContentText(enviado ? "" : exception);
    	Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();
        stage.getIcons().setAll(EnviarApp.primaryStage.getIcons());
    	dialogo.showAndWait();
	}

	@FXML
	void OnVaciarAction(ActionEvent event) {
		for(TextField elementoTextField : listaTextField) {
			elementoTextField.textProperty().set("");
		}
		sslCheckBox.selectedProperty().set(false);
		MensajeTextArea.textProperty().set("");		// Vaciando elementos individualmente que no están en el array listaTextField
	}
	
	
}
