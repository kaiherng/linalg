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
				multiplicationStep[i][j] = getDisplayValue(matrixVals[i][j],_displayType)+" \\ \\times \\ "+getDisplayValue(scalarVal,_displayType);
			}
		}
		
		Matrix step1Matrix = new Matrix(DisplayType.CUSTOM, result);
		step1Matrix.setCustomDisplay(multiplicationStep);
		_answer = step1Matrix;
		
		Matrix step2Matrix = new Matrix(answerDisplayType, result);
		
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);
		inputs.add(scalar);
		
		List<String> latex = toLatex();
		_solution = new Solution(Op.MULTIPLY,inputs,step2Matrix, latex);
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
		toReturn.add("\\vspace{10mm} \\mathrm{Scalar \\ Matrix \\ Multiplication}");
		toReturn.add("\\vspace{10mm} 1.");
		MatrixDraw m1 = new MatrixDraw(_matrixArg);
		StringBuilder b = new StringBuilder();
		b.append("\\hspace{15mm}");
		if (_scalarFirst){
			b.append(_scalarArg.getDisplayValue());
			b.append(" \\times ");
			b.append(m1.getCorrectLatex(_displayType));
		}else{
			b.append(m1.getCorrectLatex(_displayType));
			b.append(" \\times ");
			b.append(_scalarArg.getDisplayValue());
		}
		toReturn.add(b.toString());
		MatrixDraw answer = new MatrixDraw(_answer);
		toReturn.add("\\vspace{15mm} 2. \\ \\mathrm{Mutliply \\ each \\ index \\ by \\ "+_scalarArg.getDisplayValue()+"}");
		toReturn.add("\\hspace{15mm} \\vspace{15mm}"+answer.getCorrectLatex(DisplayType.CUSTOM));
		toReturn.add("\\vspace{15mm} \\mathrm{Solution:}");
		toReturn.add("\\hspace{15mm}\\vspace{15mm}"+answer.getCorrectLatex(_displayType));
		return toReturn;
	}
	
}
