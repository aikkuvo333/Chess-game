package view.src.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/*
 * @author Aivan Vo 27.2.2022
 */

public class Tilastot2_kontrolleri {

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
    private TableView<?> tilastotaulu;

    @FXML
    private TableColumn<?, ?> vari;

    @FXML
    private TableColumn<?, ?> vastustaja;

    @FXML
    private TableColumn<?, ?> tulos;

    @FXML
    private TableColumn<?, ?> siirrot;

    @FXML
    private TableColumn<?, ?> pvm;

    @FXML
    private TableColumn<?, ?> kesto;

    @FXML
    private TableColumn<?, ?> videot;

    @FXML
    void pelaajaMenu(ActionEvent event) {

    }

    @FXML
    void poistu(ActionEvent event) {

    }

}

