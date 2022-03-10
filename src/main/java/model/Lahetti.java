package model;

import java.util.ArrayList;

import dao.Ruutu;

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
			x--;
			y++;
			//Jos ruudussa on jo jotain
			if(lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun lähetti siirtyy alas oikealle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y > 0) {
			x++;
			y--;
			//Jos ruudussa on jo jotain
			if(lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun lähetti siirtyy alas vasemmalle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y > 0) {
			x--;
			y--;
			//Jos ruudussa on jo jotain
			if(lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun lähetti siirtyy ylös oikealle
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y < 7) {
			x++;
			y++;
			//Jos ruudussa on jo jotain
			if(lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari; 
	}
	@Override
	public NappulanTyyppi getTyyppi() {
		// TODO Auto-generated method stub
		return NappulanTyyppi.LAHETTI;
	}


}
