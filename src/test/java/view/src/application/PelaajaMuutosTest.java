package view.src.application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.DBKontrolleri;
import dao.Pelaaja;

class PelaajaMuutosTest {

	DBKontrolleri db = DBKontrolleri.getInstance();

	@Test
	@DisplayName("Getterit toimivat localella Englanti")
	public void getteritEnglanti() {
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));
		
		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testi = null;
		
		for (Pelaaja pelaaja: pelaajat) {
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testi = pelaaja;
			}
		}
		
		PelaajaMuutos testiMuutos = new PelaajaMuutos(testi);
		
		assertEquals("Testiheikki234", testiMuutos.getKayttajaTunnus(), "Pelaajan käyttäjätunnus oli väärin");
		assertEquals("0", testiMuutos.getVoitot(), "Voitot olivat väärin");
		assertEquals("0.0%", testiMuutos.getVoittoprosentti(), "Voittoprosentti oli väärin");
		assertEquals("0", testiMuutos.getPeleja(), "Pelien määrä oli väärin");
		
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testi));
	}
	
	@Test
	@DisplayName("Lokalisaatiosta riippuvaiset getterit toimivat localella Suomi")
	public void getteritSuomi() {
		ValittuKieli.getInstance().setSuomi();
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));
		
		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testi = null;
		
		for (Pelaaja pelaaja: pelaajat) {
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testi = pelaaja;
			}
		}
		
		PelaajaMuutos testiMuutos = new PelaajaMuutos(testi);

		assertEquals("0,0%", testiMuutos.getVoittoprosentti(), "Voittoprosentti oli väärin");
		
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testi));
	}

}
