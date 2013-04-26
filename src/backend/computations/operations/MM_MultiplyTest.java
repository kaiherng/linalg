/**
 * 
 */
package backend.computations.operations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/**
 * Tests for MM_Multiply
 * 
 * @author baebi
 */
public class MM_MultiplyTest {
	private Matrix m1 = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0},{4.0},{6.0}});
	private Matrix m2 = new Matrix(DisplayType.DECIMAL, new Double[][]{{2.0,5.0,7.0},{3.0,8.0,9.0}});
	private Matrix m3 = new Matrix(DisplayType.DECIMAL, new Double[][]{{null},{4.0},{6.0}});
	private Matrix m4 = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{1.0},{4.0},{6.0}});
	private Matrix m5 = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{2.0,5.0,7.0},{3.0,8.0,9.0}});
	private Matrix m6 = new Matrix(DisplayType.DECIMAL,new Double[][]{{3.0}});
	private Matrix m7 = new Matrix(DisplayType.DECIMAL,new Double[][]{{2.0}});
	private Matrix m8 = new Matrix(DisplayType.DECIMAL,new Double[][]{{6.0,2.0,9.0},{3.0,5.0,8.0},{0.0,1.0,6.0}});
	private Matrix m9 = new Matrix(DisplayType.DECIMAL,new Double[][]{{7.0,6.0,5.0},{4.0,7.0,0.0}});
	
	
	@Test // multiply one-element matrices
	public void multiplyOneTest(){
		MM_Multiply testMult = new MM_Multiply(m6,m7);
		Solution sol = testMult.getSolution();
		List<Step> l = sol.getSteps();
		Step step1 = l.get(0);
		Step step2 = l.get(1);
		assertTrue(step2.getCountable() instanceof Matrix);
		Matrix answer = (Matrix) step2.getCountable();
		assertTrue(answer.getNumCols() == 1);
		assertTrue(answer.getNumRows() == 1);
		assertTrue(answer.getValues()[0][0] == 6);
		
		assertTrue(step1.getCountable() instanceof Matrix);
		Matrix step1Matrix = (Matrix) step1.getCountable();
		String[][] indexSteps = step1Matrix.getCustomDisplayValues();
		assertTrue(indexSteps[0][0].equals("(3.0 * 2.0)"));
	}
	
	
	@Test // test a normal, successful multiply
	public void multiplyTest() {
		MM_Multiply testMult = new MM_Multiply(m1,m2);
		Solution sol = testMult.getSolution();
		List<Step> l = sol.getSteps();
		Step step1 = l.get(0);
		Step step2 = l.get(1);
		assertTrue(step2.getCountable() instanceof Matrix);
		Matrix answer = (Matrix) step2.getCountable();
		assertTrue(answer.getNumCols() == 2);
		assertTrue(answer.getNumRows() == 1);
		assertTrue(answer.getValues()[0][0] == 64);
		assertTrue(answer.getValues()[1][0] == 89);
		
		assertTrue(step1.getCountable() instanceof Matrix);
		Matrix step1Matrix = (Matrix) step1.getCountable();
		String[][] indexSteps = step1Matrix.getCustomDisplayValues();
		assertTrue(indexSteps[0][0].equals("(1.0 * 2.0) + (4.0 * 5.0) + (6.0 * 7.0)"));
		assertTrue(indexSteps[1][0].equals("(1.0 * 3.0) + (4.0 * 8.0) + (6.0 * 9.0)"));
	}
	
	
	@Test // Test when matrix has a null index
	public void nullTest(){
		try{
			new MM_Multiply(m3,m2);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Matrix should not contain null indices"));
		}
	}
	
	
	@Test // Test when matrices have wrong dimensions for multiplying
	public void wrongSizeTest(){
		try{
			new MM_Multiply(m1,m1);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Number of columns of first matrix must equal number of rows of second matrix"));
		}
	}
	
	
	@Test // Test that computation step entries can be non-doubles
	public void wholeNumberTest(){
		MM_Multiply testMult = new MM_Multiply(m4,m5);
		Solution sol = testMult.getSolution();
		List<Step> l = sol.getSteps();
		Step step1 = l.get(0);
		Step step2 = l.get(1);
		assertTrue(step2.getCountable() instanceof Matrix);
		Matrix answer = (Matrix) step2.getCountable();
		assertTrue(answer.getNumCols() == 2);
		assertTrue(answer.getNumRows() == 1);
		assertTrue(answer.getValues()[0][0] == 64);
		assertTrue(answer.getValues()[1][0] == 89);
		
		assertTrue(step1.getCountable() instanceof Matrix);
		Matrix step1Matrix = (Matrix) step1.getCountable();
		String[][] indexSteps = step1Matrix.getCustomDisplayValues();
		assertTrue(indexSteps[0][0].equals("(1 * 2) + (4 * 5) + (6 * 7)"));
		assertTrue(indexSteps[1][0].equals("(1 * 3) + (4 * 8) + (6 * 9)"));
	}
	
	
	@Test // Test another normal multiplication
	public void oneMoreMatrixTest(){
		MM_Multiply testMult = new MM_Multiply(m8,m9);
		Solution sol = testMult.getSolution();
		List<Step> l = sol.getSteps();
		Step step1 = l.get(0);
		Step step2 = l.get(1);
		assertTrue(step2.getCountable() instanceof Matrix);
		Matrix answer = (Matrix) step2.getCountable();
		assertTrue(answer.getNumCols() == 2);
		assertTrue(answer.getNumRows() == 3);
		assertTrue(answer.getValues()[0][0] == 60);
		assertTrue(answer.getValues()[0][1] == 49);
		assertTrue(answer.getValues()[0][2] == 141);
		assertTrue(answer.getValues()[1][0] == 45);
		assertTrue(answer.getValues()[1][1] == 43);
		assertTrue(answer.getValues()[1][2] == 92);
		
		assertTrue(step1.getCountable() instanceof Matrix);
		Matrix step1Matrix = (Matrix) step1.getCountable();
		String[][] indexSteps = step1Matrix.getCustomDisplayValues();
		assertTrue(indexSteps[0][0].equals("(6.0 * 7.0) + (3.0 * 6.0) + (0.0 * 5.0)"));
		assertTrue(indexSteps[0][1].equals("(2.0 * 7.0) + (5.0 * 6.0) + (1.0 * 5.0)"));
		assertTrue(indexSteps[0][2].equals("(9.0 * 7.0) + (8.0 * 6.0) + (6.0 * 5.0)"));
		assertTrue(indexSteps[1][0].equals("(6.0 * 4.0) + (3.0 * 7.0) + (0.0 * 0.0)"));
		assertTrue(indexSteps[1][1].equals("(2.0 * 4.0) + (5.0 * 7.0) + (1.0 * 0.0)"));
		assertTrue(indexSteps[1][2].equals("(9.0 * 4.0) + (8.0 * 7.0) + (6.0 * 0.0)"));
	}

}
