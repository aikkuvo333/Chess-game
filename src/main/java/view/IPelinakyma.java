package view;

import dao.Pelaaja;
import model.NappulanTyyppi;

/**
* @author Elmo Vahvaselkä 5.2.2022
*/

public interface IPelinakyma {
	public void siirtoAiheuttiShakin();
	public void pelinVoitti(Pelaaja pelaaja);
	public NappulanTyyppi korota();
	public Pelaaja getValkoinenPelaaja();
	public Pelaaja getMustaPelaaja();
}
