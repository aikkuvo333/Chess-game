package view.src.application;

/**
 * @author Elmo Vahvaselkä
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValittuKieliTest {
	
	@Test
	@DisplayName("Onko singleton")
	public void onkoSingleton() {
		assertEquals(ValittuKieli.getInstance().hashCode(), ValittuKieli.getInstance().hashCode(),"Singletonien hash codet eivät olleet samat.");
	}
	
	@Test
	@DisplayName("Onko oletus en_US")
	public void onkoEnkku() {
		assertEquals("en_us", ValittuKieli.getInstance().getLocale().getLanguage());
	}
	
	@Test
	@DisplayName("Kielen vaihtaminen onnistuu englannista suomeen ja päinvastoin")
	public void kielenVaihto() {
		ValittuKieli.getInstance().setSuomi();
		assertEquals("fi", ValittuKieli.getInstance().getLocale().getLanguage(), "Kieli ei vaihtunut suomeen");
		ValittuKieli.getInstance().setEnglanti();
		assertEquals("en_us", ValittuKieli.getInstance().getLocale().getLanguage(), "Kieli ei vaihtunut englantiin");
	}
	
	@Test
	@DisplayName("getBundle toimii oikein")
	public void getBundle() {
		assertEquals("text/TextResources", ValittuKieli.getInstance().getBundle().getBaseBundleName());
	}
}
