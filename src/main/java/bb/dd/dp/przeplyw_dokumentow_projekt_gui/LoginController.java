package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private TextField loginTextField;
	
	@FXML
	private PasswordField passswordTextField;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Button loginButton;
	
	@FXML
	void initialize() {
		assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'login-view.fxml'.";
		assert passswordTextField != null : "fx:id=\"passswordTextField\" was not injected: check your FXML file 'login-view.fxml'.";
		assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'login-view.fxml'.";
		assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login-view.fxml'.";
		loginButton.setOnMouseClicked(this::login);
	}
	
	void login(MouseEvent mouseEvent) {
		if(!Objects.equals(loginTextField.getText(), "admin") || !Objects.equals(passswordTextField.getText(), "admin")) {
			loginTextField.setText("");
			passswordTextField.setText("");
			return;
		}
		
		System.out.println("Logged in!");
	}
}
