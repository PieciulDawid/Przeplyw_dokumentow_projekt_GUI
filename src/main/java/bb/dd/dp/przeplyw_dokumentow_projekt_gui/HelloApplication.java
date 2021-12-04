package bb.dd.dp.przeplyw_dokumentow_projekt_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		stage.setResizable(false);
		
		var mainLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
		var loginLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
		
		var scene = new Scene(mainLoader.load());
		((BorderPane)scene.getRoot()).setCenter(loginLoader.load());
		
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