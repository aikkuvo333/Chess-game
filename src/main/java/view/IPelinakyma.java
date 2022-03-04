package view;

import model.NappulanTyyppi;
import model.NappulanVari;

/**
* @author Elmo Vahvaselkä 5.2.2022
*/

public interface IPelinakyma {
	public void siirtoAiheuttiShakin();
	public void pelinVoitti(NappulanVari voittaja);
	public NappulanTyyppi korota();
	public String getValkoinenPelaaja();
	public String getMustaPelaaja();
}
