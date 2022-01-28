package model;

import java.util.ArrayList;

/**
 * @author Elmo Vahvaselkä 26.1.2022
 */

public class Lauta {
	
	private Ruutu[][] lauta;

	public Lauta() {
		this.lauta = new Ruutu[8][8];

		for (int y = 1; y <= 8; y++) {
			for (int x = 1; x <= 8; x++) {
				lauta[x][y] = new Ruutu(x, y);
			}
		}
	}
	
	public Ruutu[][] getLauta(){
		return this.lauta;
	}

	public ArrayList<Ruutu> getSiirrot(int x, int y){
		return lauta[x][y].getNappula().getSiirrot(new Ruutu(x,y));
	}

	public void siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		Nappula nappula = lauta[mistaX][mistaY].getNappula();
		lauta[mihinX][mihinY].setNappula(nappula);
	}
	
	public void asetaNappulat() {
		for (int x = 1; x <= 8; x++) {
			lauta[x][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
			lauta[x][7].setNappula(new Sotilas(NappulanVari.MUSTA));
		}
	}
	
	

}
