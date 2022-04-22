package dao;

import static org.junit.Assert.assertTrue;

/**
 * @author Elmo Vahvaselkä 27.3.2022
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Shakkipeli;

class PelaajaTest {

	private Shakkipeli peli;

	@BeforeEach
	public void setUp() {
		// aloitetaan tilastoimaton peli
		peli = new Shakkipeli();
	}

	@Test
	@DisplayName("Tilastoimattoman pelin alussa pelaajat luodaan oikein")
	public void pelaajatLuodaanOikein() {
		assertEquals("Valkoinen", peli.getPelinTiedot().getValkoinenPelaaja().getKayttajaTunnus(),
				"Valkoisen pelaajan nimi oli väärin");
		assertEquals("Musta", peli.getPelinTiedot().getMustaPelaaja().getKayttajaTunnus(),
				"Mustan pelaajan nimi oli väärin");
	}

	@Test
	@DisplayName("Parametriton konstrukttori toimii")
	public void parametritonKonstruktorioimii() {
		new Pelaaja();
	}

	@Test
	@DisplayName("Pelaajan getterit toimivat")
	public void getteritToimivat() {
		DBKontrolleri db = DBKontrolleri.getInstance();
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));

		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testi = null;

		for (Pelaaja pelaaja : pelaajat) {
			if (pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testi = pelaaja;
			}
		}
		
		assertEquals(0, testi.getPeleja(), "Pelattujen pelien määrä ei ollut nolla");
		assertEquals(0, testi.getVoittoprosentti(), "Voittoprosentti ei ollut nolla");
		assertEquals(0, testi.getVoitot(), "Voitot ei ollut nolla");
		assertEquals(0, testi.getPelit().size(), "Peli lista ei ollut tyhjä");
		
		PelinTiedot testiPeli = new PelinTiedot(testi, null);
		testiPeli.setVoittaja(testi);
		
		assertTrue("Pelin tallentaminen ei onnistunut", db.tallennaPeli(testiPeli));
		
		pelaajat = db.getPelaajat();
		for (Pelaaja pelaaja : pelaajat) {
			if (pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testi = pelaaja;
			}
		}
		
		assertEquals(1, testi.getPeleja(), "Pelattujen pelien määrä ei ollut 1");
		assertEquals(100, testi.getVoittoprosentti(), "Voittoprosentti ei ollut 100");
		assertEquals(1, testi.getVoitot(), "Voitot ei ollut 1");
		assertEquals(1, testi.getPelit().size(), "Pelilistan pituus ei ollut yksi");
		
		List<PelinTiedot> pelit = db.getKaikkiPelit();
		for (PelinTiedot peli : pelit) {
			if (peli.getVoittaja().getKayttajaTunnus().equals("Testiheikki234") && peli.getMustaPelaaja() == null && peli.getValkoinenPelaaja().getKayttajaTunnus().equals("Testiheikki234"));
				testiPeli = peli;
		}
		
		assertTrue("Pelin poistaminen ei onnistunut", db.poistaPeli(testiPeli));
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testi));
	}
}
