package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainController {
	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private URL location;
	
	@FXML
	private BorderPane mainPane;

	private AnimationTimer animationTimer;

	private int i=0;
	private int count = 0;

	private Background colors[] = new Background[190];
	
	@FXML
	void initialize() {
		var siema = new Text("Siema");
		var backgroundColor = new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY));
		mainPane.setBackground(backgroundColor);
		mainPane.setTop(siema);

		for (int i = 160; i<255; i++) {
			colors[i-160] = new Background(new BackgroundFill(Color.color(0, (float) i / 255, 1), CornerRadii.EMPTY, Insets.EMPTY));
		}
		for (int i = 255; i>160; i--) {
			colors[190+160-i] = new Background(new BackgroundFill(Color.color(0, (float)i / 255, 1), CornerRadii.EMPTY, Insets.EMPTY));
		}

		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				count = (count+1)%7;
				if(count ==0)
					changeBackground();
			}
		};
		animationTimer.start();
	}
	void changeBackground(){
		i = (i+1)%190;
		mainPane.setBackground(colors[i]);
	}
}