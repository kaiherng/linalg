/**
 * TODO: write, test, toLatex, test toLatex
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

/**
 * Computes a matrix power
 *  
 * @author baebi
 */
public class M_Power extends Computable {
	private Solution _solution;
	private Matrix _matrixArg;
	private Scalar _scalarArg;
	private List<String> _latex = new ArrayList<>();
	
	
	/**
	 * Computes the solution to a matrix power
	 * 
	 * @param matrix the base matrix
	 * @param scalar the scalar exponent
	 */
	public M_Power(Matrix matrix, Scalar scalar){
		if (Math.floor(scalar.getValue()) != scalar.getValue() || scalar.getValue() < 0){
			throw new IllegalArgumentException("ERROR (M_Power.java) : expects scalar to be non-negative whole-number");
		}
		if (matrix.getNumCols() != matrix.getNumRows()){
			throw new IllegalArgumentException("ERROR: Matrix Power requires a square matrix");
		}
		_scalarArg = scalar;
		_matrixArg = matrix;
		List<Countable> argList = new ArrayList<>();
		argList.add(matrix);
		argList.add(scalar);
		DisplayType answerDisplayType = resolveDisplayType(argList);
		
		_scalarArg.setDisplayType(answerDisplayType);
		_matrixArg.setDisplayType(answerDisplayType);
		Matrix toMult = matrix;
		for (int i = 0; i < (int) scalar.getValue(); i++){
			MM_Multiply mult = new MM_Multiply(toMult,matrix);
			_latex.addAll(mult.toLatex());
			toMult = (Matrix) mult.getSolution().getAnswer();
		}
		_solution = new Solution(Op.M_POWER,argList,toMult,_latex);
	}
	
	
	/* (non-Javadoc)
	 * @see backend.operations.Computable#getSolution()
	 */
	@Override
	public Solution getSolution() {
		return _solution;
	}
	

	// TODO: finish
	@Override
	/**
	 * Steps:
	 * - "m1 ^ s1"
	 * - one multiplication per power
	 * - answer
	 */
	public List<String> toLatex() {
		return _latex;
	}

}
