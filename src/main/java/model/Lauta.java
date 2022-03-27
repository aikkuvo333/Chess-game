package model;

import java.util.ArrayList;

/**
 * @author Elmo Vahvaselkä 26.1.2022 & Aivan Vo 9.2.2022
 */

 /**
  * Luokka <code>Lauta</code> edustaa shakkipelin shakkilautaa ja huolehtii <code>nappuloiden</code> sijanneista  
  * <code>ruutujen</code> avulla. Säännöistä huolehtiminen ei ole tämän luokan vastuulla.
  */
public class Lauta {
	/**
	 * Kaksiulotteiseen <code>lauta</code> tauluun tallennetaan shakkilaudan ruudut.
	 */
	private Ruutu[][] lauta;
	
	/**
	 * Muuttuja <code>valkoinenKuningas</code> on ruutu-olio, jossa tiedot valkoisen kuninkaan sijainnista.
	 */
	private Ruutu valkoinenKuningas;
	
	/**
	 * Muuttuja <code>mustaKuningas</code> on ruutu-olio, jossa tiedot musta kuninkaan sijainnista.
	 */
	private Ruutu mustaKuningas;

	/**
	 * Luo uuden <code>lauta</code>-olion samlla luoden kaksiulotteisen taulun. Asettaa tauluun <code>ruudut</code>,
	 * joihin asetetaan mahdolliset <code>nappulat</code> pelin aloituksen mukaisesti.
	 */
	public Lauta() {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
		this.asetaNappulat();
	}

	/**
	 * Konstruktori testeille. Ei aseta <code>nappuloita</code> automaattisesti ruutuihin.
	 * @param testi <code>boolean</code> arvo, joka voi olla <code>true</code> tai <code>false</code>.
	 */
	public Lauta(boolean testi) {
		this.lauta = new Ruutu[8][8];
		this.luoRuudut();
	}

	/**
	 * Palauttaa kaksiulotteisen taulokon, jossa pelilaudalla olevat <code>ruudut</code>.
	 * @return <code>Ruutu[][]</code> taulukko, jossa kaikki pelilaudan <code>ruudut</code>.
	 */
	public Ruutu[][] getLauta() {
		return this.lauta;
	}

	/**
	 * Palauttaa laudalla olevan tietyn <code>ruudun</code> x- ja y-koordinaattien perusteella.
	 * @param x kokonaisluku väliltä 0-7, joka edustaa x-koordinaattia.
	 * @param y kokonaisluku väliltä 0-7, joka edustaa y-koordinaattia.
	 * @return palauttaa koordinaattien mukaisen <code>ruutu</code>-olion.
	 */
	public Ruutu getRuutu(int x, int y) {
		return this.lauta[x][y];
	}

	/**
	 * Palauttaa tietyssä ruudussa olevan nappulan siirrot.
	 * @param x kokonaisluku väliltä 0-7, joka edustaa x-koordinaattia.
	 * @param y kokonaisluku väliltä 0-7, joka edustaa y-koordinaattia.
	 * @return <code>ArrayListin</code>, joka sisältää kaikki <code>ruudut</code>, joihin nappula voi siirtyä.
	 */
	public ArrayList<Ruutu> getSiirrot(int x, int y) {
		return lauta[x][y].getNappula().getSiirrot(new Ruutu(x, y), this.lauta);
	}

	/**
	 * Toteuttaa siirron laudalla.
	 * @param mistaX Siirron lähtökoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mistaY Siirron lähtökoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinX Siirron kodekoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinY Siirron kodekoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @return <code>Boolean</code> arvo siirron onnistumisen mukaan.
	 */
	public boolean siirra(int mistaX, int mistaY, int mihinX, int mihinY) {
		Nappula nappula = lauta[mistaX][mistaY].poistaNappula();

		if (!tornita(mistaX, mistaY, mihinX, mihinY, nappula)) {
			lauta[mihinX][mihinY].setNappula(nappula);
		}
		
		if (nappula instanceof Kuningas) {
			paivitaKuninkaanSijainti((Kuningas) nappula, new Ruutu(mihinX, mihinY));
		}
		
		merkitseEkaSiirto(nappula);
		return true;
	}

	/**
	 * Toteuttaa tornituksen laudalla.
	 * @param mistaX Siirron lähtökoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mistaY Siirron lähtökoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinX Siirron kodekoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinY Siirron kodekoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @param nappula Siirrettävä <code>nappula</code>.
	 * @return <code>Boolean</code> arvo tornituksen onnistumisesta.
	 */
	public boolean tornita(int mistaX, int mistaY, int mihinX, int mihinY, Nappula nappula) {
		if (nappula instanceof Kuningas && mistaX == 4 && mistaY == 0 && mihinX == 6 && mihinY == 0) {
			Nappula torni = lauta[7][0].poistaNappula();
			lauta[6][0].setNappula(nappula);
			lauta[5][0].setNappula(torni);
			merkitseEkaSiirto(torni);
			return true;
		}

		if (nappula instanceof Kuningas && mistaX == 4 && mistaY == 0 && mihinX == 2 && mihinY == 0) {
			Nappula torni = lauta[0][0].poistaNappula();
			lauta[2][0].setNappula(nappula);
			lauta[3][0].setNappula(torni);
			merkitseEkaSiirto(torni);
			return true;
		}

		if (nappula instanceof Kuningas && mistaX == 4 && mistaY == 7 && mihinX == 6 && mihinY == 7) {
			Nappula torni = lauta[7][7].poistaNappula();
			lauta[6][7].setNappula(nappula);
			lauta[5][7].setNappula(torni);
			merkitseEkaSiirto(torni);
			return true;
		}

		if (nappula instanceof Kuningas && mistaX == 4 && mistaY == 7 && mihinX == 2 && mihinY == 7) {
			Nappula torni = lauta[0][7].poistaNappula();
			lauta[2][7].setNappula(nappula);
			lauta[3][7].setNappula(torni);
			merkitseEkaSiirto(torni);
			return true;
		}
		return false;
	}
	
	/**
	 * Kuomaa tehdyn tornituksen. Käytetään siirron turvallisuuden tarkistamiseen 
	 * <code>Shakkipeli</code>-luokan toimesta.
	 * @param mistaX Siirron lähtökoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mistaY Siirron lähtökoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinX Siirron kodekoordinaatti x-akselilla kokonaislukuna väliltä 0-7.
	 * @param mihinY Siirron kodekoordinaatti y-akselilla kokonaislukuna väliltä 0-7.
	 * @return <code>Boolean</code> arvo tornituksen purkamisen onnistumisesta.
	 */
	public boolean kumoaTornitus(int mistaX, int mistaY, int mihinX, int mihinY) {
		if (mistaX == 4 && mistaY == 0 && mihinX == 6 && mihinY == 0) {
			Torni torni = (Torni)lauta[5][0].poistaNappula();
			lauta[7][0].setNappula(torni);
			torni.kumoaEkaSiirto();
			Kuningas kuningas = (Kuningas)lauta[6][0].poistaNappula();
			lauta[4][0].setNappula(kuningas);
			paivitaKuninkaanSijainti((Kuningas) kuningas, new Ruutu(4, 0));
			kuningas.kumoaEkaSiirto();
			return true;
		}

		if (mistaX == 4 && mistaY == 0 && mihinX == 2 && mihinY == 0) {
			Torni torni = (Torni)lauta[3][0].poistaNappula();
			torni.kumoaEkaSiirto();
			lauta[0][0].setNappula(torni);
			Kuningas kuningas = (Kuningas)lauta[2][0].poistaNappula();
			lauta[4][0].setNappula(kuningas);
			paivitaKuninkaanSijainti((Kuningas) kuningas, new Ruutu(4, 0));
			kuningas.kumoaEkaSiirto();
			return true;
		}

		if (mistaX == 4 && mistaY == 7 && mihinX == 6 && mihinY == 7) {
			Torni torni = (Torni)lauta[5][7].poistaNappula();
			lauta[7][7].setNappula(torni);
			torni.kumoaEkaSiirto();
			Kuningas kuningas = (Kuningas)lauta[6][7].poistaNappula();
			lauta[4][7].setNappula(kuningas);
			paivitaKuninkaanSijainti((Kuningas) kuningas, new Ruutu(4, 7));
			kuningas.kumoaEkaSiirto();
			return true;
		}

		if (mistaX == 4 && mistaY == 7 && mihinX == 2 && mihinY == 7) {
			Torni torni = (Torni)lauta[3][7].poistaNappula();
			lauta[0][7].setNappula(torni);
			torni.kumoaEkaSiirto();
			Kuningas kuningas = (Kuningas)lauta[2][7].poistaNappula();
			lauta[4][7].setNappula(kuningas);
			paivitaKuninkaanSijainti((Kuningas) kuningas, new Ruutu(4, 7));
			kuningas.kumoaEkaSiirto();
			return true;
		}
		return false;
	}

	/**
	 * Palautaa <code>Ruudun</code>, jossa valkoinen kuningas on.
	 * @return <code>Ruudun</code>, jossa valkoinen kuningas on.
	 */
	public Ruutu getValkoinenKuningas() {
		return this.valkoinenKuningas;
	}

	/**
	 * Palautaa <code>Ruudun</code>, jossa musta kuningas on.
	 * @return <code>Ruudun</code>, jossa musta kuningas on.
	 */
	public Ruutu getMustaKuningas() {
		return this.mustaKuningas;
	}

	/**
	 * Päivittää kuninkaan sijainnin muuttujaan <code>valkoinenKuningas</code> tai 
	 * <code>mustaKuningas</code>
	 * @param kuningas <code>Kuningas</code>, jota on liikutettu.
	 * @param ruutu <code>Ruutu</code>, johon kuningas on siirretty.
	 */
	private void paivitaKuninkaanSijainti(Kuningas kuningas, Ruutu ruutu) {
		if (kuningas.getVari() == NappulanVari.VALKOINEN) {
			this.setValkoinenKuningas(ruutu);
		} else {
			this.setMustaKuningas(ruutu);
		}
	}

	/**
	 * Asettaa <code>valkoinenKuningas</code> muuttujaan <code>ruudun</code>, jossa valkoinen kuningas on.
	 * @param ruutu <code>Ruutu</code>, johon valkoinen kuningas on asetettu.
	 */
	private void setValkoinenKuningas(Ruutu ruutu) {
		this.valkoinenKuningas = ruutu;
	}

	/**
	 * Asettaa <code>mustaKuningas</code> muuttujaan <code>ruudun</code>, jossa musta kuningas on.
	 * @param ruutu <code>Ruutu<code>, johon musta kuningas on asetettu.
	 */
	private void setMustaKuningas(Ruutu ruutu) {
		this.mustaKuningas = ruutu;
	}
	
	/**
	 * Luo <code>ruudut</code> laudalle ja asettaa ne taulukkoon.
	 */
	private void luoRuudut() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				lauta[x][y] = new Ruutu(x, y);
			}
		}
	}

	/**
	 * Asettaa <code>nappulat</code> <code>ruutuihin</code> pelin aloituksen mukaisiin sijanteihin.
	 */
	private void asetaNappulat() {
		for (int x = 0; x < 8; x++) {
			lauta[x][1].setNappula(new Sotilas(NappulanVari.VALKOINEN));
			lauta[x][6].setNappula(new Sotilas(NappulanVari.MUSTA));
		}

		lauta[4][0].setNappula(new Kuningas(NappulanVari.VALKOINEN));
		this.setValkoinenKuningas(new Ruutu(4, 0));
		lauta[4][7].setNappula(new Kuningas(NappulanVari.MUSTA));
		this.setMustaKuningas(new Ruutu(4, 7));
		lauta[0][0].setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta[7][0].setNappula(new Torni(NappulanVari.VALKOINEN));
		lauta[0][7].setNappula(new Torni(NappulanVari.MUSTA));
		lauta[7][7].setNappula(new Torni(NappulanVari.MUSTA));
		lauta[1][0].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta[6][0].setNappula(new Ratsu(NappulanVari.VALKOINEN));
		lauta[1][7].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta[6][7].setNappula(new Ratsu(NappulanVari.MUSTA));
		lauta[2][0].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		lauta[5][0].setNappula(new Lahetti(NappulanVari.VALKOINEN));
		lauta[2][7].setNappula(new Lahetti(NappulanVari.MUSTA));
		lauta[5][7].setNappula(new Lahetti(NappulanVari.MUSTA));
		lauta[3][0].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
		lauta[3][7].setNappula(new Kuningatar(NappulanVari.MUSTA));
	}

	/**
	 * Merkitsee <code>nappulan</code> ensimmäisen siirron tehdyksi.
	 * @param nappula <code>Nappula</code>, jota on siirretty.
	 */
	private void merkitseEkaSiirto(Nappula nappula) {
		if (nappula instanceof NappulaEka) {
			((NappulaEka) nappula).ekaSiirtoTehty();
		}
	}

	/**
	 * Korottaa <code>nappulan</code> poistamalla <code>sotilaan</code> ja 
	 * korvaamalla sen valitulla <code>nappulalla</code>.
	 * @param x korotetun <code>nappulan</code> x-koordinaatti.
	 * @param y korotetun <code>nappulan</code> y-koordinaatti.
	 * @param tyyppi <code>NappulanTyyppi</code>, johon <code>sotilas</code> korotetaan.
	 */
	@SuppressWarnings("incomplete-switch")
	public void korota(int x, int y, NappulanTyyppi tyyppi) {
		//poistetaan korotettava nappula
		Sotilas sotilas = (Sotilas) lauta[x][y].poistaNappula(); 
		
		//Korvataan sotilas uudella nappulalla.
		if(sotilas.getVari() == NappulanVari.VALKOINEN) {
			switch (tyyppi) {
			case TORNI:
				lauta[x][y].setNappula(new Torni(NappulanVari.VALKOINEN));
				break;
			case RATSU:
				lauta[x][y].setNappula(new Ratsu(NappulanVari.VALKOINEN));
				break;
			case LAHETTI:
				lauta[x][y].setNappula(new Lahetti(NappulanVari.VALKOINEN));
				break;
			case KUNINGATAR:
				lauta[x][y].setNappula(new Kuningatar(NappulanVari.VALKOINEN));
				break;
			}
		} else if (sotilas.getVari() == NappulanVari.MUSTA) {
			switch (tyyppi) {
			case TORNI:
				lauta[x][y].setNappula(new Torni(NappulanVari.MUSTA));
				break;
			case RATSU:
				lauta[x][y].setNappula(new Ratsu(NappulanVari.MUSTA));
				break;
			case LAHETTI:
				lauta[x][y].setNappula(new Lahetti(NappulanVari.MUSTA));
				break;
			case KUNINGATAR:
				lauta[x][y].setNappula(new Kuningatar(NappulanVari.MUSTA));
				break;
			}
		} 
	}
}
