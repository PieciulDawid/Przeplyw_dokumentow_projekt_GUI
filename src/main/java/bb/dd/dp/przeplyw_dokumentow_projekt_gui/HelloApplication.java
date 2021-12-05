package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models.EmployeeModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		stage.setResizable(false);
		
		var mainLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
		var leftLoader = new FXMLLoader(HelloApplication.class.getResource("leftpane-view.fxml"));
		var bottomLoader = new FXMLLoader(HelloApplication.class.getResource("bottompane-view.fxml"));

		var scene = new Scene(mainLoader.load());
		((BorderPane)scene.getRoot()).setLeft(leftLoader.load());
		((BorderPane)scene.getRoot()).setBottom(bottomLoader.load());

		var table = new TableView<EmployeeModel>();
		table.setEditable(true);
		var namecol = new TableColumn("Imie");
		var surnamecol = new TableColumn("Nazwisko");
		var logincol = new TableColumn("Login");
		var passwordcol = new TableColumn("Haslo");
		namecol.setPrefWidth(225);
		namecol.setCellValueFactory(
				new PropertyValueFactory<EmployeeModel,String>("Name")
		);
		surnamecol.setPrefWidth(225);
		surnamecol.setCellValueFactory(
				new PropertyValueFactory<EmployeeModel,String>("Surname")
		);
		logincol.setPrefWidth(225);
		logincol.setCellValueFactory(
				new PropertyValueFactory<EmployeeModel,String>("Login")
		);
		passwordcol.setPrefWidth(225);
		passwordcol.setCellValueFactory(
				new PropertyValueFactory<EmployeeModel,String>("Password")
		);

		table.getColumns().addAll(namecol, surnamecol, logincol, passwordcol);

		final ObservableList<EmployeeModel> data = FXCollections.observableArrayList(EmployeeModel.getAll().values());
		table.setItems(data);
		((BorderPane)scene.getRoot()).setCenter(table);

		var stylesheets = scene.getStylesheets();
		
		//	TODO css do dodania gdy uda się w nim ogarnąć
		//	 jak dodać widoczność klikania przycisków
		//stylesheets.add("stylesheet.css");
		stage.setTitle("BB DD DP");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}