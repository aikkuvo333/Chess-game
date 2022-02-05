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

	public Shakkipeli(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
		this.shakattu = false;
	}

	// konstruktori testeille
	public Shakkipeli() {
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
	}

	public Ruutu[][] getPelitilanne() {
		return this.lauta.getLauta();
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (siirtyykoOikeaVari(mistaX, mistaY) && onkoSiirtoListalla(mistaX, mistaY, mihinX, mihinY)) {

			//if (shakattu) {}

			if (lauta.getRuutu(mistaX, mistaY).getNappula() instanceof Kuningas) {
				if (this.kuningasVaarantaaItsensa(mihinX, mihinY)) {
					return false;
				}
			}

			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);
			// ilmoita shakista käyttäjälle.
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

	public boolean siirtyykoOikeaVari(int mistaX, int mistaY) {
		return this.lauta.getLauta()[mistaX][mistaY].getNappula().getVari() == this.vuorossa;
	}

	private boolean onkoSiirtoListalla(int mistaX, int mistaY, int mihinX, int mihinY) {
		ArrayList<Ruutu> siirrot = this.lauta.getRuutu(mistaX, mistaY).getNappula()
				.getSiirrot(new Ruutu(mistaX, mistaY), this.lauta.getLauta());
		for (Ruutu siirto : siirrot) {
			if (siirto.getX() == mihinX && siirto.getY() == mihinY) {
				return true;
			}
		}
		return false;
	}

	private boolean tarkistaShakkasiko(int mihinX, int mihinY) {

		ArrayList<Ruutu> siirrot = this.lauta.getLauta()[mihinX][mihinY].getNappula()
				.getSiirrot(new Ruutu(mihinX, mihinY), this.lauta.getLauta());

		for (Ruutu siirto : siirrot) {
			if (siirto.getNappula() != null) {
				if (siirto.getNappula().getVari() != vuorossa && siirto.getNappula() instanceof Kuningas) {
					this.shakattu = true;
				}
			}
		}
		return false;
	}
	
	private boolean kuningasVaarantaaItsensa(int mihinX, int mihinY) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (lauta.getRuutu(x,y).getNappula() instanceof Nappula) {
					if (lauta.getRuutu(x,y).getNappula().getVari() != vuorossa) {
						ArrayList<Ruutu> siirrot = lauta.getRuutu(x,y).getNappula().getSiirrot(new Ruutu(x,y), this.getPelitilanne());
						
						for(Ruutu siirto: siirrot) {
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

	private boolean tarkistaPoistuikoShakista(int mistaX, int mistaY, int mihinX, int mihinY) {
		return false;
	}
}
