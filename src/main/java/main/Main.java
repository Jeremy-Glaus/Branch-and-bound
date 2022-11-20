package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

	//current main stage, public accessible
	public static Stage mainStage;

	@Override
	public void start(Stage stage) throws Exception {
		//init stage
		mainStage = stage;
		mainStage.setOnCloseRequest(e -> Platform.exit());
		String[] css = {"Main.css"};

		//calculate client screen
		double width = getClientScreen().getWidth();
		double height = getClientScreen().getHeight();

		//create and show main stage
		mainStage.setScene(loadScene("CallTree.fxml", css, width, height));
		mainStage.show();
	}

	//Creates main Scene and loads CSS-Files
	public static Scene loadScene(String path, String[] cssFiles, double width, double height){
		Scene scene = null;
		try{
			Parent root = FXMLLoader.load(Main.class.getClassLoader().getResource(path));
			scene = new Scene(root, width, height);

			for (String cssFile : cssFiles) {
				URL url = Main.class.getClassLoader().getResource(cssFile);
				scene.getStylesheets().add(url.toExternalForm());
			}
		} catch (Exception e ){
			e.printStackTrace();
		}
		return scene;
	}

	//Returns clients primary screen bounds
	public static Rectangle2D getClientScreen(){
		return  Screen.getPrimary().getBounds();
	}

}
