package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;

public class ZabaController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private AnchorPane viewRoot;
	
	@FXML
	private Button goBackButton;
	
	@FXML
	void initialize() {
		assert viewRoot != null : "fx:id=\"viewRoot\" was not injected: check your FXML file 'zaba-view.fxml'.";
		assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'zaba-view.fxml'.";
		goBackButton.setOnKeyPressed(this::goBack);
		goBackButton.setOnMouseClicked(this::goBack);
	}
	
	void goBack(InputEvent inputEvent) {
		var childrenList = ((Group)viewRoot.getParent()).getChildren();
		childrenList.remove(childrenList.size()-1);
	}
}
