package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
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
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert productsButton != null : "fx:id=\"productsButton\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert clientsButton != null : "fx:id=\"clientsButton\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert employesButton != null : "fx:id=\"employesButton\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert ordersButton != null : "fx:id=\"ordersButton\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert aboutUsButton != null : "fx:id=\"aboutUsButton\" was not injected: check your FXML file 'menu-view.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'menu-view.fxml'.";

        logoutButton.setOnMouseClicked(this::logout);
        logoutButton.setOnKeyPressed(this::logout);

        aboutUsButton.setOnMouseClicked(this::aboutUs);
        aboutUsButton.setOnKeyPressed(this::aboutUs);

    }
    
    void logout(InputEvent inputEvent) {
        var group = ((Group) viewRoot.getParent());
        group.getChildren().forEach((n)->n.setDisable(true));
        try {
            var logoutView = (AnchorPane)FXMLLoader.load(HelloApplication.class.getResource("logout-view.fxml"));
            group.getChildren().add(logoutView);
            logoutView.relocate(logoutView.getBoundsInParent().getHeight()/2, logoutView.getBoundsInParent().getWidth()/2);
        } catch(IOException e) {
            var parent = ((BorderPane)group.getParent());
            parent.setCenter(null);
            e.printStackTrace();
            Platform.exit();
        }
    }

    void aboutUs(InputEvent inputEvent) {
        var group = ((Group) viewRoot.getParent());
        group.getChildren().forEach((n)->n.setDisable(true));
        try {
            var logoutView = (AnchorPane)FXMLLoader.load(HelloApplication.class.getResource("about-view.fxml"));
            group.getChildren().add(logoutView);
        } catch(IOException e) {
            var parent = ((BorderPane)group.getParent());
            parent.setCenter(null);
            e.printStackTrace();
            Platform.exit();
        }
    }
}
