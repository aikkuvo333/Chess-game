package model;

import java.util.ArrayList;
/* 
 * Kuningatar nappulan toimivuus shakkilaudalla
 * @author Oliver Hamberg 
 *
 */


public class Kuningatar extends Nappula{
	int x,y;
	
	/* Konstruktori Kuningatar nappulalle
	 * @param Nappulan väri
	 */
	public Kuningatar(NappulanVari vari) {
		this.vari = vari;
		this.tyyppi = NappulanTyyppi.KUNINGATAR;
	}
	
	/* Palauttaa kuningattaren kaikki mahdolliset sirrot shakkilaudalla
	 * Toimii lähes samalla tavalla kuin lähetti + torni nappulat yhdistettynä.
	 * @param ruutu sisältää nappulan sijainnin.
	 * @param lauta sisältää tiedon shakkilaudan tilanteesta.
	 */
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		/* Kuningattaren mahdolliset siirrot liikuessa viistoon ylös vasemmalle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y < 7) {
			x--;
			y++;
			/* Jos ruudussa on jo jokin nappula */
			if(lauta[x][y].getNappula() != null) {
				/* Jos edessä oleva nappula on eri värinen (vastustaja) kuin tämän Kuningatar-nappulan väri
				 * voimme edetä ja syödä eri värisen nappulan.
				 */
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa viistoon alas oikealle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y > 0) {
			x++;
			y--;
			if(lauta[x][y].getNappula() != null) {
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa viistoon alas vasemmalle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y > 0) {
			x--;
			y--;
			if(lauta[x][y].getNappula() != null) {
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa viistoon ylös oikealle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while(x < 7 && y < 7) {
			x++;
			y++;
			if(lauta[x][y].getNappula() != null) {
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa vasemmalle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while (x > 0) {
			x--;
			if (lauta[x][y].getNappula() != null) {
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa oikealle shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while (x < 7) {
			x++;
			if (lauta[x][y].getNappula() != null) {
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa ylöspäin shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while (y < 7) {
			y++;
			if (lauta[x][y].getNappula() != null) {
				if (lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
					break;
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Kuningattaren mahdolliset siirrot liikuessa alaspäin shakkilaudalla */
		x = ruutu.getX();
		y = ruutu.getY();
		while (y > 0) {
			y--;
			if (lauta[x][y].getNappula() != null) {
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
}
