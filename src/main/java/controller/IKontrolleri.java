package controller;

import java.util.ArrayList;

import model.Lauta;
import model.Ruutu;

public interface IKontrolleri {
	public boolean aloitaPeli();
	public Lauta getPelitilanne();
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y);
	public boolean teeSiirto();
	
}
