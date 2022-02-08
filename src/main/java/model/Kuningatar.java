package model;

import java.util.ArrayList;
/* @author Oliver Hamberg */

public class Kuningatar extends Nappula{
	NappulanVari vari;
	int x,y;
	public Kuningatar(NappulanVari vari) {
		this.vari = vari;
	}
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		//Kun Kuningatar siirtyy ylös vasemmalle
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
		
		//Kun Kuningatar siirtyy alas oikealle
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
		
		//Kun Kuningatar siirtyy alas vasemmalle
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
		
		//Kun Kuningatar siirtyy ylös oikealle
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
		
		//Kun Kuningatar liikkuu vasemmalle
		x = ruutu.getX();
		y = ruutu.getY();
		while (y > 0) {
			y--;
			//Jos ruudussa on jo jotain
			if (lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun Kuningatar liikkuu oikealle
		x = ruutu.getX();
		y = ruutu.getY();
		while (x < 7) {
			x++;
			//Jos ruudussa on jo jotain
			if (lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun Kuningatar liikkuu ylöspäin
		x = ruutu.getX();
		y = ruutu.getY();
		while (y < 7) {
			y++;
			//Jos ruudussa on jo jotain
			if (lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		//Kun Kuningatar liikkuu alaspäin
		x = ruutu.getX();
		y = ruutu.getY();
		while (y > 0) {
			y--;
			//Jos ruudussa on jo jotain
			if (lauta[x][y].getNappula() != null) {
				//Jos ruudussa on vastustaja
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
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
}