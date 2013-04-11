/**
 * 
 */
package backend.blocks;

/** Enumerates types of Operators
 * 
 * @author baebi
 */
public enum Op {
	PLUS(2,false,"PLUS"), MINUS(2,false,"MINUS"), TIMES(1,false,"TIMES"), SCALAR_MULTIPLY(1,false,"SCALAR-MULTIPLY"), 
	DETERMINANT(0,true,"DETERMINANT"), ROW_REDUCE(0,true,"ROW-REDUCE"); // do not give rank below zero
	private int _rank; // the PEMDAS rank of this operation
	private boolean _isUnary; // true if this is a unary operation like determinant, power, or row-reduce
	private String _name;
	
	/** Give the Op a rank
	 * 
	 * @param value the PEMDAS rank of this Op
	 */
	private Op(int value,boolean isUnary,String name){
		_rank = value;
		_isUnary = isUnary;
		_name = name;
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
	
	
	/**
	 * @return the name of the given Op
	 */
	public String getName(){
		return _name;
	}
}
