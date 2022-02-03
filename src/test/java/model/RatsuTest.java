package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * @author Aivan Vo 30.1.2022
 * */

class RatsuTest {
	private ArrayList<Ruutu> siirrot;
	private Lauta lauta;

	@BeforeEach
	public void init() {
		lauta = new Lauta(true); //luo uusi tyhjä lauta(jossa ei nappuloita)
	}

	@Test
	@DisplayName("Ratsu palauttaa oikean määrän siirtoja tyhjällä laudalla ollessaan keskellä lautaa")
	public void ratsuKaikkiinsuuntiin() {
		lauta.getLauta()[3][4].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(3, 4);
		assertEquals(8, siirrot.size(), "kaikki tai jokin ratsun siirtovaihtoehto puuttuu");
		siirrot.clear();
	}

	@Test
	@DisplayName("Ratsu palauttaa oikean määrän siirtoja ollessaan omien ympäröimänä (jokaisella halutulla siirtokohdalla on jo samanvärinen nappula)")
	public void ratsuOmienYmparoimana() {
		lauta.getLauta()[3][4].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][6].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][6].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][5].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[5][3].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][2].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][2].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[1][5].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[1][3].setNappula(new Ratsu(NappulanVari.VALKOINEN));

		siirrot = lauta.getSiirrot(3, 4);
		assertEquals(0, siirrot.size(), "kaikki tai jokin ratsun siirtovaihtoehto puuttuu");
	}

	@Test
	@DisplayName("Ratsu palauttaa oikean määrän siirtoja ollessaan vihollisten ympäröimänä (jokaisella halutulla siirtokohdalla on vihollisen nappula)")
	public void ratsuVihollistenYmparoimana() {
		lauta.getLauta()[3][4].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][6].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[2][6].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[5][5].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[5][3].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[4][2].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[2][2].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[1][5].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta.getLauta()[1][3].setNappula(new Ratsu(NappulanVari.MUSTA));

		siirrot = lauta.getSiirrot(3, 4);
		assertEquals(8, siirrot.size(), "kaikki tai jokin ratsun siirtovaihtoehto puuttuu");
		siirrot.clear();
	}

	@Test
	@DisplayName("palauttaa valkoisen ratsun värin")
	public void getValkoinenRatsu() {
		lauta.getLauta()[3][4].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		assertTrue("Valkoinen ratsu ei palauttanut oikeaa väriä",
				((Ratsu) lauta.getLauta()[3][4].getNappula()).getVari() == NappulanVari.VALKOINEN);
	}

	@Test
	@DisplayName("palauttaa mustan ratsun värin")
	public void getMustaRatsu() {
		lauta.getLauta()[3][4].setNappula(new Ratsu(NappulanVari.MUSTA));
		assertTrue("Valkoinen ratsu ei palauttanut oikeaa väriä",
				((Ratsu) lauta.getLauta()[3][4].getNappula()).getVari() == NappulanVari.MUSTA);
	}

}