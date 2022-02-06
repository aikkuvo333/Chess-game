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

	public Shakkipeli(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = false;
	}

	// konstruktori testeille
	public Shakkipeli() {
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
		this.testi = true;
	}

	public Ruutu[][] getPelitilanne() {
		return this.lauta.getLauta();
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (siirtyykoOikeaVari(mistaX, mistaY) && onkoSiirtoListalla(mistaX, mistaY, mihinX, mihinY)) {

			if (shakattu) {
				tarkistaShakki(mistaX, mistaY, mihinX, mihinY);
				if(shakattu) {
					return false;
				}
			}

			if (lauta.getRuutu(mistaX, mistaY).getNappula() instanceof Kuningas) {
				if (this.vaarantuukoKuningas(mihinX, mihinY)) {
					return false;
				}
			}

			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);
			this.tarkistaShakkasiko(mihinX, mihinY);
			this.vaihdaVuoro();
			return true;
		}
		return false;
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		return this.lauta.getSiirrot(x, y);
	}

	private void vaihdaVuoro() {
		if (this.vuorossa == NappulanVari.VALKOINEN) {
			this.vuorossa = NappulanVari.MUSTA;
		} else {
			this.vuorossa = NappulanVari.VALKOINEN;
		}
	}

	public boolean siirtyykoOikeaVari(int x, int y) {
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
		
		//Haetaan siirretyn nappulan mahdolliset seuraavat siirrot
		ArrayList<Ruutu> siirrot = this.getRuudunNappula(x, y).getSiirrot(new Ruutu(x, y), this.getPelitilanne());

		//Käydään läpi siirrot ja tarkisteen voiko joku siirto osua vastustajan kuninkaan päälle.
		for (Ruutu siirto : siirrot) {
			if (this.getRuudunNappula(siirto.getX(), siirto.getY()) != null) {
				if (this.getRuudunNappula(siirto.getX(), siirto.getY()).getVari() != vuorossa && this.getRuudunNappula(siirto.getX(), siirto.getY()) instanceof Kuningas) {
					this.shakattu = true;
					if(!this.testi) {
						this.kontrolleri.siirtoAiheuttiShakin();
					}
				}
			}
		}
		return false;
	}

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
	
	private void tarkistaShakki(int mistaX, int mistaY, int mihinX, int mihinY) {
		this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);
		if(this.vuorossa == NappulanVari.VALKOINEN) {
			shakattu = vaarantuukoKuningas(lauta.getValkoinenKuningas().getX(), lauta.getValkoinenKuningas().getY());			
		} else {
			shakattu = vaarantuukoKuningas(lauta.getMustaKuningas().getX(), lauta.getMustaKuningas().getY());
		}
		this.lauta.siirra(mihinX, mihinY, mistaX, mistaY);
	}

	private Nappula getRuudunNappula(int x, int y) {
		return lauta.getRuutu(x, y).getNappula();
	}
}
