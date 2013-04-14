/**
 * 
 */
package backend.computations.operations;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** Tests MM_PlusMinus
 * 
 * @author baebi
 */
public class MM_PlusMinusTest {
	private Matrix m1 = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{1.0,2.0}});
	private Matrix m2 = new Matrix(DisplayType.DECIMAL, new Double[][]{{3.0,4.0},{3.0,4.0}});
	private Matrix m3 = new Matrix(DisplayType.DECIMAL, new Double[][]{{3.0,4.0,5.0},{3.0,4.0,5.0}});
	private Matrix m4 = new Matrix(DisplayType.DECIMAL, new Double[][]{{3.0,null},{3.0,4.0}});
	private Matrix m5 = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{1.0,2.0},{1.0,2.0}});
	private Matrix m6 = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{1.0,2.0},{1.0,2.0}});
	
	@Test
	// tests a basic addition
	public void plusTest() {
		MM_PlusMinus test = new MM_PlusMinus(m1,m2,true);
		Solution sol = test.getSolution();
		Matrix answer = (Matrix) sol.getAnswer();
		assertTrue(answer.getValues()[0][0] == 4.0);
		assertTrue(answer.getValues()[0][1] == 6.0);
		assertTrue(answer.getValues()[1][0] == 4.0);
		assertTrue(answer.getValues()[1][1] == 6.0);
		List<Step> steps = sol.getSteps();
		Step additionStep = steps.get(0);
		Matrix addStep = (Matrix) additionStep.getCountable();
		assertTrue(addStep.getCustomDisplayValues()[0][0].equals("1.0 + 3.0"));
		assertTrue(addStep.getCustomDisplayValues()[0][1].equals("2.0 + 4.0"));
		assertTrue(addStep.getCustomDisplayValues()[1][0].equals("1.0 + 3.0"));
		assertTrue(addStep.getCustomDisplayValues()[1][1].equals("2.0 + 4.0"));
	}
	
	@Test
	// tests a basic subtraction
	public void minusTest() {
		MM_PlusMinus test = new MM_PlusMinus(m1,m2,false);
		Solution sol = test.getSolution();
		Matrix answer = (Matrix) sol.getAnswer();
		assertTrue(answer.getValues()[0][0] == -2.0);
		assertTrue(answer.getValues()[0][1] == -2.0);
		assertTrue(answer.getValues()[1][0] == -2.0);
		assertTrue(answer.getValues()[1][1] == -2.0);
		List<Step> steps = sol.getSteps();
		Step additionStep = steps.get(0);
		Matrix addStep = (Matrix) additionStep.getCountable();
		assertTrue(addStep.getCustomDisplayValues()[0][0].equals("1.0 - 3.0"));
		assertTrue(addStep.getCustomDisplayValues()[0][1].equals("2.0 - 4.0"));
		assertTrue(addStep.getCustomDisplayValues()[1][0].equals("1.0 - 3.0"));
		assertTrue(addStep.getCustomDisplayValues()[1][1].equals("2.0 - 4.0"));
	}
	
	@Test
	// test for error on wrong dimension matrix
	public void wrongDimensionsTest(){
		try{
			@SuppressWarnings("unused")
			MM_PlusMinus test = new MM_PlusMinus(m1,m3,false);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR (Plus): Matrices must have same dimensions"));
		}
	}
	
	@Test
	// test for error on null entry in matrix
	public void nullEntryTest(){
		try{
			@SuppressWarnings("unused")
			MM_PlusMinus test = new MM_PlusMinus(m1,m4,false);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Each index must contain a non-null entry"));
		}
	}
	
	@Test
	// tests a basic subtraction
	public void minusTestWholeNumber() {
		MM_PlusMinus test = new MM_PlusMinus(m5,m6,false);
		Solution sol = test.getSolution();
		Matrix answer = (Matrix) sol.getAnswer();
		assertTrue(answer.getValues()[0][0] == 0.0);
		assertTrue(answer.getValues()[0][1] == 0.0);
		assertTrue(answer.getValues()[1][0] == 0.0);
		assertTrue(answer.getValues()[1][1] == 0.0);
		List<Step> steps = sol.getSteps();
		Step additionStep = steps.get(0);
		Matrix addStep = (Matrix) additionStep.getCountable();
		assertTrue(addStep.getCustomDisplayValues()[0][0].equals("1 - 1"));
		assertTrue(addStep.getCustomDisplayValues()[0][1].equals("2 - 2"));
		assertTrue(addStep.getCustomDisplayValues()[1][0].equals("1 - 1"));
		assertTrue(addStep.getCustomDisplayValues()[1][1].equals("2 - 2"));
		
//		Step endStep = steps.get(1);
//		Matrix m = (Matrix) endStep.getCountable();
//		assertTrue(m.getDisplayValues()[0][0].equals("0"));
	}
	

}
