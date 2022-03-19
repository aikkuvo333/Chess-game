package dao;

/*
 * @author Oliver Hamberg, Elmo Vahvaselkä 19.3.2022
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.NappulanVari;

@Entity
@Table
public class PelinTiedot {
	@Id
	private int peliId;
	private Pelaaja valkoinenPelaaja;
	private Pelaaja mustaPelaaja;
	private Pelaaja voittaja;


	private Date pvm;
	@OneToMany
	private List<Siirto> siirrot;
	public PelinTiedot() {
		
	}
	public PelinTiedot (Pelaaja valkoinenPelaaja, Pelaaja mustaPelaaja) {
		this.valkoinenPelaaja = valkoinenPelaaja;
		this.mustaPelaaja = mustaPelaaja;
		this.siirrot = new ArrayList<Siirto>();
	}
	
	public void lisaaSiirto(Siirto siirto) {
		this.siirrot.add(siirto);
	}

	public Pelaaja getVoittaja() {
		return voittaja;
	}

	public void setVoittaja(Pelaaja voittaja) {
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
	public Date getPvm() {
		return pvm;
	}
	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}
	

}
