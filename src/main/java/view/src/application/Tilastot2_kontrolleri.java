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
import java.util.Date;
import java.util.List;

/*
 * @author Aivan Vo 27.2.2022, Elmo Vahvaselkä
 */

public class Tilastot2_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;
	IDaoController dbKontrolleri = DBKontrolleri.getInstance();

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
	private Text pelaajanNimi;

	@FXML
	private TableView<PeliMuutos> tilastotaulu;

	@FXML
	private TableColumn<PeliMuutos, String> vari;

	@FXML
	private TableColumn<PeliMuutos, String> vastustaja;

	@FXML
	private TableColumn<PeliMuutos, String> tulos;
	
	@FXML
	private TableColumn<PeliMuutos, String> siirrot;

	@FXML
	private TableColumn<PeliMuutos, String> pvm;
	
	@FXML
	private TableColumn<PeliMuutos, String> kesto;

	@FXML
	void pelaajaMenu(ActionEvent event) {
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
		voittoProsentti.setText(String.format(ValittuKieli.getInstance().getLocale(), "%.1f%%", p.getVoittoprosentti()));
		voitot.setText(String.valueOf(p.getVoitot()));
		peliLkm.setText(String.valueOf(p.getPeleja()));
		pelaajanNimi.setText(p.getKayttajaTunnus());
		
		List<PelinTiedot> pelit = p.getPelit();
		List<PeliMuutos> tiedot = new ArrayList<>();
		
		for(PelinTiedot peli: pelit) {
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
		pelaajaMenu(null);
	}
}
