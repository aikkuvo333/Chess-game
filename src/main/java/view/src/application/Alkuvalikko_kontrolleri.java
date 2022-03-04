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
		
		loader = new FXMLLoader(getClass().getResource("anonyymi-lauta2.fxml"));
		loader.setController(controller);
		root = loader.load();
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void TilastoituPeli(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Tilastoitupeli1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	@FXML
	void leaderboard(ActionEvent event) {

	}

	@FXML
	void suljeSovellus(ActionEvent event) {
		stage = (Stage) suljeSobellusBtn.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void tilastot(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Tilastot1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

}