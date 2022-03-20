package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

/**
 * Luokka <code>Ruutu</code> edustaa yhtä ruutua Shakkilaudalla.
 */
public class Ruutu {
	/**
	 * Kokonaisluku <code>x</code> ilmaisee ruudun x-koordinaatin shakkilaudalla.
	 * Arvon oltava luku väliltä 0-7.
	 */
	private final int x;
	
	/**
	 * Kokonaisluku <code>y</code> ilmaisee ruudun y-koordinaatin shakkilaudalla.
	 * Arvon oltava luku väliltä 0-7.
	 */
	private final int y;
	
	/**
	 * Muuttujaan <code>nappula</code> tallennetaan tieto ruudussa mahdollisesti olevasta nappulasta.
	 */
	private Nappula nappula;
	
	/**
	 * Luo uuden ruudun
	 * @param x ruudun x-koordinaatti kokonaislukuna väliltä 0-7.
	 * @param y ruudun y-koordinaatti kokonaislukuna väliltä 0-7.
	 */
	public Ruutu(int x, int y) {
		this.x = x;
		this.y = y;
		this.nappula = null;
	}
	
	/**
	 * Asettaa nappulan ruutuun.
	 * @param nappula rutuun asetettava nappula.
	 */
	public void setNappula (Nappula nappula) {
		this.nappula = nappula;
	}
	
	/**
	 * Poistaa ruudusta nappulan ja palauttaa sen.
	 * @return ruudusta poistettava nappula.
	 */
	public Nappula poistaNappula() {
		Nappula palautettava = this.nappula;
		this.nappula = null;
		return palautettava;
	}
	
	/**
	 * Palauttaa ruudun x-koordinaatin.
	 * @return ruudun x-koordinaatti kokonaislukuna.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Palauttaa ruudun y-koordinaatin.
	 * @return ruudun y-koordinaatti kokonaislukuna.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Palauttaa ruudussa olevan nappulan. Jos ruudussa ei ole nappulaa palauttaa null.
	 * @return Ruudussa olevan nappulan tai null.
	 */
	public Nappula getNappula() {
		return this.nappula;
	}

}

