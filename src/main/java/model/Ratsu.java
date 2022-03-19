package model;

import java.util.ArrayList;

/**
* @author Aivan Vo 30.1.2022
*/


/**
 * Luokka <code>Ratsu</code> määrittelee pelissä käytettävän ratsun toiminnallisuuden.
 */
public class Ratsu extends Nappula{
	/**
	 * Luo uuden ratsun.
	 * @param vari määrittelee ratsun värin.
	 */
	public Ratsu (NappulanVari vari) {
		this.vari = vari;
		this.tyyppi = NappulanTyyppi.RATSU;
	}

	/**
	 * Palauttaa ratsun mahdolliset siirrot ruutu-olioina. 
	 * Ei huomioi mahdollisia shakkaamisen tuomia rajotteita.
	 * 
	 * @param ruutu ilmoittaa missä ruudussa liikutettava nappula sijaitsee.
	 * @param lauta välittää nappulalle tiedon koko pelitilanteesta.
	 * @return <code>ArrayList</code>-olion, johon on listattu mahdolliset siirrot <code>Ruutu</code>-olioina.
	 */
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		
		/* Kun siirtyy ylös oikealle */
		if ((ruutu.getY() +2 >= 0 && ruutu.getY() +2 < 8) && (ruutu.getX() +1 >= 0 && ruutu.getX() +1 < 8)) { //jos ratsu on laudan sisällä
			
			if(lauta[ruutu.getX()+1][ruutu.getY()+2].getNappula() == null) { //tarkista onko ruudussa nappuloita, mikäli ei ole 
				siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()+2));	//..niin lisää ratsun mahdolliseksi siirtopaikaksi
			} else { //mikäli ruudussa on nappula
				if (lauta[ruutu.getX()+1][ruutu.getY()+2].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()+2));	
				}
			}
		}
		
			
		/* Kun siirtyy ylös vasemmalle */
		if ((ruutu.getY() +2 >= 0 && ruutu.getY() +2 < 8) && (ruutu.getX() -1 >= 0 && ruutu.getX() -1 < 8)) { 
			
			if(lauta[ruutu.getX()-1][ruutu.getY()+2].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()+2));
			} else { 
				if (lauta[ruutu.getX()-1][ruutu.getY()+2].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()+2));
				}
			}
		}
		
		
		/* Kun siirtyy oikealle ylös */
		if ((ruutu.getX() +2 >= 0 && ruutu.getX() +2 < 8) && (ruutu.getY() +1 >= 0 && ruutu.getY() +1 < 8) ) { 
			
			if(lauta[ruutu.getX()+2][ruutu.getY()+1].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()+1));
			} else { 
				if (lauta[ruutu.getX()+2][ruutu.getY()+1].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()+1));
				}
			}
		}
		
		/* Kun siirtyy oikealle alas */
		if ((ruutu.getX() +2 >= 0 && ruutu.getX() +2 < 8) && (ruutu.getY() -1 >= 0 && ruutu.getY() -1 < 8) ) { 
			
			
			if(lauta[ruutu.getX()+2][ruutu.getY()-1].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()-1));
			} else { 
				if (lauta[ruutu.getX()+2][ruutu.getY()-1].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()+2, ruutu.getY()-1));
				}
			}
		}
		
		
		/* Kun siirtyy alas oikelle */
		if ((ruutu.getY() -2 >= 0 && ruutu.getY() -2 < 8) && (ruutu.getX() +1 >= 0 && ruutu.getX() +1 < 8)) { 
			
			
			if(lauta[ruutu.getX()+1][ruutu.getY()-2].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()-2));
			} else { 
				if (lauta[ruutu.getX()+1][ruutu.getY()-2].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()+1, ruutu.getY()-2));
				}
			}
		}
		
		/* Kun siirtyy alas vasemmalle */
		if ((ruutu.getY() -2 >= 0 && ruutu.getY() -2 < 8) && (ruutu.getX() -1 >= 0 && ruutu.getX() -1 < 8)) { 
			
			
			if(lauta[ruutu.getX()-1][ruutu.getY()-2].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()-2));
			} else { 
				if (lauta[ruutu.getX()-1][ruutu.getY()-2].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()-1, ruutu.getY()-2));
				}
			}
		}

		
		/* Kun siirtyy vasemmalle ylös */
		if ((ruutu.getX() -2 >= 0 && ruutu.getX() -2 < 8) && (ruutu.getY() +1 >= 0 && ruutu.getY() +1 < 8) ) { 
			
			
			if(lauta[ruutu.getX()-2][ruutu.getY()+1].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()+1));
			} else { 
				if (lauta[ruutu.getX()-2][ruutu.getY()+1].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()+1));
				}
			}
		}
		
		/* Kun siirtyy vasemmalle alas */
		if ((ruutu.getX() -2 >= 0 && ruutu.getX() -2 < 8) && (ruutu.getY() -1 >= 0 && ruutu.getY() -1 < 8) ) { 
			
			
			if(lauta[ruutu.getX()-2][ruutu.getY()-1].getNappula() == null) { 
				siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()-1));
			} else { 
				if (lauta[ruutu.getX()-2][ruutu.getY()-1].getNappula().getVari() != vari) {
					siirrot.add(new Ruutu(ruutu.getX()-2, ruutu.getY()-1));
				}
			}
		}
			
		return siirrot;
	}
}