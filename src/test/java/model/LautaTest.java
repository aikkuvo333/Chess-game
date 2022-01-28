package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LautaTest {

	private Lauta lauta;
	
	@Test
	@DisplayName("Ensimmäinen ruutu on oikein")
	public void ensimmäinenRuutuOnAsetettuOikein() {
		lauta = new Lauta();
		assertEquals(1, lauta.getLauta()[0][0].getX(), "Ensimmäisen ruudun x-koordinaatti on oikein");
		assertEquals(1, lauta.getLauta()[0][0].getY(), "Ensimmäisen ruudun y-koordinaatti on oikein");
	}
	
	@Test
	@DisplayName("Laudan ruudut on asetettu oikein")
	public void laudaRuutujenAsettaminenToimiiOikein() {
		lauta = new Lauta();
		assertTrue("Ruudut on numeroitu oikein", testaaRuudut(lauta));
	}
	
	@Test
	@DisplayName("Laudan nappulat on asetettu oikein")
	public void laudaNappuloidenAsettaminenToimiiOikein() {
		lauta = new Lauta();
		lauta.asetaNappulat();
		assertTrue("Ruudut on numeroitu oikein", testaaNappulat(lauta));
	}
	
	private boolean testaaRuudut(Lauta lauta) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta.getLauta()[x][y].getX() != x+1 || lauta.getLauta()[x][y].getY() != y+1) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean testaaNappulat(Lauta lauta) {
		for (int x = 0; x < 8; x++) {
			if( lauta.getLauta()[x][1].getNappula() instanceof Nappula
					&& lauta.getLauta()[x][1].getNappula().getVari() != NappulanVari.VALKOINEN) {
				return false;
			}
			if( lauta.getLauta()[x][1].getNappula() instanceof Nappula
					&& lauta.getLauta()[x][6].getNappula().getVari() != NappulanVari.MUSTA) {
				return false;
			} 
		}
		return true;
	}

}
