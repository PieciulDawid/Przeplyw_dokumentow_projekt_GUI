package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.ClientModel;
import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
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

public class ClientController {
	
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
	
	public void setTableView(TableView<ClientModel> tableView) {
		this.tableView = tableView;
	}
	
	private TableView<ClientModel> tableView;
	
	
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
				.observableArrayList(ClientModel.search(searchTextField.getText()).values());
		tableView.setItems(items);
	}
	
	
	void add(InputEvent inputEvent) {
		ClientModel.add(new ClientModel(0,"","",123456789));
		var items = ClientModel.getAll().values();
		tableView.getItems().add(items.stream().skip(items.size()-1).toList().get(0));
	}
	
	void initTableView() {
		tableView.setEditable(true);
		var idCol = new TableColumn<ClientModel,String>("ID");
		var emailCol = new TableColumn<ClientModel,String>("Email");
		var addressCol = new TableColumn<ClientModel,String>("Adres");
		var telephoneNumberCol = new TableColumn<ClientModel,String>("Numer telefonu");
		idCol.setPrefWidth(30);
		idCol.setCellValueFactory(
				new PropertyValueFactory<>("Id")
		);
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setOnEditCommit(
				t ->{
					var tmp = t.getTableView().getItems().get(
							t.getTablePosition().getRow());
					tmp.setEmail(t.getNewValue());
					ClientModel.modify(tmp);
				}
		);
		
		emailCol.setPrefWidth(270);
		emailCol.setCellValueFactory(
				new PropertyValueFactory<>("Email")
		);
		addressCol.setPrefWidth(270);
		addressCol.setCellValueFactory(
				new PropertyValueFactory<>("Address")
		);
		addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
		addressCol.setOnEditCommit(
				t ->{
					var tmp = t.getTableView().getItems().get(
							t.getTablePosition().getRow());
					tmp.setAddress(t.getNewValue());
					ClientModel.modify(tmp);
				}
		);
		telephoneNumberCol.setPrefWidth(270);
		telephoneNumberCol.setCellValueFactory(
				new PropertyValueFactory<>("TelephoneNumber")
		);
		telephoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
		telephoneNumberCol.setOnEditCommit(
				t ->{
					var tmp = t.getTableView().getItems().get(
							t.getTablePosition().getRow());
					tmp.setTelephoneNumber(Integer.parseInt(t.getNewValue()));
					ClientModel.modify(tmp);
				}
		);
		
		var mi1 = new MenuItem("Usuń klienta");
		mi1.setOnAction((ActionEvent event) -> {
			var index = tableView.getSelectionModel().getSelectedIndex();
			var tmp = tableView.getItems().remove(index);
			ClientModel.delete(tmp);
		});
		
		tableView.setContextMenu(new ContextMenu(mi1));
		
		tableView.getColumns().addAll(idCol, emailCol, addressCol, telephoneNumberCol);
		
		var data = FXCollections.observableArrayList(ClientModel.getAll().values());
		tableView.setItems(data);
		
	}
}