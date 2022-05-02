package dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 * 
 * @author Oliver Hamberg
 * @author Elmo Vahvaselkä
 */

public class DBKontrolleri implements IDaoController {
	private Session ses;
	private static DBKontrolleri instance;

	public static DBKontrolleri getInstance() {
		if (instance == null) {
			instance = new DBKontrolleri();
		}
		return instance;
	}

	public DBKontrolleri() {
		SessionFactory istuntoTehdas = new Configuration().configure().buildSessionFactory();
		ses = istuntoTehdas.openSession();
	}

	@SuppressWarnings("unchecked")
	public List<Pelaaja> getPelaajat() {
		List<Pelaaja> pelaajat = ses.createQuery("from Pelaaja").getResultList();
		for (Pelaaja pelaaja : pelaajat) {
			pelaaja.setPelit(haePelaajanPelit(pelaaja));
		}
		return pelaajat;
	}

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

	public List<PelinTiedot> haePelaajanPelit(Pelaaja p) {
		@SuppressWarnings("unchecked")
		List<PelinTiedot> tulokset = ses.createQuery("FROM PelinTiedot WHERE mustaPelaaja = " + p.getPelaajaId()
				+ " OR valkoinenPelaaja =  " + p.getPelaajaId()).getResultList();
		return tulokset;
	}
	public List<Pelaaja> haePelaaja(String nimi) {
		NativeQuery<Pelaaja> query = ses.createNativeQuery("select * from Pelaaja where kayttajaTunnus = ?", Pelaaja.class); 
		query.setParameter(1, nimi);  
		return query.getResultList();
	}

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

	@Override
	public boolean poistaPelaaja(Pelaaja pelaaja) {
		ses.beginTransaction();
		pelaaja.setKayttajaTunnus("Anonyymi");
		SQLQuery sqlQuery = ses.createSQLQuery("UPDATE Pelaaja SET kayttajaTunnus = \"Anonyymi\" WHERE kayttajaTunnus = \""+pelaaja.getKayttajaTunnus() +"\";");
		sqlQuery.executeUpdate();
		return false;
	}

	// Testaamiseen
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

	// testaamiseen
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
	
	public List<PelinTiedot> getKaikkiPelit() {
		@SuppressWarnings("unchecked")
		List<PelinTiedot> pelit = ses.createQuery("from PelinTiedot").getResultList();
		return pelit;
	}
}
