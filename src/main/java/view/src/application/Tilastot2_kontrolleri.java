package view.src.application;

import java.io.IOException;

import dao.DBKontrolleri;
import dao.IDaoController;
import dao.Pelaaja;
import dao.PelinTiedot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Luokka <code>Tilastot2_kontrolleri</code> luo Omat tilastot
 * -käyttöliittymänäkymän.
 * 
 * @author Aivan Vo 27.2.2022
 * @author Oliver Hamberg
 * @author Elmo Vahvaselkä
 */

public class Tilastot2_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;
	IDaoController dbKontrolleri = DBKontrolleri.getInstance();

	/**
	 * MenuButton olio jolla valitaan sen pelaajan pelaajatunnus, jonka tilastoja
	 * halutaan tarkastella.
	 */
	@FXML
	private MenuButton pelaajaMenuBtn;

	/**
	 * Button olio Poistu -napille
	 */
	@FXML
	private Button poistuBtn;

	/**
	 * Text olio, jolla esitetään tarkasteltavan pelaajan pelien lukumäärä
	 */
	@FXML
	private Text peliLkm;

	/**
	 * Text olio, jolla esitetään tarkasteltavan pelaajan voittomäärä
	 */
	@FXML
	private Text voitot;

	/**
	 * Text olio, jolla esitetään tarkasteltavan pelaajan voittoprosentti
	 */
	@FXML
	private Text voittoProsentti;

	/**
	 * Text olio, jolla esitetään tarkasteltavan pelaajan pelaajatunnus
	 */
	@FXML
	private Text pelaajanNimi;

	/**
	 * TableView olio, joka sisältää PeliMuutos olion tiedot sen pelaajatunnuksen
	 * näkökulmasta, joka on valittu
	 */
	@FXML
	private TableView<PeliMuutos> tilastotaulu;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion värin sen pelaajatunnuksen
	 * näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> vari;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion vastustajan sen
	 * pelaajatunnuksen näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> vastustaja;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion tuloksen sen
	 * pelaajatunnuksen näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> tulos;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion pelin siirtojen lukumäärän
	 * sen pelaajatunnuksen näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> siirrot;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion päivämäärän sen
	 * pelaajatunnuksen näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> pvm;

	/**
	 * TableColumn olio, joka sisältää PeliMuutos olion keston sen pelaajatunnuksen
	 * näkökulmasta, joka on valittu
	 */
	@FXML
	private TableColumn<PeliMuutos, String> kesto;

	/**
	 * Button olio Poista käyttäjä -napille
	 */
	@FXML
	private Button poistaKayttajaBtn;

	/**
	 * Pelaaja Menu- MenuButtonin metodi, joka näyttää sen pelaajan tiedot, joka on
	 * käyttöliittymässä valittu Menubuttoniin
	 * 
	 * @param event Pelaaja Menu- MenuButtonin valinta
	 */
	@FXML
	void pelaajaMenu(ActionEvent event) {
		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			if (!p.getKayttajaTunnus().equals("Anonyymi")) {
				MenuItem menuItem = new MenuItem(p.getKayttajaTunnus());
				menuItem.setOnAction(a -> {
					pelaajaMenuBtn.setText(p.getKayttajaTunnus());
					openPelaajaData(p);
				});
				pelaajaMenuBtn.getItems().add(menuItem);
			}
		}

	}

	/**
	 * Metodi hakee halutun pelaajan tiedot sekä niiden pelien tiedot, joissa
	 * kyseinen pelaaja on ollut osallisena
	 * 
	 * @param p Pelaaja olio, jonka tietoja halutaan tarkastella
	 */
	void openPelaajaData(Pelaaja p) {
		tilastotaulu.getItems().clear();
		voittoProsentti
				.setText(String.format(ValittuKieli.getInstance().getLocale(), "%.1f%%", p.getVoittoprosentti()));
		voitot.setText(String.valueOf(p.getVoitot()));
		peliLkm.setText(String.valueOf(p.getPeleja()));
		pelaajanNimi.setText(p.getKayttajaTunnus());

		List<PelinTiedot> pelit = p.getPelit();
		List<PeliMuutos> tiedot = new ArrayList<>();

		for (PelinTiedot peli : pelit) {
			tiedot.add(new PeliMuutos(peli, p));
		}

		vari.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("vari"));
		vastustaja.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("vastustaja"));
		tulos.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("tulos"));
		siirrot.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("siirrot"));
		tulos.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("tulos"));
		pvm.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("pvm"));
		kesto.setCellValueFactory(new PropertyValueFactory<PeliMuutos, String>("kesto"));

		for (PeliMuutos data : tiedot) {
			tilastotaulu.getItems().add(data);
		}
		System.out.println(tilastotaulu.getColumns());

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
	 * Metodi jota kutsutaan kerran, kun siihen liittyvä sisältö on ladattu
	 * kokonaan. Metodi asettaa MenuButton vetovalikon pelaajatunnuksen arvon null
	 * arvoksi.
	 */
	public void initialize() {
		pelaajaMenu(null);
	}

	/**
	 * Poista käyttäjä- Buttonin metodi, jossa sitä napsauttamalla poistetaan
	 * tietokannasta sen pelaajan tiedot
	 * 
	 * @param event Poista käyttäjä- Buttonin napsautus
	 */
	@FXML
	void poistaKayttaja(ActionEvent event) {
		System.out.println("Poistetaan kayttaja: ");
		for (Pelaaja p : dbKontrolleri.haePelaaja(pelaajanNimi.getText())) {
			dbKontrolleri.poistaPelaaja(p);
		}
	}
}
