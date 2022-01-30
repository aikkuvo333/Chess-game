package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import java.util.ArrayList;

public class Torni extends Nappula{
	
	private NappulanVari vari;
	private boolean ekaSiirto;

	public Torni (NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
	}
	
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu) {
		ArrayList<Ruutu> siirrot = new ArrayList<>(); {
			
			for (int x = ruutu.getX(); x < 7; x++) {
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}
			
			for (int x = ruutu.getX(); x > 8; x--) {
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}
			
			for (int y = ruutu.getY(); y < 7; y++) {
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
			
			for (int y = ruutu.getY(); y > 0; y--) {
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
			
		}
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}
	
	public void ensimmaineSiirtoTehty() {
		this.ekaSiirto = true;
	}
	
	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}

}
