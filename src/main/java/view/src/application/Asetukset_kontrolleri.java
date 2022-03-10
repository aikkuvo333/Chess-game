package view.src.application;
import java.io.IOException;

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
    private RadioButton darkModeAsetus;

    @FXML
    private RadioButton aanetAsetus;
    
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
    void asetaAanet(ActionEvent event) {
    	asetukset.setAanet(aanetAsetus.isSelected());
    	asetukset.asetaAsetukset();
    	lauta_kontrolleri.asetaAanet(aanetAsetus.isSelected());
    }

    public void initialize() throws IOException {
    	Asetukset.initConfig();
    	animaatioAsetus.setSelected(asetukset.isLaudanAnimaatio());
    	darkModeAsetus.setSelected(asetukset.isDarkMode());
    	
    }
}
