package view.src.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dao.DBKontrolleri;
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
 * @author Aivan Vo 7.3.2022
 */

public class Leaderboard_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button poistuBtn;

	@FXML
	private Label pelaaja3tunnus;

	@FXML
	private Label pelaaja3score;

	@FXML
	private Label pelaaja1tunnus;

	@FXML
	private Label pelaaja1score;

	@FXML
	private Label pelaaja2tunnus;

	@FXML
	private Label pelaaja2score;

	@FXML
	private Text tunnus;

	@FXML
	private TableView<Pelaaja> leaderboardtaulu;

//	@FXML
//	private TableColumn<String, Integer> sijoitus;

	@FXML
	private TableColumn<Pelaaja, String> pelaajatunnus;

	@FXML
	private TableColumn<Pelaaja, Integer> voittomaara;

	@FXML
	private TableColumn<Pelaaja, String> voittoprosentti;

	List<Pelaaja> lista = new ArrayList<Pelaaja>();

	ObservableList<Pelaaja> taulukkolista = FXCollections.observableArrayList();

	@FXML
	void poistu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void initialize() {
		getTop3();
		getTaulukkotiedot();

		if (lista.size() >= 3) {
			pelaaja1tunnus.setText(lista.get(0).getKayttajaTunnus());
			pelaaja2tunnus.setText(lista.get(1).getKayttajaTunnus());
			pelaaja3tunnus.setText(lista.get(2).getKayttajaTunnus());

			pelaaja1score.setText(lista.get(0).getVoittoprosentti());
			pelaaja2score.setText(lista.get(1).getVoittoprosentti());
			pelaaja3score.setText(lista.get(2).getVoittoprosentti());
		} else if (lista.size() == 2) {
			pelaaja1tunnus.setText(lista.get(0).getKayttajaTunnus());
			pelaaja2tunnus.setText(lista.get(1).getKayttajaTunnus());

			pelaaja1score.setText(lista.get(0).getVoittoprosentti());
			pelaaja2score.setText(lista.get(1).getVoittoprosentti());
		} else if (lista.size() == 1) {
			pelaaja1tunnus.setText(lista.get(0).getKayttajaTunnus());

			pelaaja1score.setText(lista.get(0).getVoittoprosentti());
		} else {
			System.out.println("Leaderboardiin ei voida hakea arvoja, sillä tietokanta on tyhjä.");
		}

	}

	private void getTop3() {
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		System.out.println("top3");

		// tallennetaan pelaajat listaan
		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			lista.add(p);
		}

		// sortataan pelaajalista voittoprosentin perusteella suurimmasta pienimpään
		Collections.sort(lista, new Comparator<Pelaaja>() {
			public int compare(Pelaaja p1, Pelaaja p2) {
				return p2.getVoittoprosentti().compareTo(p1.getVoittoprosentti());
			}
		});

	}

	private void getTaulukkotiedot() {
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		System.out.println("taulukkotiedot");

		pelaajatunnus.setCellValueFactory(new PropertyValueFactory<Pelaaja, String>("kayttajaTunnus"));
		voittomaara.setCellValueFactory(new PropertyValueFactory<Pelaaja, Integer>("voittomaara"));
		voittoprosentti.setCellValueFactory(new PropertyValueFactory<Pelaaja, String>("voittoprosentti"));

		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			taulukkolista.add(p);
		}

		leaderboardtaulu.setItems(taulukkolista);
	}

}
