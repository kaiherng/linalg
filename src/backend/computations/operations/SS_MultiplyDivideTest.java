package backend.computations.operations;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import backend.blocks.Scalar;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

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
		List<Step> l = sol.getSteps();
		assertTrue(l.get(0).getCountable() instanceof Scalar);
		assertTrue("1.0 * 2.0".equals(((Scalar) l.get(0).getCountable()).getCustomDisplayValue()));
		System.out.println();
		assertTrue("$1.0 * 2.0 = 2.0$".equals(s.toLatex().get(0)));
	}
	
	@Test
	public void divisionTest() {
		SS_MultiplyDivide s = new SS_MultiplyDivide(s1,s2,false);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 0.5);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("0.5"));
		List<Step> l = sol.getSteps();
		assertTrue(l.get(0).getCountable() instanceof Scalar);
		assertTrue("1.0 / 2.0".equals(((Scalar) l.get(0).getCountable()).getCustomDisplayValue()));
		assertTrue("$1.0 / 2.0 = 0.5$".equals(s.toLatex().get(0)));
	}

}
