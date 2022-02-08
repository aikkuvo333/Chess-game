package model;

import java.util.ArrayList;

//* @author Oliver Hamberg*//


public class Lahetti extends Nappula{
	NappulanVari vari;
	int x,y;
	public Lahetti(NappulanVari vari) {
		this.vari = vari;
	}
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		//Kun lähetti siirtyy ylös vasemmalle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y < 7) {
			//Jos ruudussa on jo jotain
			if(lauta[x-1][y+1].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x-1][y + 1].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x-1, y+1));
				}
				break;
			}
			siirrot.add(new Ruutu(x-1, y+1));
			x--;
			y++;
		}
		
		//Kun lähetti siirtyy alas oikealle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y > 0) {
			//Jos ruudussa on jo jotain
			if(lauta[x+1][y-1].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x+1][y-1].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x+1, y-1));
				}
				break;
			}
			siirrot.add(new Ruutu(x+1, y-1));
			x++;
			y--;
		}
		
		//Kun lähetti siirtyy alas vasemmalle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y > 0) {
			//Jos ruudussa on jo jotain
			if(lauta[x-1][y-1].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x-1][y-1].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x-1, y-1));
				}
				break;
			}
			siirrot.add(new Ruutu(x-1, y-1));
			x--;
			y--;
		}
		
		//Kun lähetti siirtyy ylös oikealle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y < 7) {
			//Jos ruudussa on jo jotain
			if(lauta[x+1][y+1].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x+1][y+1].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x+1, y+1));
				}
				break;
			}
			siirrot.add(new Ruutu(x+1, y+1));
			x++;
			y++;
		}
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari; 
	}


}
