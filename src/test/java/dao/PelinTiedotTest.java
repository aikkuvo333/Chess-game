package dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.NappulanTyyppi;
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
		assertEquals(0, siirrot.get(0).getMistaX(), "MistäX on väärin");
		assertEquals(1, siirrot.get(0).getMistaY(), "MistäY on väärin");
		assertEquals(0, siirrot.get(0).getMihinX(),"MihinX on väärin");
		assertEquals(3, siirrot.get(0).getMihinY(), "MihinY on väärin");
		assertEquals(null, siirrot.get(0).getKorotus(), "Korotus ei palauttanut null");
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
	
	@Test
	@DisplayName("Korotus tellentuu oikein")
	public void korotusTallentuuOikein() {
		//Siirretään nappuloita niin, että valkoinen sotilas korottuu Kuningattareksi
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		assertEquals(NappulanTyyppi.KUNINGATAR, peli.getPelitilanne()[0][7].getNappula().getTyyppi(), "Sotilasta ei korotettu kuningattareksi");	
		
		//Tarkastetaan tellentuneet siirrot
		PelinTiedot tiedot = peli.getPelinTiedot();
		assertEquals(9, tiedot.getSiirrot().size(), "Siirtoja ei tallentunut oikea määrä");
		assertEquals(NappulanTyyppi.KUNINGATAR, tiedot.getSiirrot().get(8).getKorotus(), "Korotus ei tallentunut oikein");	
	}
}
