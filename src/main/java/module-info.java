module bb.dd.dp.przeplyw_dokumentow_projekt_gui {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.opencsv;
	
	
	opens bb.dd.dp.przeplyw_dokumentow_projekt_gui to javafx.fxml;
	exports bb.dd.dp.przeplyw_dokumentow_projekt_gui;
	exports bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers;
	opens bb.dd.dp.przeplyw_dokumentow_projekt_gui.Controllers to javafx.fxml;
}