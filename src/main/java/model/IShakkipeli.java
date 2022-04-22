package model;

import java.util.ArrayList;

/**
 * Rajapinta Shakkipelin toiminnallisuudella.
* @author Elmo Vahvaselkä 
* @author Oliver Hamberg 1.3.2022
*/

public interface IShakkipeli {

	/**
	 * Käytetään hakemaan shakkipelin vallitseva pelitilanne.
	 * @return Kaksiulotteisen taulukon pelin ruuduista, jonka molemmat indeksit ovat väliltä 1-7.
	 */
	public Ruutu[][] getPelitilanne();
	
	/**
	 * Käytetään hakemaan tietyssä kordinaatissa olevan nappulan mahdolliset siirrot.
	 * @param x Pelinappulan koordinaatti x-akselilla, jonka arvo on väliltä 0-7.
	 * @param y Pelinappulan koordinaatti y-akselilla, jonka arvo on väliltä 0-7.
	 * @return Palauttaa ArrayListin, joka sisältää mahdolliset siirrot Ruutu-olioina;
	 */
	public ArrayList<Ruutu> getSiirrot(int x, int y);
	
	/**
	 * Käytetään siiroon tekemiseen shakkipelissä.
	 * @param mistaX Siirrettävän pelinappulan lähtökoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mistaY Siirrettävän pelinappulan lähtökoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinX Siirrettävän pelinappulan kohdekoordinaatti x-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @param mihinY Siirrettävän pelinappulan kohdekoordinaatti y-akselilla, jonka arvon on kokonaisluku väliltä 0-7.
	 * @return Palauttaa booleanarvon true, mikäli siirto onnistui ja false, mikäli siirto ei onnistunut.
	 */
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY);
	
	/**
	 * Metodin kutsuminen päättää pelin ja julistaa voittajan.
	 */
	public void julistaVoittaja();
	
	/**
	 * Palauttaa vuorossa olevan nappulan värin.
	 * @return Palauttaa NappulanVari enumin, joka voi olla olla joko VALKOINEN tai MUSTA;
	 */
	public NappulanVari getVuoro();
}
