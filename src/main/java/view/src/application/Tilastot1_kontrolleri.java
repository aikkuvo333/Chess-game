package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    void poistu(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
