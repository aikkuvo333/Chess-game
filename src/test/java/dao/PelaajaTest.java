package dao;

/**
 * @author Elmo Vahvaselkä 27.3.2022
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Shakkipeli;

class PelaajaTest {

	private Shakkipeli peli;
	
	@BeforeEach
	public void setUp() {
		// aloitetaan tilastoimaton peli
		peli = new Shakkipeli(false);
	}
	
	@Test
	@DisplayName("Tilastoimattoman pelin alussa pelaajat luodaan oikein")
	public void pelaajatLuodaanOikein() {
		assertEquals("Valkoinen", peli.getPelinTiedot().getValkoinenPelaaja().getKayttajaTunnus(), "Valkoisen pelaajan nimi oli väärin");
		assertEquals("Musta", peli.getPelinTiedot().getMustaPelaaja().getKayttajaTunnus(), "Mustan pelaajan nimi oli väärin");
	}
	
	@Test
	@DisplayName("Parametriton konstrukttori toimii")
	public void parametritonKonstruktorioimii() {
		new Pelaaja();
	}
	
}
