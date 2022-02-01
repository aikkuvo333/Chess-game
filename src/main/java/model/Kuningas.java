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
					|| lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula().getVari() != this.vari)) {
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
