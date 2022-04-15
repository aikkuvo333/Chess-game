package dao;

import javax.persistence.Column;

/*
 * @author Oliver Hamberg, Elmo Vahvaselkä 19.3.2022
 */

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.NappulanTyyppi;


@Entity
@Table
public class Siirto {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int mistaX;
	
	@Column
	private int mistaY;
	
	@Column
	private int mihinX;
	
	@Column
	private int mihinY;
	
	@Column
	@Enumerated(EnumType.STRING)
	private NappulanTyyppi korotus;

	public Siirto() {}
	
	public Siirto(int mistaX, int mistaY, int mihinX, int mihinY, NappulanTyyppi korotus) {
		this.mistaX = mistaX;
		this.mistaY = mistaY;
		this.mihinX = mihinX;
		this.mihinY = mihinY;
		this.korotus = korotus;
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
