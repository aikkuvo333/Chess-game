package view.src.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/*
 * 
 * @author Santeri Kuusisto
 * 
 */
public class Asetukset {
	private static final String CONFIG_FILE = "config.properties";
	private boolean laudanAnimaatio;
	private boolean darkMode;
	private boolean aanet;
	
	public Asetukset() {
		haeAsetukset();
	}

	public void setLaudanAnimaatio(boolean laudanAnimaatio) {
		this.laudanAnimaatio = laudanAnimaatio;
		System.out.println("Asetukset setLaudanAnimaatio(): " + this.laudanAnimaatio);
	}
	
	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
		System.out.println("Asetukset setDarkMode(): " + this.darkMode);
	}
	
	public void setAanet(boolean aanet) {
		this.aanet = aanet;
		System.out.println("Asetukset setAanet(): " + this.aanet);
	}
	
	public boolean isLaudanAnimaatio() {
		return laudanAnimaatio;
	}
	
	public boolean isDarkMode() {
		return darkMode;
	}
	
	public boolean isAanet() {
		return aanet;
	}
	
	//Asettaa oletusarvot, jos properties-tiedostoa ei löydy
	public static void initConfig() throws IOException {
		Properties properties = new Properties();
		
		try(FileInputStream ip = new FileInputStream("config.properties")) {
			ip.close();
		} catch (FileNotFoundException e) {
			properties.setProperty("laudanAnimaatio", "true");
			properties.setProperty("darkMode", "false");
			properties.setProperty("aanet", "true");
			FileWriter writer = new FileWriter("config.properties");
			properties.store(writer, "Laudan asetukset");
			writer.close();
		}
	}
	
	private void haeAsetukset() {
		Properties properties = new Properties();
		try (FileInputStream ip = new FileInputStream("config.properties")) {
			properties.load(ip);
			
			laudanAnimaatio = Boolean.parseBoolean(properties.getProperty("laudanAnimaatio"));
			darkMode = Boolean.parseBoolean(properties.getProperty("darkMode"));
			aanet = Boolean.parseBoolean(properties.getProperty("aanet"));
			
			ip.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void asetaAsetukset() {
		Properties properties = new Properties();
		try (FileOutputStream op = new FileOutputStream("config.properties")) {
			
			properties.setProperty("laudanAnimaatio", String.valueOf(laudanAnimaatio));
			properties.setProperty("darkMode", String.valueOf(darkMode));
			properties.setProperty("aanet", String.valueOf(aanet));
			properties.store(op, null);
			
			op.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
