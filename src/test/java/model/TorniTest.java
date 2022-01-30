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
	private Ruutu ruutu;
	private Torni torni;
	private Ruutu[][] lauta;

	@Test
	@DisplayName("Torni ei palauta yli rajojen meneviä siirtoja")
	public void testaaTorninRajat() {
		lauta = new Ruutu[8][8];
		ruutu = new Ruutu(0,0);
		torni = new Torni(NappulanVari.VALKOINEN);
		assertFalse("Siirto laudan ulkopuolella", menikoYli(torni.getSiirrot(ruutu, lauta)));
	}
	
	@Test
	@DisplayName("Torni palauttaa oikean määrän siirtoja")
	public void testaaTorninSiirtojenMaara() {
		lauta = new Ruutu[8][8];
		ruutu = new Ruutu(0,0);
		torni = new Torni(NappulanVari.VALKOINEN);
		assertEquals(14, torni.getSiirrot(ruutu, lauta).size(), "siirtoja tuli väärä määrä");
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
