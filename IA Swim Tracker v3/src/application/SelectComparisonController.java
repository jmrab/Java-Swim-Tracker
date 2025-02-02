package application;

import java.io.IOException;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

public class SelectComparisonController extends FXMLManager {
	@FXML
    private ChoiceBox<String> courseChoiceBox = new ChoiceBox<String>(), eventChoiceBox = new ChoiceBox<String>();
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
    
    // Pass event and course variables to SingleEventController class and switch to scene
    public void switchSingleEventComparison(ActionEvent e) throws IOException {
        String event = eventChoiceBox.getValue();
        String course = courseChoiceBox.getValue();

        instance = Swimmer.getInstance("", 0, "");
        LinkedList<Race> specificEvent = instance.getSpecificEvents(course, event);
        
        if(specificEvent == null || specificEvent.isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("There are no races matching " + course + " " + event);
            alert.showAndWait();
            super.switchSelectEventComparison(e);
            return;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SingleEventComparison.fxml"));
        @SuppressWarnings("unused")
		Parent tableRoot = loader.load();
        SingleEventController singleEventController = loader.getController();
        
        singleEventController.setInfo(course, event);
        singleEventController.calculatePercentImprovement();
        singleEventController.updateTable();

        super.switchSingleEventComparison(e);
    }
}

