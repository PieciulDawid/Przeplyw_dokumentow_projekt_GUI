package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.HelloApplication;
import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        logoutButton.setOnKeyPressed((KeyEvent event)->{
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.logout(event);
            }
        });
        employesButton.setOnMouseClicked(this::employee);
        employesButton.setOnKeyPressed(this::employee);
//        clientsButton.setOnMouseClicked(this::client);
//        clientsButton.setOnKeyPressed(this::client);
//        productsButton.setOnMouseClicked(this::product);
//        productsButton.setOnKeyPressed(this::product);
        aboutUsButton.setOnMouseClicked(this::aboutUs);
        aboutUsButton.setOnKeyPressed((KeyEvent event)->{
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.aboutUs(event);
            }
        });


    }
    void  employee(InputEvent inputEvent){
       var group = ((Group)viewRoot.getParent());
        try{
            var searchLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
            var search = (HBox)searchLoader.load();
            group.getChildren().forEach((n)->n.setDisable(true));
            var tableView = new TableView<EmployeeModel>();
            ((EmployeeController)searchLoader.getController()).setTableView(tableView);
            ((EmployeeController)searchLoader.getController()).initTableView();
            group.getChildren().add(tableView);
            ((BorderPane)group.getParent()).setTop(search);
        }
        catch(IOException e){
            var parent = ((BorderPane)group.getParent());
            parent.setCenter(null);
            e.printStackTrace();
            Platform.exit();
        }


    }

    void logout(InputEvent inputEvent) {
        var group = ((Group) viewRoot.getParent());
        group.getChildren().forEach((n)->n.setDisable(true));
        try {
            var logoutView = (AnchorPane)FXMLLoader.load(HelloApplication.class.getResource("logout-view.fxml"));
            group.getChildren().add(logoutView);
            logoutView.relocate(group.getLayoutBounds().getWidth() /2 - logoutView.getWidth()/2,
                    group.getLayoutBounds().getHeight() /2 - logoutView.getHeight()/2);
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
