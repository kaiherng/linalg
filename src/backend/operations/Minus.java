package backend.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;

public class Minus extends Computable {
	private Solution _solution;
	
	@Override
	public Solution getSolution() {
		return _solution;
	}

	
	/** Computes the solution to a subtraction
	 * 
	 * @param matrixA The matrix to subtract from
	 * @param matrixB The matrix that subtracts
	 */
	public Minus(Matrix matrixA, Matrix matrixB){
		List<Countable> matrixList = new ArrayList<>();
		matrixList.add(matrixA);
		matrixList.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(matrixList); // choose DisplayType to use
		
		Double[][] aValues = matrixA.getValues();
		Double[][] bValues = matrixB.getValues();
		if (aValues.length != bValues.length || aValues[0].length != bValues[0].length){
			throw new IllegalArgumentException("ERROR (Plus): Matrices must have same dimensions");
		}
		
		String[][] subtractionStep = new String[aValues.length][aValues[0].length]; // we'll show the addition step in here
		Double[][] result = new Double[aValues.length][aValues[0].length];          // this will be the result matrix
		for(int i = 0; i < aValues.length; i++){
			for (int j = 0; j < aValues[0].length; j++){
				if (aValues[i][j] == null || bValues[i][j] == null){
					throw new IllegalArgumentException("ERROR: Each index must contain a non-null entry");
				}
				result[i][j] = aValues[i][j] - bValues[i][j];
				subtractionStep[i][j] = Double.toString(aValues[i][j])+" - "+Double.toString(bValues[i][j]);
			}
		}
		
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result); // this will show the addition in each index, for instance ("1 - 2")
		step1Matrix.setCustomDisplay(subtractionStep);
		Step step1 = new Step(step1Matrix);
		
		Matrix step2Matrix = new Matrix(answerDisplayType,result);
		Step step2 = new Step(step2Matrix);
		
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrixA);
		inputs.add(matrixB);
		_solution = new Solution(Op.MINUS,inputs,step2Matrix, steps);
	}
				
}

