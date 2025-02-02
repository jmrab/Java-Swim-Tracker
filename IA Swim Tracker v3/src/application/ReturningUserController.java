package application;	

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReturningUserController extends FXMLManager{
	
	private Stage stage;
	private Swimmer instance;
	
	// Switch to main menu
	public void switchMain(ActionEvent e) throws IOException {	
		super.switchStartup(e);
	}
	
	// Choose file to import data
	public void chooseFile(ActionEvent e) throws IOException {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Resource File");
	    
	    File selectedFile = fileChooser.showOpenDialog(stage);
	    instance = Swimmer.getInstance(null, 0, null);
	    
	    if (selectedFile != null) {
	        boolean importSuccess = instance.importData(selectedFile.getAbsolutePath());
	        if (!importSuccess) {
	            return;
	        }
	    } else {
	        super.switchStartup(e);
	        return;
	    }
	    
	    super.switchMain(e);
	}
}