package dao;

/**
 * @author Elmo Vahvaselkä, Oliver Hamberg 14.3.2022
 */

import java.util.List;

public interface IDaoController {
	public List<Pelaaja>getPelaajat();
	public void tallennaPeli(PelinTiedot pelinTiedot);
	public void luoPelaaja(String nimi);
	public void haePelaajanPelit(int pelaajaID);
	public void haeKaikkipelit();
}
