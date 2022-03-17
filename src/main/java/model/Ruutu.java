package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/
public class Ruutu {
	private int x;
	private int y;
	private Nappula nappula;
	
	public Ruutu(int x, int y) {
		this.x = x;
		this.y = y;
		this.nappula = null;
	}
	
	public void setNappula (Nappula nappula) {
		this.nappula = nappula;
	}
	
	public Nappula poistaNappula() {
		Nappula palautettava = this.nappula;
		this.nappula = null;
		return palautettava;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Nappula getNappula() {
		return this.nappula;
	}

}

