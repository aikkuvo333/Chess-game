package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PelinTiedot on luokkaan tallennetaan tiedot pelin kulusta.
 * Luokkaa käytetään myös apuna tietojen tallettamiseen ja 
 * lukemiseen tietokannasta.
 * @author Oliver Hamberg
 * @author Elmo Vahvaselkä
 *
 */
@Entity
@Table
public class PelinTiedot {
	/**
	 * Pelin tiedot identifioiva luku.
	 */
	@Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int peliId;
	
	/**
	 * Valkoinen pelaaja Pelaaja oliona.
	 */
	@ManyToOne private Pelaaja valkoinenPelaaja;
	
	/**
	 * Musta pelaaja Pelaaja oliona.
	 */
	@ManyToOne private Pelaaja mustaPelaaja;
	
	/**
	 * Pelin voittaja Pelaaja oliona.
	 */
	@ManyToOne private Pelaaja voittaja;

	/**
	 * Päivämäärä jolloin peli on pelattu Date oliona.
	 */
	@Column @Temporal(TemporalType.DATE) private Date pvm;
	
	/**
	 * Pelin kesto sekuntteina.
	 */
	@Column private long kesto;

	/**
	 * Lista olio, johon kirjataan pelissä tehdyt siirrot Siirto olioina.
	 */
	@OneToMany(cascade = CascadeType.ALL) private List<Siirto> siirrot;

	/**
	 * Parametriton konstruktori.
	 */
	public PelinTiedot() {
	}

	/**
	 * Konstruktori, jota Shakkipeli luokka kutsuu, kun peli aloitetaan.
	 * Kirjaa talteen myös alkamisajan luonnin yhteydessä.
	 * @param valkoinenPelaaja Valkoinen pelaaja Pelaaja oliona.
	 * @param mustaPelaaja Musta pelaaja Pelaaja oliona.
	 */
	public PelinTiedot(Pelaaja valkoinenPelaaja, Pelaaja mustaPelaaja) {
		this.valkoinenPelaaja = valkoinenPelaaja;
		this.mustaPelaaja = mustaPelaaja;
		this.siirrot = new ArrayList<Siirto>();
		pvm = new Date();
	}

	/**
	 * Tallettaa pelissä tehdyn siirron.
	 * @param siirto tehty siirto Siirto oliona.
	 */
	public void lisaaSiirto(Siirto siirto) {
		this.siirrot.add(siirto);
	}

	/**
	 * Palauttaa pelin voittajan.
	 * @return Palauttaa pelin voittajan Pelaaja oliona.
	 */
	public Pelaaja getVoittaja() {
		return voittaja;
	}

	/**
	 * Metodia kutsutaan, kun halutaan asettaa pelin
	 * voittaja pelin päätteenksi. Laskee myös peliin 
	 * kuluneen ajan sekuntteina.
	 * @param voittaja Pelin voittaja Pelaaja oliona.
	 */
	public void setVoittaja(Pelaaja voittaja) {
		// lasketaan peliin kulunut aika sekunteina
		kesto = ((new Date().getTime()) - pvm.getTime()) / (1000);
		this.voittaja = voittaja;
	}

	/**
	 * Getteri valkoiselle pelaajalle.
	 * @return Palauttaa valkoisen pelaajan Pelaaja oliona.
	 */
	public Pelaaja getValkoinenPelaaja() {
		return valkoinenPelaaja;
	}

	/**
	 * Getteri mustalle pelaajalle.
	 * @return Palauttaa mustan pelaajan Pelaaja oliona.
	 */
	public Pelaaja getMustaPelaaja() {
		return mustaPelaaja;
	}

	/**
	 * Palauttaa pelissä tehdyt siirrot.
	 * @return Pelissä tehdyt siirrot Siirto olioina Listissä.
	 */
	public List<Siirto> getSiirrot() {
		return this.siirrot;
	}

	/**
	 * Palautta pelin päivämäärän.
	 * @return Päivämäärä Date oliona.
	 */
	public Date getPvm() {
		return pvm;
	}

	/**
	 * Palautaaa pelin pituuden sekuntteina.
	 * @return Pelin kesto sekuntteina long integerinä.
	 */
	public long getKesto() {
		return kesto;
	}
	
	
	/**
	 * Getteri pelin id:lle.
	 * @return palauttaa pelin id:en integerinä.
	 */
	public int getId() {
		return peliId;
	}

}
