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
			throw new IllegalArgumentException("Number of columns of first matrix must equal number of rows of second matrix");
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
		_step1Matrix = new Matrix(DisplayType.CUSTOM,result);
		_step1Matrix.setCustomDisplay(multStep);
		
		// second step shows the resulting matrix product
		Matrix step2Matrix = new Matrix(answerDisplayType,result);
		
		List<String> latex = toLatex();
		
		// create the solution
		_solution = new Solution(Op.MULTIPLY,inputs,step2Matrix,latex);
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
		toReturn.add("\\vspace{10mm} \\mathrm{Matrix \\ Multiply:}");
		StringBuilder b = new StringBuilder();
		String m1String = MatrixDraw.getCorrectLatex(_displayType,_matrixA);
		String m2String = MatrixDraw.getCorrectLatex(_displayType,_matrixB);
		b.append(m1String);
		b.append(" $\\times$ ");
		b.append(m2String);
		
		toReturn.add("\\vspace{10mm} 1.\\\\ \\hspace{15mm}"+b.toString());
		b.delete(0, b.length());
		
		toReturn.add("\\vspace{15mm} 2. \\ \\mathrm{Equation \\ at \\ each \\ index}");
		// make list of equations for each index
		int counter = 0;
		String[][] customEntries = _step1Matrix.getCustomDisplayValues();
		for (int i = 0; i < customEntries.length; i++){
			for (int j = 0; j < customEntries[0].length; j++){
				toReturn.add("\\vspace{15mm} \\hspace{15mm} \\mathrm{Column \\ "+(i+1)+" \\ of \\ 1st \\ matrix \\ multiplied \\ with \\ row \\ " + (j+1) + " \\ of \\ 2nd \\ matrix: }\\\\ \\vspace{10mm} \\hspace{60mm} "+customEntries[i][j]);
				counter++;
				if (counter > 4){
					toReturn.add("\\vspace{25mm} \\hspace{15mm} \\mathrm{Continue \\ by \\ multiplying \\ together \\ the \\ remaining \\ rows \\ and \\ columns \\\\ of \\ the \\ matrices}");
					break;
				}
			}
			if (counter > 4){
				break;
			}
		}
		
		toReturn.add("\\vspace{15mm} \\mathrm{Solution:} \\\\ \\hspace{15mm}"+MatrixDraw.getCorrectLatex(_displayType,_step1Matrix));
		return toReturn;
	}
}










