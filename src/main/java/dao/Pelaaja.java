package dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Luokka joka kuvaa pelissä olevaa pelaajaa.
 * Luokkaa käytetään myös pelaajien tallentamiseen 
 * ja hakemiseen tietokannasta.
 * @author Oliver Hamberg
 * @author Elmo vahvaselkä
 *
 */

@Entity
@Table
public class Pelaaja {
	
	/**
	 * Pelaajan yksilöivä id integerinä.
	 */
	@Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pelaajaId;

	/**
	 * Pelaajan kayttäjätunnus String oliona.
	 */
	@Column private String kayttajaTunnus;
	
	/**
	 * Pelaajan voittojen määrä integerinä.
	 */
	@Transient private int voitot;
	
	/**
	 * Lista, johon talletetaan ne PelinTiedot oliot,
	 * joihin pelaaja on osallistunut.
	 */
	@Transient private List<PelinTiedot> pelit;
	
	
	/**
	 * Parameteritön konstruktori.
	 */
	public Pelaaja() {}
	
	/**
	 * Konstruktori, jota käytetään uuden pelaajan luomiseen.
	 * @param kayttajaTunnus Pelaajan nimimerkki String oliona.
	 */
	public Pelaaja(String kayttajaTunnus) {	
		this.kayttajaTunnus = kayttajaTunnus;
	}
	
	
	/**
	 * Getteri pelaajan id:lle.
	 * @return Pelaajan id integerinä.
	 */
	public int getPelaajaId() {
		return pelaajaId;
	}

	/**
	 * Getteri pelaajan käyttäjätunnukselle.
	 * @return Pelaajan käyttäjätunnus String oliona.
	 */
	public String getKayttajaTunnus() {
		return kayttajaTunnus;
	}
	
	/**
	 * Metodi jolla asetetaan pelaajan pelaamat pelit.
	 * Laskee samalla myös pelaajan voitot.
	 * @param Palayttaa Lista pelaajan pelaamista peleistä PelinTiedot olioina.
	 */
	public void setPelit(List<PelinTiedot> pelit){
		this.pelit = pelit;
		voitot = 0;
		for(PelinTiedot peli: this.pelit) {
			if(peli.getVoittaja().getPelaajaId() == pelaajaId) {
				voitot++;
			}
		}
	}
	
	/**
	 * Palauttaa pelaajan voitto prosentin.
	 * Mikäli pelaaja ei ole pelannut yhtään peliä,
	 * palauttaa nollan.
	 * @return Palauttaa pelaajan voittoprosentti doublena.
	 */
	public double getVoittoprosentti() {
		if (getPeleja() == 0) {
			return 0;
		}
		return (double) getVoitot() / (double) getPeleja() * 100;
	}
	
	/**
	 * Getteri pelaajan voittomäärälle.
	 * @return Palauttaa pelaajan voittojen määrä integerinä.
	 */
	public int getVoitot() {
		return voitot;
	}
	
	/**
	 * Getteri pelaajan pelaamien pelejen määrälle.
	 * @return Palauttaa pelaajan pelaamien pelien määrä integerinä.
	 */
	public int getPeleja() {
		return pelit.size();
	}
	
	/**
	 * Getteri pelaajan pelaamien pelien tiedoille.
	 * @return Palauttaa Listin, johon asetettu ne PelinTiedot oliot, joihin pelaaja on osallistunut.
	 */
	public List<PelinTiedot> getPelit(){
		return pelit;
	}
	
	/**
	 * Getteri pelaaja id:elle.
	 * @return palauttaa pelaajan id:en integerinä.
	 */
	public int getId() {
		return pelaajaId;
	}
	
}
