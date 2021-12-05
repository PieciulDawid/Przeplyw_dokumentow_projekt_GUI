package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
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
	
	@FXML
	void initialize() {
		var siema = new Text("Siema");
		var backgroundColor = new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY));
		mainPane.setBackground(backgroundColor);
		mainPane.setTop(siema);
	}
}