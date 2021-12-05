package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.HelloApplication;
import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LogoutController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private AnchorPane viewRoot;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	void initialize() {
		EmployeeModel.logout();
		logoutButton.setOnMouseClicked(this::logout);
		logoutButton.setOnKeyPressed(this::logout);
	}
	
	void logout(InputEvent inputEvent) {
		var menu = (BorderPane)viewRoot.getScene().getRoot();
		menu.setTop(null);
		menu.setBottom(null);
		menu.setRight(null);
		menu.setLeft(null);
		try {
			menu.setCenter(FXMLLoader.load(HelloApplication.class.getResource("login-view.fxml")));
		} catch(IOException e) {
			menu.setCenter(null);
			e.printStackTrace();
			Platform.exit();
		}
	}
}
