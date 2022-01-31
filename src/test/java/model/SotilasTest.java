package model;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SotilasTest {

	private Lauta lauta;

	@BeforeEach
	public void setUp() {
		lauta = new Lauta();
	}

	@Test
	@DisplayName("Valkoinen sotilas, jota ei ole liikutettu, palauttaa oikeat ruudut")
	public void testaaValkoistaSotilasta() {
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 1);
		assertEquals(2, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(2, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
		assertEquals(0, siirrot.get(1).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(3, siirrot.get(1).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}

	@Test
	@DisplayName("Valkoinen sotilas, jota on liikutettu, palauttaa oikean ruudun")
	public void testaaValkoistaSotilastaJokaOnLiikkunut() {
		lauta.siirra(0, 1, 0, 2);
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 2);
		assertEquals(1, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(3, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}

	@Test
	@DisplayName("Musta sotilas, jota ei ole liikutettu palauttaa, oikeat ruudut")
	public void testaaMustaaSotilasta() {
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 6);
		assertEquals(2, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(5, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
		assertEquals(0, siirrot.get(1).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(4, siirrot.get(1).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}

	@Test
	@DisplayName("Musta sotilas, jota on liikutettu, palauttaa oikean ruudun")
	public void testaaMustaaSotilastaJokaOnLiikkunut() {
		lauta.siirra(0, 1, 0, 2);
		lauta.siirra(0, 6, 0, 5);
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 5);
		assertEquals(1, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(4, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}

	@Test
	@DisplayName("Valkoista nappulaa ei voi siirtää reunan yli")
	public void valkoinenSotilasReunanYli() {
		lauta.getLauta()[0][7].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 7);
		assertEquals(0, siirrot.size(), "Palautti siirtoja vaikka ei pitäisi");
	}

	@Test
	@DisplayName("Mustaa nappulaa ei voi siirtää reunan yli")
	public void mustaSotilasReunanYli() {
		lauta.getLauta()[0][1].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 1);
		assertEquals(0, siirrot.size(), "Palautti siirtoja vaikka ei pitäisi");
	}

	@Test
	@DisplayName("Valkoinen palauttaa oikean määrän siirtoja, kun on mahdollista syödä musta")
	public void valkoinenSyö() {
		lauta.getLauta()[2][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		((Sotilas) lauta.getLauta()[2][4].getNappula()).ekaSiirtoTehty();

		lauta.getLauta()[1][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(2, 4);
		assertEquals(2, siirrot.size(), "Palautti väärän määrän siirtoja, kun voi syödä yhden");

		lauta.getLauta()[3][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		siirrot = lauta.getSiirrot(2, 4);
		assertEquals(3, siirrot.size(), "Palautti väärän määrän siirtoja, kun voi syödä kaksi");
	}

	@Test
	@DisplayName("Musta palauttaa oikean määrän siirtoja, kun on mahdollista syödä valkoinen")
	public void mustaSyö() {
		lauta.getLauta()[2][5].setNappula(new Sotilas(NappulanVari.MUSTA));
		((Sotilas) lauta.getLauta()[2][5].getNappula()).ekaSiirtoTehty();

		lauta.getLauta()[1][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(2, 5);
		assertEquals(2, siirrot.size(), "Palautti väärän määrän siirtoja, kun voi syödä yhden");

		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(2, 5);
		assertEquals(3, siirrot.size(), "Palautti väärän määrän siirtoja, kun voi syödä kaksi");
	}

	@Test
	@DisplayName("Valkoinen sotilas ei voi liikkua ruutuun, jossa on nappula")
	public void valkoinenEiVoiLiikkua() {
		// asetetaan musta kahden ruudun päähän valkoisesta
		lauta.getLauta()[0][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 1);
		assertEquals(1, siirrot.size(), "Palautti väärän määrän siirtoja, kun kahden ruudun päässä on nappula");

		// asetetaan musta ruudun päähän valkoisesta
		lauta.getLauta()[0][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		siirrot = lauta.getSiirrot(0, 1);
		assertEquals(0, siirrot.size(), "Palautti väärän määrän siirtoja, kun nappula on edessä");
	}
	
	@Test
	@DisplayName("Musta sotilas ei voi liikkua ruutuun, jossa on nappula")
	public void mustaEiVoiLiikkua() {
		// asetetaan valkoinen kahden ruudun päähän valkoisesta
		lauta.getLauta()[0][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu> siirrot = lauta.getSiirrot(0, 6);
		assertEquals(1, siirrot.size(), "Palautti väärän määrän siirtoja, kun kahden ruudun päässä on nappula");

		// asetetaan musta ruudun päähän valkoisesta
		lauta.getLauta()[0][5].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		siirrot = lauta.getSiirrot(0, 6);
		assertEquals(0, siirrot.size(), "Palautti väärän määrän siirtoja, kun nappula on edessä");
	}
}