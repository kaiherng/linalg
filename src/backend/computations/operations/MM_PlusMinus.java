/**
 * TODO: test toLatex method 
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import matrixDraw.MatrixDraw;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;


/** 
 * Addition Operation
 * 
 * @author baebi
 */
public class MM_PlusMinus extends Computable {
	private Solution _solution;
	private Matrix _matrix1,_matrix2,_step1Matrix,_step2Matrix;
	private DisplayType _displayType;
	private String _operation;
	
	@Override
	public Solution getSolution() {
		return _solution;
	}
	

	/** 
	 * Sums two matrices
	 * 
	 * @param matrixA the first matrix to add
	 * @param matrixB the second matrix to add
	 * @param isPlus true iff this is a plus operation. false iff this is a minus operation
	 */
	public MM_PlusMinus(Matrix matrixA, Matrix matrixB, boolean isPlus) throws IllegalArgumentException {
		_matrix1 = matrixA;
		_matrix2 = matrixB;
		
		List<Countable> matrixList = new ArrayList<>();
		matrixList.add(matrixA);
		matrixList.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(matrixList); // choose DisplayType to use
		_displayType = answerDisplayType;
		
		// set the display type of the matrices to the same type
		matrixA.setDisplayType(answerDisplayType);
		matrixB.setDisplayType(answerDisplayType);
		
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
					additionStep[i][j] = "$"+getDisplayValue(aValues[i][j],answerDisplayType)+" \\ + \\ "+getDisplayValue(bValues[i][j],answerDisplayType) + "$";
				}else{
					result[i][j] = aValues[i][j] - bValues[i][j];
					additionStep[i][j] = "$" +getDisplayValue(aValues[i][j],answerDisplayType)+" \\ - \\ "+getDisplayValue(bValues[i][j],answerDisplayType) + "$";
				}
			}
		}
		
		_step1Matrix = new Matrix(DisplayType.CUSTOM, result); // this will show the addition in each index, for instance ("1 + 2")
		_step1Matrix.setCustomDisplay(additionStep);
		_step2Matrix = new Matrix(answerDisplayType,result);

		List<String> latex = toLatex();
		if (isPlus){
			_solution = new Solution(Op.MM_PLUS,matrixList, _step2Matrix, latex);
			_operation = "+";
		}else{
			_solution = new Solution(Op.MM_MINUS,matrixList, _step2Matrix, latex);
			_operation = "-";
		}
	}


	@Override
	/**
	 * Three steps:
	 *  - "m1 + m2"
	 *  - result matrix with each index containing an operation
	 *  - the answer matrix
	 */
	public List<String> toLatex() {
		List<String> toReturn = new ArrayList<>();
		MatrixDraw m1 = new MatrixDraw(_matrix1);
		MatrixDraw m2 = new MatrixDraw(_matrix2);
		String m1String = m1.getCorrectLatex(_displayType);
		String m2String = m2.getCorrectLatex(_displayType);
		StringBuffer b = new StringBuffer();
		b.append(m1String);
		b.append("$\\"+_operation+"\\$");
		b.append(m2String);
		toReturn.add(b.toString()); // first step shows "m1 + m2"
		b.delete(0, b.length()); // empty buffer
		MatrixDraw m3 = new MatrixDraw(_step1Matrix);
		MatrixDraw m4 = new MatrixDraw(_step2Matrix);
		toReturn.add(m3.getCorrectLatex(DisplayType.CUSTOM));
		toReturn.add(m4.getCorrectLatex(_displayType));
		return toReturn;
	}
	
}



