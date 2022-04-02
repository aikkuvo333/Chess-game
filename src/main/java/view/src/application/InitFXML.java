package view.src.application;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/*
 * 
 * @author Santeri Kuusisto
 * 
 */

//Huono mutta helpompi tapa avata FXML-tiedosto
public class InitFXML {
	
	public static void avaaFxml(Object object, String uri, String nimi, Modality modality, StageStyle style, Node lauta) throws IOException {
		String fxmlUri = uri == null ? object.getClass().getSimpleName()+".fxml" : uri;
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
		//stage.initOwner(owner);
		
		if(lauta != null) {
			lauta.setEffect(new BoxBlur(5, 5, 5));
			stage.showAndWait();
			lauta.setEffect(null);
		}
	}
}
