package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TorniTest {
	private Lauta lauta;

	@Test
	@DisplayName("Torni ei palauta yli rajojen meneviä siirtoja")
	public void testaaTorninRajat() {
		lauta = new Lauta(true);
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(4, 5);
		assertFalse("Siirto laudan ulkopuolella", menikoYli(siirrot));
	}

	@Test
	@DisplayName("Torni palauttaa oikean määrän siirtoja tyhjällä laudalla")
	public void testaaTorninSiirtojenMaara() {
		lauta = new Lauta(true);
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(4, 5);
		assertEquals(14, siirrot.size(), "siirtoja tuli väärä määrä");
	}

	@Test
	@DisplayName("Torni palauttaa oikean määrän siirtoja keskellä lautaa")
	public void testaaSiirtojenNappuloidenKanssa() {
		/*
		 * Torni on nyt kahden sotilas rivin välissä pitäis pystyä syömään yksi musta,
		 * mutta ei mennä omien päälle
		 */
		lauta = new Lauta();
		lauta.getLauta()[0][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[7][5].setNappula(new Sotilas(NappulanVari.MUSTA));

		// Testataan valkoisella
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(4, 5);
		assertEquals(10, siirrot.size(), "siirtoja tuli väärä määrä");

		// Testataan mustalla
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.MUSTA));
		siirrot = lauta.getSiirrot(4, 5);
		assertEquals(10, siirrot.size(), "siirtoja tuli väärä määrä");
	}

	@Test
	@DisplayName("getEkaSiirto metodin testaus")
	public void getEkaSiirto() {
		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		assertFalse("Valkoinen Torni ei palauttanut false",
				((Torni) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Valkoinen Torni ei palauttanut true", ((Torni) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());

		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.MUSTA));
		assertFalse("Musta Torni ei palauttanut false", ((Torni) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Musta Torni ei palauttanut true", ((Torni) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());
	}

	@Test
	@DisplayName("getVari metodin testaus")
	public void getVari() {
		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		assertTrue("Valkoinen torni ei palauttanut oikeata variä",
				((Torni) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.VALKOINEN);
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.MUSTA));
		assertTrue("Valkoinen torni ei palauttanut oikeata variä",
				((Torni) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.MUSTA);
	}

	private boolean menikoYli(ArrayList<Ruutu> siirrot) {
		for (Ruutu ruutu : siirrot) {
			if (ruutu.getX() > 7 || ruutu.getX() < 0 || ruutu.getY() > 7 || ruutu.getY() < 0) {
				return true;
			}
		}
		return false;
	}

}
