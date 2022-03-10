package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/*
 * @author Aivan Vo 3.2.2022
 */

public class Tilastoitupeli1_kontrolleri {
//	private Stage stage;
//	private Scene scene;
//	private Parent root;
	
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
    void aloitaPeli(ActionEvent event) {
    	System.out.println("klick");
    }

    @FXML
    void mustLuotunnus(ActionEvent event) {

    }

    @FXML
    void mustTekstikentta(ActionEvent event) {

    }

    @FXML
    void mustVetovalikko(ActionEvent event) {

    }

    @FXML
    void poistu(ActionEvent event) throws IOException {
    	System.out.println("klikattu");
//    	root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void valkLuotunnus(ActionEvent event) {

    }

    @FXML
    void valkTekstikentta(ActionEvent event) {

    }

    @FXML
    void valkVetovalikko(ActionEvent event) {

    }

}
