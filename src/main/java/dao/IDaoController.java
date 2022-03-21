package dao;

/**
 * @author Oliver Hamberg 14.3.2022
 */

import java.util.List;

public interface IDaoController {
	public List<Pelaaja>getPelaajat();
	public void tallennaPeli(PelinTiedot pelinTiedot);
	public Pelaaja luoPelaaja(String nimi);
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p);
	public void haeKaikkipelit();
	public void poistaPelaaja(int pelaajaId);
}
