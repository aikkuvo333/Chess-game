package model;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LautaTest {

	private Lauta lauta;
	
	@BeforeEach 
	public void setUp() {
		lauta = new Lauta();
	}
	
	@Test
	@DisplayName("Ensimmäinen ruutu on oikein")
	public void ensimmäinenRuutuOnAsetettuOikein() {
		assertEquals(0, lauta.getLauta()[0][0].getX(), "Ensimmäisen ruudun x-koordinaatti on väärin");
		assertEquals(0, lauta.getLauta()[0][0].getY(), "Ensimmäisen ruudun y-koordinaatti on väärin");
	}
	
	@Test
	@DisplayName("Laudan kaikki ruudut on asetettu oikein")
	public void laudaRuutujenAsettaminenToimiiOikein() {
		assertTrue("Ruudut on numeroitu väärin", testaaRuudut(lauta));
	}
	
	@Test
	@DisplayName("Laudan nappulat on asetettu oikein")
	public void laudaNappuloidenAsettaminenToimiiOikein() {
		assertTrue("Nappulat on asetettu väärin", testaaNappulat(lauta));
	}
	
	@Test
	@DisplayName("Liikuttamaton Sotilas palauttaa kaksi siirtoa")
	public void palauttaaKaksiSiirtoa() {
		assertEquals(2, lauta.getSiirrot(0,1).size(), "Siirtoja palautui väärä määrä");
	}
	
	@Test
	@DisplayName("Valkoinen sotilas siirtyy kaksi ruutua ja palauttaa enää vain yhden siirron")
	public void valkoinenSotilasSiirtyyOiken() {
		lauta.siirra(0, 1, 0, 3);
		assertTrue("Sotilas ei siirtynyt sinne minne piti", lauta.getLauta()[0][3].getNappula() instanceof Sotilas);
		assertEquals(1, lauta.getSiirrot(0,3).size(), "Siirtoja palautui väärä määrä");
		assertTrue("Ruutu josta sotilas lähti on tyhjä", lauta.getLauta()[0][1].getNappula() == null);
		assertEquals(16, laskeSotilaat(lauta), "Sotilaita on väärä määrä");
	}
	
	@Test
	@DisplayName("Kuninkaan sijainti päivittyy")
	public void setKuninkaanSijaint() {
		//tarkastetaan lähtötilanne
		assertEquals(4, lauta.getValkoinenKuningas().getX(), "Valkoisen Kuninkaan aloitus x-koordinaatti väärin");
		assertEquals(0, lauta.getValkoinenKuningas().getY(), "Valkoisen Kuninkaan aloitus y-koordinaatti väärin");
		assertEquals(4, lauta.getMustaKuningas().getX(), "Mustan Kuninkaan aloitus x-koordinaatti väärin");
		assertEquals(7, lauta.getMustaKuningas().getY(), "Mustan Kuninkaan aloitus y-koordinaatti väärin");
		
		//siirretään sotilaat pois kuninkaiden edestä.
		lauta.siirra(4, 1, 4, 3);
		lauta.siirra(4, 6, 4, 4);
		
		//siirretään kuninkaita
		lauta.siirra(4, 0, 4, 1);
		lauta.siirra(4, 7, 4, 6);
		
		//tarkastetaan lopputilanne
		assertEquals(4, lauta.getValkoinenKuningas().getX(), "Valkoisen Kuninkaan lopetus x-koordinaatti väärin");
		assertEquals(1, lauta.getValkoinenKuningas().getY(), "Valkoisen Kuninkaan lopetus y-koordinaatti väärin");
		assertEquals(4, lauta.getMustaKuningas().getX(), "Mustan Kuninkaan lopetus x-koordinaatti väärin");
		assertEquals(6, lauta.getMustaKuningas().getY(), "Mustan Kuninkaan lopetus y-koordinaatti väärin");
		
	}
	
	private boolean testaaRuudut(Lauta lauta) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta.getLauta()[x][y].getX() != x || lauta.getLauta()[x][y].getY() != y) {
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
	
	private int laskeSotilaat(Lauta lauta) {
		int sotilaita = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta.getLauta()[x][y].getNappula() instanceof Sotilas) {
					sotilaita++;
				}
			}
		}
		return sotilaita;
	}

}
