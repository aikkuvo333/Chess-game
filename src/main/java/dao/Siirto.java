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

/**
 * Tähän luokkaan talletetaan yksittäiseen siirtoon liittyvät tiedot.
 * @author Elmo Vahvaselkä
 *
 */

@Entity
@Table
public class Siirto {
	/**
	 * Siirron id integerinä.
	 */
	@Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	
	/**
	 * Siirron lähtökoordinaatti x-akselilla.
	 */
	@Column private int mistaX;
	
	/**
	 * Siirron lähtökoordinaatti y-akselilla.
	 */
	@Column private int mistaY;
	
	/**
	 * Siirron kohdekoordinaatti x-akselilla.
	 */
	@Column private int mihinX;
	
	/**
	 * Siirron kohdekoordinaatti y-akselilla.
	 */
	@Column private int mihinY;
	
	/**
	 * Mahdollinen korotus NappulanTyyppi enumina.
	 */
	@Column @Enumerated(EnumType.STRING) private NappulanTyyppi korotus;

	/**
	 * Parametriton kostruktori.
	 */
	public Siirto() {}
	
	
	/**
	 * Konstruktori, jolla luodaan uusi siirto.
	 * @param mistaX Siirron lähtökoordinaatti x-akselilla.
	 * @param mistaY Siirron lähtökoordinaatti y-akselilla.
	 * @param mihinX Siirron kohdekoordinaatti x-akselilla.
	 * @param mihinY Siirron kohdekoordinaatti y-akselilla.
	 * @param korotus Kortotus NappulanTyyppi enumina.
	 */
	public Siirto(int mistaX, int mistaY, int mihinX, int mihinY, NappulanTyyppi korotus) {
		this.mistaX = mistaX;
		this.mistaY = mistaY;
		this.mihinX = mihinX;
		this.mihinY = mihinY;
		this.korotus = korotus;
	}

	/**
	 * Getteri lähtöruudun x-koordinaatille.
	 * @return x-koordinaatti integerinä.
	 */
	public int getMistaX() {
		return mistaX;
	}

	/**
	 * Getteri lähtöruudun y-koordinaatille.
	 * @return y-koordinaatti integerinä.
	 */
	public int getMistaY() {
		return mistaY;
	}

	/**
	 * Getteri kohderuudun x-koordinaatille.
	 * @return x-koordinaatti integerinä.
	 */
	public int getMihinX() {
		return mihinX;
	}

	/**
	 * Getteri kohderuudun y-koordinaatille.
	 * @return y-koordinaatti integerinä.
	 */
	public int getMihinY() {
		return mihinY;
	}

	/**
	 * Getteri kortukselle.
	 * @return Korotus NappulanTyyppi enumina.
	 */
	public NappulanTyyppi getKorotus() {
		return korotus;
	}
	
}
