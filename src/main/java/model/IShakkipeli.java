package model;

import java.util.ArrayList;

/**
* @author Elmo Vahvaselkä, Oliver Hamberg 1.3.2022
*/

public interface IShakkipeli {

	public Ruutu[][] getPelitilanne();
	public ArrayList<Ruutu> getSiirrot(int x, int y);
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY);
	public void julistaVoittaja();
	public NappulanVari getVuoro();
}
