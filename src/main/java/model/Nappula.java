package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

/**
 * 
 *Abstrakti luokka <code>Nappula</code> toimii pohjana pelissä käytettäville nappuloille.
 *
 */
public abstract class Nappula {
	/**
	 * <code>Tyyppi</code> määrittää nappulan tyypin.
	 */
	protected NappulanTyyppi tyyppi;
	
	/**
	 * <code>Vari</code> määrittää nappulan värin.
	 */
	protected NappulanVari vari;
	
	/**
	 * 
	 * Palauttaa nappulan mahdolliset siirrot ruutu-olioina. 
	 * Ei huomioi mahdollisia shakkaamisen tuomia rajotteita.
	 * 
	 * @param ruutu ilmoittaa missä ruudussa liikutettava nappula sijaitsee.
	 * @param lauta välittää nappulalle tiedon koko pelitilanteesta.
	 * @return <code>ArrayList</code>-olion, johon on listattu mahdolliset siirrot <code>Ruutu</code>-olioina.
	 */
	public abstract ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta);
	
	/**
	 * Palauttaa nappulan värin.
	 * @return <code>NappulanVari</code>
	 */
	public NappulanVari getVari() {
		return this.vari;
	};
	
	/**
	 * Palauttaa nappulan tyypin.
	 * @return <code>NappulanTyyppi</code>
	 */
	public NappulanTyyppi getTyyppi() {
		return this.tyyppi;
	};
}
