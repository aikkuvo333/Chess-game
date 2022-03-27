package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

/**
 * Luokka <code>Sotilas</code> määrittelee shakissa kätettävän sotilaan tominnallisuuden.
 *
 */

public class Sotilas extends NappulaEka {
	/**
	 * Luo uuden sotilaan.
	 * @param vari ilmaisee sotilaan värin.
	 */
	public Sotilas(NappulanVari vari) {
		this.vari = vari;
		ekaSiirto = false;
		this.tyyppi = NappulanTyyppi.SOTILAS;
	}

	/**
	 * 
	 * Palauttaa sotilaan mahdolliset siirrot ruutu-olioina. 
	 * Ei huomioi mahdollisia shakkaamisen tuomia rajotteita.
	 * 
	 * @param ruutu ilmoittaa missä ruudussa liikutettava nappula sijaitsee.
	 * @param lauta välittää nappulalle tiedon koko pelitilanteesta.
	 * @return <code>ArrayList</code>-olion, johon on listattu mahdolliset siirrot <code>Ruutu</code>-olioina.
	 */
	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();

		if (vari == NappulanVari.VALKOINEN && ruutu.getY() < 7) {

			//Onko sotilaan edessä olevassa ruudussa tilaa
			if (lauta[ruutu.getX()][ruutu.getY() + 1].getNappula() == null) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() + 1));

				// Sotilaan ensimmäinen siirto voi olla kaksi ruutua. Onko siinä ruudussa tilaa.
				if (!ekaSiirto && lauta[ruutu.getX()][ruutu.getY() + 2].getNappula() == null) {
					siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() + 2));
				}
			}

			// Syötävä nappula etuoikealla, sotilaan menosuuntaan
			if (ruutu.getX() < 7) {
				if (lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula().getVari() == NappulanVari.MUSTA) {
					siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() + 1));
				}
			}
			
			// Syötävä nappula etuvasemmalla, sotilaan menosuuntaan
			if (ruutu.getX() > 0) {
				if (lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula().getVari() == NappulanVari.MUSTA) {
					siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() + 1));
				}
			}
		}

		if (vari == NappulanVari.MUSTA && ruutu.getY() > 0 ) {
			
			//Onko sotilaan edessä tilaa
			if (lauta[ruutu.getX()][ruutu.getY() - 1].getNappula() == null) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() - 1));

				// Sotilaan ensimmäinen ruutu voi olla kaksi siirtoa. Onko siinä ruudussa tilaa.
				if (!ekaSiirto && lauta[ruutu.getX()][ruutu.getY() - 2].getNappula() == null) {
					siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() - 2));
				}
			}

			// Syötävä nappula etuoikealla, sotilaan menosuuntaan
			if (ruutu.getX() > 0) {
				if (lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula().getVari() == NappulanVari.VALKOINEN) {
					siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() - 1));
				}
			}

			// Syötävä nappula etuvasemmalla, sotilaan menosuuntaan
			if (ruutu.getX() < 7) {
				if (lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula().getVari() == NappulanVari.VALKOINEN) {
					siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() - 1));
				}
			}
		}
		return siirrot;
	}
}
