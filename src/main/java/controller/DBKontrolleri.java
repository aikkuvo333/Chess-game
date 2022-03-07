package controller;

import java.util.List;

import model.Pelaaja;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBKontrolleri {
	private Session ses;
	private static DBKontrolleri instance;
	
	
	public DBKontrolleri() {
			SessionFactory istuntoTehdas = new Configuration().configure().buildSessionFactory();
			ses = istuntoTehdas.openSession();
			
			/*con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=Koira");
			Statement statement = con.createStatement();
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
			con.setCatalog(DB_NAME);*/
			/*
			statement.executeUpdate(DBSchema.STATEMENT_CREATE_PELAAJA);
			statement.executeUpdate(DBSchema.STATEMENT_CREATE_PELI);
			statement.executeUpdate(DBSchema.STATEMENT_CREATE_RUUTU);
			statement.executeUpdate(DBSchema.STATEMENT_CREATE_SIIRTO);
			*/
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
}
