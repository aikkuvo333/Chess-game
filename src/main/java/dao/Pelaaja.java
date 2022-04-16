package dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author Oliver Hamberg, Elmo vahvaselkä
 *
 */

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
	
	public Pelaaja(String kayttajaTunnus) {	
		this.kayttajaTunnus = kayttajaTunnus;
	}
	
	public int getPelaajaId() {
		return pelaajaId;
	}

	public String getKayttajaTunnus() {
		return kayttajaTunnus;
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
		if (getPeleja() == 0) {
			return 0;
		}
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
