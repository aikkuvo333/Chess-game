package dao;

import model.NappulanTyyppi;

/**
* @author Elmo Vahvaselkä 1.3.2022
*/

public class Siirto {
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	private NappulanTyyppi korotus;
	
	public Siirto(int mistaX, int mistaY, int mihinX, int mihinY) {
		this.mistaX = mistaX;
		this.mihinY = mistaY;
		this.mihinX = mihinX;
		this.mihinY = mihinY;
	}
	
	public void setKorotus(NappulanTyyppi tyyppi) {
		this.korotus = tyyppi;
	}

	public int getMistaX() {
		return mistaX;
	}

	public int getMistaY() {
		return mistaY;
	}

	public int getMihinX() {
		return mihinX;
	}

	public int getMihinY() {
		return mihinY;
	}
	
	public NappulanTyyppi getKorotus() {
		return korotus;
	}
	
}
