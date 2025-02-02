package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Initialize extends Application {
	
	// Start method called when JavaFX application is launched
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Startup.fxml"));
			Scene scene = new Scene(root,1000,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Swim Tracker");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Main method launches JavaFX application
	public static void main(String[] args) {
		launch(args);
	}

}
