package model;

/**
* @author Elmo Vahvaselkä 1.2.2022
*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.Ruutu;

class KuningasTest {
	private Lauta lauta;
	
	@BeforeEach 
	public void setUp() {
		lauta = new Lauta(true);
	}

	@Test
	@DisplayName("Kuningas palauttaa oikean varin")
	public void getVari() {
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		assertTrue("Valkoinen Kuningas ei palauttanut oikeata variä",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.VALKOINEN);
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.MUSTA));
		assertTrue("Musta Kuningas ei palauttanut oikeata variä",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getVari() == NappulanVari.MUSTA);
	}

	@Test
	@DisplayName("getEkaSiirto metodin testaus")
	public void getEkaSiirto() {
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		assertFalse("Valkoinen Kuningas ei palauttanut false",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Valkoinen Kuiningas ei palauttanut true",
				((Kuningas) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());

		lauta = new Lauta();
		lauta.getLauta()[4][5].setNappula(new Kuningas(NappulanVari.MUSTA));
		assertFalse("Musta Kuiningas ei palauttanut false",
				((Kuningas) lauta.getLauta()[4][5].getNappula()).getEkaSiirto());
		lauta.siirra(4, 5, 5, 5);
		assertTrue("Musta Kuiningas ei palauttanut true",
				((Kuningas) lauta.getLauta()[5][5].getNappula()).getEkaSiirto());
	}

	@Test
	@DisplayName("Kuningas ei palauta yli rajojen meneviä siirtoja")
	public void testaaRajatKuninkaalla() {
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

	@Test
	@DisplayName("Palauttaa oikean määrän siirtoja omien ympäröimänä")
	public void omienYmparoimana() {

		// Testataan valkoinen
		lauta.getLauta()[4][4].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(4, 4);
		assertEquals(0, siirrot.size(), "Valkoinen Kuningas palautti liikaa siirtoja");

		// Testataan musta
		lauta = new Lauta(true);
		lauta.getLauta()[4][4].setNappula(new Kuningas(NappulanVari.MUSTA));
		lauta.getLauta()[5][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[5][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[5][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		siirrot = lauta.getSiirrot(4, 4);
		assertEquals(0, siirrot.size(), "Musta Kuningas palautti liikaa siirtoja");
	}

	@Test
	@DisplayName("Palauttaa oikean määrän siirtoja vihollisten ympäröimänä")
	public void vihollistenYmparoimana() {

		// Testataan valkoinen Kuningas
		lauta.getLauta()[4][4].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[5][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[5][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(4, 4);
		assertEquals(8, siirrot.size(), "Valkoinen Kuningas palautti liikaa siirtoja");

		// Testataan musta Kuningas
		lauta = new Lauta(true);
		lauta.getLauta()[4][4].setNappula(new Kuningas(NappulanVari.MUSTA));
		lauta.getLauta()[5][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(4, 4);
		assertEquals(8, siirrot.size(), "Musta Kuningas palautti liikaa siirtoja");
	}
	
	@Test
	@DisplayName("Kuningas palauttaa mahdolliset tornitukset siirtoina")
	public void testaaTornitukset(){
		lauta.getRuutu(4, 0).setNappula(new Kuningas(NappulanVari.VALKOINEN));
		lauta.getRuutu(4, 7).setNappula(new Kuningas(NappulanVari.MUSTA));
		lauta.getRuutu(0, 0).setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta.getRuutu(7, 0).setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta.getRuutu(0, 7).setNappula(new Torni(NappulanVari.MUSTA));
		lauta.getRuutu(7, 7).setNappula(new Torni(NappulanVari.MUSTA));
		
		//Tarkastetaan valkoisen tornituksien koordinaatit
		assertEquals(7, lauta.getRuutu(4, 0).getNappula().getSiirrot(new Ruutu(4,0), lauta.getLauta()).size(), "Valkoinen Kuningas palautti väärän määrän siirtoja");
		ArrayList<Ruutu> siirrot = lauta.getRuutu(4, 0).getNappula().getSiirrot(new Ruutu(4,0), lauta.getLauta());
		assertEquals(6, siirrot.get(5).getX(), "Valkoisen oikealla tornituksella väärä x-koordinaatti");
		assertEquals(0, siirrot.get(5).getY(), "Valkoisen oikealla tornituksella väärä y-koordinaatti");
		assertEquals(2, siirrot.get(6).getX(), "Valkoisen vasemmalle tornituksella väärä x-koordinaatti");
		assertEquals(0, siirrot.get(6).getY(), "Valkoisen vasemmalle tornituksella väärä y-koordinaatti");

		//Tarkastetaan mustan tornituksen koordinaatit
		assertEquals(7, lauta.getRuutu(4, 7).getNappula().getSiirrot(new Ruutu(4,7), lauta.getLauta()).size(), "Musta Kuningas palautti väärän määrän siirtoja");
		siirrot = lauta.getRuutu(4, 7).getNappula().getSiirrot(new Ruutu(4,7), lauta.getLauta());
		assertEquals(6, siirrot.get(5).getX(), "Mustan oikealla tornituksella väärä x-koordinaatti");
		assertEquals(7, siirrot.get(5).getY(), "Mustan oikealla tornituksella väärä y-koordinaatti");
		assertEquals(2, siirrot.get(6).getX(), "Mustan vasemmalle tornituksella väärä x-koordinaatti");
		assertEquals(7, siirrot.get(6).getY(), "Mustan vasemmalle tornituksella väärä y-koordinaatti");
		
		//Siirretään valkoista tornia
		lauta.siirra(7, 0, 7, 1);
		assertEquals(6, lauta.getRuutu(4, 0).getNappula().getSiirrot(new Ruutu(4,0), lauta.getLauta()).size(), "Valkoinen Kuninas palautti väärän määrän siirtoja 2");
		
		//Siirretään valkoinen torni takaisin
		lauta.siirra(7, 0, 7, 0);
		assertEquals(6, lauta.getRuutu(4, 0).getNappula().getSiirrot(new Ruutu(4,0), lauta.getLauta()).size(), "Valkoinen Kuninas palautti väärän määrän siirtoja 3");
		
		//Siirretään valkoista Kuningasta
		lauta.siirra(4, 0, 5, 0);
		assertEquals(5, lauta.getRuutu(5, 0).getNappula().getSiirrot(new Ruutu(5,0), lauta.getLauta()).size(), "Valkoinen Kuninas palautti väärän määrän siirtoja 4");
		
		//Siirretään valkoinen Kuningas takasin
		lauta.siirra(5, 0, 4, 0);
		assertEquals(5, lauta.getRuutu(4, 0).getNappula().getSiirrot(new Ruutu(4,0), lauta.getLauta()).size(), "Valkoinen Kuninas palautti väärän määrän siirtoja 5");
		
		//Siirretään mustaa Kuningasta
		lauta.siirra(4, 7, 5, 7);
		assertEquals(5, lauta.getRuutu(5, 7).getNappula().getSiirrot(new Ruutu(5,7), lauta.getLauta()).size(), "Musta Kuningas palautti väärän määrän siirtoja 2");
		
		//Siirretään musta Kuningas takaisin
		lauta.siirra(5, 7, 4, 7);
		assertEquals(5, lauta.getRuutu(4, 7).getNappula().getSiirrot(new Ruutu(4,7), lauta.getLauta()).size(), "Musta Kuningas palautti väärän määrän siirtoja 3");
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
