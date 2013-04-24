/**
 * TODO: complete and test
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;

/** Performs a matrix multiplication operation
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
	
	
	/** Creates the Solution to a multiplication
	 * 
	 * @param matrixA the first factor
	 * @param matrixB the second factor
	 */
	public MM_Multiply(Matrix matrixA, Matrix matrixB){
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrixA);
		inputs.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(inputs);
		
		Double[][] aValues = matrixA.getValues();
		Double[][] bValues = matrixB.getValues();
		
		if(aValues.length != bValues[0].length){
			throw new IllegalArgumentException("ERROR: Number of columns of first matrix must equal number of rows of second matrix");
		}
		
		// we will end up with two steps: an answer, and a matrix of strings showing how each index was calculated
		double[][] result = new double[matrixB.getNumCols()][matrixA.getNumRows()];
		String[][] multStep = new String[matrixB.getNumCols()][matrixA.getNumRows()];
		
		// for each index in the result matrix
		for (int col = 0; col < result.length; col++){
			for (int row = 0; row < result[0].length; row++){
				String expanded = "";
				double res = 0;
				for (int i = 0; i < matrixA.getNumCols(); i++){
					res += aValues[i][row] * bValues[col][i];
					expanded += "("+aValues[i][row]+" * "+bValues[col][i]+") ";
				}
				result[col][row] = res; 
				multStep[col][row] = expanded;
			}
		}
		
	}
}










