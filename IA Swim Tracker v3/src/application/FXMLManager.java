package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class FXMLManager {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Swimmer instance;
	
	/**
	 * Loads and switches to a new scene from "Startup.fxml"
	 * Displays the startup menu.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchStartup(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Startup.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "Main.fxml"
	 * Displays the main menu.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		try {
			stage.setScene(scene);
			stage.setTitle("Swim Tracker");
			stage.show();
		} catch (Exception ex) {
		}
	}
	
	/**
	 * Loads and switches to a new scene from "NewUser.fxml"
	 * Allows for the creation of a new user.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchNewUser(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "ReturningUser.fxml"
	 * Allows a returning user to import their save file.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchReturningUser(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReturningUser.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "NewTimeDetails.fxml"
	 * Allows user to enter details to create new Race object.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchNewTimeDetails(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("NewTimeDetails.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "NewTimeVariables.fxml"
	 * Allows user to enter variables to create new Race object.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchNewTimeVariables(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("NewTimeVariables.fxml"));
		try {
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Swim Tracker");
			stage.show();
		} catch (Exception ex) {
		}
		
	}
	
	/**
	 * Loads and switches to a new scene from "AllTimes.fxml"
	 * Displays all stored races.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchAllTimes(ActionEvent e) {
		try {
	    	root = FXMLLoader.load(getClass().getResource("AllTimes.fxml"));
	        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Swim Tracker");
	        stage.show();
	    } catch (IOException ex) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Alert");
	        alert.setHeaderText(null);
	        alert.setContentText("There are no stored times!");
	        alert.showAndWait();
	    }
	}
	
	/**
	 * Loads and switches to a new scene from "SelectComparisonEvent.fxml"
	 * Allows user to select an event to see time dropped and percent improvement
	 * over time.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchSelectEventComparison(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SelectComparisonEvent.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "SingleEventComparison.fxml"
	 * Displays time dropped and percent improvement from selected event.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchSingleEventComparison(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SingleEventComparison.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "SelectGraph.fxml"
	 * Allows user to select an event and course to compare in graph form.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchSelectGraph(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SelectGraph.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "Graph.fxml"
	 * Displays a graph of a selected event.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchGraph(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Graph.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	
	/**
	 * Loads and switches to a new scene from "EditProfile.fxml"
	 * Allows user to edit their name, age, and gender.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchProfile(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	/**
	 * Loads and switches to a new scene from "TimeStandards.fxml"
	 * Displays time standards from Ohio High School Athletic Association meets 
	 * and USA Swimming meets.
	 * 
	 * @param e ActionEvent triggered by user action.
	 * @throws IOException if there is an error loading the FXML file.
	 */
	public void switchStandards(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TimeStandards.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Swim Tracker");
		stage.show();
	}
	
	// Exports data to desktop
	public void export() {
		instance = Swimmer.getInstance(null, 0, null);
		instance.exportData("/Users/jmrabadam/Desktop/exported_data.txt");
	}
}


