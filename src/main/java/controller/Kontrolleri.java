package controller;

import java.util.ArrayList;

import model.Ruutu;
import model.Shakkipeli;

public class Kontrolleri implements IKontrolleri{
	//private IView
	private Shakkipeli peli;
	
	@Override
	public boolean aloitaPeli() {
		this.peli = new Shakkipeli(this);
		return false;
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

}
