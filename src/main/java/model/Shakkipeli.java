package model;

import java.util.ArrayList;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/


import controller.IKontrolleri;

public class Shakkipeli implements IShakkipeli{
	private IKontrolleri kontrolleri;
	private Lauta lauta;
	private NappulanVari vuorossa;
	
	public Shakkipeli(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		this.lauta = new Lauta();
		this.vuorossa = NappulanVari.VALKOINEN;
	}
	
	public Ruutu[][] getPelitilanne(){
		return this.lauta.getLauta();
	}
	
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		if(this.lauta.getLauta()[mistaX][mistaY].getNappula().getVari() == this.vuorossa) {
			this.lauta.siirra(mistaX, mistaY, mihinX, mihinY);
			this.vaihdaVuoro();
			return true;
		}
		return false;
	}
	
	public ArrayList<Ruutu> getSiirrot(int x, int y){
		return this.lauta.getSiirrot(x, y);
	}
	
	private void vaihdaVuoro() {
		if(this.vuorossa == NappulanVari.VALKOINEN) {
			this.vuorossa = NappulanVari.MUSTA;
		}
		this.vuorossa = NappulanVari.VALKOINEN;
	}
	
}
