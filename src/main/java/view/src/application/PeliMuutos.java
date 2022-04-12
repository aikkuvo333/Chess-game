package view.src.application;

import dao.Pelaaja;
import dao.PelinTiedot;

public class PeliMuutos {
	private String vari;
	private String vastustaja;
	private String tulos;
	private String siirrot;
	private String pvm;
	private String kesto;
	
	public PeliMuutos(PelinTiedot pelinTiedot, Pelaaja pelaaja) {
		if(pelinTiedot.getValkoinenPelaaja() == pelaaja) {
			vari = "Valkoinen";
			vastustaja = pelinTiedot.getMustaPelaaja().getKayttajaTunnus();
		} else {
			vari = "Musta";
			vastustaja = pelinTiedot.getValkoinenPelaaja().getKayttajaTunnus();
		}
		
		if(pelinTiedot.getVoittaja() == pelaaja) {
			tulos = "Voitto";
		} else {
			tulos = "Häviö";
		}
		
		siirrot = String.valueOf(pelinTiedot.getSiirrot().size());
		pvm = pelinTiedot.getPvm();;
		kesto = "" + pelinTiedot.getKesto();
	
	}

	public String getVari() {
		return vari;
	}

	public String getVastustaja() {
		return vastustaja;
	}

	public String getTulos() {
		return tulos;
	}

	public String getSiirrot() {
		return siirrot;
	}

	public String getPvm() {
		return pvm;
	}

	public String getKesto() {
		return kesto;
	}
}
