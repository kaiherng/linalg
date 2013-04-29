
package backend.computations.operations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.computations.infrastructure.Solution;

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
		Matrix l = (Matrix) sol.getAnswer();

		assertTrue(l.getNumCols() == 1);
		assertTrue(l.getNumRows() == 1);
		assertTrue(l.getValues()[0][0] == 6);
		
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
//		List<String> latex = testMult.toLatex();
//		int counter = 0;
//		for (String s : latex){
//			System.out.println("======== List item: "+counter+ "========");
//			counter++;
//			System.out.println(s + "\n");
//		}
//		System.out.println();
	}
	
	
	@Test // test a normal, successful multiply
	public void multiplyTest() {
		MM_Multiply testMult = new MM_Multiply(m1,m2);
		Solution sol = testMult.getSolution();
		Matrix l = (Matrix) sol.getAnswer();
		assertTrue(l.getNumCols() == 2);
		assertTrue(l.getNumRows() == 1);
		assertTrue(l.getValues()[0][0] == 64);
		assertTrue(l.getValues()[1][0] == 89);
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
//		List<String> latex = testMult.toLatex();
//		int counter = 0;
//		for (String s : latex){
//			System.out.println("======== List item: "+counter+ "========");
//			counter++;
//			System.out.println(s+ "\n");
//		}
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
		Matrix l = (Matrix) sol.getAnswer();
		assertTrue(l.getNumCols() == 2);
		assertTrue(l.getNumRows() == 1);
		assertTrue(l.getValues()[0][0] == 64);
		assertTrue(l.getValues()[1][0] == 89);
	}
	
	
	@Test // Test another normal multiplication
	public void oneMoreMatrixTest(){
		MM_Multiply testMult = new MM_Multiply(m8,m9);
		Solution sol = testMult.getSolution();
		Matrix l = (Matrix) sol.getAnswer();
		assertTrue(l.getNumCols() == 2);
		assertTrue(l.getNumRows() == 3);
		assertTrue(l.getValues()[0][0] == 60);
		assertTrue(l.getValues()[0][1] == 49);
		assertTrue(l.getValues()[0][2] == 141);
		assertTrue(l.getValues()[1][0] == 45);
		assertTrue(l.getValues()[1][1] == 43);
		assertTrue(l.getValues()[1][2] == 92);
		
		
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
		List<String> latex = testMult.toLatex();
		assertTrue("\\begin{bmatrix} 6.0 & 2.0 & 9.0\\\\3.0 & 5.0 & 8.0\\\\0.0 & 1.0 & 6.0\\end{bmatrix} $\\times$ \\begin{bmatrix} 7.0 & 6.0 & 5.0\\\\4.0 & 7.0 & 0.0\\end{bmatrix}".equals(latex.get(0)));
		
		int counter = 0;
		for (String s : latex){
			System.out.println("======== List item: "+counter+ "========");
			counter++;
			System.out.println(s+ "\n");
		}
	}

}
