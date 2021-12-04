package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
		
		mainPane.setTop(siema);
	}
}
