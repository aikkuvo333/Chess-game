package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

public abstract class Nappula {
	protected NappulanTyyppi tyyppi;
	protected NappulanVari vari;
	
	public abstract ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta);
	public NappulanVari getVari() {
		return this.vari;
	};
	public NappulanTyyppi getTyyppi() {
		return this.tyyppi;
	};
}
