package view.src.application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/*
 * @author Aivan Vo 25.2.2022
 */

public class Tilastot1_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button poistuBtn;
    
    @FXML
    private Button naytaTilastotBtn;
    
    @FXML
    private ListView<String> pelaajaLista;
    
    private ObservableList<String> items = FXCollections.observableArrayList();
    
    public void initialize() {
    	items.removeAll(items);
    	String a="Tauski";
    	String b="Niilo";
    	String c="Kerttuli";
    	items.addAll(a,b,c);
    	pelaajaLista.getItems().addAll(items);
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
    void naytaTilastot(ActionEvent event) {
//    	root = FXMLLoader.load(getClass().getResource(".fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

}
