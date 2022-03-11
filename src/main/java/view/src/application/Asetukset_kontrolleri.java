package view.src.application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
/*
 * 
 * @author Santeri Kuusisto
 * 
 */
public class Asetukset_kontrolleri {
	private Asetukset asetukset;
	private Lauta_kontrolleri lauta_kontrolleri;
	private boolean asetus;
	
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
    	asetus = animaatioAsetus.isSelected();
    	asetukset.setLaudanAnimaatio(asetus);
    	asetukset.asetaAsetukset();
    	lauta_kontrolleri.asetaKaantyminen(asetus);
    }

    @FXML
    void asetaDarkMode(ActionEvent event) {
    	asetus = darkModeAsetus.isSelected();
    	asetukset.setDarkMode(asetus);
    	asetukset.asetaAsetukset();
    	lauta_kontrolleri.asetaDarkMode(asetus);
    }
    
    @FXML
    void asetaAanet(ActionEvent event) {
    	asetus = aanetAsetus.isSelected();
    	asetukset.setAanet(asetus);
    	asetukset.asetaAsetukset();
    	lauta_kontrolleri.asetaAanet(asetus);
    }

    public void initialize() throws IOException {
    	Asetukset.initConfig();
    	animaatioAsetus.setSelected(asetukset.isLaudanAnimaatio());
    	darkModeAsetus.setSelected(asetukset.isDarkMode());
    	aanetAsetus.setSelected(asetukset.isAanet());
    }
}
