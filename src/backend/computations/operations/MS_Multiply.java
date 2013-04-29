/**
 * TODO: Test this class
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import matrixDraw.MatrixDraw;
import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Op;
import backend.blocks.Scalar;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** Computes a scalar/matrix multiplication
 * 
 * @author baebi
 */
public class MS_Multiply extends Computable {
	private Solution _solution;
	private Scalar _scalarArg;
	private Matrix _matrixArg;
	private boolean _scalarFirst = false;
	private DisplayType _displayType;
	private Matrix _answer;
	
	
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
		_scalarFirst = true;
		generateSolution(matrix,scalar);
	}

	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param matrix the matrix to multiply
	 * @param scalar the scalar to multiply by
	 */
	private void generateSolution(Matrix matrix, Scalar scalar){
		_scalarArg = scalar;
		_matrixArg = matrix;
		List<Countable> argList = new ArrayList<>();
		argList.add(matrix);
		argList.add(scalar);
		DisplayType answerDisplayType = resolveDisplayType(argList);
		
		_displayType = answerDisplayType;
		_scalarArg.setDisplayType(answerDisplayType);
		_matrixArg.setDisplayType(answerDisplayType);
		
		Double[][] matrixVals = matrix.getValues();
		Double scalarVal = scalar.getValue();
		String[][] multiplicationStep = new String[matrixVals.length][matrixVals[0].length];
		
		Double[][] result = new Double[matrixVals.length][matrixVals[0].length];
		for (int i = 0; i < matrixVals.length; i++){
			for (int j = 0; j < matrixVals[0].length; j++){
				if (matrixVals[i][j] == null){
					throw new IllegalArgumentException("ERROR: Each matrix index must contain a non-null entry");
				}
				result[i][j] = matrixVals[i][j] * scalarVal;
				multiplicationStep[i][j] = "$"+getDisplayValue(matrixVals[i][j],_displayType)+" \\ * \\ "+getDisplayValue(scalarVal,_displayType)+"$";
			}
		}
		
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result);
		step1Matrix.setCustomDisplay(multiplicationStep);
		Step step1 = new Step(step1Matrix);
		_answer = step1Matrix;
		
		Matrix step2Matrix = new Matrix(answerDisplayType, result);
		Step step2 = new Step(step2Matrix);
		
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		
		
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);
		inputs.add(scalar);
		
		List<String> latex = toLatex();
		_solution = new Solution(Op.SM_MULTIPLY,inputs,step2Matrix, latex);
	}


	@Override
	/**
	 * Steps:
	 * - s1 * m1
	 * - answer matrix with computation at each index
	 * - answer matrix
	 */
	public List<String> toLatex() {
		List<String> toReturn = new ArrayList<>();
		MatrixDraw m1 = new MatrixDraw(_matrixArg);
		StringBuilder b = new StringBuilder();
		if (_scalarFirst){
			b.append(_scalarArg.getDisplayValue());
			b.append(" $+$ ");
			b.append(m1.getCorrectLatex(_displayType));
		}else{
			b.append(_scalarArg.getDisplayValue());
			b.append(" $+$ ");
			b.append(m1.getCorrectLatex(_displayType));
		}
		toReturn.add(b.toString());
		MatrixDraw answer = new MatrixDraw(_answer);
		toReturn.add(answer.getCorrectLatex(DisplayType.CUSTOM));
		toReturn.add(answer.getCorrectLatex(_displayType));
		return toReturn;
	}
	
}
