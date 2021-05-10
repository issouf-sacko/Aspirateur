package bo;

import java.util.Arrays;
import java.util.HashSet;

public class Grille {

	private int nbColonne;
	private int nbLigne;

	private Aspirateur[][] grille; // tableau d'aspirateur 
	private Aspirateur aspirateur;

	public static HashSet<Character> lesCommandes = new HashSet<Character>();

	/**
	 * Construit une pièce de nombre de colonne et nombre de ligne
	 * 
	 * @param nbCol    le nbColonne de la pièce
	 * @param nbLingne le nbLigne de la pièce
	 */
	public Grille(int nbCol, int nbLingne) {

		this.nbColonne = nbCol;
		this.nbLigne = nbLingne;

		this.aspirateur = new Aspirateur(0, 0, 'N');

		this.grille = new Aspirateur[nbColonne][nbLigne];

	}

	public int getNbColonne() {
		return nbColonne;
	}

	public void setNbColonne(int nbColonne) {
		this.nbColonne = nbColonne;
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}

	public Aspirateur getAspirator() {
		return aspirateur;
	}

	public void setAspirator(Aspirateur aspirator) {
		this.aspirateur = aspirator;
	}

	public Aspirateur[][] getGrille() {
		return grille;
	}

	@Override
	public String toString() {
		return Arrays.toString(grille);
	}

	public boolean changePosition(String instruction) {
		remplirCommande();
		instruction = instruction.toUpperCase();
		boolean verif = false;
		try {
			if (verifAllCommande(instruction)) {
				for (int i = 0; i < instruction.length(); i++) {

					moveAspirator(instruction.charAt(i));
				}
				verif = true;
			}

			
		} catch (Exception e) {
			
		}

		return verif;
	}

	public void addAspirator(Aspirateur aspirateur) {

		grille[aspirateur.getX()][aspirateur.getY()] = aspirateur;
	}

	public Aspirateur moveAspirator(char cmd) {

		try {

			if (cmd == 'A') {

				switch (this.aspirateur.getOrientation()) {
				case 'N':
					if (this.aspirateur.getY() < this.nbLigne)
						this.aspirateur.setY(this.aspirateur.getY() + 1);
					break;
				case 'S':
					if (this.aspirateur.getY() > 0)
					this.aspirateur.setY(this.aspirateur.getY() - 1);
					break;
				case 'W':
					if (this.aspirateur.getX() < this.nbColonne)
					this.aspirateur.setX(this.aspirateur.getX() + 1);

					break;
				case 'E':
					if (this.aspirateur.getX() > 0)
					this.aspirateur.setX(this.aspirateur.getX() - 1);
					break;
				}

			} else {
				this.aspirateur.pivoterAspirateur(cmd);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return this.aspirateur;
	}

	public boolean AjouterCommande(char commande) {
		if (lesCommandes.contains(commande)) {
			return false;
		}
		return lesCommandes.add(Character.toUpperCase(commande));
	}

	public void remplirCommande() {
		AjouterCommande('A');
		AjouterCommande('D');
		AjouterCommande('G');
	}

	public boolean verifAllCommande(String instruction) {
		remplirCommande();
		for (int i = 0; i < instruction.length(); i++) {
			if (lesCommandes.contains(instruction.charAt(i)) != true) {
				return false;
			}

		}
		return true;
	}

}
