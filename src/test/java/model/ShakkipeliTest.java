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
		peli = new Shakkipeli();
	}
	
	@Test
	@DisplayName("Peli ei hyväksy laintonta siirtoa Sotilaalla")
	public void sotilastaEiSaaSiirtääVaarin() {
		assertFalse("Siirto ei onnistunut", peli.siirra(1, 2, 1, 6));
		assertFalse("Sotilas siirtyi", peli.getPelitilanne()[0][5].getNappula() instanceof Sotilas);
		assertTrue("Sotilas ei ole enää alkuruudussa", peli.getPelitilanne()[0][1].getNappula() instanceof Sotilas);
		assertEquals(16, laskeSotilaat(peli.getPelitilanne()), "Sotilaita on väärä määrä");
		assertEquals(2, peli.getSiirrot(0,1).size(), "Siirtoja palautui väärä määrä");
	}
	
	@Test
	@DisplayName("Peli ei anna siirtää väärää väriä")
	public void vaaraaVariaEiSaaSiirtaa() {
		assertFalse("Mustan siirto onnistui aloituksessa", peli.siirra(1, 7, 1, 6));
		assertTrue("Valkoisen siirto ei onnistunut aloituksessa", peli.siirra(1, 2, 1, 4));
		assertTrue("Valkoinen ei ole oikeassa ruudussa", peli.getPelitilanne()[0][3].getNappula() instanceof Sotilas);
		assertFalse("Valkoista siirrettiin kaksi kertaa peräkkäin", peli.siirra(2, 2, 2, 1));
		assertTrue("Mustan siirto ei onnistunut", peli.siirra(1, 7, 1, 6));
		assertTrue("Musta ei ole oikeassa ruudussa", peli.getPelitilanne()[0][5].getNappula() instanceof Sotilas);
		assertEquals(16, laskeSotilaat(peli.getPelitilanne()), "Sotilaita on väärä määrä");
	}
	
	private int laskeSotilaat(Ruutu[][] lauta) {
		int sotilaita = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(lauta[x][y].getNappula() instanceof Sotilas) {
					sotilaita++;
				}
			}
		}
		return sotilaita;
	}

}
