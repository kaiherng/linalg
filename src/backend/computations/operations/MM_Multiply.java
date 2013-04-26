/**
 * TODO: complete and test
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** 
 * Performs a matrix multiplication operation
 * 
 * @author baebi
 */
public class MM_Multiply extends Computable {
	private Solution _solution;
	
	/* (non-Javadoc)
	 * @see backend.operations.Computable#getSolution()
	 */
	@Override
	public Solution getSolution() {
		return _solution;
	}
	
	
	/** 
	 * Creates the Solution to a multiplication
	 * 
	 * @param matrixA the first factor
	 * @param matrixB the second factor
	 */
	public MM_Multiply(Matrix matrixA, Matrix matrixB){
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrixA);
		inputs.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(inputs);
		
		matrixA.setDisplayType(answerDisplayType);
		matrixB.setDisplayType(answerDisplayType);
		
		Double[][] aValues = matrixA.getValues();
		Double[][] bValues = matrixB.getValues();
		String[][] aDisplay = matrixA.getDisplayValues();
		String[][] bDisplay = matrixB.getDisplayValues();
		
		if(aValues.length != bValues[0].length){
			throw new IllegalArgumentException("ERROR: Number of columns of first matrix must equal number of rows of second matrix");
		}
		
		// we will end up with two steps: an answer, and a matrix of strings showing how each index was calculated
		Double[][] result = new Double[matrixB.getNumCols()][matrixA.getNumRows()];
		String[][] multStep = new String[matrixB.getNumCols()][matrixA.getNumRows()];
		
		// for each index in the result matrix
		for (int col = 0; col < result.length; col++){
			for (int row = 0; row < result[0].length; row++){
				String expanded = "";
				double res = 0;
				for (int i = 0; i < matrixA.getNumCols(); i++){
					if (aValues[i][row] == null || bValues[col][i] == null){
						throw new IllegalArgumentException("ERROR: Matrix should not contain null indices");
					}
					res += aValues[i][row] * bValues[col][i];
					expanded += "("+aDisplay[i][row]+" * "+bDisplay[col][i]+") + ";
				}
				expanded = expanded.substring(0,expanded.length()-3);
				result[col][row] = res; 
				multStep[col][row] = expanded;
			}
		}
		
		// first step shows computation at each index
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result);
		step1Matrix.setCustomDisplay(multStep);
		Step step1 = new Step(step1Matrix);
		
		// second step shows the resulting matrix product
		Matrix step2Matrix = new Matrix(answerDisplayType,result);
		Step step2 = new Step(step2Matrix);
		
		// put steps in a list
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		
		// create the solution
		_solution = new Solution(Op.MM_MULTIPLY,inputs,step2Matrix,steps);
	}
}










