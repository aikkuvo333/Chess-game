package dao;


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
	private int valkoinenPelaaja;
	private int mustaPelaaja;
	private int voittaja;


	private Date pvm;
	@OneToMany
	private List<Siirto> siirrot;
	public PelinTiedot() {
		
	}
	public PelinTiedot (int valkoinenPelaaja, int mustaPelaaja) {
		this.valkoinenPelaaja = valkoinenPelaaja;
		this.mustaPelaaja = mustaPelaaja;
		this.siirrot = new ArrayList<Siirto>();
	}
	
	public void lisaaSiirto(Siirto siirto) {
		this.siirrot.add(siirto);
	}

	public int getVoittaja() {
		return voittaja;
	}

	public void setVoittaja(int voittaja) {
		this.voittaja = voittaja;
	}


	public int getValkoinenPelaaja() {
		return valkoinenPelaaja;
	}

	public int getMustaPelaaja() {
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
