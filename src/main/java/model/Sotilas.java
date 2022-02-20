package model;

/**
* @author Elmo Vahvaselkä 26.1.2022
*/

import java.util.ArrayList;

public class Sotilas extends Nappula {
	private NappulanVari vari;
	private boolean ekaSiirto;
	private NappulanTyyppi tyyppi;

	public Sotilas(NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
		this.tyyppi = NappulanTyyppi.SOTILAS;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();

		if (vari == NappulanVari.VALKOINEN && ruutu.getY() < 7) {

			//Onko sotilaan edessä olevassa ruudussa tilaa
			if (lauta[ruutu.getX()][ruutu.getY() + 1].getNappula() == null) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() + 1));

				// Sotilaan ensimmäinen siirto voi olla kaksi ruutua. Onko siinä ruudussa tilaa.
				if (!ekaSiirto && lauta[ruutu.getX()][ruutu.getY() + 2].getNappula() == null) {
					siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() + 2));
				}
			}

			// Syötävä nappula etuoikealla, sotilaan menosuuntaan
			if (ruutu.getX() < 7) {
				if (lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() + 1][ruutu.getY() + 1].getNappula().getVari() == NappulanVari.MUSTA) {
					siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() + 1));
				}
			}
			
			// Syötävä nappula etuvasemmalla, sotilaan menosuuntaan
			if (ruutu.getX() > 0) {
				if (lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() - 1][ruutu.getY() + 1].getNappula().getVari() == NappulanVari.MUSTA) {
					siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() + 1));
				}
			}
		}

		if (vari == NappulanVari.MUSTA && ruutu.getY() > 0 ) {
			
			//Onko sotilaan edessä tilaa
			if (lauta[ruutu.getX()][ruutu.getY() - 1].getNappula() == null) {
				siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() - 1));

				// Sotilaan ensimmäinen ruutu voi olla kaksi siirtoa. Onko siinä ruudussa tilaa.
				if (!ekaSiirto && lauta[ruutu.getX()][ruutu.getY() - 2].getNappula() == null) {
					siirrot.add(new Ruutu(ruutu.getX(), ruutu.getY() - 2));
				}
			}

			// Syötävä nappula etuoikealla, sotilaan menosuuntaan
			if (ruutu.getX() > 0) {
				if (lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() - 1][ruutu.getY() - 1].getNappula().getVari() == NappulanVari.VALKOINEN) {
					siirrot.add(new Ruutu(ruutu.getX() - 1, ruutu.getY() - 1));
				}
			}

			// Syötävä nappula etuvasemmalla, sotilaan menosuuntaan
			if (ruutu.getX() < 7) {
				if (lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula() instanceof Nappula
						&& lauta[ruutu.getX() + 1][ruutu.getY() - 1].getNappula().getVari() == NappulanVari.VALKOINEN) {
					siirrot.add(new Ruutu(ruutu.getX() + 1, ruutu.getY() - 1));
				}
			}
		}
		return siirrot;
	}

	public void ekaSiirtoTehty() {
		this.ekaSiirto = true;
	}

	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}

	@Override
	public NappulanTyyppi getTyyppi() {
		return this.tyyppi;
	}
}
