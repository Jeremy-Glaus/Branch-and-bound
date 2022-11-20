module Branch.And.Bound {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	exports gui;
	opens main;
	opens gui;
	opens utils;
}