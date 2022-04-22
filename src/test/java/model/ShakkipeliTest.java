package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShakkipeliTest {

	private Shakkipeli peli;

	@BeforeEach
	public void setUp() {
		// aloitetaan tilastoimaton peli
		peli = new Shakkipeli();
	}

	@Test
	@DisplayName("Peli ei hyväksy laintonta siirtoa Sotilaalla")
	public void sotilastaEiSaaSiirtääVaarin() {
		assertFalse("Siirto ei onnistunut", peli.siirra(0, 1, 0, 5));
		assertFalse("Sotilas siirtyi", peli.getPelitilanne()[0][5].getNappula() != null);
		assertEquals(NappulanTyyppi.SOTILAS, peli.getPelitilanne()[0][1].getNappula().getTyyppi(),
				"Sotilas ei ole enää alkuruudussa");
		assertEquals(2, peli.getSiirrot(0, 1).size(), "Siirtoja palautui väärä määrä");
	}

	@Test
	@DisplayName("Peli ei anna siirtää väärää väriä")
	public void vaaraaVariaEiSaaSiirtaa() {
		assertEquals(NappulanVari.VALKOINEN, peli.getVuoro(), "Aloituksessa ei ollut valkoisen vuoro");
		assertFalse("Mustan siirto onnistui aloituksessa", peli.siirra(0, 6, 0, 5));
		assertTrue("Valkoisen siirto ei onnistunut aloituksessa", peli.siirra(0, 1, 0, 3));
		assertEquals(NappulanVari.MUSTA, peli.getVuoro(), "Vuoro ei vaihtunut valkoisen siirron jälkeen");
		assertEquals(NappulanTyyppi.SOTILAS, peli.getPelitilanne()[0][3].getNappula().getTyyppi(),
				"Valkoinen Sotilas ei ole oikeassa ruudussa");
		assertFalse("Valkoista siirrettiin kaksi kertaa peräkkäin", peli.siirra(1, 1, 1, 0));
		assertTrue("Mustan siirto ei onnistunut", peli.siirra(0, 6, 0, 5));
		assertEquals(NappulanTyyppi.SOTILAS, peli.getPelitilanne()[0][5].getNappula().getTyyppi(),
				"Musta sotilas ei ole oikeassa ruudussa");
	}

	@Test
	@DisplayName("Kuningasta ei voi siirtää vastustajan tornin linjalle")
	public void kuningasEiVoiTapattaaItseansa() {
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 4));
		assertTrue("Valkoinen torni ei siirtynyt", peli.siirra(7, 0, 7, 2));
		assertTrue("Musta Kuningas ei siirtynyt", peli.siirra(4, 7, 4, 6));
		assertTrue("Valkoinen torni ei siirtynyt 2", peli.siirra(7, 2, 5, 2));
		assertFalse("Kuningas siirtyi itsetuhoisesti", peli.siirra(4, 6, 5, 5));
	}

	@Test
	@DisplayName("Shakattuna olevan pelaajan pitää tehdä siirto, joka poistaa shakin")
	public void pelaajanOnPoisttutavaShakista() {
		assertFalse("Peli ei ole shakatussa tilassa", peli.getShakattu());
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 4));
		assertTrue("Valkoinen torni ei siirtynyt", peli.siirra(7, 0, 7, 2));
		assertTrue("Musta Kuningas ei siirtynyt", peli.siirra(4, 7, 4, 6));
		assertTrue("Valkoinen torni ei siirtynyt 2", peli.siirra(7, 2, 5, 2));
		assertTrue("Musta Kuningas ei siirtynyt 2", peli.siirra(4, 6, 4, 5));
		assertTrue("Torni siirtyi kuninkaan viereen", peli.siirra(5, 2, 5, 5));
		assertTrue("Torni shakkasi", peli.getShakattu());
		assertFalse("Kuninkaan siirto ei purkanut shakkia", peli.siirra(4, 5, 3, 5));
		assertTrue("Musta Kuningas ei siirtynyt turvaan", peli.siirra(4, 5, 3, 4));
		assertFalse("Shakki purettu", peli.getShakattu());
	}

	@Test
	@DisplayName("Shakin purkaminen, kun shakkaava nappula syödään")
	public void pelaajaSoiItsensaPoisShakista() {
		assertFalse("Peli ei ole shakatussa tilassa", peli.getShakattu());
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 4));
		assertTrue("Valkoinen torni ei siirtynyt", peli.siirra(7, 0, 7, 2));
		assertTrue("Musta Kuningas ei siirtynyt", peli.siirra(4, 7, 4, 6));
		assertTrue("Valkoinen torni ei siirtynyt 2", peli.siirra(7, 2, 5, 2));
		assertTrue("Musta Kuningas ei siirtynyt 2", peli.siirra(4, 6, 4, 5));
		assertTrue("Torni siirtyi kuninkaan viereen", peli.siirra(5, 2, 5, 5));
		assertTrue("Torni shakkasi", peli.getShakattu());
		assertTrue("Sotilas ei syönyt tornia", peli.siirra(6, 6, 5, 5));
		assertEquals(31, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita");
		assertFalse("Shakki purettu", peli.getShakattu());
	}

	@Test
	@DisplayName("Peli ei anna siirtää Kuninkaan suojana olevaa nappulaa")
	public void kuninkaanLihakilpieaEiSaaLiikuttaa() {
		assertFalse("Peli ei ole shakatussa tilassa", peli.getShakattu());
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 5));
		assertTrue("Valkoinen torni ei siirtynyt", peli.siirra(7, 0, 7, 2));
		assertTrue("Musta Kuningas ei siirtynyt", peli.siirra(4, 7, 4, 6));
		assertTrue("Valkoinen torni ei siirtynyt 2", peli.siirra(7, 2, 5, 2));
		assertTrue("Musta Kuningas ei siirtynyt 2", peli.siirra(4, 6, 3, 5));
		assertTrue("Valkoinen torni siirtyi mustan sotilaan viereen", peli.siirra(5, 2, 5, 5));
		;
		assertFalse("Sotilas siirtyi pois suojaamasta Kuningasta", peli.siirra(4, 5, 4, 4));
	}

	@Test
	@DisplayName("Tornitus toimii")
	public void torinitusToimii() {
		assertTrue("Valkoinen ratsu ei siirtynyt", peli.siirra(6, 0, 7, 2));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(6, 7, 7, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(4, 1, 4, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 4));
		assertTrue("Valkoinen lahetti ei siirtynyt", peli.siirra(5, 0, 2, 3));
		assertTrue("Musta lahetti ei siirtynt", peli.siirra(5, 7, 2, 4));
		assertTrue("Valkoisen tornitus oikean tornin kanssa ei onnistunut", peli.siirra(4, 0, 6, 0));
		assertEquals(NappulanTyyppi.KUNINGAS, peli.getPelitilanne()[6][0].getNappula().getTyyppi(),
				"Valkoinen Kuningas ei siirtynyt");
		assertEquals(NappulanTyyppi.TORNI, peli.getPelitilanne()[5][0].getNappula().getTyyppi(),
				"Valkoinen torni ei siirtynyt");
		assertTrue("Mustan tornitus oikean tornin kanssa ei onnistunut", peli.siirra(4, 7, 6, 7));
		assertEquals(NappulanTyyppi.KUNINGAS, peli.getPelitilanne()[6][7].getNappula().getTyyppi(),
				"Valkoinen Kuningas ei siirtynyt");
		assertEquals(NappulanTyyppi.TORNI, peli.getPelitilanne()[5][7].getNappula().getTyyppi(),
				"Valkoinen torni ei siirtynyt");
	}

	@Test
	@DisplayName("Tornitusta ei voi tehdä niin, että Kuningas vaarantuu")
	public void kuningastaEiVoiTornittaaKuolemaan() {
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(2, 1, 2, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(2, 6, 2, 4));
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(3, 1, 3, 3));
		assertTrue("Musta sotilas ei syönyt valkoista sotlasta", peli.siirra(2, 4, 3, 3));
		assertEquals(31, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 31 nappulaa");
		assertTrue("Valkoinen lähetti ei siirtynyt", peli.siirra(2, 0, 5, 3));
		assertTrue("Musta sotila ei siirtynyt", peli.siirra(3, 6, 3, 4));
		assertTrue("Valkoinen sotila ei syönyt mustaa sotilasta", peli.siirra(2, 3, 3, 4));
		assertEquals(30, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 30 nappulaa");
		assertTrue("Musta lähetti ei siirtynyt", peli.siirra(2, 7, 6, 3));
		assertTrue("Valkoinen kuningatar ei syönyt mustaa sotilasta", peli.siirra(3, 0, 3, 3));
		assertEquals(29, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 29 nappulaa");
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen ratsu ei siirtynyt", peli.siirra(1, 0, 0, 2));
		assertTrue("Musta kuningatar ei liikkunut", peli.siirra(3, 7, 2, 6));
		assertFalse("Valkoisen tornitus onnistui, vaikka kuningatar söisi Kuninkaan", peli.siirra(5, 0, 3, 0));
		assertTrue("Valkoinen kuningatar ei liikkunut", peli.siirra(3, 3, 2, 3));
		assertTrue("Musta sotila ei liikkunut", peli.siirra(7, 6, 7, 5));
		assertTrue("Valkoinen tornitus ei onnistunut", peli.siirra(4, 0, 2, 0));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(7, 5, 7, 4));
		assertTrue("Valkoinen kuningatar söi musta kuningattaren", peli.siirra(2, 3, 2, 6));
		assertEquals(28, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 28 nappulaa");
		assertFalse("Musta tornitus onnistui, vaikka kuningatar söisi Kuninkaan", peli.siirra(4, 7, 2, 7));
		assertTrue("Musta ratsu söi kuningattaren", peli.siirra(0, 5, 2, 6));
		assertEquals(27, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 27 nappulaa");
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 2));
		assertTrue("Musta tornitus ei onnistunut", peli.siirra(4, 7, 2, 7));
	}

	@Test
	@DisplayName("Tornituksella ei saa purkaa shakkia")
	public void tornituksellaEiSaaPurkaaShakkia() {
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(2, 1, 2, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(2, 6, 2, 4));
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(3, 1, 3, 3));
		assertTrue("Musta sotilas ei syönyt valkoista sotlasta", peli.siirra(2, 4, 3, 3));
		assertEquals(31, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita");
		assertTrue("Valkoinen lähetti ei siirtynyt", peli.siirra(2, 0, 5, 3));
		assertTrue("Musta sotila ei siirtynyt", peli.siirra(3, 6, 3, 4));
		assertTrue("Valkoinen sotila ei syönyt mustaa sotilasta", peli.siirra(2, 3, 3, 4));
		assertEquals(30, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita 2");
		assertTrue("Musta lähetti ei siirtynyt", peli.siirra(2, 7, 6, 3));
		assertTrue("Valkoinen kuningatar ei syönyt mustaa sotilasta", peli.siirra(3, 0, 3, 3));
		assertEquals(29, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita 3");
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen ratsu ei siirtynyt", peli.siirra(1, 0, 0, 2)); 
		assertTrue("Musta kuningatar ei siirtynyt shakkaamaan", peli.siirra(3, 7, 0, 4));
		assertFalse("Valkoinen Kuningas tornitti vaikka oli shakissa", peli.siirra(4, 0, 2, 0));
		assertTrue("Valkoinen sotilas ei purkanut shakkia", peli.siirra(1, 1, 1, 3));
		assertFalse("Peli on shakissa", peli.getShakattu());
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(7, 6, 7, 5));
		assertTrue("Valkoinen Kuningas ei tornittanut", peli.siirra(4, 0, 2, 0));
		assertTrue("Musta kuningatar ei siirtynyt shakkaamaan", peli.siirra(0, 4, 2, 4));
		assertTrue("Valkoinen sotilas ei syönyt kuningatarta", peli.siirra(1, 3, 2, 4));
		assertEquals(28, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita 4");
		assertTrue("Musta ratsu söi sotilaan", peli.siirra(0, 5, 2, 4));
		assertEquals(27, laskeNappulat(peli.getPelitilanne()), "Laudalla on väärä määrä nappuloita 5");
		assertTrue("Valkoinen Kuningatar ei siirtynyt shakkaamaan", peli.siirra(3, 3, 0, 3));
		assertFalse("Musta Kuniningas tornitti vaikka oli shakissa", peli.siirra(4, 7, 2, 7));
		assertTrue("Musta ratsu ei purkanut shakkia", peli.siirra(2, 4, 3, 6));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(3, 4, 3, 5));
		assertTrue("Musta Kuningas ei tornittanut", peli.siirra(4, 7, 2, 7));
	}

	@Test
	@DisplayName("Kuningas ei saa liikkua uhatun ruudun yli tornituksessa")
	public void kuningasTornituksessaUhatunRuudunYli() {
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(2, 1, 2, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(2, 3, 1, 4));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(2, 6, 2, 4));
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(1, 1, 1, 3));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(2, 4, 1, 3));
		assertTrue("Valkoine ratsu ei siirtynyt", peli.siirra(1, 0, 0, 2));
		assertTrue("Musta sotilas ei syönyt ratsua", peli.siirra(1, 3, 0, 2));
		assertTrue("Valkoinen lähetti ei syönyt mustaa sotilasta", peli.siirra(2, 0, 0, 2));
		assertTrue("Musta kuningatar ei liikkunut", peli.siirra(3, 7, 0, 4));
		assertTrue("Valkoinen kuningatar ei liikkunut", peli.siirra(3, 0, 0, 3));
		assertTrue("Musta kuningatar ei syönyt valkoista kuningatarta", peli.siirra(0, 4, 0, 3));
		assertFalse("Kuningas tornitti uhatun ruudun yli", peli.siirra(4, 0, 2, 0));
	}

	@Test
	@DisplayName("Musta voittaa pikamatilla")
	public void mustaVoittaaPikamatilla() {
		assertEquals(null, peli.getVoittaja(), "Pelin alussa voittaja ei ollut null");
		assertFalse("Peli on päättynyt ennen siirtoja", peli.getPeliLoppunut());
		assertTrue("Valkoinen sotilas ei siirtynyt 1", peli.siirra(5, 1, 5, 2));
		assertFalse("Peli päättyi ensimmäisen siirron jälkeen", peli.getPeliLoppunut());
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt 2", peli.siirra(6, 1, 6, 3));
		assertTrue("Musta Kuningatar ei siirtynyt ja lopettanut peliä", peli.siirra(3, 7, 7, 3));
		assertTrue("peli ei päättynyt", peli.getPeliLoppunut());
		assertEquals("Musta", peli.getVoittaja().getKayttajaTunnus(), "Musta ei voittanut peliä");
		assertFalse("Valkoinen sai tehdä siirron", peli.siirra(0, 1, 0, 2));
	}

	@Test
	@DisplayName("Valkoine voittaa nopeasti")
	public void valkoinenVoittaNopeasti() {
		assertEquals(null, peli.getVoittaja(), "Pelin alussa voittaja ei ollut null");
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(4, 1, 4, 2));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 6, 6, 4));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(5, 1, 5, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(5, 6, 5, 5));
		assertTrue("Valkoinen kunigatar ei shakannut", peli.siirra(3, 0, 7, 4));
		assertTrue("peli ei päättynyt", peli.getPeliLoppunut());
		assertEquals("Valkoinen", peli.getVoittaja().getKayttajaTunnus(), "Valkoinen ei voittanut peliä");
		assertFalse("Musta sai tehdä siirron", peli.siirra(7, 1, 7, 2));
	}

	@Test
	@DisplayName("Luovuttaminen toimii valkoisella")
	public void luovutaValkoinen() {
		peli.julistaVoittaja();
		assertFalse("Valkoinen pystyi liikkumaan luovuttamisen jälkeen", peli.siirra(0, 1, 0, 2));
		assertFalse("Musta pystyi siirtymään pelin päätyttyä", peli.siirra(0, 6, 0, 5));
		assertEquals("Musta", peli.getVoittaja().getKayttajaTunnus(), "Musta ei voittanut");
		assertTrue("Peli on loppunut", peli.getPeliLoppunut());
	}

	@Test
	@DisplayName("Luovuttaminen toimii mustalla")
	public void luovutaMusta() {
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 2));
		peli.julistaVoittaja();
		assertFalse("Musta pystyi siirtymään pelin päätyttyä", peli.siirra(0, 6, 0, 5));
		assertFalse("Valkoinen sotilas pysti siirtymään pelin päätyttyä", peli.siirra(1, 1, 1, 2));
		assertEquals("Valkoinen", peli.getVoittaja().getKayttajaTunnus(), "Valkoinen ei voittanut");
		assertTrue("Peli on loppunut", peli.getPeliLoppunut());
	}

	@Test
	@DisplayName("Valkoisen sotilaan korottaminen onnistuu")
	public void valkoisenSotilaanKorottaminen() {
		// Kuningattareksi
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		assertEquals(NappulanTyyppi.KUNINGATAR, peli.getPelitilanne()[0][7].getNappula().getTyyppi(),
				"Sotilasta ei korotettu Kuingattareksi");

		// Ratsuksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.RATSU);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		assertEquals(NappulanTyyppi.RATSU, peli.getPelitilanne()[0][7].getNappula().getTyyppi(),
				"Sotilasta ei korotettu ratsuksi");

		// Torniksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.TORNI);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		assertEquals(NappulanTyyppi.TORNI, peli.getPelitilanne()[0][7].getNappula().getTyyppi(),
				"Sotilasta ei korotettu torniksi");

		// Lähetiksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.LAHETTI);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(0, 1, 0, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(1, 6, 1, 4));
		assertTrue("Valkoinen sotilas ei syönyt mustaa sotilasta", peli.siirra(0, 3, 1, 4));
		assertTrue("Musta sotilas ei liikkunut 2", peli.siirra(0, 6, 0, 4));
		assertTrue("Valkoinen sotilas ei liikkunut 2", peli.siirra(1, 4, 1, 5));
		assertTrue("Musta torni ei siirtynyt", peli.siirra(0, 7, 0, 6));
		assertTrue("Valkoinen sotilas ei syönyt tornia", peli.siirra(1, 5, 0, 6));
		assertTrue("Musta ratsu ei siirtynyt", peli.siirra(1, 7, 0, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt laudan reunalle", peli.siirra(0, 6, 0, 7));
		assertEquals(NappulanTyyppi.LAHETTI, peli.getPelitilanne()[0][7].getNappula().getTyyppi(),
				"Sotilasta ei korotettu lahetiksi");
	}

	@Test
	@DisplayName("Mustan sotilaan korottaminen onnistuu")
	public void mustanSotilaanKorottaminen() {
		// Kuningattareksi
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 6, 6, 4));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(6, 1, 6, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(6, 4, 7, 3));
		assertTrue("Valkoinen ratsu ei liikkunut", peli.siirra(6, 0, 7, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(7, 3, 6, 2));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 0, 7, 1));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 2, 6, 1));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 1, 7, 0));
		assertTrue("Musta sotilas ei siirtynyt reunalla", peli.siirra(6, 1, 6, 0));
		assertEquals(NappulanTyyppi.KUNINGATAR, peli.getPelitilanne()[6][0].getNappula().getTyyppi(),
				"Mustaa sotilasta ei korotettu kuningattareksi");

		// Ratsuksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.RATSU);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 6, 6, 4));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(6, 1, 6, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(6, 4, 7, 3));
		assertTrue("Valkoinen ratsu ei liikkunut", peli.siirra(6, 0, 7, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(7, 3, 6, 2));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 0, 7, 1));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 2, 6, 1));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 1, 7, 0));
		assertTrue("Musta sotilas ei siirtynyt reunalla", peli.siirra(6, 1, 6, 0));
		assertEquals(NappulanTyyppi.RATSU, peli.getPelitilanne()[6][0].getNappula().getTyyppi(),
				"Mustaa sotilasta ei korotettu ratsuksi");

		// Torniksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.TORNI);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 6, 6, 4));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(6, 1, 6, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(6, 4, 7, 3));
		assertTrue("Valkoinen ratsu ei liikkunut", peli.siirra(6, 0, 7, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(7, 3, 6, 2));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 0, 7, 1));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 2, 6, 1));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 1, 7, 0));
		assertTrue("Musta sotilas ei siirtynyt reunalla", peli.siirra(6, 1, 6, 0));
		assertEquals(NappulanTyyppi.TORNI, peli.getPelitilanne()[6][0].getNappula().getTyyppi(),
				"Mustaa sotilasta ei korotettu torniksi");

		// Lähetiksi
		peli = new Shakkipeli();
		peli.setTestiKorotus(NappulanTyyppi.LAHETTI);
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 6, 6, 4));
		assertTrue("Valkoinen sotilas ei liikkunut", peli.siirra(6, 1, 6, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(6, 4, 7, 3));
		assertTrue("Valkoinen ratsu ei liikkunut", peli.siirra(6, 0, 7, 2));
		assertTrue("Musta sotilas ei syönyt valkoista sotilasta", peli.siirra(7, 3, 6, 2));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 0, 7, 1));
		assertTrue("Musta sotilas ei liikkunut", peli.siirra(6, 2, 6, 1));
		assertTrue("Valkoinen torni ei liikkunut", peli.siirra(7, 1, 7, 0));
		assertTrue("Musta sotilas ei siirtynyt reunalla", peli.siirra(6, 1, 6, 0));
		assertEquals(NappulanTyyppi.LAHETTI, peli.getPelitilanne()[6][0].getNappula().getTyyppi(),
				"Mustaa sotilasta ei korotettu lähetiksi");

	}

	// Testi bugille, joka löydettiin graafisenkäyttöliittymän manuaalisessa testaamisessa.
	@Test
	@DisplayName("Torni ei katoa, kun se shakkaa kuningasta vierestä")
	public void torniEiKatoa() {
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 3));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 4));
		assertTrue("Valkoinen torni ei siirtynyt", peli.siirra(7, 0, 7, 2));
		assertTrue("Musta Kuningas ei siirtynyt", peli.siirra(4, 7, 4, 6));
		assertTrue("Valkoinen torni ei siirtynyt 2", peli.siirra(7, 2, 5, 2));
		assertTrue("Musta Kuningas ei siirtynyt 2", peli.siirra(4, 6, 4, 5));
		assertTrue("Torni siirtyi shakkaamaan kuningasta", peli.siirra(5, 2, 5, 5));
		assertTrue("Ei shakannut", peli.getShakattu());
		assertTrue("Musta Kuningas ei siirtynyt turvaan", peli.siirra(4, 5, 3, 4));
		assertFalse("Shakki purettu", peli.getShakattu());
		assertTrue("Torni ei siirtynyt shakkaamaan Kuningasta uudestaan", peli.siirra(5, 5, 3, 5));
		assertEquals(NappulanTyyppi.TORNI, peli.getPelitilanne()[3][5].getNappula().getTyyppi(), "Torni katosi");
	}

	private int laskeNappulat(Ruutu[][] lauta) {
		int nappuloita = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (lauta[x][y].getNappula() != null) {
					nappuloita++;
				}
			}
		}
		return nappuloita;
	}

}
