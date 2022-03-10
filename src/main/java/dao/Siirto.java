package dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Siirto {
	@Id
	@GeneratedValue
	private int id;
	private int pelinId;
	@ManyToOne
	private Ruutu lahtoRuutu;
	@ManyToOne
	private Ruutu kohdeRuutu;
	public Siirto(Ruutu lahtoRuutu, Ruutu kohdeRuutu) {
		this.lahtoRuutu = lahtoRuutu;
		this.kohdeRuutu = kohdeRuutu;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPelinId() {
		return pelinId;
	}
	public void setPelinId(int pelinId) {
		this.pelinId = pelinId;
	}
	public Ruutu getLahtoRuutu() {
		return lahtoRuutu;
	}
	public void setLahtoRuutu(Ruutu lahtoRuutu) {
		this.lahtoRuutu = lahtoRuutu;
	}
	public Ruutu getKohdeRuutu() {
		return kohdeRuutu;
	}
	public void setKohdeRuutu(Ruutu kohdeRuutu) {
		this.kohdeRuutu = kohdeRuutu;
	}
}
