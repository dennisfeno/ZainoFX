package it.polito.tdp.zaino;
	
import it.polito.tdp.zaino.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * com'è fatto il DB? 
 * nei pezzi ho l'id del problema, il peso e il costo di ciascun costo
 * nell'id problema contengo il numero di tutti i pezzi, insieme alla capacità dello zaino
 * 
 * non posso creare nessun pezzo se non esiste il problema ad esso associato.
 * la colonna id dei pezzi mi serve per mantenere l'univocità nel DB. 
 * 
 * @author Dennis
 *
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/**
			 * perché non metto la comboBox nell'initialize? perché viene chiamato troppo presto
			 * quando chiamo l'initialize non ho ancora tutto quanto, ma ho solo tutta la scena letta
			 * non c'è modo di fare avere al controller il riferimento del modello, non esiste ancora il controller
			 * una volta creato il controller gli dico con quale modello lavorare. nell'initialize 
			 * posso mettere solamente le parti che non dipendono dai dati.
			 * 
			 */
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Zaino.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			/**
			 * dentro loader.load viene chiamato l'initialize, vengono iniettati i riferimenti
			 * completo le informazioni dell'interfaccia utente con le informazioni che solamente il controller può darmi.
			 * 
			 */
			
			Model model = new Model() ;
			ZainoController controller = loader.getController() ;
			controller.setModel(model) ;
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
