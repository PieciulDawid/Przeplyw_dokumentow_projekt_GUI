package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class AboutController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane viewRoot;

    @FXML
    private Button zabaButton;

    @FXML
    private Button goBackButton;

    @FXML
    void initialize() {
        assert viewRoot != null : "fx:id=\"viewRoot\" was not injected: check your FXML file 'about-view.fxml'.";
        assert zabaButton != null : "fx:id=\"zabaButton\" was not injected: check your FXML file 'about-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'about-view.fxml'.";

        goBackButton.setOnMouseClicked(this::goBack);
        goBackButton.setOnKeyPressed(this::goBack);

        zabaButton.setOnMouseClicked(this::displayZaba);
        zabaButton.setOnKeyPressed(this::displayZaba);

    }

    void goBack(InputEvent inputEvent) {
        var childrenList = ((Group)viewRoot.getParent()).getChildren();
        childrenList.remove(childrenList.size()-1);
        childrenList.forEach((n)->n.setDisable(false));
    }
    void displayZaba(InputEvent inputEvent) {
        var group = ((Group) viewRoot.getParent());
        group.getChildren().forEach((n)->n.setDisable(true));
        try {
            var logoutView = (AnchorPane)FXMLLoader.load(HelloApplication.class.getResource("zaba-view.fxml"));
            group.getChildren().add(logoutView);
            logoutView.relocate(logoutView.getBoundsInParent().getHeight()/2, logoutView.getBoundsInParent().getWidth()/2);
        } catch(IOException e) {
            var parent = ((BorderPane)group.getParent());
            parent.setCenter(null);
            e.printStackTrace();
            Platform.exit();
        }
    }

}
