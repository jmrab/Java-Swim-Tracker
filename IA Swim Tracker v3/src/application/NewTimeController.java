package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.ActionEvent;


public class NewTimeController extends FXMLManager {
	private Swimmer instance;
	@FXML
	private ChoiceBox<String> eventChoiceBox = new ChoiceBox<String>(), courseChoiceBox = new ChoiceBox<String>();
	@FXML
	private TextField distanceTextField = new TextField(), minutes = new TextField(), seconds = new TextField(), deciseconds = new TextField();
	@FXML
	private DatePicker datePicker = new DatePicker();
	@FXML
	private CheckBox variables = new CheckBox(), apprehensiveCheckBox = new CheckBox(), confidentCheckBox = new CheckBox(), energeticCheckBox = new CheckBox(), excitedCheckBox = new CheckBox(), focusedCheckBox = new CheckBox(), nervousCheckBox = new CheckBox(), relaxedCheckBox = new CheckBox(), restedCheckBox = new CheckBox(), soreCheckBox = new CheckBox(), tiredCheckBox = new CheckBox(), suitedCheckBox = new CheckBox(), shavedCheckBox = new CheckBox();
	@FXML
	private RadioButton verySatisfiedRadioButton = new RadioButton(), somewhatSatisfiedRadioButton = new RadioButton(), neutralRadioButton = new RadioButton(), somewhatUnsatisfiedRadioButton = new RadioButton(), veryUnsatisfiedRadioButton = new RadioButton();
	private static LocalDate date;
	private static String time, event, course;
	
	// Initialization method
	public void initialize() {
		eventChoiceBox.getItems().addAll("50 Free", "100 Free",  "200 Free", 
				"400/500 Free", "800/1000 Free", "1500/1650 Free",
	            "50 Back", "100 Back", "200 Back",
	            "50 Breast", "100 Breast", "200 Breast",
	            "50 Fly", "100 Fly", "200 Fly",
	            "200 IM", "400 IM");
		courseChoiceBox.getItems().addAll("SCY", "LCM", "SCM");
	}
		
	/* 
	 * Inputs date, time, event, and course to class static variables
	 * and switches to scene where user can input variables.
	*/
	public void inputDetails(ActionEvent e) throws IOException{
		boolean validDetails = true;
	    
		try {
			course = courseChoiceBox.getValue();
			if (course == null) {
				validDetails = false;
			    showAlert("Please choose a course.");  
			}
		} catch (Exception ex) {
			validDetails = false;
		    showAlert("Please choose a course.");       
		}
		
		try {
			event = eventChoiceBox.getValue();
			if (event == null) {
				validDetails = false;
			    showAlert("Please choose an event.");  
			}
		} catch (Exception ex) {
			validDetails = false;
		    showAlert("Please choose an event.");       
		}
	    
		try {
			date = datePicker.getValue();
			if (date == null) {
				validDetails = false;
			    showAlert("Please enter a valid date.");  
			}
		} catch (Exception ex) {
			validDetails = false;
		    showAlert("Please enter a valid date.");       
		}
	    
	    if(validDetails) {
	    	super.switchNewTimeVariables(e);
	    } else {
	    	super.switchNewTimeDetails(e);
	    }
	    
	    try {
			time = minutes.getText() + ":" + seconds.getText() + "." + deciseconds.getText();
			if (time == null) {
				validDetails = false;
			    showAlert("Please enter a valid time in the format 00:00.00");  
			}
		} catch (Exception ex) {
			validDetails = false;
		    showAlert("Please enter a valid time in the format 00:00.00");       
		   
		}
	    
	}

	// Creates new Race with inputed data
	public void inputVariables(ActionEvent e) throws IOException {
		String[] variables = getVariables();
		String sentiment = getSentiment();
		instance = Swimmer.getInstance("", 0, "");
		instance.toString();
	    instance.inputSwimData(event, course, time, date.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")), variables, sentiment);
		super.switchMain(e);
	}
	
	// Get method for variables before race
	public String[] getVariables() {
	    ArrayList<String> selectedFeelings = new ArrayList<>();
	    
	    if(apprehensiveCheckBox.isSelected()) {
	        selectedFeelings.add("Apprehensive");
	    }
	    if(confidentCheckBox.isSelected()) {
	        selectedFeelings.add("Confident");
	    }
	    if(energeticCheckBox.isSelected()) {
	        selectedFeelings.add("Energetic");
	    }
	    if(excitedCheckBox.isSelected()) {
	        selectedFeelings.add("Excited");
	    }
	    if(focusedCheckBox.isSelected()) {
	        selectedFeelings.add("Focused");
	    }
	    if(nervousCheckBox.isSelected()) {
	        selectedFeelings.add("Nervous");
	    }
	    if(relaxedCheckBox.isSelected()) {
	        selectedFeelings.add("Relaxed");
	    }
	    if (restedCheckBox.isSelected()) {
	        selectedFeelings.add("Rested");
	    }
	    if(shavedCheckBox.isSelected()) {
	        selectedFeelings.add("Shaved");
	    }
	    if(soreCheckBox.isSelected()) {
	        selectedFeelings.add("Sore");
	    }
	    if(suitedCheckBox.isSelected()) {
	        selectedFeelings.add("Suited");
	    }
	    if(tiredCheckBox.isSelected()) {
	        selectedFeelings.add("Tired");
	    }

	    return selectedFeelings.toArray(new String[0]);
	}
	
	// Get method for sentiment after race
	public String getSentiment() throws IOException {
		try {
			ToggleGroup feelingGroup = new ToggleGroup();
	        verySatisfiedRadioButton.setToggleGroup(feelingGroup);
	        somewhatSatisfiedRadioButton.setToggleGroup(feelingGroup);
	        neutralRadioButton.setToggleGroup(feelingGroup);
	        somewhatUnsatisfiedRadioButton.setToggleGroup(feelingGroup);
	        veryUnsatisfiedRadioButton.setToggleGroup(feelingGroup);

	        RadioButton selectedRadioButton = (RadioButton) feelingGroup.getSelectedToggle();
	        return selectedRadioButton.getText();
	        
		} catch (Exception e) {
		    showAlert("Please select a sentiment.");       
		    
		    ActionEvent ev = new ActionEvent();
		    super.switchNewTimeVariables(ev);
			return "";
		}
        
    }	
	
	public void switchMain(ActionEvent e) throws IOException {
		super.switchMain(e);
	}
	
	public void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Alert");
	    alert.setHeaderText(null);
	    alert.setContentText(message);       
	    alert.showAndWait();
	}
	
}