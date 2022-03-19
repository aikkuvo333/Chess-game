package dao;

/*
 * @author Oliver Hamberg, Elmo Vahvaselkä 19.3.2022
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.NappulanTyyppi;


@Entity
@Table
public class Siirto {
	@Id
	@GeneratedValue
	private int id;
	private int pelinId;
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	private NappulanTyyppi korotus;

	public Siirto(int mistaX, int mistaY, int mihinX, int mihinY, NappulanTyyppi korotus) {
		this.mistaX = mistaX;
		this.mistaY = mistaY;
		this.mihinX = mihinX;
		this.mihinY = mihinY;
		this.korotus = korotus;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPelinId() {
		return pelinId;
	}
	
	public void setPelinId(int pelinId) {
		this.pelinId = pelinId;
	}


	public int getMistaX() {
		return mistaX;
	}


	public int getMistaY() {
		return mistaY;
	}


	public int getMihinX() {
		return mihinX;
	}


	public int getMihinY() {
		return mihinY;
	}


	public NappulanTyyppi getKorotus() {
		return korotus;
	}
	
}
