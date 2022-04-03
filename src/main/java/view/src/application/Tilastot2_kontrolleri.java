package view.src.application;

import java.io.IOException;

import dao.DBKontrolleri;
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

import java.util.Date;
import java.util.List;

/*
 * @author Aivan Vo 27.2.2022
 */

public class Tilastot2_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private MenuButton pelaajaMenuBtn;

	@FXML
	private Button poistuBtn;

	@FXML
	private Text peliLkm;

	@FXML
	private Text voitot;

	@FXML
	private Text voittoProsentti;

	@FXML
	private TableView<PelinTiedot> tilastotaulu;

	@FXML
	private TableColumn<PelinTiedot, Integer> mustat;

	@FXML
	private TableColumn<PelinTiedot, Integer> valkoiset;

	@FXML
	private TableColumn<PelinTiedot, Integer> tulos;

	@FXML
	private TableColumn<PelinTiedot, Date> pvm;

	@FXML
	void pelaajaMenu(ActionEvent event) {
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
			MenuItem menuItem = new MenuItem(p.getKayttajaTunnus());
			menuItem.setOnAction(a -> {
				pelaajaMenuBtn.setText(p.getKayttajaTunnus());
				openPelaajaData(p);
			});
			pelaajaMenuBtn.getItems().add(menuItem);
		}

	}

	@SuppressWarnings("unchecked")
	void openPelaajaData(Pelaaja p) {
		tilastotaulu.getItems().clear();
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		voittoProsentti.setText(dbKontrolleri.haeVoittoProsentti(p));
		voitot.setText(String.valueOf(dbKontrolleri.haeVoittoMaara(p)));
		peliLkm.setText(String.valueOf(dbKontrolleri.haePelienMaara(p)));
		List<PelinTiedot> tiedot = dbKontrolleri.haePelaajanPelit(p);
		mustat.setCellValueFactory(new PropertyValueFactory<>("mustaPelaaja"));
		valkoiset.setCellValueFactory(new PropertyValueFactory<>("valkoinenPelaaja"));
		tulos.setCellValueFactory(new PropertyValueFactory<>("voittaja"));
		pvm.setCellValueFactory(new PropertyValueFactory<>("pvm"));
		for (PelinTiedot data : tiedot) {
			tilastotaulu.getItems().add(data);
		}
		System.out.println(tilastotaulu.getColumns());

	}

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
		DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
		pelaajaMenu(null);
	}
}
