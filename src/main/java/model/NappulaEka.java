package model;

/**
 * Abstrakti luokkka NappulaEka perii luokan Nappulan ja tarjoaa
 * pohjan niille nappuloille, joiden on pidettävä kirjaa siitä, 
 * että onko ensimmäinen siirto tehty vai ei.
 * @author Elmo Vahvaselkä
 *
 */
public abstract class NappulaEka extends Nappula{
	/**
	 * <code>ekaSiirto</code> sisältää tiedon siitä, että onko kyseisen nappulan ensimmäinen siirto tehty.
	 */
	protected boolean ekaSiirto;
	
	/**
	 * Merkitsee nappulan ensimmäisen siirron tehdyksi.
	 */
	public void ekaSiirtoTehty() {
		this.ekaSiirto = true;
	}

	/**
	 * Palauttaa tiedon siitä, että onko ensimmäinen siirto tehty.
	 * @return <code>Booleanina</code> tiedon siitä, että onko sotilaan ensimmäinen siirto tehty. 
	 */
	public boolean getEkaSiirto() {
		return this.ekaSiirto;
	}
	
	/**
	 * Merkitsee nappulan ensimmäisen siirron tekemättömäksi.
	 */
	public void kumoaEkaSiirto() {
		this.ekaSiirto = false;
	}
}
