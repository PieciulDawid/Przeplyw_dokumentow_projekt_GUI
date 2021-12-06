package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;


import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.ClientModel;
import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class ProductController {
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
    private AnchorPane viewRoot;

    public void setTableView(TableView<ProductModel> tableView) {
        this.tableView = tableView;
    }

    private TableView<ProductModel> tableView;


    @FXML
    void initialize() {
        
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
    
        addButton.setOnMouseClicked(this::add);
        addButton.setOnKeyPressed((KeyEvent event)->{
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.add(event);
            }
        });
    }

    void goBack(InputEvent inputEvent) {
        var parent = ((BorderPane)viewRoot.getParent());
        parent.setTop(null);
        var groupList = ((Group) parent.getCenter()).getChildren();
        groupList.remove(groupList.size()-1);
        groupList.get(groupList.size()-1).setDisable(false);
    }

    void search(InputEvent inputEvent) {
        var items = FXCollections
                .observableArrayList(ProductModel.search(searchTextField.getText()).values());
        tableView.setItems(items);
    }
    
    void add(InputEvent inputEvent) {
        ProductModel.add(new ProductModel(0,"",0.0f,0));
        var items = ProductModel.getAll().values();
        tableView.getItems().add(items.stream().skip(items.size()-1).toList().get(0));
    }

    void initTableView() {
        tableView.setEditable(true);
        var idcol = new TableColumn<ProductModel,String>("ID");
        var namecol = new TableColumn<ProductModel,String>("Nazwa");
        var pricecol = new TableColumn<ProductModel,Float>("Cena");
        var amountcol = new TableColumn<ProductModel,Integer>("Ilość");
        idcol.setPrefWidth(30);
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("Id")
        );
        namecol.setPrefWidth(270);
        namecol.setCellValueFactory(
                new PropertyValueFactory<>("Name")
        );
        namecol.setCellFactory(TextFieldTableCell.forTableColumn());
        namecol.setOnEditCommit(
                t ->{
                    var tmp = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    tmp.setName(t.getNewValue());
                    ProductModel.modify(tmp);
                }
        );


        pricecol.setPrefWidth(270);
        pricecol.setCellValueFactory(
                new PropertyValueFactory<>("Price")
        );
        pricecol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<>() {
                    @Override
                    public String toString(Float aFloat) {
                        if(aFloat == null)
                            return "";
                        return aFloat.toString();
                    }
    
                    @Override
                    public Float fromString(String s) {
                        if(s == null || s.equals(""))
                            return 0.0f;
                        return Float.parseFloat(s);
                    }
                }));
        pricecol.setOnEditCommit(
                t ->{
                    var tmp = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    tmp.setPrice(t.getNewValue());
                    ProductModel.modify(tmp);
                }
        );
        amountcol.setPrefWidth(270);
        amountcol.setCellValueFactory(
                new PropertyValueFactory<>("Amount")
        );
        amountcol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<>() {
                    @Override
                    public String toString(Integer integer) {
                        if(integer == null)
                            return "";
                        return integer.toString();
                    }
    
                    @Override
                    public Integer fromString(String s) {
                        if(s == null || s.equals(""))
                            return 0;
                        return Integer.parseInt(s);
                    }
                }));
        amountcol.setOnEditCommit(
                t ->{
                    var tmp = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    tmp.setPrice(t.getNewValue());
                    ProductModel.modify(tmp);
                }
        );
    
        var mi1 = new MenuItem("Usuń produkt");
        mi1.setOnAction((ActionEvent event) -> {
            var index = tableView.getSelectionModel().getSelectedIndex();
            var tmp = tableView.getItems().remove(index);
            ProductModel.delete(tmp);
        });
    
        tableView.setContextMenu(new ContextMenu(mi1));
        

        tableView.getColumns().addAll(idcol, namecol, pricecol, amountcol);

        var data = FXCollections.observableArrayList(ProductModel.getAll().values());
        tableView.setItems(data);

    }

}
