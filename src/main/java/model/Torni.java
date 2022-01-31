package model;

/**
* @author Elmo Vahvaselkä 29.1.2022
*/

import java.util.ArrayList;

public class Torni extends Nappula {

	private NappulanVari vari;
	private boolean ekaSiirto;

	public Torni(NappulanVari vari) {
		this.vari = vari;
		this.ekaSiirto = false;
	}

	@Override
	public ArrayList<Ruutu> getSiirrot(Ruutu ruutu, Ruutu[][] lauta) {
		ArrayList<Ruutu> siirrot = new ArrayList<>();
		{

			// Siirrot tornin ruudusta ylöspäin
			int x = ruutu.getX();
			while (x < 7) {
				x++;

				if (lauta[x][ruutu.getY()].getNappula() != null) {
					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
						break;
					}

					// onko ruudussa vihollinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() != vari) {
						siirrot.add(new Ruutu(x, ruutu.getY()));
						break;
					}
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			// Siirrot tornin ruudusta alaspäin
			x = ruutu.getX();
			while (x > 0) {
				x--;

				if (lauta[x][ruutu.getY()].getNappula() != null) {
					// onko ruudussa oman värinen
					if (lauta[x][ruutu.getY()].getNappula().getVari() != null) {
						if (lauta[x][ruutu.getY()].getNappula().getVari() == vari) {
							break;
						}

						// onko ruudussa vihollinen
						if (lauta[x][ruutu.getY()].getNappula().getVari() != vari) {
							siirrot.add(new Ruutu(x, ruutu.getY()));
							break;
						}
					}
				}
				siirrot.add(new Ruutu(x, ruutu.getY()));
			}

			//Siirrot tornin ruudusta oikealle
			int y = ruutu.getY();
			while (y < 7) {
				y++;

				if (lauta[ruutu.getX()][y].getNappula() != null) {
					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() != null) {
						if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
							break;
						}

						// onko ruudussa vihollinen
						if (lauta[ruutu.getX()][y].getNappula().getVari() != vari) {
							siirrot.add(new Ruutu(ruutu.getX(), y));
							break;
						}
					}
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
			
			//Siirrot tornin ruudusta vasemmalle
			y = ruutu.getY();
			while ( y > 0) {
				y--;

				if (lauta[ruutu.getX()][y].getNappula() != null) {
					// onko ruudussa oman värinen
					if (lauta[ruutu.getX()][y].getNappula().getVari() != null) {
						if (lauta[ruutu.getX()][y].getNappula().getVari() == vari) {
							break;
						}

						// onko ruudussa vihollinen
						if (lauta[ruutu.getX()][y].getNappula().getVari() != vari) {
							siirrot.add(new Ruutu(ruutu.getX(), y));
							break;
						}
					}
				}
				siirrot.add(new Ruutu(ruutu.getX(), y));
			}
		}
		return siirrot;
	}

	@Override
	public NappulanVari getVari() {
		return this.vari;
	}

	public void ensimmaineSiirtoTehty() {
		this.ekaSiirto = true;
	}

	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}

}
