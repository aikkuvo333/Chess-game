package controller;

import java.util.ArrayList;

import dao.Pelaaja;
import model.Ruutu;
import model.IShakkipeli;
import model.NappulanTyyppi;
import model.NappulanVari;
import model.Shakkipeli;
import view.IPelinakyma;

/**
 * IKontroller rajapintaa toteuttaa kontrolleri, joka 
 * mahdollistaa modelin ja viewin kommunikoinnin keskenään.
 * @author Oliver Hamberg 
 * @author Elmo Vahvaselkä 27.1.2022
 */
public class Kontrolleri implements IKontrolleri{
	/**
	 * IShakkipeli rajapintaa toteuttava luokka, joka edustaa MVC-mallin modelia.
	 */
	private IShakkipeli peli; // Game logic
	
	/**
	 * IPelinakyma rajapintaa toteuttava luokka, joka edustaa MVC-mallin viewiä.
	 */
 	private IPelinakyma pelinakyma; // View controller
	
 	/**
 	 * Konstruktori Kontrolleri luokalle.
 	 * @param IPelinakyma rajapintaa toteuttava luokka.
 	 */
	public Kontrolleri(IPelinakyma pelinakyma) {
		this.pelinakyma = pelinakyma;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean aloitaPeli (boolean tilastoitu) {
		this.peli = new Shakkipeli(this, tilastoitu);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Ruutu[][] getPelitilanne() {
		return this.peli.getPelitilanne();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y) {
		return this.peli.getSiirrot(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean teeSiirto(int mistaX, int mistaY, int mihinX, int mihinY) {
		return peli.siirra(mistaX, mistaY, mihinX, mihinY);
	}

	@Override
	public void siirtoAiheuttiShakin() {
		this.pelinakyma.siirtoAiheuttiShakin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void luovuta() {
		peli.julistaVoittaja();	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pelinvoitti(Pelaaja pelaaja) {
		this.pelinakyma.pelinVoitti(pelaaja);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NappulanTyyppi korota() {
		return this.pelinakyma.korota();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public NappulanVari getVuoro() {
		return peli.getVuoro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pelaaja getValkoinenPelaaja() {
		return this.pelinakyma.getValkoinenPelaaja();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pelaaja getMustaPelaaja() {
		return this.pelinakyma.getMustaPelaaja();
	}

}
