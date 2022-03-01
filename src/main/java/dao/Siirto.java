package dao;

public class Siirto {
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	
	public Siirto(int mistaX, int mistaY, int mihinX, int mihinY) {
		this.mistaX = mistaX;
		this.mihinY = mistaY;
		this.mihinX = mihinX;
		this.mihinY = mihinY;
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
	
}
