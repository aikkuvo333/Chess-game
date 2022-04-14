package view.src.application;

import java.text.DateFormat;
import java.util.ResourceBundle;

import dao.Pelaaja;
import dao.PelinTiedot;

public class PeliMuutos {
	private String vari;
	private String vastustaja;
	private String tulos;
	private String siirrot;
	private String pvm;
	private String kesto;
	private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
			ValittuKieli.getInstance().getLocale());
	private ResourceBundle bundle = ValittuKieli.getInstance().getBundle();

	public PeliMuutos(PelinTiedot pelinTiedot, Pelaaja pelaaja) {
		if (pelinTiedot.getValkoinenPelaaja() == pelaaja) {
			vari = bundle.getString("PeliMuutosValkoinenTxt");
			vastustaja = pelinTiedot.getMustaPelaaja().getKayttajaTunnus();
		} else {
			vari = bundle.getString("PeliMuutosMustaTxt");
			vastustaja = pelinTiedot.getValkoinenPelaaja().getKayttajaTunnus();
		}

		if (pelinTiedot.getVoittaja() == pelaaja) {
			tulos = bundle.getString("PeliMuutosVoittoTxt");
		} else {
			tulos = bundle.getString("PeliMuutosHavioTxt");
		}

		siirrot = String.valueOf(pelinTiedot.getSiirrot().size());
		pvm = dateFormat.format(pelinTiedot.getPvm());

		if (pelinTiedot.getKesto() < 60) {
			kesto = pelinTiedot.getKesto() + bundle.getString("PeliMuutosSekunnitTxt");
		} else if (pelinTiedot.getKesto() < 60 * 60) {
			kesto = pelinTiedot.getKesto() / 60 + bundle.getString("PeliMuutosMinuutitTxt") + pelinTiedot.getKesto() % 60 + bundle.getString("PeliMuutosSekunnitTxt");
		} else {
			kesto = pelinTiedot.getKesto() / (60*60) + bundle.getString("PeliMuutosTunnitTxt") + pelinTiedot.getKesto() / 60 + bundle.getString("PeliMuutosMinuutitTxt") + pelinTiedot.getKesto() % 60 + bundle.getString("PeliMuutosSekunnitTxt");
		}

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
