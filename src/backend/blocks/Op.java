/**
 * 
 */
package backend.blocks;

/** Enumerates types of Operators
 * 
 * @author baebi
 */
public enum Op {
	PLUS(0,false), MINUS(0,false), TIMES(1,false), SCALAR_MULTIPLY(1,false), 
	DETERMINANT(2,true), ROW_REDUCE(3,true);
	private int _rank; // the PEMDAS rank of this operation
	private boolean _isUnary; // true if this is a unary operation like determinant, power, or row-reduce
	
	
	/** Give the Op a rank
	 * 
	 * @param value the PEMDAS rank of this Op
	 */
	private Op(int value,boolean isUnary){
		_rank = value;
	}
	
	
	/**
	 * @return the rank of this Op
	 */
	public int getRank(){
		return _rank;
	}
	
	
	/**
	 * @return boolean indicating whether or not this Op is a unary operator
	 */
	public boolean isUnary(){
		return _isUnary;
	}
}
