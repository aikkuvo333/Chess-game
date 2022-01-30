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
	
	private Sotilas sotilas;
	private Ruutu ruutu;
	private Ruutu[][] lauta;
	
	@BeforeEach 
	public void setUp() {
		lauta = new Ruutu[8][8];
	}

	@Test
	@DisplayName("Valkoinen sotilas, jota ei ole liikutettu, palauttaa oikeat ruudut")
	public void testaaValkoistaSotilasta() {
		ruutu = new Ruutu(0, 0);
		sotilas = new Sotilas(NappulanVari.VALKOINEN);
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(2, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(1, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
		assertEquals(0, siirrot.get(1).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(2, siirrot.get(1).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}
	
	@Test
	@DisplayName("Valkoinen sotilas, jota on liikutettu, palauttaa oikean ruudun")
	public void testaaValkoistaSotilastaJokaOnLiikkunut() {
		ruutu = new Ruutu(0, 1);
		sotilas = new Sotilas(NappulanVari.VALKOINEN);
		sotilas.ensimmaineSiirtoTehty();
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(1, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(2, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}
	
	@Test
	@DisplayName("Musta sotilas, jota ei ole liikutettu palauttaa, oikeat ruudut")
	public void testaaMustaaSotilasta() {
		ruutu = new Ruutu(0, 6);
		sotilas = new Sotilas(NappulanVari.MUSTA);
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(2, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(5, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
		assertEquals(0, siirrot.get(1).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(4, siirrot.get(1).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}
	
	@Test
	@DisplayName("Musta sotilas, jota on liikutettu, palauttaa oikean ruudun")
	public void testaaMustaaSotilastaJokaOnLiikkunut() {
		ruutu = new Ruutu(0, 5);
		sotilas = new Sotilas(NappulanVari.MUSTA);
		sotilas.ensimmaineSiirtoTehty();
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(1, siirrot.size(), "Siirtoja ei palautunut oikea määrä");
		assertEquals(0, siirrot.get(0).getX(), "Nappula liikkui vaakasuunnassa");
		assertEquals(4, siirrot.get(0).getY(), "Nappula ei liikkunut oikeata määrää ruutuja pystysuunnassa");
	}
	
	@Test
	@DisplayName("Valkoista nappulaa ei voi siirtää reunan yli")
	public void valkoinenSotilasReunanYli() {
		ruutu = new Ruutu(0, 7);
		sotilas = new Sotilas(NappulanVari.VALKOINEN);
		sotilas.ensimmaineSiirtoTehty();
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(0, siirrot.size(), "Palautti siirtoja vaikka ei pitäisi");
	}
	
	@Test
	@DisplayName("Mustaa nappulaa ei voi siirtää reunan yli")
	public void MustaSotilasReunanYli() {
		ruutu = new Ruutu(0, 0);
		sotilas = new Sotilas(NappulanVari.MUSTA);
		sotilas.ensimmaineSiirtoTehty();
		ArrayList<Ruutu> siirrot = sotilas.getSiirrot(ruutu, lauta);
		assertEquals(0, siirrot.size(), "Palautti siirtoja vaikka ei pitäisi");
	}
}