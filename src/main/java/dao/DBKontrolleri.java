package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBKontrolleri implements IDaoController{
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
		for (Pelaaja pelaaja: pelaajat) {
			pelaaja.setPelit(haePelaajanPelit(pelaaja));
		}
		return pelaajat; 
	}
	
	public Pelaaja luoPelaaja(String nimi) {
		ses.beginTransaction();
		int id = (Integer) ses.save(new Pelaaja(nimi));
		ses.getTransaction().commit();
		return new Pelaaja(id, nimi);
	}
	
	public List<PelinTiedot> haePelaajanPelit(Pelaaja p) {		
		@SuppressWarnings("unchecked")
		List<PelinTiedot> tulokset = ses.createQuery("FROM PelinTiedot WHERE mustaPelaaja = " + p.getPelaajaId() + " OR valkoinenPelaaja =  " + p.getPelaajaId()).getResultList();		
		return tulokset;
	}
	
	@Override
	public void tallennaPeli(PelinTiedot pelinTiedot) {
		ses.beginTransaction();
		ses.save(pelinTiedot);
		ses.getTransaction().commit();
	}

	@Override
	public void haeKaikkipelit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void poistaPelaaja(int pelaajaId) {
		// TODO Auto-generated method stub
	}
}
