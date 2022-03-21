package dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

public class DBKontrolleri implements IDaoController{
	private Session ses;
	private static DBKontrolleri instance;
	
	public DBKontrolleri() {
		SessionFactory istuntoTehdas = new Configuration().configure().buildSessionFactory();
		ses = istuntoTehdas.openSession();	
	}
	@SuppressWarnings("unchecked")
	public List<Pelaaja> getPelaajat() {
		return ses.createQuery("from Pelaaja").getResultList();
	}
	public static DBKontrolleri getInstance() {
		if (instance == null) {
			instance = new DBKontrolleri();
		}
		return instance;
	}
	@Transactional 
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
	public int haeVoittoMaara(Pelaaja p) {
		List<PelinTiedot> pelit = haePelaajanPelit(p);
		int voitetut = 0;
		for(PelinTiedot tiedot: pelit) {
			if(tiedot.getVoittaja().getPelaajaId() == p.getPelaajaId()) {
				voitetut++;
			}
		}
		return voitetut;
	}
	public int haePelienMaara(Pelaaja p) {
		List<PelinTiedot> pelit = haePelaajanPelit(p);
		int kaikkipelit = 0;
		for(PelinTiedot tiedot: pelit) {
			kaikkipelit++;
		}
		return kaikkipelit;
	}
	public String haeVoittoProsentti(Pelaaja p) {
		return (double) haeVoittoMaara(p) / (double) haePelienMaara(p) * 100 + "%";
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
