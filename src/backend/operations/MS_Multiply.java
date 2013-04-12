/**
 * TODO: Test this class
 */
package backend.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;
import backend.blocks.Scalar;

/** Computes a scalar/matrix multiplication
 * 
 * @author baebi
 */
public class MS_Multiply extends Computable {
	private Solution _solution;
	
	/* (non-Javadoc)
	 * @see backend.operations.Computable#getSolution()
	 */
	@Override
	public Solution getSolution() {
		return _solution;
	}
	
	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param matrix the matrix to multiply
	 * @param scalar the scalar to multiply by
	 */
	public MS_Multiply(Matrix matrix, Scalar scalar){
		generateSolution(matrix,scalar);
	}
	
	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param scalar the scalar to multiply by
	 * @param matrix the matrix to multiply
	 */
	public MS_Multiply(Scalar scalar, Matrix matrix){
		generateSolution(matrix,scalar);
	}

	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param matrix the matrix to multiply
	 * @param scalar the scalar to multiply by
	 */
	private void generateSolution(Matrix matrix, Scalar scalar){
		List<Countable> argList = new ArrayList<>();
		argList.add(matrix);
		argList.add(scalar);
		DisplayType answerDisplayType = resolveDisplayType(argList);
		
		Double[][] matrixVals = matrix.getValues();
		Double scalarVal = scalar.getValue();
		if (scalarVal == null){
			throw new IllegalArgumentException("ERROR: Scalar must have a real-number value");
		}
		String[][] multiplicationStep = new String[matrixVals.length][matrixVals[0].length];
		Double[][] result = new Double[matrixVals.length][matrixVals[0].length];
		for (int i = 0; i < matrixVals.length; i++){
			for (int j = 0; j < matrixVals[0].length; j++){
				if (matrixVals[i][j] == null){
					throw new IllegalArgumentException("ERROR: Each matrix index must contain a non-null entry");
				}
				result[i][j] = matrixVals[i][j] * scalarVal;
				multiplicationStep[i][j] = matrixVals[i][j]+" * "+scalarVal;
			}
		}
		
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result);
		step1Matrix.setCustomDisplay(multiplicationStep);
		Step step1 = new Step(step1Matrix);
		
		Matrix step2Matrix = new Matrix(answerDisplayType, matrixVals);
		Step step2 = new Step(step2Matrix);
		
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);
		inputs.add(scalar);
		_solution = new Solution(Op.SM_MULTIPLY,inputs,step2Matrix,steps);
	}
	
}
