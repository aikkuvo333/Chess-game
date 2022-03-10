package model;

import java.util.ArrayList;

import dao.Ruutu;

/**
* @author Elmo Vahvaselkä 1.3.2022
*/

public interface IShakkipeli {

	public Ruutu[][] getPelitilanne();
	public ArrayList<Ruutu> getSiirrot(int x, int y);
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY);
	public boolean luovuta();
	public NappulanVari getVuoro();
}
