package view.src.application;

import java.text.DateFormat;
import java.util.ResourceBundle;

import dao.Pelaaja;
import dao.PelinTiedot;

/**
 * Luokka jota käytetään muuttamaan PelinTiedot-luokan tiedot
 * JavaFX:än TableViewille sopivaan muotoon. PelinTiedot-luokasta
 * poiketen, PeliMuutos-luokan tiedot on esitetty tietyn pelaajan 
 * näkökulmasta. Tarkoittaen, että vari, vastustaja ja tulos 
 * päätellään konstruktorin parametrien mukaan.
 * @author Elmo Vahvaselkä
 *
 */
public class PeliMuutos {
	/**
	 * Sen pelaajan vari, joka annetaan parametrinä konstruktorissa.
	 */
	private String vari;
	
	/**
	 * Konstruktorissa annetun pelaajan vastustaja.
	 */
	private String vastustaja;
	
	/**
	 * Voittiko vai hävisikö konstruktorissa olevan pelaajan pelin. 
	 * Localen mukaan talletetaan joko sana voitto tai häviö.
	 */
	private String tulos;
	
	/**
	 * Pelissä tehtyjen siirtojen määrä String-oliona.
	 */
	private String siirrot;
	
	/**
	 * Päivämäärä jolloin pelin on pelattu String-oliona. Muotoiltu localen mukaan.
	 */
	private String pvm;
	
	/**
	 * Pelin kesto String-oliona. Ilmoitetaan tunteina, minuutteina ja sekunteina. 
	 * Yksiköt lokalisoitu. Nollaa olevia yksiköitä ei näytetä. 
	 */
	private String kesto;
	
	/**
	 * DateFormat-olio, jolla mutoillaan päivämäärä localen mukaan sopivaan muotoon.
	 */
	private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
			ValittuKieli.getInstance().getLocale());
	
	/**
	 * Localen mukaiset tekstiresurssit.
	 */
	private ResourceBundle bundle = ValittuKieli.getInstance().getBundle();

	/**
	 * Konstruktori joka päättelee pelaajan värin ja tuloksen.
	 * Muokkaa String-oliot sopiviksi localen mukaan.
	 * @param PelinTiedot-olio siitä pelistä, joka halutaan muuttaa.
	 * @param Pelaaja-olio, jonka näkökulmasta peliä halutaan tarkastella.
	 */
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

	/**
	 * Getteri värille.
	 * @return Pelaajan väri String-oliona
	 */
	public String getVari() {
		return vari;
	}

	/**
	 * Getteri vastustajalle.
	 * @return Pelaajan vastusta String-oliona.
	 */
	public String getVastustaja() {
		return vastustaja;
	}

	/**
	 * Getteri tulokselle.
	 * @return Pelin tulos String-oliona localen mukaisella kielellä.
	 */
	public String getTulos() {
		return tulos;
	}

	/**
	 * Getterin siirtojen määrälle.
	 * @return Pelissä tehtyjen siirtojen lukumäärä String-oliona.
	 */
	public String getSiirrot() {
		return siirrot;
	}

	/**
	 * Getteri päivämäärälle.
	 * @return Pelin päivämäärä String-oliona localen mukaan muotoiltuna.
	 */
	public String getPvm() {
		return pvm;
	}

	/**
	 * Getteri pelin kestolle.
	 * @return Pelin kesto String-oliona localen ja pelin pituuden mukaan muotoiltuna.
	 */
	public String getKesto() {
		return kesto;
	}
}
