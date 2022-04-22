package dao;

/**
 * @author Elmo Vahvaselkä 27.3.2022
 */

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.NappulanTyyppi;
import model.Shakkipeli;

class SiirtoTest {
	private Shakkipeli peli;

	@BeforeEach
	public void setUp() {
		// aloitetaan tilastoimaton peli
		peli = new Shakkipeli();
	}

	@Test
	@DisplayName("Valkoisen pelaajan ensimmäinen siirto tallentuu oikein")
	public void valkoisenEkaSiirto() {
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		List<Siirto> siirrot = peli.getPelinTiedot().getSiirrot();
		assertEquals(0, siirrot.get(0).getMistaX(), "MistäX oli väärin");
		assertEquals(1, siirrot.get(0).getMistaY(), "MistäY oli väärin");
		assertEquals(0, siirrot.get(0).getMihinX(), "MihinX oli väärin");
		assertEquals(3, siirrot.get(0).getMihinY(), "MihinY oli väärin");
		assertEquals(null, siirrot.get(0).getKorotus(), "Korotus ei ollut null");
	}

	@Test
	@DisplayName("Korotus tallentuu oikein")
	public void korotus() {
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		List<Siirto> siirrot = peli.getPelinTiedot().getSiirrot();
		assertEquals(9, siirrot.size(), "Siirtoja tallentui väärä määrä");
		assertEquals(0, siirrot.get(8).getMistaX(), "MistäX oli väärin");
		assertEquals(6, siirrot.get(8).getMistaY(), "MistäY oli väärin");
		assertEquals(0, siirrot.get(8).getMihinX(), "MihinX oli väärin");
		assertEquals(7, siirrot.get(8).getMihinY(), "MihinY oli väärin");
		assertEquals(NappulanTyyppi.KUNINGATAR, siirrot.get(8).getKorotus(), "Korotus ei ollut Kuningatar");
	}
	
	@Test
	@DisplayName("Parametriton konstruktori toimii")
	public void paremetritonKonstruktoriToimii(){
		new Siirto();
	}

}
