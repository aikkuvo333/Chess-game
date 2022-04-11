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
	private Button suljeSobellusBtn;

	@FXML
	private Button leaderboardBtn;
	
	@FXML
    private Button finnish;
	
	@FXML
    private Button english;
	
	@FXML
	void TilastoimatonPeli(ActionEvent event) throws IOException {

		PeliNakyma controller = new PeliNakyma();
		//Asetukset_kontrolleri asetukset_kontrolleri = new Asetukset_kontrolleri();
		loader = new FXMLLoader(getClass().getResource("Lauta.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		loader.setController(controller);
		root = loader.load();
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void TilastoituPeli(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Tilastoitupeli1.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	@FXML
	void leaderboard(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	@FXML
	void suljeSovellus(ActionEvent event) {
		stage = (Stage) suljeSobellusBtn.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void tilastot(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Tilastot2.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	void changeLanguageToFin (ActionEvent event) throws IOException {
		System.out.println("change to FIN");
		ValittuKieli.getInstance().setSuomi();
		
		loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	void changeLanguageToUs (ActionEvent event) throws IOException {
		System.out.println("change to US");
		ValittuKieli.getInstance().setEnglanti();
		
		loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

}