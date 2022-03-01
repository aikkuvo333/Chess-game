package dao;

import java.util.ArrayList;

import model.NappulanVari;

public class PelinTiedot {
	private String valkoinePelaaja;
	private String mustaPelaaja;
	private NappulanVari voittaja;
	private double kesto;
	private ArrayList<Siirto> siirrot;
	
	public PelinTiedot (String valkoinenPelaaja, String mustaPelaaja) {
		this.valkoinePelaaja = valkoinenPelaaja;
		this.mustaPelaaja = mustaPelaaja;
		this.siirrot = new ArrayList<Siirto>();
	}
	
	public void lisaaSiirto(Siirto siirto) {
		this.siirrot.add(siirto);
	}

	public NappulanVari getVoittaja() {
		return voittaja;
	}

	public void setVoittaja(NappulanVari voittaja) {
		this.voittaja = voittaja;
	}

	public double getKesto() {
		return kesto;
	}

	public void setKesto(double kesto) {
		this.kesto = kesto;
	}

	public String getValkoinePelaaja() {
		return valkoinePelaaja;
	}

	public String getMustaPelaaja() {
		return mustaPelaaja;
	}
	
	public ArrayList<Siirto> getSiirrot(){
		return this.siirrot;
	}
	
	

}
