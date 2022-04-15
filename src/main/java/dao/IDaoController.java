package dao;

/**
 * @author Oliver Hamberg 14.3.2022, Elmo Vahvaselkä
 */

import java.util.List;

public interface IDaoController {
	public List<Pelaaja> getPelaajat();
	public void tallennaPeli(PelinTiedot pelinTiedot);
	public void luoPelaaja(String nimi);
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p);
	public void poistaPelaaja(Pelaaja pelaaja);
}
