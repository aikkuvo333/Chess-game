package view.src.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.IKontrolleri;
import controller.Kontrolleri;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Nappula;
import model.NappulanTyyppi;
import model.NappulanVari;
import dao.Pelaaja;
import model.Ruutu;
import view.IPelinakyma;
import java.io.*;
import javax.sound.sampled.*;

/**
 * Luokka sisältää käyttöliittymän Pikapeli ja tilastoitu peli -näkymien
 * toiminnallisuudet
 * 
 * @author Santeri Kuusisto
 * 
 */

public class PeliNakyma implements IPelinakyma {

	private Asetukset asetukset;
	private IKontrolleri kontrolleri;
	private ImageView nappulaKuva;
	private String vaalea = "-fx-background-color: #f1dbb1";
	private String tumma = "-fx-background-color: #ba9b63";
	private String valittu = "-fx-background-color: rgba(0, 255, 0, 0.2)";
	private Pane ruutu;
	private Node valittuNappula = null;
	public boolean peruutusKaynnissa = false;
	private NappulanTyyppi korotusTyyppi = null;
	private Pelaaja pelaaja1;
	private Pelaaja pelaaja2;
	private HashMap<NappulanVari, String> pelaajat;

	// Efektejä
	private String sound1 = "src/main/resources/sounds/sound1.wav";
	private String sound2 = "src/main/resources/sounds/sound2.wav";
	private DropShadow varjo;
	private RotateTransition rotate = new RotateTransition();
	private boolean kaantynyt = false;
	private boolean onkoTilastoitu;

	// Nappulan apumuuttujia
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	private boolean siirtoAloitettu = false;
	private ArrayList<Node> mahdSiirrot;

	// Asetuksia
	private boolean kaantyminen;
	private boolean peruutus;
	private boolean aanet;

	private boolean stageShadow = false;

	// locale
	private FXMLLoader loader;
	private ResourceBundle bundle = ValittuKieli.getInstance().getBundle();

	@FXML
	private AnchorPane lautaNakyma;

	@FXML
	private GridPane pelilauta;

	@FXML
	private Text vuorossa;

	@FXML
	private Text seuraava;

	@FXML
	private Text aika;

	@FXML
	public Button peruuta;

	@FXML
	private Text vuoro;

	@FXML
	private Text shakki;

	@FXML
	void handleLaudanAsetukset(ActionEvent event) throws IOException {
		System.out.println("Klikattiin asetukset");
		InitFXML.avaaFxml(new Asetukset_kontrolleri(this, asetukset), "Asetukset.fxml", "Asetukset",
				Modality.APPLICATION_MODAL, StageStyle.DECORATED, lautaNakyma);
	}

	@FXML
	private void handleLuovuta(ActionEvent event) throws IOException {
		System.out.println("Painettiin: Luovuta");
		luovuta();
	}

	@FXML
	private void handlePeruuta(ActionEvent event) {
		System.out.println("Painettiin: Peruuta");
	}

	@FXML
	void handleRuutu(MouseEvent e) {

	}

	/**
	 * Tilastoimattoman pelin konstruktori
	 */
	public PeliNakyma() {
		onkoTilastoitu = false;
		this.pelaaja1 = new Pelaaja(bundle.getString("OletusNimiValkoinen"));
		this.pelaaja2 = new Pelaaja(bundle.getString("OletusNimiMusta"));
		peruutus = true;
	}

	/**
	 * Tilastoidun pelin konstruktori
	 * 
	 * @param pelaaja1 Pelaaja olio joka osallistuu peliin
	 * @param pelaaja2 Toinen Pelaaja olio joka osallistuu peliin
	 */
	public PeliNakyma(Pelaaja pelaaja1, Pelaaja pelaaja2) {
		onkoTilastoitu = true;
		this.pelaaja1 = pelaaja1;
		this.pelaaja2 = pelaaja2;
		peruutus = false;
	}

	EventHandler<ActionEvent> lautaHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			pelilauta.widthProperty().addListener((obs, oldVal, newVal) -> {
				System.out.println("laudan koko muuttui");
			});
		}

	};

	public void initialize() throws IOException {

		vuorossa.setText(pelaaja1.getKayttajaTunnus());
		seuraava.setText(pelaaja2.getKayttajaTunnus());

		kontrolleri = new Kontrolleri(this);
		kontrolleri.aloitaPeli(onkoTilastoitu);

		pelaajat = new HashMap<NappulanVari, String>();

		varjo = new DropShadow();
		varjo.setColor(Color.GRAY);
		varjo.setOffsetY(6);

		// Valkoinen aloittaa shakin
		vuorossa.setText(pelaaja1.getKayttajaTunnus());
		seuraava.setText(pelaaja2.getKayttajaTunnus());

		pelaajat.put(NappulanVari.VALKOINEN, pelaaja1.getKayttajaTunnus());
		pelaajat.put(NappulanVari.MUSTA, pelaaja2.getKayttajaTunnus());

		asetaRuudut();
		asetaNappulat();
		kaannaLauta();
		asetaVuoro();

		if (peruutus == false) {
			peruuta.setVisible(false);
		}

		Asetukset.initConfig();
		asetukset = new Asetukset();
		kaantyminen = asetukset.isLaudanAnimaatio();
		aanet = asetukset.isAanet();

		// Asettaa laudan elementit keskelle ruutua ikkunan venytyksen yhteydessä
		lautaNakyma.widthProperty().addListener((obs, oldVal, newVal) -> {
			double x;
			if (pelilauta.getWidth() != 0) {
				x = (double) newVal / 2 - pelilauta.getWidth() / 2;
				pelilauta.setLayoutX(x);
				vuoro.setLayoutX(x);
				vuorossa.setLayoutX((lautaNakyma.getWidth() - (x + pelilauta.getWidth())) / 2
						+ (x + pelilauta.getWidth()) - vuorossa.getLayoutBounds().getWidth() / 2);
				seuraava.setLayoutX((lautaNakyma.getWidth() - (x + pelilauta.getWidth())) / 2
						+ (x + pelilauta.getWidth()) - seuraava.getLayoutBounds().getWidth() / 2);
			}
		});

		// Säilyttää laudan suhteet koon muuttumisen yhteydessä
		pelilauta.heightProperty().addListener((obs, oldVal, newVal) -> {
			pelilauta.setPrefWidth((double) newVal);
		});

		System.out.println(vuorossa.getLayoutX());
	}

	/**
	 * Getteri pelaajatunnukselle nappulanvärin perusteella
	 * @param vari sen pelaajan väri, jonka nimi halutaan
	 * @return Stringinä halutun värin pelaajan nimen
	 */
	public String getNimiByVari(NappulanVari vari) {
		return pelaajat.get(vari);
	}

	/**
	 * Getteri nappulan värille pelaajatunnuksen perusteella
	 * @param nimi sen pelaajan nimi, jonka väri halutaan
	 * @return NappulanVari enumina halutun pelaajan värin
	 */
	public NappulanVari getVariByNimi(String nimi) {
		NappulanVari vari = null;

		if (pelaajat.containsValue(nimi)) {
			for (Map.Entry<NappulanVari, String> entry : pelaajat.entrySet()) {
				if (Objects.equals(entry.getValue(), nimi)) {
					vari = entry.getKey();
				}
			}
		}

		return vari;
	}

	/**
	 * Metodi joka kääntää laudan
	 */
	public void kaannaLauta() {

		System.out.println("Käännetään lautaa: " + kaantyminen);

		if (kaantyminen) {
			rotate.setDuration(Duration.millis(400));
			rotate.setCycleCount(1);
			rotate.setByAngle(180);
			rotate.setNode(pelilauta);
			rotate.play();
		}

		if (!kaantynyt && !kaantyminen) {
			pelilauta.setRotate(180);
		} else {
			pelilauta.setRotate(0);
		}

		if (!kaantynyt) {
			for (Node node : pelilauta.getChildren()) {
				node.setRotate(180);
			}

		} else {
			for (Node node : pelilauta.getChildren()) {
				node.setRotate(0);
			}
		}

		kaantynyt = !kaantynyt;

	}

	public void aani(String aani) {
		if (aanet) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(aani)));
				clip.start();
				Thread.sleep(clip.getMicrosecondLength() / 1000);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

	/**
	 * Nappuloiden kuvien asettaminen pelilaudalle
	 */
	public void asetaNappulat() {
		Nappula nappula;
		Ruutu[][] ruutu = kontrolleri.getPelitilanne();

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				nappula = ruutu[x][y].getNappula();
				if (nappula != null) {
					ImageView nappulaImage;
					NappulanVari vari = nappula.getVari();

					switch (nappula.getTyyppi()) {
					case SOTILAS:
						nappulaImage = Kuvakkeet.getSotilas(vari);
						break;

					case TORNI:
						nappulaImage = Kuvakkeet.getTorni(vari);
						break;

					case RATSU:
						nappulaImage = Kuvakkeet.getRatsu(vari);
						break;

					case LAHETTI:
						nappulaImage = Kuvakkeet.getLahetti(vari);
						break;

					case KUNINGATAR:
						nappulaImage = Kuvakkeet.getKuningatar(vari);
						break;

					case KUNINGAS:
						nappulaImage = Kuvakkeet.getKuningas(vari);
						break;

					default:
						nappulaImage = null;
						break;
					}

					if (nappulaImage != null) {
						nappulaKuva = luoNappula(nappulaImage, this.ruutu);
						pelilauta.add(nappulaKuva, x, y);

					}
				}
			}
		}
	}

	public void paivitaLauta() {
		pelilauta.getChildren().clear();
		asetaRuudut();
		asetaNappulat();
	}

	/**
	 * Ruutujen asettaminen laudalle
	 */
	public void asetaRuudut() {
		int col = 8;
		int row = 8;
		int count = 1;

		for (int i = 0; i < row; i++) {
			count++;
			for (int j = 0; j < col; j++) {
				count++;
				addRuutu(i, j, count);
			}
		}

		System.out.println("Ruudut asetettu");
	}

	/**
	 * Ratkaisu kuvan koon asettamiseen laudan ruudun mukaan
	 * 
	 * @param imageView ImageView olio joka halutaan skaalata
	 * @param pane      Pane olio johon Imageview halutaan skaalata
	 * @return ImageView olio määrätyssä koossa
	 */
	public ImageView skaalaaKuvake(ImageView imageView, Pane pane) {
		imageView.setSmooth(true);

		if (pane != null) {
			imageView.fitWidthProperty().bind(pane.widthProperty());
			imageView.fitHeightProperty().bind(pane.heightProperty());
		}

		imageView.setScaleX(imageView.getScaleX() * 0.8);
		imageView.setScaleY(imageView.getScaleY() * 0.9);

		return imageView;
	}

	/**
	 * Lisää ruudun GridPaneen
	 * 
	 * @param rowIndex rivi indeksi johon ruutu luotaan
	 * @param colIndex sarake indeksi johon ruutu luodaan
	 * @param count    luku joka ylläpitää tiedon siitä onko indeksi parillinen vai
	 *                 pariton
	 */
	public void addRuutu(int rowIndex, int colIndex, int count) {
		ruutu = new Pane();
		String vari = count % 2 == 0 ? tumma : vaalea;

		ruutu.setStyle(vari);
		pelilauta.setAlignment(Pos.CENTER);
		pelilauta.add(ruutu, colIndex, rowIndex);

		// Ruutu clickHandler
		ruutu.setOnMouseClicked(e -> {
			System.out.printf("ruutu [%d, %d]%n", colIndex, rowIndex);
			teeSiirto(colIndex, rowIndex);
		});

	}

	/**
	 * getteri valitulla ruudulla olevalle nappulalle
	 * @param col ruudun sarake, jonka nappula halutaan hakea
	 * @param row ruudun rivi, jonka nappula halutaan hakea
	 * @return Node palauttaa sen nappulan kuvan, joka sijaitsee siinä ruudussa jolta se on pyydetty
	 */
	public Node getRuudunNappula(int col, int row) {
		Node kuva = null;
		ObservableList<Node> children = pelilauta.getChildren();

		for (Node node : children) {
			if (node.toString().contains("ImageView")) {

				if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
					kuva = node;
					break;
				}
			}
		}

		return kuva;
	}

	/**
	 * Nappulakuvan luonti
	 * @param nappulaKuvake kuva siitä nappulasta joka halutaan luoda
	 * @param ruutu Pane johon kuva halutaan asettaa
	 * @return ImageView palauttaa halutun nappulan skaalatun kuvan 
	 */
	public ImageView luoNappula(ImageView nappulaKuvake, Pane ruutu) {
		ImageView kuvanView = skaalaaKuvake(nappulaKuvake, ruutu);
		kuvanView.setMouseTransparent(true);

		return kuvanView;
	}

	/**
	 * Nappulan napsautuksen ulkonäön muuttaminen  
	 * @param nappula napsautettu pelinappula
	 * @param onkoValittu boolean arvo, true jos hiiri on napsauttanut valittua nappulaa
	 */
	public void nappulaHighlight(Node nappula, boolean onkoValittu) {

		if (onkoValittu) {
			valittuNappula.setEffect(varjo);
		} else {
			valittuNappula.setEffect(null);
		}
	}

	/**
	 * Vaihtaa vuoron ja kääntää lautaa
	 */
	public void vaihdaVuoro() {
		asetaVuoro();
		kaannaLauta();
	}

	/**
	 * Asettaa vuoron kontrollerin mukaan
	 */
	public void asetaVuoro() {
		vuoro.setText(bundle.getString("PeliVuorossaTxt") + ": " + getNimiByVari(kontrolleri.getVuoro()));
		vuorossa.setText(getNimiByVari(kontrolleri.getVuoro()));

		if (kontrolleri.getVuoro() == NappulanVari.VALKOINEN) {
			seuraava.setText(getNimiByVari(NappulanVari.MUSTA));
		} else {
			seuraava.setText(getNimiByVari(NappulanVari.VALKOINEN));
		}
	}

	/**
	 * Palauttaa listan joka sisältää mahdolliset siirrot käyttöliittymänäkymässä
	 * @param siirrot lista joka sisältää Ruutu oliot
	 * @return Lista joka sisätää Node oliot mahdollisista siirtopaikoista taulukon arvoina
	 */
	public ArrayList<Node> naytaSiirrot(ArrayList<Ruutu> siirrot) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Pane pane;

		System.out.println("Siirrot: ");
		for (Ruutu ruutu : siirrot) {
			pane = new Pane();
			pane.setStyle(valittu);
			pane.setMouseTransparent(true);
			nodes.add(pane);
			pelilauta.add(pane, ruutu.getX(), ruutu.getY());
			System.out.println(ruutu.getX() + " " + ruutu.getY());
		}

		return nodes;
	}

	/**
	 * Tehdään nappulan siirto ja lähetetään kontrolleriin
	 * 
	 * @param col sarakkeen arvo johon siirto tehdään
	 * @param row rivin arvo johon siirto tehdään
	 */
	public void teeSiirto(int col, int row) {

		if (!siirtoAloitettu) {
			mistaX = col;
			mistaY = row;
			valittuNappula = getRuudunNappula(mistaX, mistaY);
			System.out.println("VALITTU NAPPULA: " + valittuNappula);

			if (valittuNappula != null) {
				ArrayList<Ruutu> siirrot = kontrolleri.getSiirrotNappulalle(col, row);
				mahdSiirrot = naytaSiirrot(siirrot);
				nappulaHighlight(valittuNappula, true);
				siirtoAloitettu = true;

				aani(sound2);
				System.out.println(kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula().getTyyppi() + " "
						+ kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula().getVari() + " "
						+ kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula());
			}

		} else if (!(mistaX == col && mistaY == row) && valittuNappula != null) {
			mihinX = col;
			mihinY = row;

			System.out.printf("Siirtopyyntö ruudusta [%d, %d] ruutuun [%d, %d]%n", mistaX, mistaY, mihinX, mihinY);

			if (kontrolleri.teeSiirto(mistaX, mistaY, mihinX, mihinY)) {
				paivitaLauta();
				aani(sound1);
				vaihdaVuoro();
			}

			nappulaHighlight(valittuNappula, false);
			siirtoAloitettu = false;
			valittuNappula = null;

		} else {
			System.out.println("Siirto peruutettu");
			nappulaHighlight(valittuNappula, false);
			siirtoAloitettu = false;
		}

		if (mahdSiirrot != null && !siirtoAloitettu) {
			for (Node node : mahdSiirrot) {
				pelilauta.getChildren().remove(node);
			}

			mahdSiirrot = null;
		}
	}

	/**
	 * Luovutuksen varmistus -ikkunan luonti
	 */
	public void luovuta() {
		toggleShadow();

		ImageView luovutusLogo;
		Stage stage = (Stage) lautaNakyma.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert(type, "");

		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);

		alert.getDialogPane().setContentText(bundle.getString("PeliLuovutuksenVarmistaminenTxt"));
		alert.getDialogPane().setHeaderText(bundle.getString("PeliLuovutuksenVarmistaminen2Txt") + ", "
				+ getNimiByVari(kontrolleri.getVuoro()) + "?");
		alert.setTitle(bundle.getString("PeliLuovutuksenOtsikkoTxt"));

		luovutusLogo = Kuvakkeet.getKuningas(kontrolleri.getVuoro());
		luovutusLogo = skaalaaKuvake(luovutusLogo, null);
		luovutusLogo.setFitHeight(70);
		luovutusLogo.setFitWidth(70);
		alert.getDialogPane().setGraphic(luovutusLogo);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			System.out.println(kontrolleri.getVuoro() + " luovuttaa");
			kontrolleri.luovuta(/*
								 * kontrolleri.getVuoro() == NappulanVari.MUSTA ? NappulanVari.VALKOINEN :
								 * NappulanVari.MUSTA
								 */
			);
		} else if (result.get() == ButtonType.CANCEL) {
			System.out.println(kontrolleri.getVuoro() + " ei luovuta");
		}

		toggleShadow();
	}

	public void shakkiHuomautus() {
		shakki.setVisible(true);
		PauseTransition visiblePause = new PauseTransition(Duration.seconds(4));
		visiblePause.setOnFinished(event -> shakki.setVisible(false));
		visiblePause.play();
	}

	public void avaaKorotus() throws IOException {
		InitFXML.avaaFxml(new Korotus_kontrolleri(this, kontrolleri.getVuoro()), "korotus.fxml", "Sotilaan korotus",
				Modality.APPLICATION_MODAL, StageStyle.UNDECORATED, lautaNakyma);
	}

	public void valittuKorotus(NappulanTyyppi tyyppi) {
		korotusTyyppi = tyyppi;
	}

	/**
	 * Asettaa lautanäkymään blur-efektin
	 */
	public void toggleShadow() {
		if (!stageShadow) {
			lautaNakyma.setEffect(new BoxBlur(5, 5, 5));
		} else {
			lautaNakyma.setEffect(null);
		}

		stageShadow = !stageShadow;
	}

	/**
	 * Metodi joka luo erillisen ikkunan voittajan ilmoittamiseksi
	 * 
	 * @param vari voittajan nappuloiden väri
	 */
	public void voittoIkkuna(NappulanVari vari) {
		ImageView voittoLogo;
		Stage stage = (Stage) lautaNakyma.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.INFORMATION;
		Alert alert = new Alert(type, "");

		voittoLogo = Kuvakkeet.getKuningas(vari);
		voittoLogo = skaalaaKuvake(voittoLogo, null);
		voittoLogo.setFitHeight(70);
		voittoLogo.setFitWidth(70);

		alert.getDialogPane().setGraphic(voittoLogo);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		alert.getDialogPane().setHeaderText(getNimiByVari(vari) + " " + bundle.getString("PeliVoittoikkunaTxt"));
		alert.getDialogPane().setContentText(bundle.getString("PeliVoittoikkunaPaluuTxt"));
		alert.setTitle(bundle.getString("PeliVoittoikkunaOtsikkoTxt"));

		@SuppressWarnings("unused")
		Optional<ButtonType> result = alert.showAndWait();

		try {
			// Parent root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
			loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
			loader.setResources(bundle);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Asetusten asettaminen lautanäkymään
	 * @param arvo 
	 */
	public void asetaKaantyminen(boolean arvo) {
		kaantyminen = arvo;
		System.out.println("LAUDAN KÄÄNTYMINEN: " + kaantyminen);
	}

	public void asetaAanet(boolean arvo) {
		aanet = arvo;
		System.out.println("ÄÄNET: " + kaantyminen);
	}

	@Override
	public void siirtoAiheuttiShakin() {
		System.out.println("Shakki!");
		shakkiHuomautus();
	}

	@Override
	public NappulanTyyppi korota() {
		try {
			avaaKorotus();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (korotusTyyppi == null) {
			korotusTyyppi = NappulanTyyppi.KUNINGATAR;
		}

		return korotusTyyppi;
	}

	/**
	 * Metodi ilmoittaa pelin voittajan erilliseen ikkunaan
	 * 
	 * @param pelaaja Pelaaja olio joka on voittanut pelin
	 */
	@Override
	public void pelinVoitti(Pelaaja pelaaja) {
		System.out.println("PELIN VOITTI " + pelaaja.getKayttajaTunnus());
		voittoIkkuna(getVariByNimi(pelaaja.getKayttajaTunnus()));
	}

	/**
	 * Metodi palauttaa Pelaaja olion joka pelaa valkoisilla nappuloilla.
	 * 
	 * @return Pelaaja olio joka pelaa valkoisilla nappuloilla.
	 */
	@Override
	public Pelaaja getValkoinenPelaaja() {
		return pelaaja1;
	}

	/**
	 * Metodi palauttaa Pelaaja olion joka pelaa mustilla nappuloilla.
	 * 
	 * @return Pelaaja olio joka pelaa mustilla nappuloilla.
	 */
	@Override
	public Pelaaja getMustaPelaaja() {
		return pelaaja2;
	}
}