package boTest;

import static org.junit.jupiter.api.Assertions.*;
import bo.*;
import bo.Aspirateur;
import org.junit.jupiter.api.Test;

class GrilleTest {

	Aspirateur asp = new Aspirateur(4, 5, 'E');
	
	


	@Test
	void testAspiratorPlaceInit() {
		Grille gr = new Grille(10, 10);
		assertEquals(0, gr.getAspirator().getX());
		assertEquals(0, gr.getAspirator().getY());
		assertEquals('N', gr.getAspirator().getOrientation());
	}
	
	@Test
	void testChangePosition() {
		Grille gr = new Grille(10, 10);
		gr.moveAspirator('A');
		gr.moveAspirator('D');
		assertEquals(0, gr.getAspirator().getX());
		assertEquals(1, gr.getAspirator().getY());
		assertEquals('E', gr.getAspirator().getOrientation());
	}
	
	@Test
	void testAspiratorMoveRigth() {
		Grille gr = new Grille(10, 10);
		Aspirateur asp2 = new Aspirateur(4, 5, 'N');
		Aspirateur asp3 = new Aspirateur(4, 5, 'E');
		gr.setAspirator(asp2);
		gr.moveAspirator('D');
		assertEquals(asp3.getOrientation(), gr.getAspirator().getOrientation());
	}
	
	@Test
	void testAspiratorMoveLeft() {
		Grille gr = new Grille(10, 10);
		Aspirateur asp2 = new Aspirateur(4, 5, 'W');
		Aspirateur asp3 = new Aspirateur(4, 5, 'S');
		gr.setAspirator(asp2);
		gr.moveAspirator('G');
		assertEquals(asp3.getOrientation(), gr.getAspirator().getOrientation());
	}

	@Test
	void testSetAspiForGrille() {
		Grille gr = new Grille(10, 10);
		Aspirateur asp4 = new Aspirateur(4, 5, 'N');
		gr.setAspirator(asp4);
		gr.moveAspirator('A');
		assertEquals(asp.getY()+1, gr.getAspirator().getY());
	}
	
	@Test
	void testAjouterCommande() {
		Grille gr = new Grille(10, 10);
		gr.AjouterCommande('S');
		assertEquals(true, gr.AjouterCommande('K'));
	}
	
	@Test
	void testChangePositionWithInstruction() {
		Grille gr = new Grille(10, 10);
		Aspirateur asp4 = new Aspirateur(5, 5, 'N');
		gr.setAspirator(asp4);
		gr.changePosition("DADADADAA");
		assertEquals(5, gr.getAspirator().getX());
		assertEquals(6, gr.getAspirator().getY());
		assertEquals('N', gr.getAspirator().getOrientation());
	}

	@Test
	void testChangePosition2() {
		Grille gr = new Grille(10, 10);
		gr.changePosition("GGAAG");
		assertEquals(0, gr.getAspirator().getX());
		assertEquals(0, gr.getAspirator().getY());
		assertEquals('E', gr.getAspirator().getOrientation());
	}
}
