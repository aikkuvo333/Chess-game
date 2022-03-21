package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/* @Author Oliver Hamberg */
public class KuningatarTest {
private Lauta lauta = new Lauta(true);
	
	@BeforeEach 
	public void setUp() {
		lauta = new Lauta(true);
	}
	
	@Test
	@DisplayName("Kuningatar getVari()")
	public void getVari() {
		lauta.getLauta()[0][0].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
		assertEquals(NappulanVari.VALKOINEN, lauta.getLauta()[0][0].getNappula().getVari(), "Kuningattaren värin pitäisi olla valkoinen, mutta koodi palautti mustan");
		lauta.getLauta()[1][2].setNappula(new Kuningatar(NappulanVari.MUSTA));
		assertEquals(NappulanVari.MUSTA, lauta.getLauta()[1][2].getNappula().getVari(), "Kuningattaren värin pitäisi olla musta, mutta koodi palautti valkoisen");
	}
	@Test
	@DisplayName("Kuningatar getSiirrot()")
	public void omienKeskellä() {
		lauta.getLauta()[3][3].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[3][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(0, siirrot.size(), "Kuningatar palautti väärän määrän mahdollisia siirtoja");
	}
	@Test
	@DisplayName("Kuningatar getSiirrot()")
	public void vastustajienKeskellä() {
		lauta.getLauta()[3][3].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[2][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][3].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[2][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[3][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(8, siirrot.size(), "Kuningatar palautti väärän määrän mahdollisia siirtoja");
	}
	@Test
	@DisplayName("Kuningatar getSiirrot()")
	public void rajojenYli() {
		lauta.getLauta()[3][3].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(27, siirrot.size(), "Kuningatar liikkui rajojen yli");
	}
}
