package application;

import java.io.IOException;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

public class SelectGraphController extends FXMLManager{
	
	@FXML
    private ChoiceBox<String> courseChoiceBox, eventChoiceBox;
	private Swimmer instance;

	// Initialization method
    public void initialize() {
        courseChoiceBox.getItems().addAll("SCY", "SCM", "LCM");
        eventChoiceBox.getItems().addAll("50 Free", "100 Free", "200 Free", "400/500 Free", "800/1000 Free", "1500/1650 Free", "50 Back", "100 Back", "200 Back", "50 Breast", "100 Breast", "200 Breast", "50 Fly", "100 Fly", "200 Fly", "200 IM", "400 IM");
    }
    
    // Switch to main menu
	public void switchMain(ActionEvent e) throws IOException {
		super.switchMain(e);
	}
	
	// Pass course and event variables to GraphController class and switch to view graph
	 public void switchGraph(ActionEvent e) throws IOException {
	        String course = courseChoiceBox.getValue();
	        String event = eventChoiceBox.getValue();
	        
	        instance = Swimmer.getInstance("", 0, "");
	        LinkedList<Race> specificEvent = instance.getSpecificEvents(course, event);
	        
	        if(specificEvent == null || specificEvent.isEmpty()) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Alert");
	            alert.setHeaderText(null);
	            alert.setContentText("There are no races matching " + course + " " + event);
	            alert.showAndWait();
	            super.switchSelectGraph(e);
	            return;
	        }
	        
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));
			@SuppressWarnings("unused")
			Parent tableRoot = loader.load();
	        GraphController graphController = loader.getController();
	        
	        graphController.setInfo(course, event);
	        super.switchGraph(e);
	    }
}