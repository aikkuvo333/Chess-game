package view.src.application;

import java.util.Locale;

/**
 * 
 * @author Elmo Vahvaselka 1.4.2022
 *
 */

public class ValittuKieli {
	private static ValittuKieli instance;
	private Locale locale = new Locale("en_US");

	private ValittuKieli() {
	}

	public static ValittuKieli getInstance() {
		if (instance == null) {
			instance = new ValittuKieli();
		}
		return instance;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setSuomi() {
		locale = new Locale("fi_FI");
	}

	public void setEnglanti() {
		locale = new Locale("en_US");
	}

}
