package dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Shakkipeli;

class PelinTiedotTest {
	private Shakkipeli peli;

	@BeforeEach
	public void setUp() {
		// aloitetaan tilastoimaton peli
		peli = new Shakkipeli(false);
	}
	
	@Test
	@DisplayName("Voittaja tellentuu oikein, kun musta voittaa nopeasti")
	public void mustaVoittaaPikamatilla() {
		assertTrue("Valkoinen sotilas ei siirtynyt 1", peli.siirra(5, 1, 5, 2));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt 2", peli.siirra(6, 1, 6, 3));
		assertTrue("Musta Kuningatar ei siirtynyt ja lopettanut peliä", peli.siirra(3, 7, 7, 3));
		assertEquals("Musta", peli.getPelinTiedot().getVoittaja().getKayttajaTunnus(), "Voittaja tellentui väärin");
	}
	
	@Test
	@DisplayName("Luovuttaminen tallentuu oikein, kun musta luovuttaa")
	public void mustaLuovuttaa() {
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 2));
		peli.julistaVoittaja();
		assertEquals("Valkoinen", peli.getPelinTiedot().getVoittaja().getKayttajaTunnus(), "Voittaja tellentui väärin");
	}
}
