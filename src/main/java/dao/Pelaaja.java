package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pelaaja {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pelaajaId;

	@Column
	private String kayttajaTunnus;
	
	public Pelaaja() {
		
	}
	
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
	
}
