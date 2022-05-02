package view.src.application;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.NappulanTyyppi;
import model.NappulanVari;

/**
 * 
 * Luokka kontrolloi sotilaan korotukseen liittyvää käyttöliittymänäkymää
 * 
 * @author Santeri Kuusisto
 * 
 */
public class Korotus_kontrolleri {

	private Stage stage;

	/**
	 * Stringinä taustaväri vaalea korotuskontrollerin ruudulle
	 */
	private String vaalea = "-fx-background-color: #f1dbb1";

	/**
	 * Stringinä taustaväri tumma korotuskontrollerin ruudulle
	 */
	private String tumma = "-fx-background-color: #ba9b63";
	/**
	 * PeliNakyma olio lautakontrollerille
	 */
	private PeliNakyma lautaKontrolleri;
	/**
	 * korotettavan nappulan väri
	 */
	private NappulanVari vari;
	/**
	 * Stringinä väri
	 */
	private String variPNG;
	/**
	 * ImageView nappulan kuva
	 */
	private ImageView nappulaKuva;
	/**
	 * Kuvatiedostojen sijainti
	 */
	private String kuvaURI = "File:src/main/resources/images/";
	/**
	 * Stringinä kuvatiedostojen osoite
	 */
	private String nappulaURI;
	/**
	 * Pane olio, johon sijoitetaan nappulan kuva
	 */
	private Pane ruutu;
	/**
	 * Korotettavan nappulan kuvan varjo
	 */
	private DropShadow varjo;
	/**
	 * String taulukko joka sisältää kuvatiedostojen nimien osat
	 */
	private String[] nappulat = { "kuningatar", "torni", "lahetti", "hevonen" };

	/**
	 * GridPane asettuu AnchorPanen päälle
	 */
	@FXML
	private GridPane korotettavat;

	/**
	 * AnchorPane toimii korotuskontrollerinäkymän pohjana
	 */
	@FXML
	private AnchorPane korotus;

	/**
	 * Konstruktori joka luo korotuskontrollerin näkymän nappulat sen väriseksi
	 * jonka sotilasta ollaan korottamassa.
	 * 
	 * @param object Object olio joka castataan PeliNakyma olioksi
	 * @param vari   korotettavan sotilaan nappulan väri
	 */
	public Korotus_kontrolleri(Object object, NappulanVari vari) {
		lautaKontrolleri = (PeliNakyma) object;
		this.vari = vari;
	}

	/**
	 * Kutsutaan metodeja jotka luovat korotusnäkymän kuvakkeer ja asetetaan ne
	 * näkymään
	 */
	public void initialize() {
		lautaKontrolleri.toggleShadow();

		varjo = new DropShadow();
		varjo.setColor(Color.GRAY);
		varjo.setOffsetY(6);

		ruudut();
		asetaNappulat();
	}

	/**
	 * Ruutujen luonti korotusnäkymään.
	 */
	public void ruudut() {
		for (int i = 0; i < 4; i++) {
			String vari = i % 2 != 0 ? tumma : vaalea;
			ruutu = new Pane();
			ruutu.setStyle(vari);
			korotettavat.add(ruutu, i, 0);
		}
	}

	/**
	 * Metodi asettaa pelinappuloiden kuvat korotusnäkymään.
	 */
	public void asetaNappulat() {
		NappulanTyyppi tyyppi;

		if (vari == NappulanVari.VALKOINEN || vari == null) {
			variPNG = "_v.png";
		} else {
			variPNG = "_m.png";
		}

		for (int x = 0; x < nappulat.length; x++) {
			nappulaURI = kuvaURI + nappulat[x] + variPNG;

			switch (nappulat[x]) {
			case "kuningatar":
				tyyppi = NappulanTyyppi.KUNINGATAR;
				break;
			case "torni":
				tyyppi = NappulanTyyppi.TORNI;
				break;
			case "lahetti":
				tyyppi = NappulanTyyppi.LAHETTI;
				break;
			case "hevonen":
				tyyppi = NappulanTyyppi.RATSU;
				break;
			default:
				tyyppi = null;
				break;
			}

			if (tyyppi != null) {
				nappulaKuva = luoNappula(nappulaURI, this.ruutu, tyyppi);
				korotettavat.add(nappulaKuva, x, 0);
			}
		}

	}

	/**
	 * Palauttaa kuvan niistä nappuloista joiksi sotilas voidaan korottaa
	 * 
	 * @param kuvanURI Stringinä kuvan osoite
	 * @param pane     Pane olio johon kuva asetetaan
	 * @param tyyppi   NappulanTyyppi enum, jota kuva vastaa
	 * @return ImageView olio jota käyttäjä voi napsauttaa käyttöliittymässä
	 */
	public ImageView luoNappula(String kuvanURI, Pane pane, NappulanTyyppi tyyppi) {
		ImageView kuvanView = skaalaaKuvake(new Image(kuvanURI), pane);
		Double sizeX = kuvanView.getScaleX();
		Double sizeY = kuvanView.getScaleY();

		kuvanView.setOnMouseEntered(e -> {
			kuvanView.setEffect(varjo);
			kuvanView.setScaleX(sizeX * 1.2);
			kuvanView.setScaleY(sizeY * 1.2);
		});

		kuvanView.setOnMouseExited(e -> {
			kuvanView.setEffect(null);
			kuvanView.setScaleX(sizeX);
			kuvanView.setScaleY(sizeY);
		});

		kuvanView.setOnMouseClicked(e -> {
			valitse(tyyppi);
		});

		return kuvanView;
	}

	/**
	 * Ratkaisu kuvan koon asettamiseen laudan ruudun mukaan
	 * 
	 * @param image Image olio joka halutaan skaalata
	 * @param pane  Pane olio johon Imageview halutaan skaalata
	 * @return ImageView olio määrätyssä koossa
	 */
	public ImageView skaalaaKuvake(Image image, Pane pane) {
		ImageView imageView = new ImageView();
		imageView.fitWidthProperty().bind(pane.widthProperty());
		imageView.fitHeightProperty().bind(pane.heightProperty());
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setCache(true);
		imageView.setSmooth(true);

		return imageView;
	}

	/**
	 * Valitsee sen NappulanTyyppi tyypin joksi sotilas nappula halutaan korottaa ja
	 * välittää sen lautaKontrollerille
	 * 
	 * @param tyyppi käyttäjän valitsema korotettava nappulantyyppi
	 */
	public void valitse(NappulanTyyppi tyyppi) {
		System.out.println(tyyppi);
		lautaKontrolleri.valittuKorotus(tyyppi);
		poistu();
	}

	/**
	 * Sulkee korotusikkunan
	 */
	public void poistu() {
		lautaKontrolleri.toggleShadow();
		stage = (Stage) korotus.getScene().getWindow();
		stage.close();
	}

}