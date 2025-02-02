package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SingleEventController extends FXMLManager {
	private Swimmer instance;
	@FXML
	private TableView<Race> tableView = new TableView<Race>();
    @FXML
    private TableColumn<Race, String> timeColumn = new TableColumn<Race, String>();
    @FXML
    private TableColumn<Race, Double> percentImprovementColumn = new TableColumn<Race, Double>();
    @FXML
    private TableColumn<Race, Double> timeDroppedColumn = new TableColumn<Race, Double>();
    @FXML
    private Label eventLabel = new Label();
    private static String course, event;

    // Initialization method
    public void initialize() {
    	eventLabel.setText(event + " " + course);
    	instance = Swimmer.getInstance(null, 0, null);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        percentImprovementColumn.setCellValueFactory(new PropertyValueFactory<>("percentImprovement"));
        timeDroppedColumn.setCellValueFactory(new PropertyValueFactory<>("timeDropped"));
        updateTable();
    }

    // Switch to main menu
    public void switchMain(ActionEvent e) throws IOException {
        super.switchMain(e);
    }
    
    // Set class static variables
    public void setInfo(String course, String event) {
    	SingleEventController.course = course;
    	SingleEventController.event = event;
    }
    
    // Update table with specific event's time dropped and percent improvement
    public void updateTable() {
        tableView.getItems().clear();
        LinkedList<Race> specificEvents = instance.getSpecificEvents(course, event);
        tableView.getItems().addAll(specificEvents);
    }
    
    // Calculate percent improvement and time dropped
    public void calculatePercentImprovement() {
    	instance = Swimmer.getInstance(null, 0, null);
        LinkedList<Race> specificEvents = instance.getSpecificEvents(course, event);

        if (specificEvents.size() > 1) {
        	DecimalFormat decimalFormat = new DecimalFormat("0.00");
        	
            for (int i = 0; i < specificEvents.size() - 1; i++) {
                Race currentRace = specificEvents.get(i);
                Race nextRace = specificEvents.get(i + 1);

                double currentRaceTime = currentRace.getRaceTimeInSeconds();
                double nextRaceTime = nextRace.getRaceTimeInSeconds();

                double percentImprovement = ((nextRaceTime - currentRaceTime) / currentRaceTime) * 100;
                double timeDropped = currentRaceTime - nextRaceTime;

                nextRace.setPercentImprovement(Double.parseDouble(decimalFormat.format(percentImprovement)));
                nextRace.setTimeDropped(Double.parseDouble(decimalFormat.format(timeDropped)));
            }
        }
        tableView.setItems(FXCollections.observableList(specificEvents)); 
    }
}
