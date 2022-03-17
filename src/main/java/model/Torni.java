package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import java.util.ArrayList;

public class Torni extends Nappula {
	private boolean ekaSiirto;

	public Torni(NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
		this.tyyppi = NappulanTyyppi.TORNI;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		{

			// Siirrot tornin ruudusta oikealle
			int x = ruutu.getX();
			while (x < 7) {
				x++;

				// onko ruudussa nappula
				if (lauta[x][ruutu.getY()].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
						break;
					}

					// Jos ei oma -> vihollinen
					siirrot.add(new Ruutu(x, ruutu.getY()));
					break;
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			// Siirrot tornin ruudusta vasemmalle
			x = ruutu.getX();
			while (x > 0) {
				x--;

				// onko ruudussa nappula
				if (lauta[x][ruutu.getY()].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(x, ruutu.getY()));
					break;
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			// Siirrot tornin ruudusta ylöspäin
			int y = ruutu.getY();
			while (y < 7) {
				y++;

				// onko ruudussa nappula
				if (lauta[ruutu.getX()][y].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(ruutu.getX(), y));
					break;
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}

			// Siirrot tornin ruudusta alaspäin
			y = ruutu.getY();
			while (y > 0) {
				y--;

				// Onko ruudussa nappula
				if (lauta[ruutu.getX()][y].getNappula() != null) {

					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
						break;
					}

					// jos ei oma -> vihollinen
					siirrot.add(new Ruutu(ruutu.getX(), y));
					break;
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
		}
		return siirrot;

	}

	public void ekaSiirtoTehty() {
		this.ekaSiirto = true;
	}
	
	public void kumoaEkaSiirto() {
		this.ekaSiirto = false;
	}

	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}
}
