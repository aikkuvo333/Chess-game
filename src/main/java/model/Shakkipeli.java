package model;

import java.util.ArrayList;

import controller.IKontrolleri;
import dao.DBKontrolleri;
import dao.IDaoController;
import dao.Pelaaja;
import dao.PelinTiedot;
import dao.Siirto;

/**
 * Shakkipeli luokka huolehtii Shakkipelin säännöistä.
 * @author Elmo Vahvaselkä 27.1.2022
 *
 */
public class Shakkipeli implements IShakkipeli {
	
	/**
	 * MVC-mallin mukainen kontrolleri, jonka avulla Shakkipeli kommunikoi viewvin kanssa.
	 */
	private IKontrolleri kontrolleri;
	
	/**
	 * Lauta olio, joka kuvaa shakin pelilautaa ja sisältää tiedot nappuloiden sijainneista.
	 */
	private Lauta lauta;
	
	/**
	 * Vuorossa olevan nappulan väri enuminen, jonka arvo voi olla VALKOINEN tai MUSTA.
	 */
	private NappulanVari vuorossa;
	
	/**
	 * Ilmaisee onko peli shakatussa tilassa.
	 */
	private boolean shakattu;
	
	/**
	 * Boolean joka asetettaan trueksi, kun halutaan suorittaa junit-testejä.
	 */
	private boolean testi;
	
	/**
	 * Arvo, joka asetetaan trueksi, kun halutaan pelata tilastoitu peli, jonka tiedot tallennetaan tietokantaan.
	 */
	private boolean tilastoitu;
	
	/**
	 * Ilmaisee onko peli loppunut.
	 */
	private boolean peliLoppunut;
	
	/**
	 * PelinTiedot olio, jota käytetään pelin tietojen tallentamiseen.
	 */
	private PelinTiedot pelinTiedot;
	
	/**
	 * Enum arvo, jolla voidaan määrittää junit testeissä tehtävät sotilaan korotukset.
	 */
	private NappulanTyyppi testiKorotus;
	
	/**
	 * Tietokannan kontrolleri, jota käytetään pelin tietojen tietokantaan tallentamiseen.
	 */
	private IDaoController daoKontrolleri;

	/**
	 * Konstruktori Shakkipelille.
	 * @param kontrolleri MVC-mallin mukainen kontrolleri, jonka avulla Shakkipeli kommunikoi viewvin kanssa. 
	 * @param tilastoitu True mikäli kyseessä tilastoitu peli muuten false.
	 */
	public Shakkipeli(IKontrolleri kontrolleri, boolean tilastoitu) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = false;
		this.tilastoitu = tilastoitu;
		this.peliLoppunut = false;

		if (tilastoitu) {
			this.pelinTiedot = new PelinTiedot(kontrolleri.getValkoinenPelaaja(), kontrolleri.getMustaPelaaja());
			this.daoKontrolleri = DBKontrolleri.getInstance();
		} else {
			this.pelinTiedot = new PelinTiedot(kontrolleri.getValkoinenPelaaja(), kontrolleri.getMustaPelaaja());
		}
	}

	/**
	 * Kostruktori testeille.
	 */
	public Shakkipeli() {
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = true;
		// tilastoitu false, koska testien tietoja ei haluta tallentaa tietokantaan
		this.tilastoitu = false;
		this.peliLoppunut = false;
		this.testiKorotus = NappulanTyyppi.KUNINGATAR;
		this.pelinTiedot = new PelinTiedot(new Pelaaja("Valkoinen"), new Pelaaja("Musta"));
	}

	/**
	 * {@inheritDoc}
	 */
	public Ruutu[][] getPelitilanne() {
		return this.lauta.getLauta();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (siirtyykoOikeaVari(mistaX, mistaY) && onkoSiirtoListalla(mistaX, mistaY, mihinX, mihinY)
				&& !this.peliLoppunut) {

			this.varmistaSiirronTurvallisuus(mistaX, mistaY, mihinX, mihinY);

			// Siirto ei ole kelpo, mikäli shakkaus ei poistunut, kun siirron turvallisuus
			// varmistettiin
			if (shakattu) {
				return false;
			}

			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);

			// Korotetun nappulan tyyppi otetaan talteen mahdollista tallentamista varten.
			NappulanTyyppi korotus = this.teeMahdollinenKorotus(mihinX, mihinY);

			// Tallennetaan siirto pelin tietoihin
			this.pelinTiedot.lisaaSiirto(new Siirto(mistaX, mistaY, mihinX, mihinY, korotus));

			this.tarkistaShakkasiko(mihinX, mihinY);
			this.vaihdaVuoro();

			if (this.paattyikoPeli()) {
				this.julistaVoittaja();
			}

			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		ArrayList<Ruutu> siirrot = this.lauta.getSiirrot(x, y);

		// Tornitusta ei tarjota mikäli shakattu
		if (getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.KUNINGAS && shakattu) {

			// Tornitus onnistuu vain mikäli kuningas ei ole liikkunut
			if (!((Kuningas) getRuudunNappula(x, y)).getEkaSiirto()) {
				ArrayList<Ruutu> valiaikainen = new ArrayList<>();
				for (Ruutu siirto : siirrot) {

					/*
					 * Taikanumerot, koska tornittaessa kuningas voi olla vain tietyssä paikassa.
					 * Hyväksytään vain ne siirrot, jotka ovat korkeintaan yhden ruudun päässä.
					 */
					if (siirto.getX() - 0 == 4 || siirto.getX() + 1 == 4 || siirto.getX() == 4) {
						valiaikainen.add(siirto);
					}
				}
				siirrot = valiaikainen;
			}
		}
		return siirrot;

	}
	
	/**
	 * Metodia kutsumalla peli päättyy. Ilmoittaa voittajan viewille ja tallentaa pelin tietokantaan,
	 * mikäli kyseessä oli tilastoitu peli.
	 */
	public void julistaVoittaja() {
		this.peliLoppunut = true;
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			pelinTiedot.setVoittaja(pelinTiedot.getMustaPelaaja());
		} else {
			pelinTiedot.setVoittaja(pelinTiedot.getValkoinenPelaaja());
		}

		if (tilastoitu) {
			daoKontrolleri.tallennaPeli(pelinTiedot);
		}

		if (!this.testi) {
			this.kontrolleri.pelinvoitti(pelinTiedot.getVoittaja());
		}
	}
	
	/**
	 * Palauttaa tiedon siitä, onko peli shakattu.
	 * @return Palauttaa true, mikäli peli on shakattu, muuten false;
	 */
	public boolean getShakattu() {
		return this.shakattu;
	}

	/**
	 * {@inheritDoc}
	 */
	public NappulanVari getVuoro() {
		return vuorossa;
	}

	/**
	 * Metodilla voi varmistaa onko peli loppunut();
	 * @return Palauttaa true, mikäli peli on loppunut, muuten false.
	 */
	public boolean getPeliLoppunut() {
		return this.peliLoppunut;
	}

	/**
	 * Metodilla voi hakea pelin voittaneen pelaajan.
	 * @return palauttaa pelin voittajan Pelaaja oliona.
	 */
	public Pelaaja getVoittaja() {
		return this.pelinTiedot.getVoittaja();
	}
	
	/**
	 * Metodilla voi hakea pelin tiedot.
	 * @return PelinTiedot olion.
	 */
	public PelinTiedot getPelinTiedot() {
		return pelinTiedot;
	}

	/**
	 * Metodilla voi asettaa korotettavan NappulanTyypin testejä varten.
	 * @param tyyppi
	 */
	public void setTestiKorotus(NappulanTyyppi tyyppi) {
		this.testiKorotus = tyyppi;
	}
	
	/**
	 * Vaihtaa vuorossa olevan pelaajan.
	 */
	private void vaihdaVuoro() {
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			this.vuorossa = NappulanVari.MUSTA;
		} else {
			this.vuorossa = NappulanVari.VALKOINEN;
		}
	}

	/**
	 * Metodin avulla voidaan varmistaa, että siirretään vuorssa olevan värin mukaista nappulaa.
	 * @param x Siirrettävän nappulan x-koordinatti, joka on kokonaisluku väliltä 1-7.
	 * @param y Siirrettävän nappulan x-koordinatti, joka on kokonaisluku väliltä 1-7.
	 * @return boolean arvo true, mikäli siirretään oikeata väriä, muuten false.
	 */
	private boolean siirtyykoOikeaVari(int x, int y) {
		return this.getRuudunNappula(x, y).getVari() == this.vuorossa;
	}

	/**
	 * Tarkastaa että onko suoritettava siirto sallittujen siirtojen listalla.
	 * @param mistaX Siirrettävän pelinappulan lähtökoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mistaY Siirrettävän pelinappulan lähtökoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinX Siirrettävän pelinappulan kohdekoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinY Siirrettävän pelinappulan kohdekoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @return Palauttaa true, mikäli siirto on sallittu, muuten false.
	 */
	private boolean onkoSiirtoListalla(int mistaX, int mistaY, int mihinX, int mihinY) {
		ArrayList<Ruutu> siirrot = this.getSiirrot(mistaX, mistaY);
		for (Ruutu siirto : siirrot) {
			if (siirto.getX() == mihinX && siirto.getY() == mihinY) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tarkaastaa aiheuttaako tiettyyn ruutuun siirrety nappula shakin.
	 * @param x Nappulan sijainti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param y Nappulan sijainti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 */
	private void tarkistaShakkasiko(int x, int y) {

		// Haetaan siirretyn nappulan mahdolliset seuraavat siirrot
		ArrayList<Ruutu> siirrot = this.getSiirrot(x, y);

		// Käydään läpi siirrot ja tarkisteen voiko joku siirto osua vastustajan
		// kuninkaan päälle.
		for (Ruutu siirto : siirrot) {
			if (this.getRuudunNappula(siirto.getX(), siirto.getY()) != null) {
				if (this.getRuudunNappula(siirto.getX(), siirto.getY()).getVari() != vuorossa
						&& this.getRuudunNappula(siirto.getX(), siirto.getY()).getTyyppi() == NappulanTyyppi.KUNINGAS) {
					this.shakattu = true;
					if (!this.testi) {
						this.kontrolleri.siirtoAiheuttiShakin();
					}
				}
			}
		}
	}

	/**
	 * Varmistaa, että tehtävä siirto on turvallinen. Mikäli siirto ei ole turvallinen peli pysyy 
	 * shakatussa tilassa ja käyttäjän siirtoa ei hyväksytä siirrä() metodissa.
	 * @param mistaX Siirrettävän pelinappulan lähtökoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mistaY Siirrettävän pelinappulan lähtökoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinX Siirrettävän pelinappulan kohdekoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinY Siirrettävän pelinappulan kohdekoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 */
	private void varmistaSiirronTurvallisuus(int mistaX, int mistaY, int mihinX, int mihinY) {
		Nappula nappula = null;
		Boolean ekaSiirtoTehty = true;

		if (this.lauta.getRuutu(mistaX, mistaY).getNappula() != null) {
			Nappula siirrettava = this.lauta.getRuutu(mistaX, mistaY).getNappula();

			if (siirrettava instanceof NappulaEka) {
				ekaSiirtoTehty = ((NappulaEka) siirrettava).getEkaSiirto();
			}
		}

		// Mahdollinen syötävä nappula ja mahdollinen tieto ekasta siirrosta talteen.
		if (this.lauta.getRuutu(mihinX, mihinY).getNappula() != null) {
			nappula = this.lauta.getRuutu(mihinX, mihinY).getNappula();
		}

		// Tehdään siirto
		this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);

		if (this.vuorossa == NappulanVari.VALKOINEN) {
			shakattu = vaarantuukoKuningas(lauta.getValkoinenKuningas().getX(), lauta.getValkoinenKuningas().getY());
		} else {
			shakattu = vaarantuukoKuningas(lauta.getMustaKuningas().getX(), lauta.getMustaKuningas().getY());
		}

		// Kumotaan siirto
		if (!this.lauta.kumoaTornitus(mistaX, mistaY, mihinX, mihinY)) {
			this.lauta.siirra(mihinX, mihinY, mistaX, mistaY);

			Nappula palautettu = this.getRuudunNappula(mistaX, mistaY);

			if (palautettu instanceof NappulaEka && ekaSiirtoTehty == false) {
				((NappulaEka) palautettu).kumoaEkaSiirto();
			}
		}

		// palautetaan mahdollisesti poistettu nappula paikoilleen
		if (nappula != null) {
			this.lauta.getRuutu(mihinX, mihinY).setNappula(nappula);
		}
	}
	
	/**
	 * Tarkistaa vaarantuuko kuningas siirron seuruksena. Apumetodi varmistaSiirronTurvallisuus() metodille.
	 * @param x Nappulan sijainti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param y Nappulan sijainti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @return Boolean arvo true, mikäli kuningas vaarantuu, muuten false.
	 */
	private boolean vaarantuukoKuningas(int mihinX, int mihinY) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (this.getRuudunNappula(x, y) instanceof Nappula) {
					if (this.getRuudunNappula(x, y).getVari() != vuorossa) {
						ArrayList<Ruutu> siirrot = this.getRuudunNappula(x, y).getSiirrot(new Ruutu(x, y),
								this.getPelitilanne());

						for (Ruutu siirto : siirrot) {
							if (siirto.getX() == mihinX && siirto.getY() == mihinY) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Metodia käytetään tarkistamaan, että päättyikö peli.
	 * @return Palauttaa true, mikäli peli päättyi, muuten false.
	 */
	private boolean paattyikoPeli() {

		// Peli ei voi päättyä mikäli Kuningasta ei ole shakattu
		if (!shakattu) {
			return false;
		}

		// Käydään kaikki pelilaudan ruudut
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {

				// Mikäli ruudussa uhatun kuninkaan maata oleva nappula
				if (this.getRuudunNappula(x, y) instanceof Nappula) {
					if (this.getRuudunNappula(x, y).getVari() == vuorossa) {
						// Haetaan nappulan siirrot
						ArrayList<Ruutu> siirrot = this.getSiirrot(x, y);

						// Tarkistetaan voiko joku nappulan siirroista purkaa shakin
						for (Ruutu siirto : siirrot) {
							this.varmistaSiirronTurvallisuus(x, y, siirto.getX(), siirto.getY());
							if (!shakattu) {
								shakattu = true;
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	
	
	private NappulanTyyppi teeMahdollinenKorotus(int x, int y) {
		NappulanTyyppi tyyppi = null;
		if (y == 7 && getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.SOTILAS) {
			if (!this.testi) {
				tyyppi = kontrolleri.korota();
			} else {
				// Härski viritelmä junit testejä varten.
				tyyppi = this.testiKorotus;
			}
			this.lauta.korota(x, y, tyyppi);
		}

		if (y == 0 && getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.SOTILAS) {
			if (!this.testi) {
				tyyppi = kontrolleri.korota();
			} else {
				tyyppi = this.testiKorotus;
			}
			this.lauta.korota(x, y, tyyppi);
		}
		return tyyppi;
	}
	
	/**
	 * Metodilla voi hakea tietyssä ruudussa olevan Nappula olion
	 * @param x Nappulan sijainti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param y Nappulan sijainti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @return Palauttaa Nappula olion.
	 */
	private Nappula getRuudunNappula(int x, int y) {
		return lauta.getRuutu(x, y).getNappula();
	}

}
