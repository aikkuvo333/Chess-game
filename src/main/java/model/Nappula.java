package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

public abstract class Nappula {
	
	public abstract ArrayList<Ruutu> getSiirrot(Ruutu Ruutu, Ruutu[][] lauta);
	public abstract NappulanVari getVari();
}
