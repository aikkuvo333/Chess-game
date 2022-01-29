package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

public class Sotilas extends Nappula{
	NappulanVari vari;
	private boolean ekaSiirto;
	
	public Sotilas(NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		if(vari == NappulanVari.VALKOINEN && ruutu.getY() +1 <= 8) {
			siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY()+1));
			if(!ekaSiirto) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY()+2));
			}
		}
		
		if(vari == NappulanVari.MUSTA && ruutu.getY() - 1 >= 1) {
			siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY()-1));
			if(!ekaSiirto) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY()-2));
			}
		}
		
		return siirrot;
	}
	
	public void ensimmaineSiirtoTehty() {
		this.ekaSiirto = true;
	}
	
	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}
}
