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
	private DisplayType _displayType;
	
	
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
		_scalarArg = scalar;
		_matrixArg = matrix;
		List<Countable> argList = new ArrayList<>();
		argList.add(matrix);
		argList.add(scalar);
		DisplayType answerDisplayType = resolveDisplayType(argList);
		
		_displayType = answerDisplayType;
		_scalarArg.setDisplayType(answerDisplayType);
		_matrixArg.setDisplayType(answerDisplayType);
		for (int i = 0; i < (int) scalar.getValue(); i++){
			
		}
		// TODO: finish
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
		List<String> toReturn = new ArrayList<>();
		StringBuilder b = new StringBuilder();
		MatrixDraw m1 = new MatrixDraw(_matrixArg);
		b.append(m1.getCorrectLatex(_displayType));
		b.append("$^{");
		b.append(_scalarArg.getDisplayValue()+"}");
		toReturn.add(b.toString());
		return toReturn;
	}

}
