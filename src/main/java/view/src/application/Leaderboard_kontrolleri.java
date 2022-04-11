package view.src.application;

import java.io.IOException;
import java.text.DecimalFormat;
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
	private TableView<Pelaaja> leaderboardtaulu;

	@FXML
	private TableColumn<Pelaaja, String> pelaajatunnus;

	@FXML
	private TableColumn<Pelaaja, Integer> voittomaara;

	@FXML
	private TableColumn<Pelaaja, Double> voittoprosentti;

	List<Pelaaja> lista = new ArrayList<Pelaaja>();
	
	DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();

	ObservableList<Pelaaja> taulukkolista = FXCollections.observableArrayList();

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
		
		parasVoittoprosentti.setText(getParasVoittoprosentti().getKayttajaTunnus());
		enitenVoittoja.setText(getEnitenVoittoja().getKayttajaTunnus());
		getTaulukkotiedot();

	}
	
	private Pelaaja getParasVoittoprosentti() {
		System.out.println("Parasvoittoprosentti");
		
		List<Pelaaja> pelaajat = dbKontrolleri.getPelaajat();
		
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
		
		return pelaajat.get(0);
	}
	
	private Pelaaja getEnitenVoittoja() {
		System.out.println("Parasvoittoprosentti");
		
		List<Pelaaja> pelaajat = dbKontrolleri.getPelaajat();

		// sortataan pelaajalista voittoprosentin perusteella suurimmasta pienimpään
		Collections.sort(pelaajat, new Comparator<Pelaaja>() {
			@Override
			public int compare(Pelaaja p1, Pelaaja p2) {
				return Double.compare(p2.getVoitot(), p1.getVoitot());
			}
		});
		
		return pelaajat.get(0);
	}

	private void getTaulukkotiedot() {
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		System.out.println("taulukkotiedot");

		pelaajatunnus.setCellValueFactory(new PropertyValueFactory<Pelaaja, String>("kayttajaTunnus"));
		voittomaara.setCellValueFactory(new PropertyValueFactory<Pelaaja, Integer>("voitot"));
		voittoprosentti.setCellValueFactory(new PropertyValueFactory<Pelaaja, Double>("voittoprosentti"));

		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			taulukkolista.add(p);
		}

		leaderboardtaulu.setItems(taulukkolista);
	}

}
