package view;

import model.NappulanVari;

/**
* @author Elmo Vahvaselkä 5.2.2022
*/

public interface IPelinakyma {
	public void siirtoAiheuttiShakin();
	public void pelinVoitti(NappulanVari voittaja);
}
