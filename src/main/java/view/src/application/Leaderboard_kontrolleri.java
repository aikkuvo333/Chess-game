package view.src.application;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dao.DBKontrolleri;
import dao.IDaoController;
import dao.Pelaaja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * Luokka <code>Leaderboard_kontrolleri</code> luo käyttöliittymän Leaderboard
 * -näkymän.
 * 
 * @author Aivan Vo
 * @author Oliver Hamberg
 * @author Elmo Vahvaselkä
 */

public class Leaderboard_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Button olio Poistu -napille
	 */
	@FXML
	private Button poistuBtn;

	/**
	 * Label olio jolla esitetään suurimman voittomäärän omaavan Pelaaja olion
	 * käyttäjätunnuksen
	 */
	@FXML
	private Label enitenVoittojaOtsikko;

	/**
	 * Label olio jolla esitetään suurimman voittoprosentin omaavan Pelaaja olion
	 * käyttäjätunnuksen
	 */
	@FXML
	private Label parasVoittoprosenttiOtsikko;

	/**
	 * Label olio jolla esitetään suurimman voittomäärän omaavan Pelaaja olion
	 * voittopelimäärän
	 */
	@FXML
	private Label enitenVoittoja;

	/**
	 * Label olio jolla esitetään suurimman voittoprosentin omaavan Pelaaja olion
	 * voittoprosentin
	 */
	@FXML
	private Label parasVoittoprosentti;

	/**
	 * TableView olio, joka sisältää PelaajaMuutos olion tiedot
	 */
	@FXML
	private TableView<PelaajaMuutos> leaderboardtaulu;

	/**
	 * TableColumn olio, joka sisältää PelaajaMuutos olion pelaajatunnuksen
	 * Stringinä
	 */
	@FXML
	private TableColumn<PelaajaMuutos, String> pelaajatunnus;

	/**
	 * TableColumn olio, joka sisältää PelaajaMuutos olion voittomäärän Stringinä
	 */
	@FXML
	private TableColumn<PelaajaMuutos, String> voittomaara;

	/**
	 * TableColumn olio, joka sisältää PelaajaMuutos olion voittoprosentin Stringinä
	 */
	@FXML
	private TableColumn<PelaajaMuutos, String> voittoprosentti;

	/**
	 * TableColumn olio, joka sisältää PelaajaMuutos olion pelimäärän Stringinä
	 */
	@FXML
	private TableColumn<PelaajaMuutos, String> peleja;

	/**
	 * IDaoController rajapintaa toteuttavan DBKontrolleri singletonin kutsuminen
	 */
	IDaoController dbKontrolleri = DBKontrolleri.getInstance();

	/**
	 * Lista joka sisältää olemasa olevat Pelaaja oliot
	 */
	List<Pelaaja> pelaajat = dbKontrolleri.getPelaajat();

	/**
	 * ObservableList olio, joka sisältää PelaajaMuutos oliot taulukkoa varten
	 */
	ObservableList<PelaajaMuutos> taulukkolista = FXCollections.observableArrayList();

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
	 * kokonaan. Metodi asettaa paras voittoprosentti- ja eniten voittoja-Labeleihin
	 * sen Pelaaja olion käyttäjätunnuksen jolla on suurin voittoprosentti ja suurin
	 * voittomäärä.
	 */
	public void initialize() {

		parasVoittoprosentti.setText(
				getParasVoittoprosentti().getKayttajaTunnus() + ": " + getParasVoittoprosentti().getVoittoprosentti());
		enitenVoittoja.setText(getEnitenVoittoja().getKayttajaTunnus() + ": " + getEnitenVoittoja().getVoitot());
		getTaulukkotiedot();

	}

	/**
	 * Metodi joka vertailee olemassa olevia Pelaaja olioiden voittoprosentteja
	 * toisiinsa
	 * 
	 * @return PelaajaMuutos olio jolla on paras voittoprosentti
	 */
	private PelaajaMuutos getParasVoittoprosentti() {
		if (pelaajat.size() == 0) {
			return null;
		}

		// sortataan pelaajalista voittoprosentin perusteella suurimmasta pienimpään
		Collections.sort(pelaajat, new Comparator<Pelaaja>() {
			@Override
			public int compare(Pelaaja p1, Pelaaja p2) {
				return Double.compare(p2.getVoittoprosentti(), p1.getVoittoprosentti());
			}
		});

		return new PelaajaMuutos(pelaajat.get(0));
	}

	/**
	 * Metodi joka vertailee olemassa olevia Pelaaja olioiden voittomääriä toisiinsa
	 * 
	 * @return Pelaaja olio, jolla on eniten pelivoittoja (lkm)
	 */
	private Pelaaja getEnitenVoittoja() {
		// sortataan pelaajalista voittojen määrän perusteella suurimmasta pienimpään
		Collections.sort(pelaajat, new Comparator<Pelaaja>() {
			@Override
			public int compare(Pelaaja p1, Pelaaja p2) {
				return Double.compare(p2.getVoitot(), p1.getVoitot());
			}
		});

		return pelaajat.get(0);
	}

	/**
	 * Metodi hakee jokaiseen taulukon arvoon PelaajaMuutos oliolta tiedot ja
	 * asettavat arvot taulukkoon
	 */
	private void getTaulukkotiedot() {
		pelaajatunnus.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("kayttajaTunnus"));
		voittomaara.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("voitot"));
		voittoprosentti.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("voittoprosentti"));
		peleja.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("peleja"));

		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			if (!p.getKayttajaTunnus().equals("Anonyymi")) {
				taulukkolista.add(new PelaajaMuutos(p));
			}
		}

		leaderboardtaulu.setItems(taulukkolista);
	}

}
