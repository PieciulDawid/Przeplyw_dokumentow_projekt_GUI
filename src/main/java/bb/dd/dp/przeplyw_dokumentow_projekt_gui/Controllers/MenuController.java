package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class MenuController {
    
    @FXML
    private AnchorPane viewRoot;
    
    @FXML
    private Text nameText;

    @FXML
    private Button productsButton;

    @FXML
    private Button clientsButton;

    @FXML
    private Button employesButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button aboutUsButton;

    @FXML
    private Button logoutButton;
    
    @FXML
    void initialize() {
        logoutButton.setOnMouseClicked(this::logout);
        logoutButton.setOnKeyPressed(this::logout);
    }
    
    void logout(InputEvent inputEvent) {
        var group = ((Group) viewRoot.getParent());
        group.getChildren().forEach((n)->n.setDisable(true));
        try {
            group.getChildren().add(FXMLLoader.load(HelloApplication.class.getResource("logout-view.fxml")));
        } catch(IOException e) {
            var parent = ((BorderPane)group.getParent());
            parent.setCenter(null);
            e.printStackTrace();
            Platform.exit();
        }
    }
}
