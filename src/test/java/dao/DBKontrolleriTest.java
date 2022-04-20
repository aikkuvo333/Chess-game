package dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Elmo Vahvaselkä
 *
 */

class DBKontrolleriTest {

	private DBKontrolleri db = DBKontrolleri.getInstance();
	
	@Test
	@DisplayName("Onko singleton")
	public void onkoSingleton() {
		assertEquals(DBKontrolleri.getInstance().hashCode(), db.hashCode(),"Singletonien hash codet eivät olleet samat.");
	}

	@Test
	@DisplayName("Pelaajan tallentaminen ja poistaminen tietokannasta onnistuu")
	public void pelaajanLisaaminenJaPoistaminen() {
		assertTrue("Pelaajan lisääminen ei onnistunut.", db.luoPelaaja("Testiheikki234"));
		assertTrue("Pelaaja ei löytynyt tietokannasta.", onkoPelaajaListassa("Testiheikki234"));
		
		List<Pelaaja> pelaajat = db.getPelaajat();
		Pelaaja testi = null;
		
		for (Pelaaja pelaaja: pelaajat) {
			if(pelaaja.getKayttajaTunnus().equals("Testiheikki234")) {
				testi = pelaaja;
			}
		}
		
		assertTrue("Pelaajan poistaminen ei onnistunut", db.poistaPelaajaPysyvasti(testi));
		assertFalse("Pelaaja löytyi tietokannasta", onkoPelaajaListassa("Testiheikki234"));
	}
	
	@Test
	@DisplayName("Pelin tallentaminen ja poistaminen tietokannasta onnistuu")
	public void pelinLisaaminenJaPoistaminen() {

		PelinTiedot testiPeli = new PelinTiedot(null, null);
		testiPeli.setVoittaja(null);
		
		assertTrue("Pelin tallentaminen ei onnistunut", db.tallennaPeli(testiPeli));
		assertTrue("Peli ei löydy tietokannasta", onkoPeliListassa());
		
		List<PelinTiedot> pelit = db.getKaikkiPelit();
		for (PelinTiedot peli : pelit) {
			if (peli.getVoittaja() == null && peli.getMustaPelaaja() == null && peli.getValkoinenPelaaja() == null)
				testiPeli = peli;
		}
		
		assertTrue("Pelin poistaminen ei onnistunut", db.poistaPeli(testiPeli));
		assertFalse("Peli löytyy tietokannasta", onkoPeliListassa());		
	}
	
	@Test
	@DisplayName("Tietokannas ei olevaa pelaajaa ei voi poistaa")
	public void pelaajaEiTietokannassa() {
		assertFalse("Pelaajan positaminen onnistui", db.poistaPelaajaPysyvasti(new Pelaaja("Jorma")));
	}

	private boolean onkoPelaajaListassa(String nimi) {
		List<Pelaaja> pelaajat = db.getPelaajat();
		for (Pelaaja pelaaja : pelaajat) {
			if (pelaaja.getKayttajaTunnus().equals(nimi))
				return true;
		}
		return false;
	}
	
	private boolean onkoPeliListassa() {
		List<PelinTiedot> pelit = db.getKaikkiPelit();
		for (PelinTiedot peli : pelit) {
			if (peli.getVoittaja() == null && peli.getMustaPelaaja() == null && peli.getValkoinenPelaaja() == null)
				return true;
		}
		return false;
	}

}
