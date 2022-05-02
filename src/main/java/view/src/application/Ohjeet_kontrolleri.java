package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Luokka <code>Ohjeet_kontrolleri</code> luo käyttöliittymän Peliohje -näkymän.
 *
 */
public class Ohjeet_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Button olio Poistu -napille
	 */
	@FXML
	private Button poistuBtn;

	/**
	 * TextArea olio, joka sisältää Shakkipelin ohjesäännöt tekstinä
	 */
	@FXML
	private TextArea tekstikentta;

	/**
	 * Poistu- Buttonin metodi, jossa sitä napsauttaessa palautuu alkuvalikko
	 * -näkymään
	 * 
	 * @param event Poistu- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void poistu(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
