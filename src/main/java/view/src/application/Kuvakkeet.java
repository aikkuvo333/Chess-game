package view.src.application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.NappulanVari;

public class Kuvakkeet {
	private static String kuvaURI = "File:src/main/resources/images/";
	
	public static ImageView getSotilas(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "sotilas" + getVariURI(vari)));
	}
	
	public static ImageView getRatsu(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "hevonen" + getVariURI(vari)));
	}
	
	public static ImageView getTorni(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "torni" + getVariURI(vari)));
	}
	
	public static ImageView getLahetti(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "lahetti" + getVariURI(vari)));
	}
	
	public static ImageView getKuningatar(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "kuningatar" + getVariURI(vari)));
	}
	
	public static ImageView getKuningas(NappulanVari vari) {
		return new ImageView(new Image(kuvaURI + "kuningas" + getVariURI(vari)));
	}
	
	private static String getVariURI(NappulanVari vari) {
		if(vari == NappulanVari.VALKOINEN) {
			return "_v.png";
		} else {
			return "_m.png";
		}
	}
}
