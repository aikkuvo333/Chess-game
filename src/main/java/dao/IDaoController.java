package dao;

import java.util.List;

/**
 * Rajapinta, jonka avulla toteutetaan tietokantaan tellentaminen.
 * @author Oliver Hamberg 14.3.2022, 
 * @author Elmo Vahvaselkä
 */
public interface IDaoController {
	/**
	 * Hakee kaikki pelaajat tietokannasta.
	 * @return List-olio, joka koostuu Pelaaja-olioista.
	 */
	public List<Pelaaja> getPelaajat();
	
	/**
	 * Tallentaa pelatun pelin tietokantaan.
	 * @param pelinTiedot olion, joka sisältää tiedot pelatusta pelistä.
	 * @return palautaa true, jos tallentaminen onnistui, muuten false;
	 */
	public boolean tallennaPeli(PelinTiedot pelinTiedot);
	
	/**
	 * Luo uuden pelaajan tietokantaan.
	 * @param Pelaajan nimi String-oliona.
	 * @return palauttaa true, jos pelaajan luonti onnistui, muuten false;
	 */
	public boolean luoPelaaja(String nimi);
	
	/**
	 * Hakee yhden pelaajan kaikki pelaamat pelit.
	 * @param Pelaaja-olio pelaajasta, jonka pelit halutaan hakea.
	 * @return List-olion, johon talletettu PelinTiedot-olioita.
	 */
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p);
	
	/**
	 * Pseudopoistaa pelaajan tietokannasta. Pelaajan nimi muutetaan
	 * muotoon "deleted" ja pelit pysyvät edelleen tietokannassa. 
	 * Tämä siksi, että muiden pelaajien voitot ja voittoprosentit 
	 * eivät menisi sekaisin.
	 * @param Pelaaja-oliona se pelaaja, joka halutaan poistaa.
	 * @return palauttaa true, jos pelaajan poistaminen onnistui, muuten false.
	 */
	public boolean poistaPelaaja(Pelaaja pelaaja);
}
