package view.src.application;

import java.util.ArrayList;
import java.util.Optional;

import controller.IKontrolleri;
import controller.Kontrolleri;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.image.Image;
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
import model.Ruutu;
import view.IPelinakyma;
import java.io.*;
import javax.sound.sampled.*;

/*
 * 
 * @author Santeri Kuusisto
 * 
 */

public class FXMLController implements IPelinakyma {

	private IKontrolleri kontrolleri;
	private ImageView nappulaKuva;
	private String vaalea = "-fx-background-color: #f1dbb1";
	private String tumma = "-fx-background-color: #ba9b63";
	private String valittu = "-fx-background-color: rgba(0, 255, 0, 0.2)";
	private Pane ruutu;
	private Node valittuNappula = null;
	private String vuorossa;
	public boolean peruutus = false;
	public boolean peruutusKaynnissa = false;
	private NappulanTyyppi korotusTyyppi = null;
	
	// Efektejä
	private String sound1 = "src/main/resources/sounds/sound1.wav";
	private String sound2 = "src/main/resources/sounds/sound2.wav";
	private DropShadow varjo;
	private RotateTransition rotate = new RotateTransition();
	private boolean kaantynyt = false;

	// Kuvan URI
	private String kuvaURI = "File:src/main/resources/images/";

	// Nappulan apumuuttujia
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	private boolean siirtoAloitettu = false;
	private ArrayList<Node> mahdSiirrot;

	private boolean stageShadow = false;

	@FXML
	private AnchorPane lautaNakyma;

	@FXML
	private GridPane pelilauta;

	@FXML
	private Text pelaaja2;

	@FXML
	private Text pelaaja1;

	@FXML
	private Text aika;

	@FXML
	public Button peruuta;

	@FXML
	private Text vuoro;
	
	@FXML
    private Text shakki;

	@FXML
	private void handleLuovuta(ActionEvent event) throws IOException {
		System.out.println("Luovuta");
		luovuta();
		//avaaKorotus();
	}

	@FXML
	private void handlePeruuta(ActionEvent event) {
		System.out.println("Peruuta");
		
		peruutus = true;
	}

	@FXML
	void handleRuutu(MouseEvent e) {

	}

	public void initialize() {
		kontrolleri = new Kontrolleri(this);
		kontrolleri.aloitaPeli();

		varjo = new DropShadow();
		varjo.setColor(Color.GRAY);
		varjo.setOffsetY(6);

		asetaRuudut();
		asetaNappulat();
		kaannaLauta();

		System.out.println("initialized");
	}

	// Kääntää laudan
	public void kaannaLauta() {

		rotate.setDuration(Duration.millis(400));
		rotate.setCycleCount(1);
		rotate.setByAngle(180);
		rotate.setNode(pelilauta);
		rotate.play();

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
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(aani)));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void asetaNappulat() {
		Nappula nappula;
		Ruutu[][] ruutu = kontrolleri.getPelitilanne();

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				nappula = ruutu[x][y].getNappula();
				if (nappula != null) {
					String nappulanTyyppi;
					String variPNG;
					String nappulaURI;

					if (nappula.getVari() == NappulanVari.VALKOINEN) {
						variPNG = "_v.png";
					} else {
						variPNG = "_m.png";
					}

					switch (nappula.getTyyppi()) {
					case SOTILAS:
						nappulanTyyppi = "sotilas";
						break;

					case TORNI:
						nappulanTyyppi = "torni";
						break;

					case RATSU:
						nappulanTyyppi = "hevonen";
						break;

					case LAHETTI:
						nappulanTyyppi = "lahetti";
						break;

					case KUNINGATAR:
						nappulanTyyppi = "kuningatar";
						break;

					case KUNINGAS:
						nappulanTyyppi = "kuningas";
						break;

					default:
						nappulanTyyppi = "null";
						break;
					}

					if (!nappulanTyyppi.contains("null")) {
						nappulaURI = kuvaURI + nappulanTyyppi + variPNG;
						nappulaKuva = luoNappula(nappulaURI, this.ruutu);
						pelilauta.add(nappulaKuva, x, y);

					}
				}
			}
		}
	}
	
	public void paivitaLauta() {
		
		/*ObservableList<Node> children = pelilauta.getChildren();
		for(Node node : children) {
			children.clear();
			if(node.toString().contains("ImageView")) {
				System.out.println(node);
				//children.remove((Node) node);
			}
		}
		*/
		pelilauta.getChildren().clear();
		asetaRuudut();
		asetaNappulat();
	}

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

	// Tilapäinen ratkaisu kuvan koon asettamiseen laudan ruudun mukaan
	public ImageView skaalaaKuvake(Image image, Pane pane) {

		ImageView imageView = new ImageView();
		//imageView.setPreserveRatio(true);
		//imageView.setCache(true);
		imageView.setSmooth(true);
		imageView.fitWidthProperty().bind(pane.widthProperty());
		imageView.fitHeightProperty().bind(pane.heightProperty());
		imageView.setImage(image);
		imageView.setScaleX(imageView.getScaleX()*0.8);
		imageView.setScaleY(imageView.getScaleY()*0.9);
		
		return imageView;
	}
	

	// Lisää ruudun GridPaneen
	public void addRuutu(int rowIndex, int colIndex, int count) {
		ruutu = new Pane();
		String vari = count % 2 == 0 ? tumma : vaalea;

		ruutu.setStyle(vari);
		pelilauta.setAlignment(Pos.CENTER);
		pelilauta.add(ruutu, colIndex, rowIndex);
		//ruutu.prefWidthProperty().bind(Bindings.min(lautaNakyma.widthProperty().divide(size), lautaNakyma.heightProperty().divide(size)));
		//ruutu.prefHeightProperty().bind(Bindings.min(lautaNakyma.widthProperty().divide(size), lautaNakyma.heightProperty().divide(size)));

		// Ruutu clickHandler
		ruutu.setOnMouseClicked(e -> {
			System.out.printf("ruutu [%d, %d]%n", colIndex, rowIndex);
			teeSiirto(colIndex, rowIndex);
		});

	}

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

	public ImageView luoNappula(String kuvanURI, Pane ruutu) {
		ImageView kuvanView = skaalaaKuvake(new Image(kuvanURI), ruutu);
		kuvanView.setMouseTransparent(true);

		return kuvanView;
	}

	public void nappulaHighlight(Node nappula, boolean onkoValittu) {

		if (onkoValittu) {
			valittuNappula.setEffect(varjo);
		} else {
			valittuNappula.setEffect(null);
		}
	}

	public void vaihdaVuoro() {
		String pelaaja = pelaaja1.getText();
		pelaaja1.setText(pelaaja2.getText());
		pelaaja2.setText(pelaaja);

		vuorossa = pelaaja1.getText();
		vuoro.setText("Vuoro: " + vuorossa);

		kaannaLauta();
	}

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

	// Tehdään siirto ja lähetetään kontrolleriin
	public void teeSiirto(int col, int row) {

		if (!siirtoAloitettu) {
			mistaX = col;
			mistaY = row;
			valittuNappula = getRuudunNappula(mistaX, mistaY);
			System.out.println("VALITTU NAPPULA: " + valittuNappula);
			
			//Värintarkistus puuttuu
			if (valittuNappula != null) {
				ArrayList<Ruutu> siirrot = kontrolleri.getSiirrotNappulalle(col, row);
				mahdSiirrot = naytaSiirrot(siirrot);
				nappulaHighlight(valittuNappula, true);
				siirtoAloitettu = true;
				
				aani(sound2);
				System.out.println(kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula().getTyyppi() + " "
						+ kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula().getVari() + " " + kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula());
			}

		} else if (!(mistaX == col && mistaY == row) && valittuNappula != null) {
			mihinX = col;
			mihinY = row;

			System.out.printf("Siirtopyyntö ruudusta [%d, %d] ruutuun [%d, %d]%n", mistaX, mistaY, mihinX, mihinY);

			if (kontrolleri.teeSiirto(mistaX, mistaY, mihinX, mihinY)) {
				// pelilauta.getChildren().remove(valittuNappula);
				// pelilauta.add(valittuNappula, mihinX, mihinY);

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
	
	//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	public void luovuta() {
		toggleShadow();
		Stage stage = (Stage) lautaNakyma.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert(type, "");
		
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		
		alert.getDialogPane().setContentText("Haluatko varmasti luovuttaa?");
		alert.getDialogPane().setHeaderText("Luovuttaminen");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			System.out.println(kontrolleri.getVuoro() + " luovuttaa");
			if(kontrolleri.getVuoro() == NappulanVari.VALKOINEN) {
				//pelinVoitti(NappulanVari.MUSTA);
			} else {
				//pelinVoitti(NappulanVari.VALKOINEN);
			}
		} else if (result.get() == ButtonType.CANCEL){
			System.out.println(kontrolleri.getVuoro() + " ei luovuta");
		}
		
		toggleShadow();
	}
	
	public void shakkiHuomautus() {
		shakki.setVisible(true);
		PauseTransition visiblePause = new PauseTransition(
				Duration.seconds(4)
				);
		visiblePause.setOnFinished(
				event -> shakki.setVisible(false));
		visiblePause.play();
	}

	public void avaaKorotus() throws IOException {
		InitFXML.avaaFxml(new KorotusKontrolleri(this, kontrolleri.getVuoro()), "korotus.fxml", "Sotilaan korotus", Modality.APPLICATION_MODAL, StageStyle.UNDECORATED, lautaNakyma);
	}

	public void valittuKorotus(NappulanTyyppi tyyppi) {
		korotusTyyppi = tyyppi;
	}

	public void toggleShadow() {
		if (!stageShadow) {
			//stage = (Stage) lautaNakyma.getScene().getWindow();
			lautaNakyma.setEffect(new BoxBlur(5, 5, 5));
		} else {
			lautaNakyma.setEffect(null);
		}

		stageShadow = !stageShadow;
	}
	
	public void voittoIkkuna(NappulanVari vari) {
		Stage stage = (Stage) lautaNakyma.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.INFORMATION;
		Alert alert = new Alert(type, "");
		
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		
		//alert.getDialogPane().setContentText("Haluatko varmasti luovuttaa?");
		alert.getDialogPane().setHeaderText(vari + " voitti pelin!");
		
		//Optional<ButtonType> result = alert.showAndWait();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Alkuvalikko.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void siirtoAiheuttiShakin() {
		System.out.println("Shakki!");
		shakkiHuomautus();
	}

	@Override
	public void pelinVoitti(NappulanVari voittaja) {
		System.out.println("PELIN VOITTI " + voittaja + " ASDASDASDASD");
		//voittoIkkuna(voittaja);
	}

	@Override
	public NappulanTyyppi korota() {
		
		//KorotusKontrolleri korotusKontrolleri = new KorotusKontrolleri(this, kontrolleri.getVuoro());
		try {
			//InitFXML.avaaFxml(korotusKontrolleri, "korotus.fxml", "Sotilaan korotus", Modality.APPLICATION_MODAL);
			avaaKorotus();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (korotusTyyppi == null) {
			korotusTyyppi = NappulanTyyppi.KUNINGATAR;
		}

		return korotusTyyppi;
	}
}