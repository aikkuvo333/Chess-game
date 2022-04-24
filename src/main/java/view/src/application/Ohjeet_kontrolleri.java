package view.src.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Ohjeet_kontrolleri {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button poistuBtn;

	@FXML
	private TextArea tekstikentta;

	@FXML
	void poistu(ActionEvent event) throws IOException {
		System.out.println("KLIKATTU");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Alkuvalikko.fxml"));
		loader.setResources(ValittuKieli.getInstance().getBundle());
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void initialize() {
		String msg = "SHAKIN SÄÄNNÖT JA PELIOHJEET\n\nValkeilla nappuloilla pelaava aloittaa aina pelin. "
				+ "\nPelaajat siirtävät vuorotellen aina yhtä omaa nappulaansa (poikkeuksena tornitus)."
				+ "\nNappulan voi liikkumissääntöjä noudattaen siirtää joko tyhjään ruutuun tai ruutuun, jossa on vastustajan nappula."
				+ "\nSiirrettäessä nappula jo vastustajan nappulan varaamaan ruutuun tulee vastustajan nappula syödyksi."
				+ "\nNäin ollen ei samassa ruudussa voi olla kahta nappulaa samanaikaisesti. "
				+ "\nNappulat eivät voi hyppiä toisten yli ratsua lukuun ottamatta.\n"
				
				+ "\nPelin tavoitteena on saattaa vastustajan kuningas torjumattoman uhkauksen alaiseksi. "
				+ "\nTällaista pelin päättävää tilannetta kutsutaan shakkimatiksi tai yleisemmin matiksi"
				+ "\nTilannetta, jossa kuningasta uhataan, mutta tilanne ei ole matti, kutsutaan shakiksi. "
				+ "\nKun kuningas on uhattuna, siirtovuorossa olevan pelaajan on torjuttava tämä uhkaus välittömästi joko"
				+ "\nsiirtämällä kuningas naapuriruutuun, jossa se ei ole uhattuna,"
				+ "\nlyömällä uhkaava nappula tai"
				+ "\nsiirtämällä jokin muu oma nappula uhkaavan nappulan ja oman kuninkaan väliin.\n"
				
				+ "\nJos mikään näistä ei ole mahdollista, kyseessä on siis matti ja peli on hävitty. "
				+ "\nMuutoinkaan kuningasta ei saa siirtää sellaiseen ruutuun, jossa se joutuisi välittömästi uhatuksi, "
				+ "\neikä muuta nappulaa saa siirtää siten, että oma kuningas joutuisi shakkiin. "
				+ "\nToisin sanoen kuningas ei saa olla shakattuna vastapelaajan ollessa siirtovuorossa.";
        tekstikentta.setText(msg);
    }

}
