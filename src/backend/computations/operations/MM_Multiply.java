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
			throw new IllegalArgumentException("ERROR: Number of columns of first matrix must equal number of columns of second matrix");
		}
		
		//for (int ) TODO
		
	}

}
