package view.src.application;
	
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author Aivan Vo 3.2.2022
 */

public class Main extends Application {
	private ValittuKieli valittuKieli = ValittuKieli.getInstance();
	
	@Override
	public void start(Stage primaryStage) {
		valittuKieli.setSuomi();
		ResourceBundle bundle = ResourceBundle.getBundle("text/TextResources", valittuKieli.getLocale());
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Alkuvalikko.fxml"));
			loader.setResources(bundle);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
