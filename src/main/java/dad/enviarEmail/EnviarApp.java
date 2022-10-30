package dad.enviarEmail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// David Alejandro Hernández Alonso 2º DAM A
public class EnviarApp extends Application{

	public static Stage primaryStage;

	private Controller controller;

	@Override
	public void start(Stage primaryStage) throws Exception {

		EnviarApp.primaryStage = primaryStage;

		controller = new Controller();
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("EnviarEmail");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(EnviarApp.class.getResourceAsStream("/email-send-icon-32x32.png")));
		primaryStage.show();
		 
		//scene.getRoot().requestFocus();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
