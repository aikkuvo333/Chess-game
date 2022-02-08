package model;

/**
* @author Elmo Vahvaselkä 1.2.2022
*/

import java.util.ArrayList;

public class Kuningas extends Nappula {
	private NappulanVari vari;
	private boolean ekaSiirto;

	public Kuningas(NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();

		if (ruutu.getY() < 7) {
			// Tarkastetaan yläoikea ruutu
			if (ruutu.getX() < 7 && (lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula() == null
					|| lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula().getVari() != this.vari)) {
				siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() + 1));
			}
			// Tarkastetaan yläpuolella oleva ruutu
			if (lauta[ruutu.getX()][ruutu.getY() + 1].getNappula() == null
					|| lauta[ruutu.getX()][ruutu.getY() + 1].getNappula().getVari() != this.vari) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() + 1));
			}

			// Tarkastetaan ylävasen ruutu
			if (ruutu.getX() > 0 && (lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula() == null
					|| lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula().getVari() != this.vari)) {
				siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() + 1));
			}
		}

		if (ruutu.getY() > 0) {
			// Tarkastetaan alaoikea ruutu
			if (ruutu.getX() < 7 && (lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula() == null
					|| lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula().getVari() != this.vari)) {
				siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() - 1));
			}
			// Tarkastetaan alapuolella oleva ruutu
			if (lauta[ruutu.getX()][ruutu.getY() - 1].getNappula() == null
					|| lauta[ruutu.getX()][ruutu.getY() - 1].getNappula().getVari() != this.vari) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() - 1));
			}

			// Tarkastetaan alavasen ruutu
			if (ruutu.getX() > 0 && (lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula() == null
					|| lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula().getVari() != this.vari)) {
				siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() - 1));
			}
		}

		// Tarkastetaan vasemmalla oleva ruutu
		if (ruutu.getX() > 0 && (lauta[ruutu.getX() - 1][ruutu.getY()].getNappula() == null
				|| lauta[ruutu.getX() - 1][ruutu.getY()].getNappula().getVari() != this.vari)) {
			siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY()));
		}

		// Tarkastetaan oikealla oleva ruutu
		if (ruutu.getX() < 7 && (lauta[ruutu.getX() + 1][ruutu.getY()].getNappula() == null
				|| lauta[ruutu.getX() + 1][ruutu.getY()].getNappula().getVari() != this.vari)) {
			siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY()));
		}
		
		if(this.vari == NappulanVari.VALKOINEN) {
			//Valkoisen Kuninkaan tornitus oikealle
			if(!this.ekaSiirto && lauta[7][0].getNappula() instanceof Torni) {
				Torni torni = (Torni)lauta[7][0].getNappula();
				if(torni.getEkaSiirto() == false && lauta[6][0].getNappula() == null && lauta[5][0].getNappula() == null) {
					siirrot.add(new Ruutu(6,0));
				}
			}
			
			//Tornitus vasemmalle
			if(!this.ekaSiirto && lauta[0][0].getNappula() instanceof Torni) {
				Torni torni = (Torni)lauta[0][0].getNappula();
				if(torni.getEkaSiirto() == false && lauta[1][0].getNappula() == null && lauta[2][0].getNappula() == null && lauta[3][0].getNappula() == null) {
					siirrot.add(new Ruutu(2,0));
				}
			}
		}
		
		if(this.vari == NappulanVari.MUSTA) {
			//Valkoisen Kuninkaan tornitus oikealle
			if(!this.ekaSiirto && lauta[7][7].getNappula() instanceof Torni) {
				Torni torni = (Torni)lauta[7][7].getNappula();
				if(torni.getEkaSiirto() == false && lauta[6][7].getNappula() == null && lauta[5][7].getNappula() == null) {
					siirrot.add(new Ruutu(6,7));
				}
			}
			
			//Tornitus vasemmalle
			if(!this.ekaSiirto && lauta[0][7].getNappula() instanceof Torni) {
				Torni torni = (Torni)lauta[0][7].getNappula();
				if(torni.getEkaSiirto() == false && lauta[1][7].getNappula() == null && lauta[2][7].getNappula() == null && lauta[3][7].getNappula() == null) {
					siirrot.add(new Ruutu(2,7));
				}
			}
		}

		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}

	public void ekaSiirtoTehty() {
		this.ekaSiirto = true;
	}

	public boolean getEkaSiirto() {
		return ekaSiirto;
	}
}
