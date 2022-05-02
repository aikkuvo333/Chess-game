package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

/**
 * Luokka joka ohjaa kontrolloi Asetukset luokan oliota
 * @author Santeri Kuusisto
 */
public class Asetukset_kontrolleri {
	/**
	 * Asetukset olio pelilaudan asetuksille
	 */
	private Asetukset asetukset;
	/**
	 * PeliNakyma olio pelilaudan kontrollerille
	 */
	private PeliNakyma lauta_kontrolleri;
	/**
	 * boolean arvo laudan animaatiolle
	 */
	private boolean asetus;

	/**
	 * Konstruktori, joka asettaa valitut asetukset Asetukset oliosta haluttuun
	 * PeliNäkymä olioon
	 * 
	 * @param lauta_kontrolleri PeliNakymä olio johon asetukset kohdistetaan
	 * @param asetukset         Asetukset olio josta haetaan asetusten tiedot
	 *                          pelilautaa varten
	 */
	public Asetukset_kontrolleri(PeliNakyma lauta_kontrolleri, Asetukset asetukset) {
		this.lauta_kontrolleri = lauta_kontrolleri;
		this.asetukset = asetukset;
	}

	/**
	 * RadioButton olio animaation sallimiseksi tai estämiseksi
	 */
	@FXML
	private RadioButton animaatioAsetus;

	/**
	 * RadioButton olio äänten sallimiseksi tai estämiseksi
	 */
	@FXML
	private RadioButton aanetAsetus;

	/**
	 * animaatioAsetus- RadioButtonin metodi, jolla sallitaan tai kielletään laudan
	 * animaatio
	 * 
	 * @param event animaatioAsetus- RadioButtonin napsautus
	 */
	@FXML
	void asetaAnimaatio(ActionEvent event) {
		asetus = animaatioAsetus.isSelected();
		asetukset.setLaudanAnimaatio(asetus);
		asetukset.asetaAsetukset();
		lauta_kontrolleri.asetaKaantyminen(asetus);
	}

	/**
	 * aanetAsetus- RadioButtonin metodi, jolla sallitaan tai kielletään peliäänet
	 * 
	 * @param event aanetAsetus- RadioButtonin napsautus
	 */
	@FXML
	void asetaAanet(ActionEvent event) {
		asetus = aanetAsetus.isSelected();
		asetukset.setAanet(asetus);
		asetukset.asetaAsetukset();
		lauta_kontrolleri.asetaAanet(asetus);
	}

	/**
	 * Oletusasetusten hakeminen ja näiden implementointi käyttöliittymään
	 * 
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	public void initialize() throws IOException {
		Asetukset.initConfig();
		animaatioAsetus.setSelected(asetukset.isLaudanAnimaatio());
		aanetAsetus.setSelected(asetukset.isAanet());
	}
}
