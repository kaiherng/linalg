/**
 * 
 */
package backend.computations.operations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Scalar;
import backend.computations.infrastructure.Solution;

/**
 * Tests for Matrix-Scalar multiplications
 * 
 * @author baebi
 */
public class MS_MultiplyTest {
	private Scalar s1 = new Scalar(2,DisplayType.DECIMAL);
	private Scalar s2 = new Scalar(2,DisplayType.WHOLENUMBER);
	private Matrix m1 = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
	private Matrix m2 = new Matrix(DisplayType.DECIMAL, new Double[][]{{null,2.0},{3.0,4.0}});
	private Matrix m3 = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{1.0,2.0},{3.0,4.0}});
	
	@Test // tests that multiplication works
	public void multiplicationTest() {
		MS_Multiply msm = new MS_Multiply(s1,m1);
		Solution sol = msm.getSolution();
		assertTrue(sol.getAnswer() instanceof Matrix);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][0] == 2);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][1] == 4);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][0] == 6);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][1] == 8);
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
//		List<String> latex = msm.toLatex();
//		int counter = 0;
//		for (String s : latex){
//			System.out.println("======== List item: "+counter+ "========");
//			counter++;
//			System.out.println(s + "\n");
//		}
//		System.out.println();
		
	}
	
	@Test // tests that the same multiplication works if the argument order is switched
	public void multiplicationTestReverse() {
		MS_Multiply msm = new MS_Multiply(m1,s1);
		Solution sol = msm.getSolution();
		assertTrue(sol.getAnswer() instanceof Matrix);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][0] == 2);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][1] == 4);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][0] == 6);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][1] == 8);
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
//		List<String> latex = msm.toLatex();
//		int counter = 0;
//		for (String s : latex){
//			System.out.println("======== List item: "+counter+ "========");
//			counter++;
//			System.out.println(s + "\n");
//		}
//		System.out.println();
	}
	
	@Test // tests multiplication with whole numbers
	public void multiplicationWholeNumber() {
		MS_Multiply msm = new MS_Multiply(m3,s2);
		Solution sol = msm.getSolution();
		assertTrue(sol.getAnswer() instanceof Matrix);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][0] == 2);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[0][1] == 4);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][0] == 6);
		assertTrue(((Matrix) sol.getAnswer()).getValues()[1][1] == 8);
		
		// use this to check LaTeX (copy and paste into LaTeX compiler)
//		List<String> latex = msm.toLatex();
//		int counter = 0;
//		for (String s : latex){
//			System.out.println("======== List item: "+counter+ "========");
//			counter++;
//			System.out.println(s + "\n");
//		}
//		System.out.println();
	}
	
	@Test // test a matrix with a null entry
	public void badMatrixTest(){
		try{
			new MS_Multiply(s1,m2);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Each matrix index must contain a non-null entry"));
		}
	}

}
