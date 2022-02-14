package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShakkipeliTest {

	private Shakkipeli peli;
	
	@BeforeEach 
	public void setUp() {
		peli = new Shakkipeli();
	}
	
	@Test
	@DisplayName("Peli ei hyväksy laintonta siirtoa Sotilaalla")
	public void sotilastaEiSaaSiirtääVaarin() {
		assertFalse("Siirto ei onnistunut", peli.siirra(0, 1, 0, 5));
		assertFalse("Sotilas siirtyi", peli.getPelitilanne()[0][5].getNappula() instanceof Sotilas);
		assertTrue("Sotilas ei ole enää alkuruudussa", peli.getPelitilanne()[0][1].getNappula() instanceof Sotilas);
		assertEquals(2, peli.getSiirrot(0,1).size(), "Siirtoja palautui väärä määrä");
	}
	
	@Test
	@DisplayName("Peli ei anna siirtää väärää väriä")
	public void vaaraaVariaEiSaaSiirtaa() {
		assertFalse("Mustan siirto onnistui aloituksessa", peli.siirra(0, 6, 0, 5));
		assertTrue("Valkoisen siirto ei onnistunut aloituksessa", peli.siirra(0, 1, 0, 3));
		assertTrue("Valkoinen ei ole oikeassa ruudussa", peli.getPelitilanne()[0][3].getNappula() instanceof Sotilas);
		assertFalse("Valkoista siirrettiin kaksi kertaa peräkkäin", peli.siirra(1, 1, 1, 0));
		assertTrue("Mustan siirto ei onnistunut", peli.siirra(0, 6, 0, 5));
		assertTrue("Musta ei ole oikeassa ruudussa", peli.getPelitilanne()[0][5].getNappula() instanceof Sotilas);
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
	@DisplayName("Pelaaja ei voi tehdä siirtoa, joka ei poista shakkia")
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
		assertTrue("Musta Kuningas siirtyi turvaan", peli.siirra(4, 5, 3, 4));
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
		assertTrue("Valkoinen torni siirtyi mustan sotilaan viereen", peli.siirra(5, 2, 5, 5));;
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
		assertTrue("Valkoinen Kuningas ei siirtynyt", peli.getPelitilanne()[6][0].getNappula() instanceof Kuningas);
		assertTrue("Valkoinen torni ei siirtynyt", peli.getPelitilanne()[5][0].getNappula() instanceof Torni);
		assertTrue("Mustan tornitus oikean tornin kanssa ei onnistunut", peli.siirra(4, 7, 6, 7));
		assertTrue("Valkoinen Kuningas ei siirtynyt", peli.getPelitilanne()[6][7].getNappula() instanceof Kuningas);
		assertTrue("Valkoinen torni ei siirtynyt", peli.getPelitilanne()[5][7].getNappula() instanceof Torni);
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
		assertTrue("Torni ei ole paikallaan", peli.getPelitilanne()[0][7].getNappula() instanceof Torni);  
		assertTrue("Kuningas ei ole paikallaan", peli.getPelitilanne()[4][7].getNappula() instanceof Kuningas);
		assertTrue("Musta ratsu söi kuningattaren", peli.siirra(0, 5, 2, 6));
		assertEquals(27, laskeNappulat(peli.getPelitilanne()), "Laudalla ei ole 27 nappulaa");
		assertTrue("Valkoinen sotilas ei siirtynyt", peli.siirra(7, 1, 7, 2));
		assertTrue("Musta tornitus ei onnistunut", peli.siirra(4, 7, 2, 7));

	}
	
	@Test
	@DisplayName("Tornituksella voi purkaa shakin")
	public void tornitusPelastaaKuninkaan() {
		
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
		assertTrue("Valkoinen Kuningas ei tornittanut itseään turvaan", peli.siirra(4, 0, 2, 0));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(7, 6, 7, 5));
		assertTrue("Valkoinen Kuningatar ei siirtynyt shakkaamaan", peli.siirra(3, 3, 0, 3));
		assertTrue("Musta Kuningas ei tornittanut itseään turvaan", peli.siirra(4, 7, 2, 7));
	}
	
	@Test
	@DisplayName("Pikamatti päättää pelin")
	public void PikamattiPaattiPelin() {
		assertTrue("Valkoinen sotilas ei siirtynyt 1", peli.siirra(5, 1, 5, 2));
		assertTrue("Musta sotilas ei siirtynyt", peli.siirra(4, 6, 4, 5));
		assertTrue("Valkoinen sotilas ei siirtynyt 2", peli.siirra(6, 1, 6, 3));
		assertTrue("Musta Kuningatar ei siirtynyt ja lopettanut peliä", peli.siirra(3, 7, 7, 3));
		assertFalse("peli ei päättynyt", peli.getPeliLoppunut());
		assertEquals(NappulanVari.MUSTA, peli.getVoittaja(), "Musta ei voittanut peliä");
		assertFalse("Valkoinen sai tehdä siirron", peli.siirra(0, 1, 0, 2));		
	}
	
	private int laskeNappulat(Ruutu[][] lauta) {
		int nappuloita = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta[x][y].getNappula() != null) {
					nappuloita++;
				}
			}
		}
		return nappuloita;
	}

}
