package controller;

import java.util.ArrayList;
import dao.Pelaaja;
import model.Ruutu;
import model.NappulanTyyppi;
import model.NappulanVari;


/**
 * MVC-mallin mukainen kontrolleri rajapinta, joka mahdollistaa modelin
 * ja viewin kommunikoinnin keskenään. 
* @author Elmo Vahvaselkä 27.1.2022
*/
public interface IKontrolleri {
	/**
	 * Metodia kutsumalla shakkipeli alkaa.
	 * @param tilastoitu Booleanarvo joka ilmoittaa, että onko kyseessä tilastoitu peli. True mikäli kyseessä tilastoitu, muuten false
	 * @return palauttaa booleanin sen mukaan onnistuiko pelin aloittaminen.
	 */
	public boolean aloitaPeli(boolean tilastoitu);
	
	/**
	 * Metodi palauttaa vallitsevan pelitilanteen.
	 * @return Kaksiulotteinen taulukkon, joka koostuu Ruutu olioista.
	 */
	public Ruutu[][] getPelitilanne();
	
	/**
	 * Hakee tietyssä ruudussa olevan nappulan mahdolliset siirrot.
	 * @param x Siirrettävän nappulan x-koordinaatti.
	 * @param y Siirrettävän nappulan y-koordinaatti.
	 * @return Ruutu olioista koostuvan ArrayListin. Jokainen Ruutu edustaa mahdollista paikkaa, johon nappulan voi liikuttaa.
	 */
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y);
	
	/**
	 * Siirtää nappulaa pelilaudalla.
	 * @param mistaX Siirron lähtökoordinaatti x-akselilla, kokonaisluku väliltä 1-7.
	 * @param mistaY Siirron lähtökoordinaatti y-akselilla, kokonaisluku väliltä 1-7.
	 * @param mihinX Siirron kohdekoordinaatti x-akselilla, kokonaisluku väliltä 1-7.
	 * @param mihinY Siirron kohsekoordinaatti y-akselilla, kokonaisluku väliltä 1-7.
	 * @return palauttaa true, jos siirto onnistui, muuten false.
	 */
	public boolean teeSiirto(int mistaX, int mistaY, int mihinX, int mihinY);
	
	/**
	 * Metodin kutsutaan, jos siirto aiheutti shakin.
	 */
	public void siirtoAiheuttiShakin();
	
	/**
	 * Metodia kutsutaan, mikäli vuorossa oleva pelaaja haluaa luovuttaa pelin.
	 */
	public void luovuta();
	
	/**
	 * Metodi jolla ilmoitetaan pelin voittaja.
	 * @param pelaaja Pelin voittunt pelaaja Pelaaja oliona.
	 */
	public void pelinvoitti(Pelaaja pelaaja);
	
	/**
	 * Metodi jolla sotilas korotetaan.
	 * @return NappulanTyyppi enumina, johon sotilas halutaan korottaa.
	 */
	public NappulanTyyppi korota();
	
	/**
	 * Palauttaa vuorossa olevan pelaajan värin.
	 * @return Vuorossa olevan pelaajan väri NappulanVari enumina.
	 */
	public NappulanVari getVuoro();
	
	/**
	 * Getteri valkoiselle pelaajalle.
	 * @return Palauttaa valkoisen pelaajan Pelaaja oliona.
	 */
	public Pelaaja getValkoinenPelaaja();
	
	/**
	 * Getteri mustalle pelaajalle.
	 * @return Palauttaa mustan pelaajan Pelaaja oliona.
	 */
	public Pelaaja getMustaPelaaja();
}
