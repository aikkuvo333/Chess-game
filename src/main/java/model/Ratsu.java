package model;

import java.util.ArrayList;

/**
* @author Aivan Vo 30.1.2022
*/

public class Ratsu extends Nappula{
	NappulanVari vari;
	
	public Ratsu (NappulanVari vari) {
		this.vari = vari;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		/* Kun siirtyy ylös oikealle */
		if ((ruutu.getY() +2 >= 0 && ruutu.getY() +2 < 8) && (ruutu.getX() +1 >= 0 && ruutu.getX() +1 < 8)) { //jos ratsu pääsee 2 ruutua ylös JA oikealle niin lisätään mahdolliseksi siirtovaihtoehdoksi listaan
			siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()+2));
		}
		
		/* Kun siirtyy ylös vasemmalle */
		if ((ruutu.getY() +2 >= 0 && ruutu.getY() +2 < 8) && (ruutu.getX() -1 >= 0 && ruutu.getX() -1 < 8)) { 
			siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()+2));
		}
		
		
		/* Kun siirtyy oikealle ylös */
		if ((ruutu.getX() +2 >= 0 && ruutu.getX() +2 < 8) && (ruutu.getY() +1 >= 0 && ruutu.getY() +1 < 8) ) { 
			siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()+1));
		}
		
		/* Kun siirtyy oikealle alas */
		if ((ruutu.getX() +2 >= 0 && ruutu.getX() +2 < 8) && (ruutu.getY() -1 >= 0 && ruutu.getY() -1 < 8) ) { 
			siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()-1));
		}
		
		
		/* Kun siirtyy alas oikelle */
		if ((ruutu.getY() -2 >= 0 && ruutu.getY() -2 < 8) && (ruutu.getX() +1 >= 0 && ruutu.getX() +1 < 8)) { 
			siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()-2));
		}
		
		/* Kun siirtyy alas vasemmalle */
		if ((ruutu.getY() -2 >= 0 && ruutu.getY() -2 < 8) && (ruutu.getX() -1 >= 0 && ruutu.getX() -1 < 8)) { 
			siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()-2));
		}

		
		/* Kun siirtyy vasemmalle ylös */
		if ((ruutu.getX() -2 >= 0 && ruutu.getX() -2 < 8) && (ruutu.getY() +1 >= 0 && ruutu.getY() +1 < 8) ) { 
			siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()+1));
		}
		
		/* Kun siirtyy vasemmalle alas */
		if ((ruutu.getX() -2 >= 0 && ruutu.getX() -2 < 8) && (ruutu.getY() -1 >= 0 && ruutu.getY() -1 < 8) ) { 
			siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()-1));
		}
			
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}
	
}