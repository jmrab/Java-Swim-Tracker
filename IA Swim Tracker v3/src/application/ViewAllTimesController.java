package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.LinkedList;

public class ViewAllTimesController extends FXMLManager {
	private Swimmer instance;
	@FXML
    private TableView<Race> raceTableView;
    @FXML
    private TableColumn<Race, String> timeColumn, eventColumn, dateColumn, feelingsBeforeColumn, feelingAfterColumn;

	
	// Initialization method
	public void initialize() {
        instance = Swimmer.getInstance("", 0, "");
        LinkedList<Race> swimData = instance.getSwimData();

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        feelingsBeforeColumn.setCellValueFactory(new PropertyValueFactory<>("feelingsBeforeAsString"));
        feelingAfterColumn.setCellValueFactory(new PropertyValueFactory<>("sentiment"));

        raceTableView.setItems(FXCollections.observableList(swimData));   
	}	
	
	// Switch to main menu
	public void switchMain(ActionEvent e) throws IOException {
		super.switchMain(e);
	}
}