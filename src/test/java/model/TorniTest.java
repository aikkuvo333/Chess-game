package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import static org.junit.Assert.assertFalse;
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
		/*Torni on nyt kahden sotilas rivin välissä
		 *pitäis pystyä syömään yksi musta, mutta ei mennä omien päälle
		 */
		lauta = new Lauta();
		//Testataan valkoisella
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(4, 5);
		assertEquals(11, siirrot.size(), "siirtoja tuli väärä määrä");
		
		//Testataan mustalla
		lauta.getLauta()[4][5].setNappula(new Torni(NappulanVari.MUSTA));
		siirrot = lauta.getSiirrot(4, 5);
		assertEquals(11, siirrot.size(), "siirtoja tuli väärä määrä");
	}
	
	private boolean menikoYli(ArrayList<Ruutu> siirrot) {
		for(Ruutu ruutu: siirrot) {
			if(ruutu.getX() > 7 || ruutu.getX() < 0 || ruutu.getY() > 7 || ruutu.getY() < 0) {
				return true;
			}
		}
		return false;
	}
	
}
