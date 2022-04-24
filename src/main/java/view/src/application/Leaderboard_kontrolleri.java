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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * @author Aivan Vo 7.3.2022, Elmo Vahvaselkä
 */

public class Leaderboard_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button poistuBtn;

	@FXML
	private Label enitenVoittojaOtsikko;

	@FXML
	private Label parasVoittoprosenttiOtsikko;

	@FXML
	private Label enitenVoittoja;

	@FXML
	private Label parasVoittoprosentti;

	@FXML
	private Text tunnus;

	@FXML
	private TableView<PelaajaMuutos> leaderboardtaulu;

	@FXML
	private TableColumn<PelaajaMuutos, String> pelaajatunnus;

	@FXML
	private TableColumn<PelaajaMuutos, String> voittomaara;

	@FXML
	private TableColumn<PelaajaMuutos, String> voittoprosentti;

	@FXML
	private TableColumn<PelaajaMuutos, String> peleja;

	IDaoController dbKontrolleri = DBKontrolleri.getInstance();
	List<Pelaaja> pelaajat = dbKontrolleri.getPelaajat();
	ObservableList<PelaajaMuutos> taulukkolista = FXCollections.observableArrayList();

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

	public void initialize() {

		parasVoittoprosentti.setText(
				getParasVoittoprosentti().getKayttajaTunnus() + ": " + getParasVoittoprosentti().getVoittoprosentti());
		enitenVoittoja.setText(getEnitenVoittoja().getKayttajaTunnus() + ": " + getEnitenVoittoja().getVoitot());
		getTaulukkotiedot();

	}

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

	private void getTaulukkotiedot() {
		pelaajatunnus.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("kayttajaTunnus"));
		voittomaara.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("voitot"));
		voittoprosentti.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("voittoprosentti"));
		peleja.setCellValueFactory(new PropertyValueFactory<PelaajaMuutos, String>("peleja"));

		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			taulukkolista.add(new PelaajaMuutos(p));
		}

		leaderboardtaulu.setItems(taulukkolista);
	}

}
