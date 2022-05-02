package view.src.application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.NappulanVari;

/**
 * Luokka joka sisältää pelilaudalla olevien pelinappuloiden kuvat
 * 
 * @author Santeri Kuusisto
 *
 */
public class Kuvakkeet {
	/**
	 * Kansio josta kuvat haetaan
	 */
	private static String kuvaURI = "File:src/main/resources/images/";

	/**
	 * Metodi joka palauttaa Sotilas -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getSotilas(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "sotilas" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa Hevonen -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getRatsu(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "hevonen" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa Torni -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getTorni(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "torni" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa Lähetti -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getLahetti(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "lahetti" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa Kuningatar -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getKuningatar(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "kuningatar" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa Kuningas -pelinappulan halutussa värissä
	 * 
	 * @param vari pelinappulan väri
	 * @return ImageView olio kuvasta halutussa värissä
	 */
	public static ImageView getKuningas(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "kuningas" + getVariURI(vari)));
	}

	/**
	 * Metodi joka palauttaa halutun string päätteen kuvien nimille toiston
	 * välttämiseksi.
	 * 
	 * @param vari pelinappulan väri
	 * @return Stringinä oikean päätteen kuvan nimelle
	 */
	private static String getVariURI(NappulanVari vari) {
		if (vari == NappulanVari.VALKOINEN) {
			return "_v.png";
		} else {
			return "_m.png";
		}
	}
}
