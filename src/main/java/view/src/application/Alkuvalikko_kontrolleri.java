package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * @author Aivan Vo 5.2.2022
 */

public class Alkuvalikko_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;

	@FXML
	private Button tilastotBtn;

	@FXML
	private Button tilastoituPeliBtn;

	@FXML
	private Button tilastoimatonPeliBtn;

	@FXML
	private Button luoTunnusBtn;

	@FXML
	private Button suljeSobellusBtn;

	@FXML
	private Button leaderboardBtn;

	@FXML
	void TilastoimatonPeli(ActionEvent event) throws IOException {

		FXMLController controller = new FXMLController();
		
		System.out.println("AAAAAAAAA");
		loader = new FXMLLoader(getClass().getResource("anonyymi-lauta2.fxml"));
		loader.setController(controller);
		root = loader.load();
		
		//root = FXMLLoader.load(getClass().getResource("anonyymi-lauta2.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		/*
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		loader.setController(new MainController(path));
		Pane mainPane = loader.load();
		*/
	}

	@FXML
	void TilastoituPeli(ActionEvent event) {

	}

	@FXML
	void leaderboard(ActionEvent event) {

	}

	@FXML
	void suljeSovellus(ActionEvent event) {

	}

	@FXML
	void tilastot(ActionEvent event) {

	}

}