package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	
	private AnimationTimer animationTimer;
	
	@FXML
	void initialize() {
		
		goBackButton.setOnMouseClicked(this::goBack);
		goBackButton.setOnKeyPressed((KeyEvent event)->{
			if (event.getCode().equals(KeyCode.ENTER)) {
				this.goBack(event);
			}
		});

		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				rotateButton();
			}
		};
		animationTimer.start();
	}
	
	void goBack(InputEvent inputEvent) {
		var childrenList = ((Group)viewRoot.getParent()).getChildren();
		childrenList.remove(childrenList.size()-1);
		childrenList.get(childrenList.size()-1).setDisable(false);
		animationTimer.stop();
	}
	void rotateButton(){
		goBackButton.setRotate(goBackButton.getRotate()+1%360);
		viewRoot.setRotate(viewRoot.getRotate()-1%360);
	}
}
