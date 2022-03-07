package model;

public class DBSchema {
	public static String STATEMENT_CREATE_PELAAJA = "CREATE TABLE IF NOT EXISTS Pelaaja("
			+ "pelaajaId int(64) NOT NULL AUTO_INCREMENT, "
			+ "kayttajanimi varchar(255), "
			+ "PRIMARY KEY(pelaajaId)"
			+ ");";
	public static String STATEMENT_CREATE_PELI = "CREATE TABLE IF NOT EXISTS Peli("
			+ "peliId int(64) NOT NULL AUTO_INCREMENT, "
			+ "kesto DATE, pvm DATE, "
			+ "voittavaVari boolean, "
			+ "pelaaMustillaId int(64), "
			+ "pelaaValkoisillaId int(64), "
			+ "PRIMARY KEY(peliId), "
			+ "FOREIGN KEY (pelaaMustillaId) REFERENCES Pelaaja(pelaajaId), "
			+ "FOREIGN KEY (pelaaValkoisillaId) REFERENCES Pelaaja(pelaajaId)"
			+ ");";
	public static String STATEMENT_CREATE_RUUTU = "CREATE TABLE IF NOT EXISTS Ruutu("
			+ "ruutuId int(64) NOT NULL AUTO_INCREMENT, "
			+ "x int(64), "
			+ "y int(64), "
			+ "PRIMARY KEY(ruutuId)"
			+ ");";
	public static String STATEMENT_CREATE_SIIRTO = "CREATE TABLE IF NOT EXISTS Siirto("
			+ "siirtoId int(64) NOT NULL AUTO_INCREMENT, "
			+ "peliId int(64), "
			+ "ruutu int(64), "
			+ "PRIMARY KEY(siirtoId), "
			+ "FOREIGN KEY (ruutu) REFERENCES Ruutu(ruutuId)"
			+ ");";
}
