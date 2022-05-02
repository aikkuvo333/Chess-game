package dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

/**
 * Tietokannan Singleton kontrolleri luokka, jonka avulla voidaan ottaa yhteys tietokantaan
 * @author Oliver Hamberg
 * @author Elmo Vahvaselkä
 */

/* IDaoKontrolleria toteuttava Singleton luokka, joka toimii tietokannan parissa */
@SuppressWarnings("deprecation")
public class DBKontrolleri implements IDaoController {

	/* Tietokanta tapahtumien avoin "sessio" */
	private Session ses;
	
	/* Kontrollerin Singleton instance */
	private static DBKontrolleri instance;

	/* Konstruktori Singleton luokalle */
	public static DBKontrolleri getInstance() {
		if (instance == null) {
			instance = new DBKontrolleri();
		}
		return instance;
	}

	/* Singletonin luonti-funktio ja tietokanta sessionin avaaminen */
	public DBKontrolleri() {
		SessionFactory istuntoTehdas = new Configuration().configure().buildSessionFactory();
		ses = istuntoTehdas.openSession();
	}

	/* Funktio, jolla haetaan kaikki pelaajat tietokannasta */
	@SuppressWarnings("unchecked")
	public List<Pelaaja> getPelaajat() {
		List<Pelaaja> pelaajat = ses.createQuery("from Pelaaja").getResultList();
		for (Pelaaja pelaaja : pelaajat) {
			pelaaja.setPelit(haePelaajanPelit(pelaaja));
		}
		return pelaajat;
	}

	/* Funktio pelaajan luomiselle tietokantaan */
	public boolean luoPelaaja(String nimi) {
		try {
			ses.beginTransaction();
			ses.save(new Pelaaja(nimi));
			ses.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/* Funktio tietyn pelaajan pelien hakemiselle tietokannasta
	 * @param Hakua koskeva pelaaja
	 */
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p) {
		@SuppressWarnings("unchecked")
		List<PelinTiedot> tulokset = ses.createQuery("FROM PelinTiedot WHERE mustaPelaaja = " + p.getPelaajaId()
				+ " OR valkoinenPelaaja =  " + p.getPelaajaId()).getResultList();
		return tulokset;
	}
	
	/* Funktio tietyn pelaajan hakemiselle käyttäjätunnuksen perusteella
	 * @param Hakua koskevan pelaajan nimi
	 */
	public List<Pelaaja> haePelaaja(String nimi) {
		NativeQuery<Pelaaja> query = ses.createNativeQuery("select * from Pelaaja where kayttajaTunnus = ?", Pelaaja.class); 
		query.setParameter(1, nimi);  
		return query.getResultList();
	}

	/* Funktio pelitietojen tallentamiselle
	 * @param Pelintiedot olio, joka tallennetaan tietokantaan
	 */
	@Override
	public boolean tallennaPeli(PelinTiedot pelinTiedot) {
		try {
			ses.beginTransaction();
			ses.save(pelinTiedot);
			ses.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/* Funktio, jolla poistetaan tietty pelaaja tietokannasta
	 * @param Poistoa koskeva pelaaja
	 */
	@Override
	public boolean poistaPelaaja(Pelaaja pelaaja) {
		ses.beginTransaction();
		pelaaja.setKayttajaTunnus("Anonyymi");
		SQLQuery sqlQuery = ses.createSQLQuery("UPDATE Pelaaja SET kayttajaTunnus = \"Anonyymi\" WHERE kayttajaTunnus = \""+pelaaja.getKayttajaTunnus() +"\";");
		sqlQuery.executeUpdate();
		return false;
	}

	/* JUnit testifunktio */
	public boolean poistaPeli(PelinTiedot peli) {
		try {
			ses.beginTransaction();
			PelinTiedot poistettava = (PelinTiedot) ses.get(PelinTiedot.class, peli.getId());
			ses.delete(poistettava);
			ses.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/* JUnit testifunktio */
	public boolean poistaPelaajaPysyvasti(Pelaaja pelaaja) {
		try {
			ses.beginTransaction();
			Pelaaja poistettava = (Pelaaja) ses.get(Pelaaja.class, pelaaja.getPelaajaId());
			ses.delete(poistettava);
			ses.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/* Funktio, jolla haetaan tietokannan kaikki pelit */
	public List<PelinTiedot> getKaikkiPelit() {
		@SuppressWarnings("unchecked")
		List<PelinTiedot> pelit = ses.createQuery("from PelinTiedot").getResultList();
		return pelit;
	}
}
