package view.src.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Luokka joka sisältää käyttöliittymän pelaamiseen liittyvät pelilliset
 * asetukset
 * 
 * @author Santeri Kuusisto
 * 
 */
public class Asetukset {

	/**
	 * boolean arvo laudan animaatiolle
	 */
	private boolean laudanAnimaatio;

	/**
	 * boolean arvo peliäänille
	 */
	private boolean aanet;

	/**
	 * Konstruktori, joka kutsuu metodia joka hakee tekstitiedostosta
	 * oletusasetukset.
	 */
	public Asetukset() {
		haeAsetukset();
	}

	/**
	 * Metodi joka asettaa laudan animaation päälle tai pois.
	 * 
	 * @param laudanAnimaatio boolean arvo, joka ollessaan true tarkoittaa että
	 *                        laudan animaatio on päällä.
	 */
	public void setLaudanAnimaatio(boolean laudanAnimaatio) {
		this.laudanAnimaatio = laudanAnimaatio;
		System.out.println("Asetukset setLaudanAnimaatio(): " + this.laudanAnimaatio);
	}

	/**
	 * Metodi joka asettaa äänet päälle tai pois.
	 * 
	 * @param aanet boolean arvo, joka ollessaan true tarkoittaa että äänet ovat
	 *              päällä.
	 */
	public void setAanet(boolean aanet) {
		this.aanet = aanet;
		System.out.println("Asetukset setAanet(): " + this.aanet);
	}

	/**
	 * Metodi joka palauttaa boolean arvon, joka kertoo onko laudan animaatio päällä
	 * vai ei
	 * 
	 * @return true jos laudan animaatio on päällä
	 */
	public boolean isLaudanAnimaatio() {
		return laudanAnimaatio;
	}

	/**
	 * Metodi joka palauttaa boolean arvon, joka kertoo onko äänet päällä vai ei
	 * 
	 * @return true jos äänet on päällä
	 */
	public boolean isAanet() {
		return aanet;
	}

	/**
	 * Asettaa oletusarvot, jos properties-tiedostoa ei löydy
	 * 
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	public static void initConfig() throws IOException {
		Properties properties = new Properties();

		try (FileInputStream ip = new FileInputStream("config.properties")) {
			ip.close();
		} catch (FileNotFoundException e) {
			properties.setProperty("laudanAnimaatio", "true");
			properties.setProperty("aanet", "true");
			FileWriter writer = new FileWriter("config.properties");
			properties.store(writer, "Laudan asetukset");
			writer.close();
		}
	}

	/**
	 * Hakee tekstitiedosta tiedot asetuksia varten
	 */
	private void haeAsetukset() {
		Properties properties = new Properties();
		try (FileInputStream ip = new FileInputStream("config.properties")) {
			properties.load(ip);

			laudanAnimaatio = Boolean.parseBoolean(properties.getProperty("laudanAnimaatio"));
			aanet = Boolean.parseBoolean(properties.getProperty("aanet"));

			ip.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * Asettaa tekstitiedosta haetut asetuksen tiedot
	 */
	public void asetaAsetukset() {
		Properties properties = new Properties();
		try (FileOutputStream op = new FileOutputStream("config.properties")) {

			properties.setProperty("laudanAnimaatio", String.valueOf(laudanAnimaatio));
			properties.setProperty("aanet", String.valueOf(aanet));
			properties.store(op, null);

			op.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
