package model;

import java.util.ArrayList;

/* 
 * Lähetti-nappulan toiminnallisuus shakkilaudalla
 * @author Oliver Hamberg
 * 
 */


public class Lahetti extends Nappula{
	int x,y;
	/* Lähetin konstruktori
	 * @param Nappulan väri
	 */
	public Lahetti(NappulanVari vari) {
		this.vari = vari;
		this.tyyppi = NappulanTyyppi.LAHETTI;
	}
	/* Palauttaa lähetin kaikki mahdolliset siirrot shakkilaudalla 
	 * @param ruutu sisältää nappulan sijainnin.
	 * @param lauta sisältää tiedon shakkilaudan tilanteesta.
	 */
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		/* Lähetin kaikki mahdolliset siirrot, kun siirrytään viistoon ylös vasemmalle */
		x = ruutu.getX();
		y = ruutu.getY();
		while(x > 0 && y < 7) {
			x--;
			y++;
			/* Jos ruudussa on jo jokin nappula */
			if(lauta[x][y].getNappula() != null) {
				/* Jos edessä oleva nappula on eri värinen (vastustaja) kuin tämän Lähetti-nappulan väri
				 * voimme edetä ja syödä eri värisen nappulan.
				 */
				if(lauta[x][y].getNappula().getVari() != this.vari) {
					siirrot.add(new Ruutu(x, y));
				}
				break;
			}
			siirrot.add(new Ruutu(x, y));
		}
		
		/* Lähetin kaikki mahdolliset siirrot, kun siirrytään viistoon alas oikealle */
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
		
		/* Lähetin kaikki mahdolliset siirrot, kun siirrytään viistoon alas vasemmalle */
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
		
		/* Lähetin kaikki mahdolliset siirrot, kun siirrytään viistoon ylös oikealle */
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
		return siirrot;
	}
}
