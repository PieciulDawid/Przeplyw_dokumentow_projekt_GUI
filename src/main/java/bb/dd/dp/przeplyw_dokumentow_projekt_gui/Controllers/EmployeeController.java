package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button seachButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private HBox viewroot;

    private TableView<EmployeeModel> tableView;

    private ObservableList<Node> group;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert seachButton != null : "fx:id=\"seachButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'search-view.fxml'.";
        assert viewroot != null : "fx:id=\"viewroot\" was not injected: check your FXML file 'search-view.fxml'.";

        group = ((Group)((BorderPane)viewroot.getScene().getRoot()).getCenter()).getChildren();

        tableView = new TableView<>();
        tableView.setEditable(true);
        var idcol = new TableColumn<EmployeeModel,Integer>("ID");
        var namecol = new TableColumn<EmployeeModel,String>("Imie");
        var surnamecol = new TableColumn<EmployeeModel,String>("Nazwisko");
        var logincol = new TableColumn<EmployeeModel,String>("Login");
        var passwordcol = new TableColumn<EmployeeModel,String>("Haslo");
        idcol.setPrefWidth(40);
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("Id")
        );
        namecol.setPrefWidth(290);
        namecol.setCellValueFactory(
                new PropertyValueFactory<>("Name")
        );
        surnamecol.setPrefWidth(290);
        surnamecol.setCellValueFactory(
                new PropertyValueFactory<>("Surname")
        );
        logincol.setPrefWidth(290);
        logincol.setCellValueFactory(
                new PropertyValueFactory<>("Login")
        );
        passwordcol.setPrefWidth(290);
        passwordcol.setCellValueFactory(
                new PropertyValueFactory<>("Password")
        );

        tableView.getColumns().addAll(idcol, namecol, surnamecol, logincol, passwordcol);

        ObservableList<EmployeeModel> data = FXCollections.observableArrayList(EmployeeModel.getAll().values());
        tableView.setItems(data);

        group.add(tableView);
        group.get(group.size()-2).setDisable(true);
    }
}
