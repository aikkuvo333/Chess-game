package view.src.application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.DBKontrolleri;
import dao.Pelaaja;
import dao.PelinTiedot;

class PeliMuutosTest {
	
	private DBKontrolleri db = DBKontrolleri.getInstance();

	@Test
	@DisplayName("Muutos toimii localella Englantin")
	public void muutosEnglanti() {
		ValittuKieli.getInstance().setEnglanti();
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki1337"));

		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testiPelaaja = null;
		Pelaaja testiPelaaja2 = null;
		
		for (Pelaaja pelaaja: pelaajat) {
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testiPelaaja = pelaaja;
			}
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki1337")) {
				testiPelaaja2 = pelaaja;
			}
		}
		
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, ValittuKieli.getInstance().getLocale());
		Date paivamaara = new Date();
		String pvm =  dateFormat.format(paivamaara);
		
		PelinTiedot testiPeli = new PelinTiedot(testiPelaaja, testiPelaaja2);
		testiPeli.setVoittaja(testiPelaaja);

		assertTrue("Pelin tallentaminen ei onnistunut", db.tallennaPeli(testiPeli));

		List<PelinTiedot> pelit = db.getKaikkiPelit();
		testiPeli = pelit.get(0);
		
		PeliMuutos testiMuutos = new PeliMuutos(testiPeli, testiPelaaja);
		
		assertEquals("White", testiMuutos.getVari(), "Väri oli väärin");
		assertEquals("Testiheikki1337", testiMuutos.getVastustaja(), "Vastustaja oli väärin");
		assertEquals("Win", testiMuutos.getTulos(), "Tulos oli väärin");
		assertEquals("0", testiMuutos.getSiirrot(), "Siirtojen määrä oli väärä");
		assertEquals(pvm, testiMuutos.getPvm(), "Päivämäärä oli väärin");
		assertEquals("0s ", testiMuutos.getKesto(), "Aika oli väärin");

		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testiPelaaja));
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testiPelaaja2));
		assertTrue("Pelin poistaminen ei onnistunut", db.poistaPeli(testiPeli));
	}
	
	@Test
	@DisplayName("Muutos toimii localella Suomi")
	public void muutosSuomi() {
		ValittuKieli.getInstance().setSuomi();
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki1337"));

		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testiPelaaja = null;
		Pelaaja testiPelaaja2 = null;
		
		for (Pelaaja pelaaja: pelaajat) {
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testiPelaaja = pelaaja;
			}
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki1337")) {
				testiPelaaja2 = pelaaja;
			}
		}
		
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, ValittuKieli.getInstance().getLocale());
		Date paivamaara = new Date();
		String pvm =  dateFormat.format(paivamaara);
		
		PelinTiedot testiPeli = new PelinTiedot(testiPelaaja, testiPelaaja2);
		testiPeli.setVoittaja(testiPelaaja);

		assertTrue("Pelin tallentaminen ei onnistunut", db.tallennaPeli(testiPeli));

		List<PelinTiedot> pelit = db.getKaikkiPelit();
		testiPeli = pelit.get(0);
		
		PeliMuutos testiMuutos = new PeliMuutos(testiPeli, testiPelaaja2);
		
		assertEquals("Musta", testiMuutos.getVari(), "Väri oli väärin");
		assertEquals("Testiheikki234", testiMuutos.getVastustaja(), "Vastustaja oli väärin");
		assertEquals("Häviö", testiMuutos.getTulos(), "Tulos oli väärin");
		assertEquals("0", testiMuutos.getSiirrot(), "Siirtojen määrä oli väärä");
		assertEquals(pvm, testiMuutos.getPvm(), "Päivämäärä oli väärin");
		assertEquals("0s ", testiMuutos.getKesto(), "Aika oli väärin");

		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testiPelaaja));
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testiPelaaja2));
		assertTrue("Pelin poistaminen ei onnistunut", db.poistaPeli(testiPeli));
	}
}
