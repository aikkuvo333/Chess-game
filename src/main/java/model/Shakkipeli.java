package model;

import java.util.ArrayList;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import controller.IKontrolleri;

//Luokka huolehtii säännösitä
public class Shakkipeli implements IShakkipeli {
	private IKontrolleri kontrolleri;
	private Lauta lauta;
	private NappulanVari vuorossa;
	private boolean shakattu;
	private boolean testi;
	private boolean peliLoppunut;
	private NappulanVari voittaja;
	
	public Shakkipeli(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = false;
		this.peliLoppunut = false;
		this.voittaja = null;
	}

	// konstruktori testeille
	public Shakkipeli() {
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = true;
		this.peliLoppunut = false;
		this.voittaja = null;
	}

	public Ruutu[][] getPelitilanne() {
		return this.lauta.getLauta();
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (siirtyykoOikeaVari(mistaX, mistaY) && onkoSiirtoListalla(mistaX, mistaY, mihinX, mihinY) && !this.peliLoppunut) {

			this.varmistaSiirronTurvallisuus(mistaX, mistaY, mihinX, mihinY);

			
			if (shakattu) {
				return false;
			}

			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);

			this.tarkistaShakkasiko(mihinX, mihinY);
			this.vaihdaVuoro();
			
			if(this.paattyikoPeli()) {
				this.peliLoppunut = false;
				if(this.vuorossa == NappulanVari.VALKOINEN) {
					this.voittaja = NappulanVari.MUSTA;
				} else {
					this.voittaja = NappulanVari.VALKOINEN;
				}
				//lisää metodi kontrollerin kutsumiseen
			}
			
			return true;
		}
		return false;
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		return this.lauta.getSiirrot(x, y);
	}
	
	public boolean luovuta() {
		this.peliLoppunut = true;
		if(this.vuorossa == NappulanVari.VALKOINEN) {
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
		ArrayList<Ruutu> siirrot = this.getRuudunNappula(mistaX, mistaY).getSiirrot(new Ruutu(mistaX, mistaY),
				this.getPelitilanne());
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
		ArrayList<Ruutu> siirrot = this.getRuudunNappula(x, y).getSiirrot(new Ruutu(x, y), this.getPelitilanne());

		// Käydään läpi siirrot ja tarkisteen voiko joku siirto osua vastustajan
		// kuninkaan päälle.
		for (Ruutu siirto: siirrot) {
			if (this.getRuudunNappula(siirto.getX(), siirto.getY()) != null) {
				if (this.getRuudunNappula(siirto.getX(), siirto.getY()).getVari() != vuorossa
						&& this.getRuudunNappula(siirto.getX(), siirto.getY()) instanceof Kuningas) {
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
		this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			shakattu = vaarantuukoKuningas(lauta.getValkoinenKuningas().getX(), lauta.getValkoinenKuningas().getY());
		} else {
			shakattu = vaarantuukoKuningas(lauta.getMustaKuningas().getX(), lauta.getMustaKuningas().getY());
		}
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
		if(shakattu) {

			//käydään kaikki pelilaudan ruudut
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					
					//mikäli ruudussa uhatun kuninkaan maata oleva nappula
					if (this.getRuudunNappula(x, y) instanceof Nappula) {
						if (this.getRuudunNappula(x, y).getVari() == vuorossa) {
							
							//Haetaan nappulan siirrot
							ArrayList<Ruutu> siirrot = this.getRuudunNappula(x, y).getSiirrot(new Ruutu(x, y),
									this.getPelitilanne());
							
							//Tarkistetaan voiko joku nappulan siirroista purkaa shakin
							for (Ruutu siirto : siirrot) {
								this.varmistaSiirronTurvallisuus(x, y, siirto.getX(), siirto.getY());
								if(!shakattu) {
									shakattu = true;
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
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
}
