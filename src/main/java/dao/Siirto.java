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
	private DaoRuutu lahtoRuutu;
	@ManyToOne
	private DaoRuutu kohdeRuutu;
	public Siirto(DaoRuutu lahtoRuutu, DaoRuutu kohdeRuutu) {
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
	public DaoRuutu getLahtoRuutu() {
		return lahtoRuutu;
	}
	public void setLahtoRuutu(DaoRuutu lahtoRuutu) {
		this.lahtoRuutu = lahtoRuutu;
	}
	public DaoRuutu getKohdeRuutu() {
		return kohdeRuutu;
	}
	public void setKohdeRuutu(DaoRuutu kohdeRuutu) {
		this.kohdeRuutu = kohdeRuutu;
	}
}
