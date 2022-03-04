package model;

import java.util.ArrayList;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import controller.IKontrolleri;
import dao.PelinTiedot;
import dao.Siirto;

//Luokka huolehtii säännösitä
public class Shakkipeli implements IShakkipeli {
	private IKontrolleri kontrolleri;
	private Lauta lauta;
	private NappulanVari vuorossa;
	private boolean shakattu;
	private boolean testi;
	private boolean peliLoppunut;
	private NappulanVari voittaja;
	private boolean tilastoitu;
	private PelinTiedot pelinTiedot;

	public Shakkipeli(IKontrolleri kontrolleri, boolean tilastoitu) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = false;
		this.peliLoppunut = false;
		this.voittaja = null;
		this.tilastoitu = tilastoitu;

		if (tilastoitu) {
			this.pelinTiedot = new PelinTiedot(kontrolleri.getValkoinenPelaaja(), kontrolleri.getMustaPelaaja());
		}
	}

	// konstruktori testeille
	public Shakkipeli(boolean tilastoitu) {
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = true;
		this.peliLoppunut = false;
		this.voittaja = null;
		this.tilastoitu = tilastoitu;
		
		if (this.tilastoitu) {
			this.pelinTiedot = new PelinTiedot("Sebastian", "Daniel");
		}
	}

	public Ruutu[][] getPelitilanne() {
		return this.lauta.getLauta();
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (siirtyykoOikeaVari(mistaX, mistaY) 
				&& onkoSiirtoListalla(mistaX, mistaY, mihinX, mihinY)
				&& !this.peliLoppunut) {

			this.varmistaSiirronTurvallisuus(mistaX, mistaY, mihinX, mihinY);

			// Siirto ei ole turvallinen, mikäli shakkaus ei poistunut
			if (shakattu) {
				return false;
			}

			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);

			// Korotetun nappulan tyyppi otetaan talteen mahdollista tallentamista varten.
			NappulanTyyppi korotus = this.teeMahdollinenKorotus(mihinX, mihinY);

			// Tallennetaan siirto mikäli tilastoitu peli
			if (tilastoitu) {
				Siirto siirto = new Siirto(mistaX, mistaY, mihinX, mihinY);
				siirto.setKorotus(korotus);
				this.pelinTiedot.lisaaSiirto(siirto);
			}

			this.tarkistaShakkasiko(mihinX, mihinY);
			this.vaihdaVuoro();

			if (this.paattyikoPeli()) {
				this.julistaVoittaja();
			}

			return true;
		}
		return false;
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		ArrayList<Ruutu> siirrot = this.lauta.getSiirrot(x, y);

		// Jos aikaa jää täydennä tänne siirroon sopivuuksien tarkistus, jotta pelilauta näyttää vain täysin lailliset siirrot

		// Tornitusta ei tarjota mikäli shakattu
		if (getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.KUNINGAS && shakattu) {
			if (!((Kuningas) getRuudunNappula(x, y)).getEkaSiirto()) {
				ArrayList<Ruutu> valiaikainen = new ArrayList<>();
				for (Ruutu siirto : siirrot) {

					// Taikanumerot, koska tornittaessa kuningas voi olla vain tietyssä paikassa.
					if (siirto.getX() - 1 == 4 || siirto.getX() + 1 == 4 || siirto.getX() == 4) {
						valiaikainen.add(siirto);
					}
				}
				siirrot = valiaikainen;
			}
		}

		return siirrot;
	}

	public boolean luovuta() {
		this.peliLoppunut = true;
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			this.voittaja = NappulanVari.MUSTA;
		} else {
			this.voittaja = NappulanVari.VALKOINEN;
		}
		return true;
	}

	private void vaihdaVuoro() {
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			this.vuorossa = NappulanVari.MUSTA;
		} else {
			this.vuorossa = NappulanVari.VALKOINEN;
		}
	}

	private boolean siirtyykoOikeaVari(int x, int y) {
		return this.getRuudunNappula(x, y).getVari() == this.vuorossa;
	}

	private boolean onkoSiirtoListalla(int mistaX, int mistaY, int mihinX, int mihinY) {
		ArrayList<Ruutu> siirrot = this.getSiirrot(mistaX, mistaY);
		for (Ruutu siirto : siirrot) {
			if (siirto.getX() == mihinX && siirto.getY() == mihinY) {
				return true;
			}
		}
		return false;
	}

	public boolean getShakattu() {
		return this.shakattu;
	}

	private boolean tarkistaShakkasiko(int x, int y) {

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
		return false;
	}

	// tarkastetaan onko ruutu, johon kuningas on siirtymässä, vastustajan
	// syömälinjalla.
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

	private void varmistaSiirronTurvallisuus(int mistaX, int mistaY, int mihinX, int mihinY) {
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

			if (palautettu instanceof Torni) {
				((Torni) palautettu).kumoaEkaSiirto();
			}

			if (palautettu instanceof Kuningas) {
				((Kuningas) palautettu).kumoaEkaSiirto();
			}
		}
	}

	private boolean paattyikoPeli() {

		// peli ei voi päättyä mikäli Kuningasta ei ole shakattu
		if (!shakattu) {
			return false;
		}

		// käydään kaikki pelilaudan ruudut
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {

				// mikäli ruudussa uhatun kuninkaan maata oleva nappula
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

	private void julistaVoittaja() {
		this.peliLoppunut = true;
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			this.voittaja = NappulanVari.MUSTA;
		} else {
			this.voittaja = NappulanVari.VALKOINEN;
		}
		
		if(this.tilastoitu) {
			this.pelinTiedot.setVoittaja(this.voittaja);
		}

		if (!this.testi) {
			this.kontrolleri.pelinvoitti(this.voittaja);
		}
	}

	private NappulanTyyppi teeMahdollinenKorotus(int x, int y) {
		NappulanTyyppi tyyppi = null;
		if (y == 7 && getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.SOTILAS) {
			if (!this.testi) {
				tyyppi = kontrolleri.korota();
			} else {
				// härski viritelmä junit testejä varten.
				tyyppi = NappulanTyyppi.KUNINGATAR;
			}
			this.lauta.korota(x, y, tyyppi);
		}

		if (y == 0 && getRuudunNappula(x, y).getTyyppi() == NappulanTyyppi.SOTILAS) {
			if (!this.testi) {
				tyyppi = kontrolleri.korota();
			} else {
				tyyppi = NappulanTyyppi.KUNINGATAR;
			}
			this.lauta.korota(x, y, tyyppi);
		}
		return tyyppi;
	}

	public NappulanVari getVuoro() {
		return vuorossa;
	}

	public boolean getPeliLoppunut() {
		return this.peliLoppunut;
	}

	public NappulanVari getVoittaja() {
		return this.voittaja;
	}

	private Nappula getRuudunNappula(int x, int y) {
		return lauta.getRuutu(x, y).getNappula();
	}

	public PelinTiedot getPelinTiedot() {
		return pelinTiedot;
	}
}
