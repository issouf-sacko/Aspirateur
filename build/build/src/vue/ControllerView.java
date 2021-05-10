package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import bo.Grille;

import java.util.ArrayList;

import bo.Aspirateur;

public class ControllerView {

	@FXML
	HBox hbGrille;

	@FXML
	HBox hbApi;
	@FXML
	HBox hbMoveAspi;
	@FXML
	HBox hbShowActualAspi;
	@FXML
	private TextField txtGrNbColonne;
	@FXML
	private TextField txttGrNbLigne;
	@FXML
	private TextField txtAspiX;
	@FXML
	private TextField txtAspiY;
	@FXML
	private TextField txtInstruction;

	@FXML
	private Button btnCreateGrille;
	
	@FXML
	private Button btnClose;

	@FXML
	private ChoiceBox<String> choixOrientation;

	@FXML
	private Label lblActuelAspirateur;

	private Grille piece;

	@FXML
	public void initialize() {

		hbGrille.setDisable(false);
		hbApi.setVisible(false);
		hbMoveAspi.setVisible(false);
		hbShowActualAspi.setVisible(false);
		ObservableList<String> lesPointsCardinaux = FXCollections.observableArrayList("East", "North", "West", "Sud");
		choixOrientation.setItems(lesPointsCardinaux);
		choixOrientation.getSelectionModel().select(1);
	}

	/**
	 * Initialisation Grille 
	 */
	@FXML
	public void createGrid() {

		verifTextisDigit(txtGrNbColonne.getText());
		verifTextisDigit(txtGrNbColonne.getText());
		try {
			if (pasErreurGrille() == true) {
				int nbCol = Integer.parseInt(txtGrNbColonne.getText());
				int nbLine = Integer.parseInt(txttGrNbLigne.getText());
				piece = new Grille(nbCol, nbLine);

				// affectation des valeurs de l'nspirateur de la pièce dans le textField
				// correspondant
				txtAspiX.setText(Integer.toString(piece.getAspirator().getX()));
				txtAspiY.setText(Integer.toString(piece.getAspirator().getY()));

				hbGrille.setDisable(true);
				hbApi.setVisible(true);
			} else
				alertSaisi();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(e.getMessage());
		}

	}

	/**
	 * Permet d'initialiser les dimensions de l'aspiratuer
	 */
	@FXML
	public void SetAspirateur() {
		String x = txtAspiX.getText();
		String y = txtAspiY.getText();
		// recupère l'orientation choisie dans le choiceBox
		char orientation = choixOrientation.getSelectionModel().getSelectedItem().charAt(0);

		if (x.isEmpty() || y.isEmpty()) {
			alertSaisi();
		} else {

			verifTextisDigit(x);
			verifTextisDigit(y);
			Aspirateur aspirateur = new Aspirateur(Integer.parseInt(x), Integer.parseInt(y), orientation);

			piece.setAspirator(aspirateur);

			lblActuelAspirateur.setText(piece.getAspirator().toString());
			hbMoveAspi.setVisible(true);
			hbShowActualAspi.setVisible(true);
		}
	}
	
	@FXML
	public void moveAspirateur() {
		
		String instruction = txtInstruction.getText();
		if (instruction.isEmpty() || piece.verifAllCommande(instruction.toLowerCase()))
				alertSaisi();
		
		piece.changePosition(instruction);
		lblActuelAspirateur.setText(piece.getAspirator().toString());
		
	}
	
	
	
	
	

	/**
	 * Permet de verifier  les saisis pour créer une pièce
	 * 
	 * @return true si nbCollonne et nbLine sont bien saisis et correcte
	 */
	public boolean pasErreurGrille() {

		String gridCol = txtGrNbColonne.getText();
		String gridLine = txttGrNbLigne.getText();

		if (gridCol.isEmpty() || gridLine.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Informations incomplètes");
			alert.setHeaderText("Compléter les champs indiqués");
			if (gridCol.isEmpty())

				txtGrNbColonne.setPromptText("à compléter");

			if (gridLine.isEmpty())
				txttGrNbLigne.setPromptText("à compléter");

			alert.showAndWait();
			return false;

		}
		return true;
	}

	
	
	public void verifTextisDigit(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i)) == false)
				alertSaisi();
		}

	}

	public void alertSaisi() {
		Alert alert = new Alert(AlertType.WARNING);

		alert.setTitle("Erreur");
		alert.setHeaderText("Saisie est incorrecte?");

		alert.showAndWait();
	}
	
	
	
	public void closeWindows() {
		
		 //  recup un aperçu de la scène
	    Stage stage = (Stage) btnClose.getScene().getWindow();
	    // fermeture de la fenêtre
	    stage.close();
		
	}
}
