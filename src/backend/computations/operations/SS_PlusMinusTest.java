/**
 * 
 */
package backend.computations.operations;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Scalar;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** 
 * Test scalar addition and subtraction
 * 
 * @author baebi
 */
public class SS_PlusMinusTest {
	private Scalar s1 = new Scalar(1, DisplayType.DECIMAL);
	private Scalar s2 = new Scalar(2, DisplayType.DECIMAL);
	
	@Test
	public void additionTest() {
		SS_PlusMinus s = new SS_PlusMinus(s1,s2,true);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == 3.0);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("3.0"));
		List<Step> l = sol.getSteps();
		assertTrue(l.get(0).getCountable() instanceof Scalar);
		assertTrue("1.0 + 2.0".equals(((Scalar) l.get(0).getCountable()).getCustomDisplayValue()));
	}
	
	@Test
	public void subtractionTest() {
		SS_PlusMinus s = new SS_PlusMinus(s1,s2,false);
		Solution sol = s.getSolution();
		assertTrue(sol.getAnswer() instanceof Scalar);
		assertTrue(((Scalar) sol.getAnswer()).getValue() == -1.0);
		assertTrue(((Scalar) sol.getAnswer()).getDisplayValue().equals("-1.0"));
		List<Step> l = sol.getSteps();
		assertTrue(l.get(0).getCountable() instanceof Scalar);
		assertTrue("1.0 - 2.0".equals(((Scalar) l.get(0).getCountable()).getCustomDisplayValue()));
	}

}
