package dao;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Pelaaja {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pelaajaId;

	@Column
	private String kayttajaTunnus;
	
	@Transient
	private int voitot;
	
	@Transient
	private List<PelinTiedot> pelit;
	
	public Pelaaja() {}
	
	public Pelaaja(int id, String name) {
		this.pelaajaId = id;
		this.kayttajaTunnus = name;
	}
	
	public Pelaaja(String kayttajaTunnus) {
	
		this.kayttajaTunnus = kayttajaTunnus;
	}
	
	public int getPelaajaId() {
		return pelaajaId;
	}

	public void setPelaajaId(int pelaajaId) {
		this.pelaajaId = pelaajaId;
	}

	public String getKayttajaTunnus() {
		return kayttajaTunnus;
	}

	public void setKayttajaTunnus(String kayttajaTunnus) {
		this.kayttajaTunnus = kayttajaTunnus;
	}
	
	public void setPelit(List<PelinTiedot> pelit){
		this.pelit = pelit;
		voitot = 0;
		for(PelinTiedot peli: this.pelit) {
			if(peli.getVoittaja().getPelaajaId() == pelaajaId) {
				voitot++;
			}
		}
	}
	
	public double getVoittoprosentti() {
		return (double) getVoitot() / (double) getPeleja() * 100;
	}
	
	public int getVoitot() {
		return voitot;
	}
	
	public int getPeleja() {
		return pelit.size();
	}
	
	public List<PelinTiedot> getPelit(){
		return pelit;
	}
	
}
