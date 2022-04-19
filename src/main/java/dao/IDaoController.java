package dao;

/**
 * @author Oliver Hamberg 14.3.2022, Elmo Vahvaselkä
 */

import java.util.List;

public interface IDaoController {
	public List<Pelaaja> getPelaajat();
	public boolean tallennaPeli(PelinTiedot pelinTiedot);
	public boolean luoPelaaja(String nimi);
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p);
	public boolean poistaPelaaja(Pelaaja pelaaja);
}
