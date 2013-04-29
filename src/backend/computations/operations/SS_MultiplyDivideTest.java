package backend.computations.operations;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.blocks.Scalar;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Solution;

/**
 * Tests for scalar multiplication and division
 * 
 * @author baebi
 */
public class SS_MultiplyDivideTest {
	private Scalar s1 = new Scalar(1, DisplayType.DECIMAL);
	private Scalar s2 = new Scalar(2, DisplayType.DECIMAL);
	
	@Test
	public void multiplicationTest() {
		SS_MultiplyDivide s = new SS_MultiplyDivide(s1,s2,true);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 2.0);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("2.0"));
	}
	
	@Test
	public void divisionTest() {
		SS_MultiplyDivide s = new SS_MultiplyDivide(s1,s2,false);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 0.5);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("0.5"));
	}

}
