package model;

import java.util.ArrayList;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import controller.IKontrolleri;

public class Shakkipeli implements IShakkipeli {
	private IKontrolleri kontrolleri;
	private Lauta lauta;
	private NappulanVari vuorossa;

	public Shakkipeli(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
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
		if (siirretaankoOikeataVaria(mistaX, mistaY) && onkoSiirtoSallittu(mistaX, mistaY, mihinX, mihinY)) {
			this.lauta.siirra(mistaX-1, mistaY-1, mihinX-1, mihinY-1);
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

	public boolean siirretaankoOikeataVaria(int mistaX, int mistaY) {
		return this.lauta.getLauta()[mistaX - 1][mistaY - 1].getNappula().getVari() == this.vuorossa;
	}

	private boolean onkoSiirtoSallittu(int mistaX, int mistaY, int mihinX, int mihinY) {
		ArrayList<Ruutu> siirrot = this.lauta.getLauta()[mistaX - 1][mistaY - 1].getNappula()
				.getSiirrot(new Ruutu(mistaX, mistaY));
		for (Ruutu siirto : siirrot) {
			if (siirto.getX() == mihinX && siirto.getY() == mihinY) {
				return true;
			}
		}
		return false;
	}

}
