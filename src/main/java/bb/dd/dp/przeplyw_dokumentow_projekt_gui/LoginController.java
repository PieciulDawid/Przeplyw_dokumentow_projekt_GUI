package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private VBox viewRoot;
	
	@FXML
	private TextField loginTextField;
	
	@FXML
	private PasswordField passswordTextField;

	@FXML
	private Text errosText;
	
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
		loginButton.setOnKeyPressed((KeyEvent event)->{
			if (event.getCode().equals(KeyCode.ENTER)) {
				this.login(event);
			}
		});
		exitButton.setOnMouseClicked(this::exit);
		exitButton.setOnKeyPressed((KeyEvent event)->{
			if (event.getCode().equals(KeyCode.ENTER)) {
				this.exit(event);
			}
		});
	}
	
	void login(InputEvent inputEvent) {
		if(!Objects.equals(loginTextField.getText(), "admin") || !Objects.equals(passswordTextField.getText(), "admin")) {
			errosText.setText("Niepoprawne dane logowania!");
			//loginTextField.setText("");
			passswordTextField.setText("");
			return;
		}
		
		try {
			var zabaLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));
			((BorderPane) viewRoot.getParent()).setCenter(zabaLoader.load());
		}
		catch(IOException e) {
			System.out.println("No " + "zaba-view.fxml" + " found.");
		}
	}
	void exit(InputEvent mouseEvent) {
		Platform.exit();
	}

}
