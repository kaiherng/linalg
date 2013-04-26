/**
 * TODO: complete and test
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
import backend.computations.infrastructure.Step;

/** 
 * Performs a matrix multiplication operation
 * 
 * @author baebi
 */
public class MM_Multiply extends Computable {
	private Solution _solution;
	private Matrix _matrixA, _matrixB,_step1Matrix;
	private DisplayType _displayType;
	
	
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
		_matrixA = matrixA;
		_matrixB = matrixB;
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrixA);
		inputs.add(matrixB);
		DisplayType answerDisplayType = resolveDisplayType(inputs);
		_displayType = answerDisplayType;
		
		_matrixA.setDisplayType(answerDisplayType);
		_matrixB.setDisplayType(answerDisplayType);
		
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
					expanded += "("+aDisplay[i][row]+" \\ * \\ "+bDisplay[col][i]+") \\ + \\ ";
				}
				expanded = expanded.substring(0,expanded.length()-7);
				expanded += " \\ = \\ "+getDisplayValue(res,answerDisplayType);
				result[col][row] = res; 
				multStep[col][row] = expanded;
			}
		}
		
		// first step shows computation at each index
		_step1Matrix = new Matrix(DisplayType.CUSTOM, result);
		_step1Matrix.setCustomDisplay(multStep);
		Step step1 = new Step(_step1Matrix);
		
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


	@Override
	/**
	 * Three steps:
	 * - "m1 + m2"
	 * - a list of 
	 * - the answer matrix
	 */
	public List<String> toLatex() {
		List<String> toReturn = new ArrayList<>();
		MatrixDraw m1 = new MatrixDraw(_matrixA);
		MatrixDraw m2 = new MatrixDraw(_matrixB);
		StringBuilder b = new StringBuilder();
		String m1String = m1.getCorrectLatex(_displayType);
		String m2String = m2.getCorrectLatex(_displayType);
		b.append(m1String);
		b.append(" $\\times$ ");
		b.append(m2String);
		toReturn.add(b.toString());
		b.delete(0, b.length());
		
		// make list of equations for each index
		b.append("\\begin{itemize} \n");
		String[][] customEntries = _step1Matrix.getCustomDisplayValues();
		for (int i = 0; i < customEntries.length; i++){
			for (int j = 0; j < customEntries[0].length; j++){
				b.append("\\item $"+customEntries[i][j]+"$ \\\\ \n");
			}
		}
		b.append("\\end{itemize}");
		toReturn.add(b.toString());
		
		MatrixDraw m3 = new MatrixDraw(_step1Matrix);
		toReturn.add(m3.getCorrectLatex(_displayType));
		return toReturn;
	}
}










