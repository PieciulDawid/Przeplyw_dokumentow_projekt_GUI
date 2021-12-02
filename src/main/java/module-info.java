module bb.dd.dp.przeplyw_dokumentow_projekt_gui {
	requires javafx.controls;
	requires javafx.fxml;
	
	
	opens bb.dd.dp.przeplyw_dokumentow_projekt_gui to javafx.fxml;
	exports bb.dd.dp.przeplyw_dokumentow_projekt_gui;
}