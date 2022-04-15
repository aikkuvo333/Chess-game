package view.src.application;

import dao.Pelaaja;

/**
 * @author Elmo Vahvaselä
 */

public class PelaajaMuutos {
	private String kayttajaTunnus;
	private String voitot;
	private String voittoprosentti;
	private String peleja;

	public PelaajaMuutos(Pelaaja pelaaja) {
		kayttajaTunnus = pelaaja.getKayttajaTunnus();
		voitot = pelaaja.getVoitot() + "";
		voittoprosentti = String.format(ValittuKieli.getInstance().getLocale(), "%.1f%%", pelaaja.getVoittoprosentti());
		peleja = pelaaja.getPeleja() + "";
	}

	public String getKayttajaTunnus() {
		return kayttajaTunnus;
	}

	public String getVoitot() {
		return voitot;
	}

	public String getVoittoprosentti() {
		return voittoprosentti;
	}

	public String getPeleja() {
		return peleja;
	}
}
