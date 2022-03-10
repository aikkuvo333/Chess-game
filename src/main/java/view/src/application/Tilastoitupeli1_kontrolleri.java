package view.src.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collector;

import controller.DBKontrolleri;
import dao.Pelaaja;
import javafx.collections.ObservableList;
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
    private TextField mustTekstikenttaBtn;

    @FXML
    private Button mustLuotunnusBtn;

  
    @FXML
    void aloitaPeli(ActionEvent event) throws IOException {
    	Lauta_kontrolleri controller = new Lauta_kontrolleri(musta, valkoinen);
		loader = new FXMLLoader(getClass().getResource("Lauta.fxml"));
		loader.setController(controller);
		root = loader.load();
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    public void initialize() {
    	valkVetovalikko(null);
    	mustVetovalikko(null);
    }

    @FXML
    void mustLuotunnus(ActionEvent event) {
    	DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
    	musta = dbKontrolleri.luoPelaaja(mustTekstikenttaBtn.getText());
    	mustVetovalikko(event);
    }

    @FXML
    void mustTekstikentta(ActionEvent event) {

    }
    
    public void lisaaMenuItemit(MenuButton btn, boolean isMusta) {
    	DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
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
    	root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void valkLuotunnus(ActionEvent event) {
    	DBKontrolleri dbKontrolleri = DBKontrolleri.getInstance();
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
