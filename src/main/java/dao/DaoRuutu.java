package dao;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.Nappula;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/
@Entity
@Table
public class DaoRuutu {
	@Id
	private int ruutuId;
	private int x;
	private int y;
	
	@Transient
	private Nappula nappula;
	
	public DaoRuutu(int x, int y) {
		this.x = x;
		this.y = y;
		this.nappula = null;
	}
	
	public void setNappula (Nappula nappula) {
		this.nappula = nappula;
	}
	
	public Nappula poistaNappula() {
		Nappula palautettava = this.nappula;
		this.nappula = null;
		return palautettava;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Nappula getNappula() {
		return this.nappula;
	}

}
