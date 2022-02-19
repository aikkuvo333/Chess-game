package controller;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/

import java.util.ArrayList;

import model.NappulanVari;
import model.Ruutu;
import model.Shakkipeli;
import view.IPelinakyma;

public class Kontrolleri implements IKontrolleri{
	//private IView
	private Shakkipeli peli;
	private IPelinakyma pelinakyma;
	
	public Kontrolleri(IPelinakyma pelinakyma) {
		this.pelinakyma = pelinakyma;
	}
	
	@Override
	public boolean aloitaPeli() {
		this.peli = new Shakkipeli(this);
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
	public void pelinvoitti(NappulanVari voittaja) {
		this.pelinakyma.pelinVoitti(voittaja);
	}

}
