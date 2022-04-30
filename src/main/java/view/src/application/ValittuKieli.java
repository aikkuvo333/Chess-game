package view.src.application;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Singleton luokka, jota käytetään sovelluksen kielen vaihtamiseen.
 * @author Elmo Vahvaselka 1.4.2022
 */

public class ValittuKieli {
	
	/**
	 * Singleton luokan mukainen instanssimuuttuja.
	 */
	private static ValittuKieli instance;
	
	/**
	 * Valittu locale. Oletuksena "en_US";
	 */
	private Locale locale = new Locale("en_US");
	
	/**
	 * Valituun localen mukaiset tekstiresurssit.
	 */
	private ResourceBundle bundle = ResourceBundle.getBundle("text/TextResources", locale);

	/**
	 * Singletonin mukainen private konstruktori.
	 */
	private ValittuKieli() {}

	/**
	 * Singletonin mukainen getteri instanssille. Luo uuden instanssin, mikäli sitä ei ole.
	 * @return Singletonin instanssin.
	 */
	public static ValittuKieli getInstance() {
		if (instance == null) {
			instance = new ValittuKieli();
		}
		return instance;
	}

	/**
	 * Getteri localelle.
	 * @return valiton localen Locale-oliona.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Getteri tekstiresursseille.
	 * @return ResourceBundle-olion valitun localen mukaan.
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	/**
	 * Asettaa valituksi localeksi suomen ja vaihtaa tekstiresurssit localen mukaiseksi.
	 */
	public void setSuomi() {
		locale = new Locale("FI", "FI");
		bundle = ResourceBundle.getBundle("text/TextResources", locale);
	}

	/**
	 * Asettaa valituksi localeksi englannin ja vaihtaa tekstiresurssit localen mukaiseksi.
	 */
	public void setEnglanti() {
		locale = new Locale("en_US");
		bundle = ResourceBundle.getBundle("text/TextResources", locale);
	}

}
