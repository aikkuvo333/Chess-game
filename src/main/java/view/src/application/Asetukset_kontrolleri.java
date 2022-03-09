package view.src.application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class Asetukset_kontrolleri {
	private Asetukset asetukset;
	private Lauta_kontrolleri lauta_kontrolleri;
	
	public Asetukset_kontrolleri(Lauta_kontrolleri lauta_kontrolleri, Asetukset asetukset) {
		this.lauta_kontrolleri = lauta_kontrolleri;
		this.asetukset = asetukset;
	}
	
	public Asetukset_kontrolleri() {
		
	}
	
    @FXML
    private RadioButton animaatioAsetus;

    @FXML
    private RadioButton peruutusAsetus;

    @FXML
    private RadioButton darkModeAsetus;

    @FXML
    void asetaAnimaatio(ActionEvent event) {
    	asetukset.setLaudanAnimaatio(animaatioAsetus.isSelected());
    	asetukset.asetaAsetukset();
    	lauta_kontrolleri.asetaKaantyminen(animaatioAsetus.isSelected());
    }

    @FXML
    void asetaDarkMode(ActionEvent event) {
    	asetukset.setDarkMode(darkModeAsetus.isSelected());
    	asetukset.asetaAsetukset();
    }

    @FXML
    void asetaPeruutus(ActionEvent event) {
    	asetukset.setPeruutus(peruutusAsetus.isSelected());
    	asetukset.asetaAsetukset();
    }

    public void initialize() throws IOException {
    	Asetukset.initConfig();
    	animaatioAsetus.setSelected(asetukset.isLaudanAnimaatio());
    	peruutusAsetus.setSelected(asetukset.isPeruutus());
    	darkModeAsetus.setSelected(asetukset.isDarkMode());
    }
}
