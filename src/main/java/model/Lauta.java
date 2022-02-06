package model;

import java.util.ArrayList;

/**
 * @author Elmo Vahvaselkä 26.1.2022
 */

/* Ei otaa kantaa sääntöihin. 
 * Huolehtii, että nappulat ovat siellä, 
 * minne Shakkipeliluokka ne haluaa laittaa.
 * Helpottaa testejä, kun laudalla voi 
 * tehdä mitä huvittaa.
 * */
 
public class Lauta {

	private Ruutu[][] lauta;
	private Ruutu valkoinenKuningas;
	private Ruutu mustaKuningas;

	public Lauta() {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
		this.asetaNappulat();
	}

	// Konstruktori testeille, mikäli halutaan käyttää tyhjää lautaa
	public Lauta(boolean testi) {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
	}

	public Ruutu[][] getLauta() {
		return this.lauta;
	}
	
	public Ruutu getRuutu(int x, int y) {
		return this.lauta[x][y];
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		return lauta[x][y].getNappula().getSiirrot(new Ruutu(x, y), this.lauta);
	}

	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		Nappula nappula = lauta[mistaX][mistaY].poistaNappula();
		lauta[mihinX][mihinY].setNappula(nappula);
		if (nappula instanceof Kuningas) {
			paivitaKuninkaanSijainti((Kuningas)nappula, new Ruutu(mihinX, mihinY));
		}
		merkitseEkaSiirto(nappula);
		return true;
	}
	
	public Ruutu getValkoinenKuningas() {
		return this.valkoinenKuningas;
	}
	
	public Ruutu getMustaKuningas() {
		return this.mustaKuningas;
	}
	
	private void paivitaKuninkaanSijainti(Kuningas kuningas, Ruutu ruutu) {
		if (kuningas.getVari() == NappulanVari.VALKOINEN) {
			this.setValkoinenKuningas(ruutu);
		} else {
			this.setMustaKuningas(ruutu);
		}
	}
	
	private void setValkoinenKuningas(Ruutu ruutu) {
		this.valkoinenKuningas = ruutu;
	}
	
	private void setMustaKuningas(Ruutu ruutu) {
		this.mustaKuningas = ruutu;
	}

	private void asetaNappulat() {
		for (int x = 0; x < 8; x++) {
			lauta[x][1].setNappula(new Sotilas(NappulanVari.VALKOINEN));
			lauta[x][6].setNappula(new Sotilas(NappulanVari.MUSTA));
		}
		
		lauta[4][0].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		this.setValkoinenKuningas(new Ruutu(4,0));
		lauta[4][7].setNappula(new Kuningas(NappulanVari.MUSTA));
		this.setMustaKuningas(new Ruutu(4,7));
		lauta[0][0].setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta[7][0].setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta[0][7].setNappula(new Torni(NappulanVari.MUSTA));
		lauta[7][7].setNappula(new Torni(NappulanVari.MUSTA));
	}

	private void luoRuudut() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				lauta[x][y] = new Ruutu(x, y);
			}
		}
	}

	private void merkitseEkaSiirto(Nappula nappula) {
		if (nappula instanceof Sotilas) {
			((Sotilas) nappula).ekaSiirtoTehty();
		}
		if (nappula instanceof Torni) {
			((Torni) nappula).ekaSiirtoTehty();
		}
		if (nappula instanceof Kuningas) {
			((Kuningas) nappula).ekaSiirtoTehty();
		}
	}

	
}
