package model;

/**
 * @author Oliver Hamberg
* @author Elmo Vahvaselkä 29.1.2022
*/

import java.util.ArrayList;

/**
 * Luokka <code>Torni</code> määrittelee pelissä käytettävän tornin toiminnalisuuden.
 */
public class Torni extends NappulaEka {
	/**
	 * Luo uuden tornin.
	 * @param vari ilmaisee tornin värin.
	 */
	public Torni(NappulanVari vari) {
		this.vari = vari;
		ekaSiirto = false;
		this.tyyppi = NappulanTyyppi.TORNI;
	}

	/**
	 * Palauttaa tornin mahdolliset siirrot ruutu-olioina. 
	 * Ei huomioi mahdollisia shakkaamisen tuomia rajotteita.
	 * 
	 * @param ruutu ilmoittaa missä ruudussa liikutettava nappula sijaitsee.
	 * @param lauta välittää nappulalle tiedon koko pelitilanteesta.
	 * @return <code>ArrayList</code>-olion, johon on listattu mahdolliset siirrot <code>Ruutu</code>-olioina.
	 */
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		{

			/* Tornin kaikki mahdolliset siirrot, kun siirrytään oikealle */
			int x = ruutu.getX();
			while (x < 7) {
				x++;

				// onko ruudussa nappula
				if (lauta[x][ruutu.getY()].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
						break;
					}

					// Jos ei oma -> vihollinen
					siirrot.add(new Ruutu(x, ruutu.getY()));
					break;
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			/* Tornin kaikki mahdolliset siirrot, kun siirrytään vasemmalle */
			x = ruutu.getX();
			while (x > 0) {
				x--;

				// onko ruudussa nappula
				if (lauta[x][ruutu.getY()].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(x, ruutu.getY()));
					break;
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			/* Tornin kaikki mahdolliset siirrot, kun siirrytään ylöspäin */
			int y = ruutu.getY();
			while (y < 7) {
				y++;

				// onko ruudussa nappula
				if (lauta[ruutu.getX()][y].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(ruutu.getX(), y));
					break;
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}

			/* Tornin kaikki mahdolliset siirrot, kun siirrytään alaspäin */
			y = ruutu.getY();
			while (y > 0) {
				y--;

				// Onko ruudussa nappula
				if (lauta[ruutu.getX()][y].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(ruutu.getX(), y));
					break;
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
		}
		return siirrot;

	}
}
