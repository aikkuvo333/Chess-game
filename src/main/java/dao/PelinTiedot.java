package dao;

import java.text.SimpleDateFormat;

/*
 * @author Oliver Hamberg
 */

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
import javax.persistence.Transient;

import model.NappulanVari;

@Entity
@Table
public class PelinTiedot {
	@Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int peliId;
	@ManyToOne
	private Pelaaja valkoinenPelaaja;
	@ManyToOne
	private Pelaaja mustaPelaaja;
	@ManyToOne
	private Pelaaja voittaja;

	@Column
	private String pvm;
	@Column
	private long kesto;
	
	@Transient
	private Date aloitusaika;
	
	@Transient 
	SimpleDateFormat muokkain = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Siirto> siirrot;
	
	public PelinTiedot() {}
	
	public PelinTiedot (Pelaaja valkoinenPelaaja, Pelaaja mustaPelaaja) {
		this.valkoinenPelaaja = valkoinenPelaaja;
		this.mustaPelaaja = mustaPelaaja;
		this.siirrot = new ArrayList<Siirto>();
		aloitusaika = new Date();
		pvm = muokkain.format(aloitusaika);
	}
	
	public void lisaaSiirto(Siirto siirto) {
		this.siirrot.add(siirto);
	}

	public Pelaaja getVoittaja() {
		return voittaja;
	}

	public void setVoittaja(Pelaaja voittaja) {
		//lasketaan peliin kulunut aika sekunteina
		kesto = ((new Date().getTime()) - aloitusaika.getTime())/ (1000) ;
		this.voittaja = voittaja;
	}

	public Pelaaja getValkoinenPelaaja() {
		return valkoinenPelaaja;
	}

	public Pelaaja getMustaPelaaja() {
		return mustaPelaaja;
	}
	
	public List<Siirto> getSiirrot(){
		return this.siirrot;
	}
	
	@SuppressWarnings("deprecation")
	public Date getPvm() {
		return new Date(pvm);
	}

}
