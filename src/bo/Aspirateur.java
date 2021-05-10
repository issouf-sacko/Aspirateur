package bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.MissingFormatWidthException;
import java.util.Set;

public class Aspirateur {

	private int x;
	private int y;
	private char orientation;

	private String angle;
	int valDegre = 90;

	final private char[] pointsCardinaux = new char[] { 'E', 'N', 'W', 'S' };

	/**
	 * @lesangles contient les angles possibles (+x=0, +y=90°, -x=180,-y=270) à
	 *            laquelle elle peut faire face
	 */
	private List<String> dimAngle = new ArrayList<String>();

	public Aspirateur() {

	}

	public Aspirateur(int x, int y, char o) {

		try {

			this.x = x;
			this.y = y;
			this.orientation = o;
			setAngle();
			

		} catch (MissingFormatWidthException e) {
			System.out.println("Format incorrect");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char oriatation) {
		this.orientation = oriatation;
	}

	public char[] getPointsCardinaux() {
		return pointsCardinaux;
	}

	public void pivoterAspirateur(char cmd) {

		try {

			if (cmd == 'D') {
				turnRigth();
			} else if (cmd == 'G') {
				turnLeft();
			}

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean verifOrientation(char orientation) {

		boolean verif = false;
		int i = 0;
		while (verif == false && i < pointsCardinaux.length) {

			if (Character.toUpperCase(orientation) == pointsCardinaux[i]) {
				verif = true;
			}
			i++;
		}

		return verif;
	}

	public String getAngle() {
		return angle;
	}
	/**
	 * Pivoter l'aspirateur et automatiquement son orientation aussi
	 */
	public void setAngle() {
		remplirDegre();
		switch (this.orientation) {

		case 'E':
			this.angle = dimAngle.get(0);
			break;
		case 'N':
			this.angle = dimAngle.get(1);
			break;
		case 'W':
			this.angle = dimAngle.get(2);
			break;
		case 'S':
			this.angle = dimAngle.get(3);
			break;
		}

	}

	

	public void remplirDegre() {

		
		dimAngle.add("+x");
		dimAngle.add("+y");
		dimAngle.add("-x");
		dimAngle.add("-y");

	}

	public void turnLeft() {
		remplirDegre();

		int index = dimAngle.indexOf(this.angle);
		if (index == dimAngle.size() - 1) {
			this.angle = dimAngle.get(0);
		} else
			this.angle = dimAngle.get(index + 1);
		
		changeOrientationByAngle();
	}

	public void turnRigth() {
		remplirDegre();

		int index = dimAngle.indexOf(this.angle);
		if (index == 0) {
			this.angle = dimAngle.get(3);
		} else
			this.angle = dimAngle.get(index - 1);
		
		changeOrientationByAngle();
	}

	public void changeOrientationByAngle() {
		remplirDegre();
		int index = dimAngle.indexOf(this.angle);
		for (int i = 0; i < pointsCardinaux.length; i++) {
			if (index == i) {
				this.orientation = pointsCardinaux[i];
			}
		}

	}
	
	@Override
	public String toString() {
		return x + " " + y + " " + orientation;
	}

	public boolean equals(Object anObject) {
		return angle.equals(anObject);
	}

	public int hashCode() {
		return angle.hashCode();
	}
	
	
	
	
}
