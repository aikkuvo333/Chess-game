package view.src.application;

import java.util.ArrayList;
import java.util.HashMap;

import controller.IKontrolleri;
import controller.Kontrolleri;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Nappula;
import model.NappulanVari;
import model.Ruutu;
import view.IPelinakyma;

import java.io.*;
import javax.sound.sampled.*;

public class FXMLController implements IPelinakyma {

	//private NappulaUtil nappulaUtil;
	private IKontrolleri kontrolleri;
	private ImageView nappulaKuva;
	private String vaalea = "-fx-background-color: #f1dbb1";
	private String tumma = "-fx-background-color: #ba9b63";
	private Pane ruutu;
	private Node valittuNappula = null;
	private String sound1 = "src/main/resources/sounds/sound1.wav";
	private String sound2 = "src/main/resources/sounds/sound2.wav";

	//Efektejä
	private DropShadow varjo;
	private RotateTransition rotate = new RotateTransition();
	private boolean kaantynyt = false;

	//Testiä
	private String sotilasURI = "File:src/main/resources/images/sotilas_v.png";
	private String sotilasURI2 = "File:src/main/resources/images/hevonen_m.png";
	private String torniURI = "File:src/main/resources/images/torni_v.png";
	
	private String kuvaURI = "File:src/main/resources/images/";


	//private HashMap<Pane, ImageView> nappulat = new HashMap<Pane, ImageView>();

	//Nappulan apumuuttujia
	private int mistaX;
	private int mistaY;
	private int mihinX;
	private int mihinY;
	private boolean siirtoAloitettu = false;

	@FXML
	private GridPane pelilauta;

	@FXML
	private Text pelaaja2;

	@FXML
	private Text pelaaja1;

	@FXML
	private Text aika;

	@FXML
	private Button peruuta;

	@FXML
	private Text vuoro;

	@FXML
	private void handleLuovuta(ActionEvent event) {
		//nappulaUtil = new NappulaUtil();
		//Testiä

		//System.out.println(kontrolleri.getPelitilanne()[0][1].getNappula());
		asetaNappulat();
		
		
		/*
		nappulaKuva = skaalaaKuvake(new Image(torniURI), ruutu);
		pelilauta.add(nappulaKuva, 3, 7);

		System.out.println("AAAAAAAAAA " + GridPane.getColumnIndex(nappulaKuva));
		kaannaLauta();
		
		*/
	}

	@FXML
	private void handlePeruuta(ActionEvent event) {
		System.out.println("Klikattiin peruuta");
	}

	//Kääntää laudan
	private void kaannaLauta() {

		if(!kaantynyt) {
			pelilauta.setRotate(180);

		} else {
			pelilauta.setRotate(0);
		}

		/*
    	rotate.setDuration(Duration.millis(300));
    	rotate.setCycleCount(1);
    	rotate.setByAngle(180);
    	rotate.setNode(pelilauta);
    	rotate.play();
		 */

		kaantynyt = !kaantynyt;
		System.out.println("Kääntynyt: " + kaantynyt);
	}

	@FXML
	void handleRuutu(MouseEvent e) {

	}

	public void aani(String aani) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(aani)));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch(Exception e) {

		}
	}
	
	public void asetaNappulat() {
		Nappula nappula;
		Ruutu[][] ruutu = kontrolleri.getPelitilanne();
		
		System.out.println(ruutu[0][0]);
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				nappula = ruutu[x][y].getNappula();
				if(nappula != null) {
					String nappulanTyyppi;
					String variPNG;
					String nappulaURI;
					
					System.out.println(ruutu[x][y].getNappula());
					
					if(nappula.getVari() == NappulanVari.VALKOINEN) {
						variPNG = "_v.png";
					} else {
						variPNG = "_m.png";
					}
					
					System.out.println(nappula.getVari() + " " + variPNG);
					//System.out.println(nappula + " " + nappula.getVari() + " X: " + x + " Y: " + y);
					
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
					
					//System.out.println(kuvaURI + nappulanTyyppi + variPNG);
					
					if(!nappulanTyyppi.contains("null")) {
						nappulaURI = kuvaURI + nappulanTyyppi + variPNG;
						System.out.println("ACTUAL URI " + nappulaURI);
						nappulaKuva = luoNappula(nappulaURI, this.ruutu);
						pelilauta.add(nappulaKuva, x, y);
						
					}
				}
			}
		}
	}

	public void initialize() {
		System.out.println("initialized");

		//Nappulan varjo
		varjo = new DropShadow();
		varjo.setColor(Color.GRAY);
		varjo.setOffsetY(6);

		kontrolleri = new Kontrolleri(this);
		kontrolleri.aloitaPeli();


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

		//TESTINAPPULA
		//nappulaKuva = luoNappula("File:src/main/resources/images/lahetti_m.png", ruutu);
		//pelilauta.add(nappulaKuva, 2, 4);

	}

	//Tilapäinen ratkaisu kuvan koon asettamiseen laudan ruudun mukaan
	private ImageView skaalaaKuvake(Image image, Pane pane) {

		ImageView imageView = new ImageView();
		imageView.fitWidthProperty().bind(pane.widthProperty());
		imageView.fitHeightProperty().bind(pane.heightProperty());
		imageView.setImage(image);
		imageView.setMouseTransparent(true);

		return imageView;
	}

	//Lisää ruudun GridPaneen
	private void addRuutu(int rowIndex, int colIndex, int count) {
		ruutu = new Pane();
		String vari = count % 2 == 0 ? tumma : vaalea;

		ruutu.setStyle(vari);

		//ruudut.add(ruutu);
		pelilauta.add(ruutu, colIndex, rowIndex);

		//Ruutu clickHandler
		ruutu.setOnMouseClicked(e -> {
			System.out.printf("ruutu [%d, %d]%n", colIndex, rowIndex);
			teeSiirto(colIndex, rowIndex);

			//nappulaKuva = skaalaaKuvake(new Image(sotilasURI), ruutu);
			//pelilauta.add(nappulaKuva, colIndex, rowIndex);

			//System.out.println(pelilauta.getCellBounds(colIndex, rowIndex));
			//System.out.println(ruutu.getChildren());
		});

		/*ruutu.setOnMouseEntered(e -> {
        	if(getRuudunNappula(colIndex, rowIndex) != null) {
        		getRuudunNappula(colIndex, rowIndex).setEffect(varjo);
        	}
        });

        ruutu.setOnMouseExited(e -> {
        	if(getRuudunNappula(colIndex, rowIndex) != null) {
        		getRuudunNappula(colIndex, rowIndex).setEffect(null);
        	}
        });*/
	}

	public Node getRuudunNappula(int col, int row) {
		Node kuva = null;
		ObservableList<Node> children = pelilauta.getChildren();
		//System.out.println(children);

		for (Node node : children) {
			if(node.toString().contains("ImageView")) {

				if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
					kuva = node;
					//System.out.println("getRuudunNappula(): " + kuva);
					break;
				}
			}
		}

		return kuva;
	}

	public ImageView luoNappula(String kuvanURI, Pane ruutu) {
		//Image kuva = new Image(kuvanURI);

		ImageView kuvanView = skaalaaKuvake(new Image(kuvanURI), ruutu);

		/*
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.GRAY);
		shadow.setOffsetX(5.0);
		shadow.setOffsetY(5.0);

		kuvanView.setOnMouseEntered(e -> {
			System.out.println("ENTERED");
			kuvanView.setEffect(shadow);
		});

		kuvanView.setOnMouseExited(e -> {
			System.out.println("EXITED");
			kuvanView.setEffect(null);
		});

		kuvanView.setOnMouseClicked(e -> {

		});
		 */
		//System.out.println("LÄPINÄKYVYYS " + kuvanView.isMouseTransparent());

		return kuvanView;
	}


	public void nappulaHighlight(Node nappula, boolean onkoValittu) {

		if(onkoValittu) {
			valittuNappula.setEffect(varjo);
		} else {
			valittuNappula.setEffect(null);
		}
	}

	//Tehdään siirto ja lähetetään kontrolleriin
	public void teeSiirto(int col, int row) {

		if(!siirtoAloitettu) {
			mistaX = col;
			mistaY = row;
			valittuNappula = getRuudunNappula(mistaX, mistaY);
			//System.out.println("VALITTIIN NODE: " + valittuNappula);
			if(valittuNappula != null) {
				System.out.println(kontrolleri.getPelitilanne()[mistaX][mistaY].getNappula().getVari());
				nappulaHighlight(valittuNappula, true);
				siirtoAloitettu = true;
				aani(sound2);
			}

		} else if (!(mistaX == col && mistaY == row) && valittuNappula != null) {
			mihinX = col;
			mihinY = row;
			//System.out.println("Siirretään nappula " + valittuNappula);
			System.out.printf("Siirtopyyntö ruudusta [%d, %d] ruutuun [%d, %d]%n", mistaX, mistaY, mihinX, mihinY);
			
			if(kontrolleri.teeSiirto(mistaX, mistaY, mihinX, mihinY)) {
				pelilauta.getChildren().remove(valittuNappula);
				pelilauta.add(valittuNappula, mihinX, mihinY);
				aani(sound1);
			}
			
			nappulaHighlight(valittuNappula, false);
			siirtoAloitettu = false;
			valittuNappula = null;
			
		} else {
			System.out.println("Siirto peruutettu");

			nappulaHighlight(valittuNappula, false);
			siirtoAloitettu = false;
		}

		//System.out.println("Siirron tila: " + siirtoAloitettu);
	}

	@Override
	public void siirtoEiPoistaShakkia() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void siirtoAiheuttiShakin() {
		// TODO Auto-generated method stub
		
	}
}