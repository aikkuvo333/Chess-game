package model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TorniTest {
	private Ruutu ruutu;
	private Torni torni;

	@Test
	@DisplayName("Torni ei palauta yli rajojen meneviä siirtoja")
	public void testaaTorninRajat() {
		ruutu = new Ruutu(1,1);
		torni = new Torni(NappulanVari.VALKOINEN);
		assertEquals(14, torni.getSiirrot(ruutu).size(), "siirtoja tuli väärä määrä");
		assertFalse("Siirto laudan ulkopuolella", menikoYli(torni.getSiirrot(ruutu)));
	}
	
	private boolean menikoYli(ArrayList<Ruutu> siirrot) {
		for(Ruutu ruutu: siirrot) {
			if(ruutu.getX() > 8 || ruutu.getX() < 1 || ruutu.getY() > 8 || ruutu.getY() < 1) {
				return true;
			}
		}
		return false;
	}
	
}
