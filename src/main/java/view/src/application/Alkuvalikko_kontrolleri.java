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

/**
 * Luokka <code>Alkuvalikko_kontrolleri</code> luo käyttöliittymän
 * alkuvalikkonäkymän.
 * 
 * @author Aivan Vo 5.2.2022
 */

/**
 * @author aivanvo
 *
 */
/**
 * @author aivanvo
 *
 */
public class Alkuvalikko_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;

	/**
	 * Button olio Pelaajien tilastot- napille
	 */
	@FXML
	private Button tilastotBtn;

	/**
	 * Button olio Tilastoitu peli- napille
	 */
	@FXML
	private Button tilastoituPeliBtn;

	/**
	 * Button olio Pikapeli- napille
	 */
	@FXML
	private Button tilastoimatonPeliBtn;

	/**
	 * Button olio Sulje sovellus -napille
	 */
	@FXML
	private Button suljeSobellusBtn;

	/**
	 * Button olio Leaderboard -napille
	 */
	@FXML
	private Button leaderboardBtn;

	/**
	 * Button olio Suomi -kielivalinta napille
	 */
	@FXML
	private Button finnish;

	/**
	 * Button olio Englanti -kielivalinta napille
	 */
	@FXML
	private Button english;

	/**
	 * Button olio Säännöt -napille
	 */
	@FXML
	private Button ohjeetBtn;

	/**
	 * Tilastoimaton peli- Buttonin metodi, jossa sitä napsauttaessa avautuu
	 * pelilautanäkymä.
	 * 
	 * @param event Tilastoimaton peli- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void TilastoimatonPeli(ActionEvent event) throws IOException {

		PeliNakyma controller = new PeliNakyma();
		loader = new FXMLLoader(getClass().getResource("Lauta.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		loader.setController(controller);
		root = loader.load();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Tilastoitu peli- Buttonin metodi, jossa sitä napsauttaessa avautuu
	 * pelaajatunnuksen valinta-/ ja tunnuksen luontinäkymä.
	 * 
	 * @param event Tilastoitu peli- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void TilastoituPeli(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Tilastoitupeli1.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Leaderboard- Buttonin metodi, jossa sitä napsauttaessa avautuu Leaderboard
	 * -näkymä.
	 * 
	 * @param event Leaderboard- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void leaderboard(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Sulje sovellus- Buttonin metodi, jossa sitä napsauttaessa sovellus sulkeutuu.
	 * 
	 * @param event Sulje sovellus- Buttonin napsautus
	 */
	@FXML
	void suljeSovellus(ActionEvent event) {
		stage = (Stage) suljeSobellusBtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * Tilastot- Buttonin metodi, jossa sitä napsauttaessa avautuu Pelaajien
	 * tilastot -näkymä.
	 * 
	 * @param event Tilastot- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void tilastot(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Tilastot2.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Finnish - Buttonin metodi, jossa sitä napsauttaessa valituksi kieleksi
	 * asetetaan suomen kieli.
	 * 
	 * @param event Finnish- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void changeLanguageToFin(ActionEvent event) throws IOException {
		ValittuKieli.getInstance().setSuomi();

		loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Englanti - Buttonin metodi, jossa sitä napsauttaessa valituksi kieleksi
	 * asetetaan englannin kieli.
	 * 
	 * @param event Englanti- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void changeLanguageToUs(ActionEvent event) throws IOException {
		ValittuKieli.getInstance().setEnglanti();

		loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Ohjeet - Buttonin metodi, jossa sitä napsauttaessa avautuu Säännöt -näkymä.
	 * 
	 * @param event Ohjeet- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen.
	 */
	@FXML
	void ohjeet(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Ohjeet.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}