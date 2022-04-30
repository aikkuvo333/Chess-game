package view.src.application;

import dao.Pelaaja;

/**
 * Luokka jota käytetään muuttamaan Pelaaja-luokan tiedot
 * JavaFX:än TableViewille sopivaan muotoon.
 * @author Elmo Vahvaselä
 */
public class PelaajaMuutos {
	/**
	 * Pelaajan tunnus String-oliona.
	 */
	private String kayttajaTunnus;
	
	/**
	 * Pelaajan voittojen määrä String-oliona.
	 */
	private String voitot;
	
	/**
	 * Pelaajan voittot String-oliona. Decimaalierotin esitetään localen mukaan.
	 */
	private String voittoprosentti;
	
	/**
	 * Pelatut pelit Strgin-oliona.
	 */
	private String peleja;

	/**
	 * Konstruktori. Kaikki Pelaaja-olion tiedot muutetaan
	 * String-olioiksi ja voittoprosentti muokataan localen mukaiseksi.
	 * @param Saa parametrinä Pelaaja-oliona sen pelaajan,
	 * jonka tiedot halutaan muuttaa
	 */
	public PelaajaMuutos(Pelaaja pelaaja) {
		kayttajaTunnus = pelaaja.getKayttajaTunnus();
		voitot = pelaaja.getVoitot() + "";
		voittoprosentti = String.format(ValittuKieli.getInstance().getLocale(), "%.1f%%", pelaaja.getVoittoprosentti());
		peleja = pelaaja.getPeleja() + "";
	}

	/**
	 * Getteri käyttäjätunnukselle.
	 * @return Käyttäjätunnus String-oliona.
	 */
	public String getKayttajaTunnus() {
		return kayttajaTunnus;
	}

	/**
	 * Getteri voittojen määrälle.
	 * @return Voittojen määrä String-oliona.
	 */
	public String getVoitot() {
		return voitot;
	}

	/**
	 * Getteri voittoprosentille.
	 * @return Voittoprosentti String-oliona, joka on muotoiltu localen mukaan.
	 */
	public String getVoittoprosentti() {
		return voittoprosentti;
	}

	/**
	 * Getteri pelattujen pelien määrälle.
	 * @return Pelattujen pelien määrä String-oliona.
	 */
	public String getPeleja() {
		return peleja;
	}
}
