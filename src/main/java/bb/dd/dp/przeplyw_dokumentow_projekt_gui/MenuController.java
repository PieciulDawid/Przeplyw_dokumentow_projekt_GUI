package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MenuController {

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

    }
}
