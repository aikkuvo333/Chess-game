package controller;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/


import java.util.ArrayList;

import model.NappulanTyyppi;
import model.NappulanVari;
import model.Ruutu;

public interface IKontrolleri {
	public boolean aloitaPeli(boolean tilastoitu);
	public Ruutu[][] getPelitilanne();
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y);
	public boolean teeSiirto(int mistaX, int mistaY, int mihinX, int mihinY);
	public void siirtoAiheuttiShakin();
	public void luovuta();
	public void pelinvoitti(NappulanVari voittaja);
	public NappulanTyyppi korota();
	public NappulanVari getVuoro();
	public String getValkoinenPelaaja();
	public String getMustaPelaaja();
}
