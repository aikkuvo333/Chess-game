package view.src.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * Sovelluksen käynnistyspääohjelma
 * 
 * @author Aivan Vo 3.2.2022
 */

public class Main extends Application {

	/**
	 * Metodi käynnistää sovelluksen käyttöliittymänäkymän
	 */
	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Alkuvalikko.fxml"));
			loader.setResources(ValittuKieli.getInstance().getBundle());
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
