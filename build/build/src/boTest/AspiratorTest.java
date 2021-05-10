package boTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bo.Aspirateur;

class AspiratorTest {

	@Test
	void testChangeOrientation() {
		Aspirateur asp = new Aspirateur(5, 6, 'S');
		asp.pivoterAspirateur('G');
		assertEquals('E', asp.getOriatation());
	}
	
	
	@Test
	void testTurnRigth() {
		Aspirateur asp = new Aspirateur(5, 6, 'N');
		asp.pivoterAspirateur('D');
		assertEquals("+x", asp.getAngle());
	}
	
	@Test
	void testTurnLeft() {
		Aspirateur asp = new Aspirateur(5, 6, 'W');
		asp.pivoterAspirateur('G');
		assertEquals("-y", asp.getAngle());
	}

	@Test
	void testVerifOrientation() {
		
		Aspirateur asp = new Aspirateur(5, 6, 'N');
		boolean verif = asp.verifOrientation(asp.getOriatation());
		assertEquals(true, verif);
	}
	
	
	

}
