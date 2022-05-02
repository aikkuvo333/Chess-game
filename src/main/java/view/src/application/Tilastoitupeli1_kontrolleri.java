package view.src.application;

import java.io.IOException;
import dao.DBKontrolleri;
import dao.IDaoController;
import dao.Pelaaja;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Luokka <code>Tilastoitupeli1_kontrolleri</code> luo käyttöliittymän Tilastoidun pelin pelaajavalinnan näkymän.
 * 
 * @author Aivan Vo 3.2.2022
 * @author Oliver Hamberg
 */

public class Tilastoitupeli1_kontrolleri {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	
	/**
	 * Pelaaja olio, joka pelaa mustilla nappuloilla
	 */
	private Pelaaja musta;
	
	/**
	 * Pelaaja olio, joka pelaa valkoisilla nappuloilla
	 */
	private Pelaaja valkoinen;
	
	/**
	 * IDaoController rajapintaa toteuttavan DBKontrolleri singletonin kutsuminen
	 */
	private IDaoController dbKontrolleri = DBKontrolleri.getInstance();

	/**
	 * Button olio Poistu- napille
	 */
	@FXML
	private Button poistuBtn;

	/**
	 * MenuButton olio valkoisen pelaajan pelaajatunnuksen valitsemiselle
	 */
	@FXML
	private MenuButton valkVetovalikko;
	
	/**
	 * MenuButton olio mustan pelaajan pelaajatunnuksen valitsemiselle
	 */
	@FXML
	private MenuButton mustVetovalikko;

	/**
	 * TextField kenttä johon kirjoitetaan uuden luotavan pelaajatunnuksen nimi
	 */
	@FXML
	private TextField luoTunnusTekstikentta;

	/**
	 * Button olio Luo tunnus- napille
	 */
	@FXML
	private Button luoTunnusBtn;

	/**
	 * Button olio Aloita peli- napille
	 */
	@FXML
	private Button aloitaPeliBtn;

	/**
	 * Aloita peli- Buttonin metodi, jossa sitä napsauttaessa avautuu pelilautanäkymä.
	 * @param event Aloita peli- Buttonin napsautus
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	@FXML
	void aloitaPeli(ActionEvent event) throws IOException {
		PeliNakyma controller = new PeliNakyma(musta, valkoinen);
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
	 * Metodi jota kutsutaan kerran, kun siihen liittyvä sisältö on ladattu kokonaan. Metodi asettaa valkoisen ja mustan pelaajatunnuksen valinta MenuButtonin vetovalikoihin null-arvot. 
	 */
	public void initialize() {
		valkVetovalikko(null);
		mustVetovalikko(null);
	}

	/**
	 * Metodi joka lisää uuden pelaajatunnuksen valittuun MenuButtoniin
	 * @param btn MenuButton olio johon uusi MenuItem lisätään
	 * @param isMusta boolean arvo joka ollessaan true on musta ja ollessaan false on valkoinen nappulan väri
	 */
	public void lisaaMenuItemit(MenuButton btn, boolean isMusta) {
		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			if(!p.getKayttajaTunnus().equals("Anonyymi")) {
				MenuItem menuItem = new MenuItem(p.getKayttajaTunnus());
				menuItem.setOnAction(a -> {
					btn.setText(p.getKayttajaTunnus());
					if (isMusta) {
						musta = p;
					} else {
						valkoinen = p;
					}
				});
				
				btn.getItems().add(menuItem);
			}
		}
	}

	/**
	 * mustVetovalikko- MenuButtonin metodi, jossa lisätään uusi MenuItem eli pelaajatunnus mustan pelaajan MenuButton valikkoon
	 * @param event mustVetovalikko- MenuButtonin napsauttaminen
	 */
	@FXML
	void mustVetovalikko(ActionEvent event) {
		lisaaMenuItemit(mustVetovalikko, true);
	}

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

	/**
	 * Luo tunnus- Buttonin metodi, jossa sitä napsauttaessa kutsutaan DBkontrolleri singletonia luomaan uusi pelaaja tietokantaan
	 * @param event Luo tunnus- Buttonin napsautus
	 */
	@FXML
	void luoTunnusBtn(ActionEvent event) {
		dbKontrolleri.luoPelaaja(luoTunnusTekstikentta.getText());
		valkVetovalikko.getItems().clear();
		mustVetovalikko.getItems().clear();
		valkVetovalikko(event);
		mustVetovalikko(event);
		
		luoTunnusTekstikentta.clear(); 
	}

	/**
	 * valkVetovalikko- MenuButtonin metodi, jossa lisätään uusi MenuItem eli pelaajatunnus valkoisen pelaajan MenuButton valikkoon
	 * @param event valkVetovalikko- MenuButtonin napsauttaminen
	 */
	@FXML
	void valkVetovalikko(ActionEvent event) {
		lisaaMenuItemit(valkVetovalikko, false);
	}

}
