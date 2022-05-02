package view;

import dao.Pelaaja;
import model.NappulanTyyppi;

/**
 * Sovelluksen käyttöliittymän rajapinta
 * 
 * @author Elmo Vahvaselkä 5.2.2022
 */

public interface IPelinakyma {
	/**
	 * Metodi tarkistaa aiheuttiko tehty siirto shakin
	 */
	public void siirtoAiheuttiShakin();

	/**
	 * Metodi jossa ilmoitetaan pelin voittaja
	 * @param pelaaja Pelaaja olio joka voitti kyseisen pelin
	 */
	public void pelinVoitti(Pelaaja pelaaja);

	/**
	 * Metodi korottaa sotilas nappulan käyttäjän valitsemaan nappulaan.
	 * @return NappulanTyyppi enumin joka kertoo nappulan tyypin
	 */
	public NappulanTyyppi korota();

	/**
	 * Metodi palauttaa Pelaaja olion joka pelasi valkoisilla nappuloilla
	 * @return Pelaaja olio joka pelasi valkoisilla nappuloilla
	 */
	public Pelaaja getValkoinenPelaaja();

	/**
	 * Metodi palauttaa Pelaaja olion joka pelasi mustilla nappuloilla
	 * @return Pelaaja olio joka pelasi mustilla nappuloilla
	 */
	public Pelaaja getMustaPelaaja();
}
