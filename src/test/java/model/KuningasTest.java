package model;

/**
* @author Elmo Vahvaselkä 1.2.2022
*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KuningasTest {
	private Lauta lauta;

	@Test
	@DisplayName("Kuningas palauttaa oikean varin")
	public void gerVari() {
		lauta = new Lauta(true);
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		assertTrue("Valkoinen Kuningas ei palauttanut oikeata variä",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.VALKOINEN);
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.MUSTA));
		assertTrue("Valkoinen Kuningas ei palauttanut oikeata variä",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.MUSTA);
	}
	
	@Test
	@DisplayName("getEkaSiirto metodin testaus")
	public void getEkaSiirto() {
		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		assertFalse("Valkoinen Kuningas ei palauttanut false",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Valkoinen Kuiningas ei palauttanut true", ((Kuningas) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());

		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.MUSTA));
		assertFalse("Musta Kuiningas ei palauttanut false", ((Kuningas) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Musta Kuiningas ei palauttanut true", ((Kuningas) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());
	}
	
	@Test
	@DisplayName("Kuningas ei palauta yli rajojen meneviä siirtoja")
	public void testaaTorninRajat() {
		lauta = new Lauta(true);
		lauta.getLauta()[0][0].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 0);
		assertFalse("Siirto laudan ulkopuolella vasemmassa alanurkassa", menikoYli(siirrot));
		assertEquals(3, siirrot.size(), "Vasen alanurkka palautti vääränä määrän siirtoja");
		
		lauta = new Lauta(true);
		lauta.getLauta()[0][7].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(0, 7);
		assertFalse("Siirto laudan ulkopuolella vasemmassa ylänurkassa", menikoYli(siirrot));
		assertEquals(3, siirrot.size(), "Vasen ylänurkka palautti vääränä määrän siirtoja");
		
		lauta = new Lauta(true);
		lauta.getLauta()[7][7].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(7, 7);
		assertFalse("Siirto laudan ulkopuolella oikeassa ylänurkassa", menikoYli(siirrot));
		assertEquals(3, siirrot.size(), "Oikea ylänurkka palautti vääränä määrän siirtoja");
		
		lauta = new Lauta(true);
		lauta.getLauta()[7][0].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(7, 0);
		assertFalse("Siirto laudan ulkopuolella oikeassa alanurkassa", menikoYli(siirrot));
		assertEquals(3, siirrot.size(), "Oikea alanurkka palautti vääränä määrän siirtoja");
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