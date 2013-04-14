/**
 * TODO: test this class
 */
package backend.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;

/** Addition Operation
 * 
 * @author baebi
 */
public class MM_PlusMinus extends Computable {
	private Solution _solution;
	
	@Override
	public Solution getSolution() {
		return _solution;
	}
	

	/** Sums two matrices
	 * 
	 * @param matrixA the first matrix to add
	 * @param matrixB the second matrix to add
	 * @param isPlus true iff this is a plus operation. false iff this is a minus operation
	 */
	public MM_PlusMinus(Matrix matrixA, Matrix matrixB, boolean isPlus) throws IllegalArgumentException {
		List<Countable> matrixList = new ArrayList<>();
		matrixList.add(matrixA);
		matrixList.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(matrixList); // choose DisplayType to use
		
		Double[][] aValues = matrixA.getValues();
		Double[][] bValues = matrixB.getValues();
		if (aValues.length != bValues.length || aValues[0].length != bValues[0].length){
			throw new IllegalArgumentException("ERROR (Plus): Matrices must have same dimensions");
		}
		
		String[][] additionStep = new String[aValues.length][aValues[0].length]; // we'll show the addition step in here
		Double[][] result = new Double[aValues.length][aValues[0].length];       // this will be the result matrix
		for(int i = 0; i < aValues.length; i++){
			for (int j = 0; j < aValues[0].length; j++){
				if (aValues[i][j] == null || bValues[i][j] == null){
					throw new IllegalArgumentException("ERROR: Each index must contain a non-null entry");
				}
				if (isPlus){
					result[i][j] = aValues[i][j] + bValues[i][j];
					additionStep[i][j] = Double.toString(aValues[i][j])+" + "+Double.toString(bValues[i][j]);
					System.out.println(additionStep[i][j]);
				}else{
					result[i][j] = aValues[i][j] - bValues[i][j];
					additionStep[i][j] = Double.toString(aValues[i][j])+" - "+Double.toString(bValues[i][j]);
				}
			}
		}
		
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result); // this will show the addition in each index, for instance ("1 + 2")
		step1Matrix.setCustomDisplay(additionStep);
		Step step1 = new Step(step1Matrix);
		
		Matrix step2Matrix = new Matrix(answerDisplayType,result);
		Step step2 = new Step(step2Matrix);
		
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrixA);
		inputs.add(matrixB);
		if (isPlus){
			_solution = new Solution(Op.MM_PLUS,inputs, step2Matrix, steps);
		}else{
			_solution = new Solution(Op.MM_MINUS,inputs, step2Matrix, steps);
		}
	}
	
}
