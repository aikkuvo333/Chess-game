package view.src.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Luokka sisältää metodin FXML tiedoston avaamista varten
 * 
 * @author Santeri Kuusisto
 * 
 */

public class InitFXML {

	/**
	 * Metodi avaa fxml tiedoston
	 * 
	 * @param object   Object
	 * @param uri      String
	 * @param nimi     String
	 * @param modality Modality
	 * @param style    StageStyle
	 * @param lauta    Node
	 * @throws IOException osoittaa tietojen lukemisen aikana tapahtuvan virheen
	 */
	public static void avaaFxml(Object object, String uri, String nimi, Modality modality, StageStyle style, Node lauta)
			throws IOException {
		String fxmlUri = uri == null ? object.getClass().getSimpleName() + ".fxml" : uri;
		FXMLLoader loader = new FXMLLoader(object.getClass().getResource(fxmlUri));
		loader.setController(object);
		loader.setResources(ValittuKieli.getInstance().getBundle());
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(nimi);
		stage.initModality(modality);
		stage.initStyle(style);

		if (lauta != null) {
			lauta.setEffect(new BoxBlur(5, 5, 5));
			stage.showAndWait();
			lauta.setEffect(null);
		}
	}
}
