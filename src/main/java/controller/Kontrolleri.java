package controller;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import java.util.ArrayList;

import dao.Pelaaja;
import model.Ruutu;
import model.IShakkipeli;
import model.NappulanTyyppi;
import model.NappulanVari;
import model.Shakkipeli;
import view.IPelinakyma;

public class Kontrolleri implements IKontrolleri{
	private IShakkipeli peli;
	private IPelinakyma pelinakyma;
	
	public Kontrolleri(IPelinakyma pelinakyma) {
		this.pelinakyma = pelinakyma;
	}
	
	@Override
	public boolean aloitaPeli(boolean tilastoitu) {
		this.peli = new Shakkipeli(this, tilastoitu);
		return true;
	}

	@Override
	public Ruutu[][] getPelitilanne() {
		return this.peli.getPelitilanne();
	}

	@Override
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y) {
		return this.peli.getSiirrot(x, y);
	}

	@Override
	public boolean teeSiirto(int mistaX, int mistaY, int mihinX, int mihinY) {
		return peli.siirra(mistaX, mistaY, mihinX, mihinY);
	}

	@Override
	public void siirtoAiheuttiShakin() {
		this.pelinakyma.siirtoAiheuttiShakin();
	}

	@Override
	public void luovuta() {
		peli.luovuta();	
	}

	@Override
	public void pelinvoitti(Pelaaja pelaaja) {
		this.pelinakyma.pelinVoitti(pelaaja);
	}

	@Override
	public NappulanTyyppi korota() {
		return this.pelinakyma.korota();
	}
	
	@Override
	public NappulanVari getVuoro() {
		return peli.getVuoro();
	}

	@Override
	public Pelaaja getValkoinenPelaaja() {
		return this.pelinakyma.getValkoinenPelaaja();
	}

	@Override
	public Pelaaja getMustaPelaaja() {
		return this.pelinakyma.getMustaPelaaja();
	}
}
