package model;

import java.util.ArrayList;

/**
 * @author Elmo Vahvaselkä 26.1.2022
 */

public class Lauta {

	private Ruutu[][] lauta;

	public Lauta() {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
		this.asetaNappulat();
	}

	//Konstruktori testeille, mikäli halutaan käyttää tyhjää lautaa
	public Lauta(boolean testi) {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
	}

	public Ruutu[][] getLauta() {
		return this.lauta;
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		return lauta[x][y].getNappula().getSiirrot(new Ruutu(x, y), this.lauta);
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		Nappula nappula = lauta[mistaX][mistaY].poistaNappula();
		lauta[mihinX][mihinY].setNappula(nappula);
		if (nappula instanceof Sotilas) {
			((Sotilas) nappula).ekaSiirtoTehty();
		}
		return true;
	}

	public void asetaNappulat() {
		for (int x = 0; x < 8; x++) {
			lauta[x][1].setNappula(new Sotilas(NappulanVari.VALKOINEN));
			lauta[x][6].setNappula(new Sotilas(NappulanVari.MUSTA));
		}
	}

	public void luoRuudut() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				lauta[x][y] = new Ruutu(x, y);
			}
		}
	}

}
