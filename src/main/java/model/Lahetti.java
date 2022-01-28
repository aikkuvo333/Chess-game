package model;

import java.util.ArrayList;

//* @author Oliver Hamberg 28/01/22 *//


public class Lahetti extends Nappula{
	NappulanVari vari;
	public Lahetti(NappulanVari vari) {
		this.vari = vari;
	}
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		//Kun lähetti siirtyy ylös vasemmalle
		while(ruutu.getX() > 0 && ruutu.getY() > 0) {
			siirrot.add(new Ruutu(ruutu.getX()- 1, ruutu.getY()-1));
		}
		
		//Kun lähetti siirtyy alas oikealle
		while(ruutu.getX() <= 8 && ruutu.getY() <= 8) {
			siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()+1));
		}
		
		//Kun lähetti siirtyy alas vasemmalle
		while(ruutu.getY() <= 8 && ruutu.getX() > 0) {
			siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()+1));
		}
		
		//Kun lähetti siirtyy ylös oikealle
		while(ruutu.getY() > 0 && ruutu.getX() <= 8) {
			siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()-1));
		}
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari; 
	}

}
