package model;

/**
* @author Elmo Vahvaselkä 27.1.2022 & Aivan Vo 9.2.2022
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
		assertEquals(0, lauta.getRuutu(0, 0).getX(), "Ensimmäisen ruudun x-koordinaatti on väärin");
		assertEquals(0, lauta.getRuutu(0, 0).getY(), "Ensimmäisen ruudun y-koordinaatti on väärin");
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
		assertEquals(32, laskeNappulat(lauta), "Nappuloita väärä määrä");
		assertEquals(16, laskeSotilaat(lauta), "Sotilaita väärä määrä");
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
		assertTrue("Sotilas ei siirtynyt sinne minne piti", lauta.getLauta()[0][3].getNappula().getTyyppi() == NappulanTyyppi.SOTILAS);
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
				if(lauta.getRuutu(x, y).getNappula() instanceof Sotilas) {
					sotilaita++;
				}
			}
		}
		return sotilaita;
	}
	
	private int laskeNappulat(Lauta lauta) {
		int nappuloita = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta.getRuutu(x, y).getNappula() != null) {
					nappuloita++;
				}
			}
		}
		return nappuloita;
	}

	@Test
	@DisplayName("Valkoinen sotilas korotetaan lähetiksi")
	public void korotaValkSotilasLahetiksi() {
		lauta.getLauta()[3][7].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.korota(3, 7, NappulanTyyppi.LAHETTI);
		assertTrue("Valkoisesta sotilaasta ei tullut lähettiä", lauta.getLauta()[3][7].getNappula().getTyyppi() == NappulanTyyppi.LAHETTI);
		assertTrue("Lähetti ei ollut valkoinen", lauta.getLauta()[3][7].getNappula().getVari() == NappulanVari.VALKOINEN);
	}
	
	@Test
	@DisplayName("Valkoinen sotilas korotetaan torniksi")
	public void korotaValkSotilasTorniksi() {
		lauta.getLauta()[4][7].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.korota(4, 7, NappulanTyyppi.TORNI);
		assertTrue("Valkoisesta sotilaasta ei tullut tornia", lauta.getLauta()[4][7].getNappula().getTyyppi() == NappulanTyyppi.TORNI);
		assertTrue("Torni ei ollut valkoien", lauta.getLauta()[4][7].getNappula().getVari() == NappulanVari.VALKOINEN);
	}
	
	@Test
	@DisplayName("Valkoinen sotilas korotetaan ratsuksi")
	public void korotaValkSotilasRatsuksi() {
		lauta.getLauta()[5][7].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.korota(5, 7, NappulanTyyppi.RATSU);
		assertTrue("Valkoisesta sotilaasta ei tullut ratsua", lauta.getLauta()[5][7].getNappula().getTyyppi() == NappulanTyyppi.RATSU);
		assertTrue("Ratsu ei ollut valkoinen", lauta.getLauta()[5][7].getNappula().getVari() == NappulanVari.VALKOINEN);
	}
	
	@Test
	@DisplayName("Valkoinen sotilas korotetaan Kuningattareksi")
	public void korotaValkSoKuningattareksi() {
		lauta.getLauta()[6][7].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.korota(6, 7, NappulanTyyppi.KUNINGATAR);
		assertTrue("Valkoisesta sotilaasta ei tullut kuningatarta", lauta.getLauta()[6][7].getNappula().getTyyppi() == NappulanTyyppi.KUNINGATAR);
		assertTrue("Kuningatar ei ollut valkoinen", lauta.getLauta()[6][7].getNappula().getVari() == NappulanVari.VALKOINEN);
	}
	
	@Test
	@DisplayName("Musta sotilas korotetaan lähetiksi")
	public void korotaMustaSotilasLahetiksi() {
		lauta.getLauta()[3][0].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.korota(3, 0, NappulanTyyppi.LAHETTI);
		assertTrue("Mustasta sotilaasta ei tullut lähettiä", lauta.getLauta()[3][0].getNappula().getTyyppi() == NappulanTyyppi.LAHETTI);
		assertTrue("Lähetti ei ollut musta", lauta.getLauta()[3][7].getNappula().getVari() == NappulanVari.MUSTA);
	}
	
	@Test
	@DisplayName("Musta sotilas korotetaan torniksi")
	public void korotaMustaSotilasTorniksi() {
		lauta.getLauta()[4][0].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.korota(4, 0, NappulanTyyppi.TORNI);
		assertTrue("Mustasta sotilaasta ei tullut tornia", lauta.getLauta()[4][0].getNappula().getTyyppi() == NappulanTyyppi.TORNI);
		assertTrue("Torni ei ollut musta", lauta.getLauta()[4][0].getNappula().getVari() == NappulanVari.MUSTA);

	}
	
	@Test
	@DisplayName("Musta sotilas korotetaan ratsuksi")
	public void korotaMustaSotilasRatsuksi() {
		lauta.getLauta()[5][0].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.korota(5, 0, NappulanTyyppi.RATSU);
		assertTrue("Mustasta sotilaasta ei tullut Ratsua", lauta.getLauta()[5][0].getNappula().getTyyppi() == NappulanTyyppi.RATSU);
		assertTrue("Ratsu ei ollut musta", lauta.getLauta()[5][0].getNappula().getVari() == NappulanVari.MUSTA);

	}
	
	@Test
	@DisplayName("Musta sotilas korotetaan Kuningattareksi")
	public void korotaMustaSoKuningattareksi() {
		lauta.getLauta()[6][0].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.korota(6, 0, NappulanTyyppi.KUNINGATAR);
		assertTrue("Mustasta sotilaasta ei tullut Kuningatarta", lauta.getLauta()[3][0].getNappula().getTyyppi() == NappulanTyyppi.KUNINGATAR);
		assertTrue("Kuningatar ei ollut musta", lauta.getLauta()[6][0].getNappula().getVari() == NappulanVari.MUSTA);

	}
	
}
