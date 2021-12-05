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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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
    
    public void setTableView(TableView<EmployeeModel> tableView) {
        this.tableView = tableView;
    }
    
    private TableView<EmployeeModel> tableView;


    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert seachButton != null : "fx:id=\"seachButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'search-view.fxml'.";
        assert viewroot != null : "fx:id=\"viewroot\" was not injected: check your FXML file 'search-view.fxml'.";
        
        goBackButton.setOnMouseClicked(this::goBack);
        goBackButton.setOnKeyPressed((KeyEvent event)->{
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.goBack(event);
            }
        });
    
        seachButton.setOnMouseClicked(this::search);
        seachButton.setOnKeyPressed((KeyEvent event)->{
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.search(event);
            }
        });
    }
    
    void goBack(InputEvent inputEvent) {
        var parent = ((BorderPane)viewroot.getParent());
        parent.setTop(null);
        var groupList = ((Group) parent.getCenter()).getChildren();
        groupList.remove(groupList.size()-1);
        groupList.get(groupList.size()-1).setDisable(false);
    }
    
    void search(InputEvent inputEvent) {
        var items = FXCollections
                .observableArrayList(EmployeeModel.search(searchTextField.getText()).values());
        tableView.setItems(items);
    }
    
    
    void initTableView() {
        tableView.setEditable(true);
        var idcol = new TableColumn<EmployeeModel,String>("ID");
        var namecol = new TableColumn<EmployeeModel,String>("Imie");
        var surnamecol = new TableColumn<EmployeeModel,String>("Nazwisko");
        var logincol = new TableColumn<EmployeeModel,String>("Login");
        var passwordcol = new TableColumn<EmployeeModel,String>("Haslo");
        idcol.setPrefWidth(30);
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("Id")
        );
        namecol.setCellFactory(TextFieldTableCell.forTableColumn());
        namecol.setOnEditCommit(
                t ->{
                    var tmp = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    tmp.setName(t.getNewValue());
                    EmployeeModel.modify(tmp);
                }
        );
        
        namecol.setPrefWidth(270);
        namecol.setCellValueFactory(
                new PropertyValueFactory<>("Name")
        );
        surnamecol.setPrefWidth(270);
        surnamecol.setCellValueFactory(
                new PropertyValueFactory<>("Surname")
        );
        logincol.setPrefWidth(270);
        logincol.setCellValueFactory(
                new PropertyValueFactory<>("Login")
        );
        passwordcol.setPrefWidth(270);
        passwordcol.setCellValueFactory(
                new PropertyValueFactory<>("Password")
        );
        
        tableView.getColumns().addAll(idcol, namecol, surnamecol, logincol, passwordcol);
        
        var data = FXCollections.observableArrayList(EmployeeModel.getAll().values());
        tableView.setItems(data);
    
    }
}