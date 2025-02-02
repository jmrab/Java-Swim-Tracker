package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NewUserController extends FXMLManager {
    private static String name, gender;
    private static int age;
    @FXML
    private TextField nameTextField, ageTextField;
    @FXML
    private ChoiceBox<String> genderChoiceBox;

    // Initialization method
    public void initialize() {
        genderChoiceBox.getItems().addAll("Male", "Female");
        
        if(name != null) {
        	nameTextField.setText(name);
        }
        
        if(age != 0) {
        	ageTextField.setText("" + age);
        }
        
        if(gender != null) {
        	genderChoiceBox.setValue(gender);
        }
    }

    // Method to show an error alert
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Create swimmer and assign name, age, & gender
    public void createSwimmer(ActionEvent ev) throws IOException {
        name = nameTextField.getText();
        if(name == null || name.isEmpty()) {
            showErrorAlert("Please enter a name.");
            return;
        }
      
        try {
            age = Integer.parseInt(ageTextField.getText());
            if(age == 0) {
                showErrorAlert("Please enter a nonzero integer for your age.");
                return;
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Please enter a nonzero integer for your age.");
            return;
        }
        
        if(genderChoiceBox == null || genderChoiceBox.getValue() == null) {
            showErrorAlert("Please select a gender.");
            super.switchNewUser(ev);
            return;
        }
        
        gender = genderChoiceBox.getValue();
        Swimmer.getInstance(name, age, gender);
    }

    // Switch to main menu
    public void switchMain(ActionEvent e) throws IOException {
        createSwimmer(e);
        if(age != 0) {
            super.switchMain(e);
        } else {
            super.switchNewUser(e);
        }
    }

    // Switch to startup menu
    public void switchStartup(ActionEvent e) throws IOException {
        super.switchStartup(e);
    }
}
