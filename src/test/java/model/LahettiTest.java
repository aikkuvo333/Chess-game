package model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.*;

/* @Author Oliver Hamberg */
public class LahettiTest {
	private Lauta lauta = new Lauta(true);
	
	@BeforeEach 
	public void setUp() {
		lauta = new Lauta(true);
	}
	
	@Test
	@DisplayName("Lähetti getVari()")
	public void getVari() {
		lauta.getLauta()[0][0].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		assertEquals(NappulanVari.VALKOINEN, lauta.getLauta()[0][0].getNappula().getVari(), "Lähetin värin pitäisi olla valkoinen, mutta koodi palautti mustan");
		lauta.getLauta()[1][2].setNappula(new Lahetti(NappulanVari.MUSTA));
		assertEquals(NappulanVari.MUSTA, lauta.getLauta()[1][2].getNappula().getVari(), "Lähetin värin pitäisi olla musta, mutta koodi palautti valkoisen");
	}
	
	@Test
	@DisplayName("Lähetti getSiirrot()")
	public void omienKeskellä() {
		lauta.getLauta()[3][3].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][4].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		lauta.getLauta()[4][2].setNappula(new Sotilas(NappulanVari.VALKOINEN));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(0, siirrot.size(), "Lähetti palautti väärän määrän mahdollisia siirtoja");
	}
	
	@Test
	@DisplayName("Lähetti getSiirrot()")
	public void vastustajienKeskellä() {
		lauta.getLauta()[3][3].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		lauta.getLauta()[2][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[2][4].setNappula(new Sotilas(NappulanVari.MUSTA));
		lauta.getLauta()[4][2].setNappula(new Sotilas(NappulanVari.MUSTA));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(4, siirrot.size(), "Lähetti palautti väärän määrän mahdollisia siirtoja");
	}
	
	@Test
	@DisplayName("Lähetti getSiirrot()")
	public void rajojenYli() {
		lauta.getLauta()[3][3].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		ArrayList<Ruutu>  siirrot = lauta.getSiirrot(3, 3);
		assertEquals(13, siirrot.size(), "Lähetti liikkui rajojen yli");
	}
}
