/**
 * 
 */
package backend.operations;

import backend.blocks.Matrix;
import backend.blocks.Scalar;

/**
 * @author baebi
 *
 */
public class ScalarMultiply extends Computable {
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
	public ScalarMultiply(Matrix matrix, Scalar scalar){
		generateSolution(matrix,scalar);
	}
	
	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param scalar the scalar to multiply by
	 * @param matrix the matrix to multiply
	 */
	public ScalarMultiply(Scalar scalar, Matrix matrix){
		generateSolution(matrix,scalar);
	}

	
	/** Generates the Solution to a scalar multiplication
	 * 
	 * @param matrix the matrix to multiply
	 * @param scalar the scalar to multiply by
	 */
	private void generateSolution(Matrix matrix, Scalar scalar){
		// TODO
	}
}
