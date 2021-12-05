package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import javafx.fxml.FXML;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.VBox;

public class LogoutController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private VBox viewRoot;
	
	@FXML
	void initialize() {
		EmployeeModel.logout();
	}
	
	void logout(InputEvent inputEvent) {
	
	}
}
