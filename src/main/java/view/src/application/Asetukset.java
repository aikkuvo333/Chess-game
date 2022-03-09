package view.src.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Asetukset {
	private static final String CONFIG_FILE = "config.properties";
	private boolean laudanAnimaatio;
	private boolean peruutus;
	private boolean darkMode;
	
	
	public Asetukset() {
		haeAsetukset();
	}

	public void setLaudanAnimaatio(boolean laudanAnimaatio) {
		this.laudanAnimaatio = laudanAnimaatio;
		System.out.println("Laudan animaatio asetettu: " + this.laudanAnimaatio);
	}

	public void setPeruutus(boolean peruutus) {
		this.peruutus = peruutus;
		System.out.println("Peruutus asetettu: " + this.peruutus);
	}
	
	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
		System.out.println("DarkMode asetettu: " + this.darkMode);
	}
	
	public boolean isLaudanAnimaatio() {
		return laudanAnimaatio;
	}

	public boolean isPeruutus() {
		return peruutus;
	}
	
	public boolean isDarkMode() {
		return darkMode;
	}
	
	public static void initConfig() throws IOException {
		Properties properties = new Properties();
		
		try(FileInputStream ip = new FileInputStream("config.properties")) {
			ip.close();
		} catch (FileNotFoundException e) {
			properties.setProperty("laudanAnimaatio", "true");
			properties.setProperty("peruutus", "true");
			properties.setProperty("darkMode", "false");
			FileWriter writer = new FileWriter("config.properties");
			properties.store(writer, "Laudan asetukset");
			writer.close();
		}
	}
	
	private void haeAsetukset() {
		Properties properties = new Properties();
		try (FileInputStream ip = new FileInputStream("config.properties")) {
			System.out.println("Haetaan asetuksia...");
			properties.load(ip);
			laudanAnimaatio = Boolean.parseBoolean(properties.getProperty("laudanAnimaatio"));
			peruutus = Boolean.parseBoolean(properties.getProperty("peruutus"));
			darkMode = Boolean.parseBoolean(properties.getProperty("darkMode"));
			
			System.out.println(Boolean.parseBoolean(properties.getProperty("laudanAnimaatio")));
			System.out.println(Boolean.parseBoolean(properties.getProperty("peruutus")));
			System.out.println(Boolean.parseBoolean(properties.getProperty("darkMode")));

			ip.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void asetaAsetukset() {
		Properties properties = new Properties();
		try (FileOutputStream op = new FileOutputStream("config.properties")) {
			properties.setProperty("laudanAnimaatio", String.valueOf(laudanAnimaatio));
			properties.setProperty("peruutus", String.valueOf(peruutus));
			properties.setProperty("darkMode", String.valueOf(darkMode));
			properties.store(op, null);
			op.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
