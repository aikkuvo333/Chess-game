package controller;

/**
* @author Elmo Vahvaselkä 27.1.2022
*/


import java.util.ArrayList;

import dao.Pelaaja;
import model.Ruutu;
import model.NappulanTyyppi;
import model.NappulanVari;

public interface IKontrolleri {
	public boolean aloitaPeli(boolean tilastoitu);
	public Ruutu[][] getPelitilanne();
	public ArrayList<Ruutu> getSiirrotNappulalle(int x, int y);
	public boolean teeSiirto(int mistaX, int mistaY, int mihinX, int mihinY);
	public void siirtoAiheuttiShakin();
	public void luovuta();
	public void pelinvoitti(Pelaaja pelaaja);
	public NappulanTyyppi korota();
	public NappulanVari getVuoro();
	public Pelaaja getValkoinenPelaaja();
	public Pelaaja getMustaPelaaja();
}
