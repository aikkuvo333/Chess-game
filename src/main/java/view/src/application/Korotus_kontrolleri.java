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

/*
 * 
 * @author Santeri Kuusisto
 * 
 */
public class Korotus_kontrolleri {

	private Stage stage;
	private String vaalea = "-fx-background-color: #f1dbb1";
	private String tumma = "-fx-background-color: #ba9b63";
	private Lauta_kontrolleri lautaKontrolleri;
	private NappulanVari vari;
	private String variPNG;
	private ImageView nappulaKuva;
	private String kuvaURI = "File:src/main/resources/images/";
	private String nappulaURI;
	private Pane ruutu;
	private DropShadow varjo;
	private String[] nappulat = { "kuningatar", "torni", "lahetti", "hevonen" };

	@FXML
	private GridPane korotettavat;

	@FXML
	private AnchorPane korotus;

	public Korotus_kontrolleri(Object object, NappulanVari vari) {
		lautaKontrolleri = (Lauta_kontrolleri) object;
		this.vari = vari;
	}

	public void initialize() {
		lautaKontrolleri.toggleShadow();
		
		varjo = new DropShadow();
		varjo.setColor(Color.GRAY);
		varjo.setOffsetY(6);

		ruudut();
		asetaNappulat();
	}

	public void ruudut() {
		for (int i = 0; i < 4; i++) {
			String vari = i % 2 != 0 ? tumma : vaalea;
			ruutu = new Pane();
			ruutu.setStyle(vari);
			korotettavat.add(ruutu, i, 0);
		}
	}

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

	public ImageView skaalaaKuvake(Image image, Pane pane) {
		ImageView imageView = new ImageView();
		imageView.fitWidthProperty().bind(pane.widthProperty());
		imageView.fitHeightProperty().bind(pane.heightProperty());
		imageView.setImage(image);
		//imageView.setScaleX(imageView.getScaleX()*0.8);
		imageView.setPreserveRatio(true);
		imageView.setCache(true);
		imageView.setSmooth(true);
		
		return imageView;
	}

	public void valitse(NappulanTyyppi tyyppi) {
		System.out.println(tyyppi);
		lautaKontrolleri.valittuKorotus(tyyppi);
		poistu();
	}

	public void poistu() {
		lautaKontrolleri.toggleShadow();
		stage = (Stage) korotus.getScene().getWindow();
		stage.close();
	}
	
	public void exit() {
		System.out.println("EXIT");
	}
}