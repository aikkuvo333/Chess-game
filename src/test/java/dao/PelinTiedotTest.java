package dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.NappulanVari;
import model.Shakkipeli;

class PelinTiedotTest {

	private Shakkipeli peli;
	
	@BeforeEach 
	public void setUp() {
		//aloitetaan tilastoimaton peli
		peli = new Shakkipeli(true);
	}
	
	@Test
	@DisplayName("Ensimmäinen siirto tallentuu oikein")
	public void ensimmainenSiirtoTallentuuOikein() {
		assertTrue("Valkoisen sotilaan siirtäminen ei onnistunut", peli.siirra(0, 1, 0, 3));
		PelinTiedot tiedot = peli.getPelinTiedot();
		assertTrue("Valkoisen pelaajan nimi on väärä", tiedot.getValkoinenPelaaja().equals("Sebastian"));
		assertTrue("Mustan pelaajan nimi on väärä", tiedot.getMustaPelaaja().equals("Daniel"));
		ArrayList<Siirto> siirrot = tiedot.getSiirrot();
		assertTrue("MistäX on väärin", siirrot.get(0).getMistaX() == 0);
		assertTrue("MistäY on väärin", siirrot.get(0).getMistaY() == 1);
		assertTrue("MihinX on väärin", siirrot.get(0).getMihinX() == 0);
		assertTrue("MihinY on väärin", siirrot.get(0).getMihinY() == 3);
		assertTrue("Korotus ei palauttanut null", siirrot.get(0).getKorotus() == null);
	}

	@Test
	@DisplayName("Voittaja tallentuu oikein")
	public void tallentuukoVoittajaOikein() {
		assertTrue("Valkoinen sotilas ei siirtynyt 1", peli.siirra(5, 1, 5, 2));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt 2", peli.siirra(6, 1, 6, 3));
		assertTrue("Musta Kuningatar ei siirtynyt ja lopettanut peliä", peli.siirra(3, 7, 7, 3));
		PelinTiedot tiedot = peli.getPelinTiedot();
		assertEquals(4, tiedot.getSiirrot().size(), "Siirtoja ei tallentunut oikea määrä");
		assertEquals(NappulanVari.MUSTA, tiedot.getVoittaja(), "Voittaja ei ollut musta");
	}
}
