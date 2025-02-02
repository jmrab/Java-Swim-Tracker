package application;

import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ProfileController extends FXMLManager {
	private String name, gender;
	private int age;
	private Swimmer instance;
	@FXML
	private TextField nameTextField, ageTextField;
	@FXML
	private ChoiceBox<String> genderChoiceBox;
	@FXML
	private Label nameLabel = new Label(), ageLabel = new Label(), genderLabel = new Label();

	// Initialization method
	public void initialize() {
		genderChoiceBox.getItems().addAll("Male", "Female");
		instance = Swimmer.getInstance(null, 0,  null);
		nameLabel.setText(instance.getName());
	    ageLabel.setText("" + instance.getAge());
	    genderLabel.setText(instance.getGender());
	}
	
	// Edit swimmer's age, name, & gender
	public void editSwimmer(ActionEvent e) throws IOException {
		instance = Swimmer.getInstance("", 0, "");
		name = nameTextField.getText();
		
        if(name == null || name.isEmpty()) {
            showErrorAlert();
            return;
        }
        instance.setName(name);
      
        try {
            age = Integer.parseInt(ageTextField.getText());
            if(age == 0) {
                showErrorAlert();
                return;
            }
        } catch (NumberFormatException ex) {
            showErrorAlert();
            return;
        }
        
        instance.setAge(age);
        
        if(genderChoiceBox == null || genderChoiceBox.getValue() == null) {
            showErrorAlert();
            super.switchMain(e);
            return;
        }
		
		gender = (String)genderChoiceBox.getValue();
		instance.setGender(gender);
		if(name != null && age != 0 && gender != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(null);
	        alert.setHeaderText(null);
	        alert.setContentText("Your profile has been edited. \n Name: " + name + "\n Age: " + age + "\n Gender: " + gender);
	        alert.showAndWait();
		}
	}
	
	// Edit swimmer and load main menu
	public void editSwitchMain(ActionEvent e) throws IOException {
		editSwimmer(e);
		super.switchMain(e);
	}
	
	// Method to show an error alert
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Missing Fields!");
        alert.showAndWait();
    }
}