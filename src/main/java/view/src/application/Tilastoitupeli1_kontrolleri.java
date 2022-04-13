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

/*
 * @author Aivan Vo 3.2.2022
 */

public class Tilastoitupeli1_kontrolleri {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	private Pelaaja musta;
	private Pelaaja valkoinen;
	private IDaoController dbKontrolleri = DBKontrolleri.getInstance();

	@FXML
	private Button poistuBtn;

	@FXML
	private MenuButton valkVetovalikkoBtn;

	@FXML
	private TextField valkTekstikenttaBtn;

	@FXML
	private Button valkLuotunnusBtn;

	@FXML
	private Button aloitaPeliBtn;

	@FXML
	private MenuButton mustVetovalikkoBtn;

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

	public void initialize() {
		valkVetovalikko(null);
		mustVetovalikko(null);
	}

	public void lisaaMenuItemit(MenuButton btn, boolean isMusta) {
		for (Pelaaja p : dbKontrolleri.getPelaajat()) {
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

	@FXML
	void mustVetovalikko(ActionEvent event) {
		lisaaMenuItemit(mustVetovalikkoBtn, true);
	}

	@FXML
	void poistu(ActionEvent event) throws IOException {
		loader.setLocation(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void valkLuotunnus(ActionEvent event) {
		valkoinen = dbKontrolleri.luoPelaaja(valkTekstikenttaBtn.getText());
		valkVetovalikko(event);
	}

	@FXML
	void valkTekstikentta(ActionEvent event) {

	}

	@FXML
	void valkVetovalikko(ActionEvent event) {
		lisaaMenuItemit(valkVetovalikkoBtn, false);
	}

}
