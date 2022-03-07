package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pelaaja {
	@Id
	@Column
	private int pelaajaId;

	@Column
	private String kayttajaTunnus;
	
	public Pelaaja() {
		
	}
	
	public Pelaaja(int pelaajaId, String kayttajaTunnus) {
		this.pelaajaId = pelaajaId;
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
	
}
