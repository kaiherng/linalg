package backend.computations.operations;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.blocks.Scalar;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Solution;

/**
 * Tests for a scalar power
 * 
 * @author baebi
 */
public class S_PowerTest {
	private Scalar s1 = new Scalar(3, DisplayType.DECIMAL);
	private Scalar s2 = new Scalar(2, DisplayType.DECIMAL);
	private Scalar s3 = new Scalar(3, DisplayType.WHOLENUMBER);
	private Scalar s4 = new Scalar(2, DisplayType.WHOLENUMBER);
	
	@Test
	public void powerTest() {
		S_Power s = new S_Power(s1,s2);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 9.0);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("9.0"));
		assertTrue("$3.0^{2.0} \\ = \\ 9.0$".equals(s.toLatex().get(0)));
	}
	
	
	@Test
	public void powerTestWholeNumber() {
		S_Power s = new S_Power(s3,s4);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 9.0);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("9"));
	}

}
