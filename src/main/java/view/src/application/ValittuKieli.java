package view.src.application;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author Elmo Vahvaselka 1.4.2022
 *
 */

public class ValittuKieli {
	private static ValittuKieli instance;
	private Locale locale = new Locale("en_US");
	private ResourceBundle bundle = ResourceBundle.getBundle("text/TextResources", locale);

	private ValittuKieli() {}

	public static ValittuKieli getInstance() {
		if (instance == null) {
			instance = new ValittuKieli();
		}
		return instance;
	}

	public Locale getLocale() {
		return locale;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public void setSuomi() {
		locale = new Locale("FI", "FI");
		bundle = ResourceBundle.getBundle("text/TextResources", locale);
	}

	public void setEnglanti() {
		locale = new Locale("en_US");
		bundle = ResourceBundle.getBundle("text/TextResources", locale);
	}

}
