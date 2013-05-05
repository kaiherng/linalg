package backend.blocks;

/** 
 * Enumerates types of Operators
 * 
 * @author baebi
 */
public enum Op {
	// rank 2 operations
	PLUS(2,false,"PLUS"),
	MINUS(2,false,"MINUS"),
	
	//rank 1 operations
	MULTIPLY(1,false,"TIMES"),  
	SS_DIVIDE(1,false,"SS_DIVIDE"),
	
	//rank 0 (unary) operations
	DETERMINANT(0,true,"DETERMINANT"),
	ROW_REDUCE(0,true,"ROW-REDUCE"),
	M_COLUMNSPACE(0,true,"COLUMN-SPACE"),
	M_INVERSE(0,true,"M-INVERSE"),
	POWER(0,false,"POWER"),
	M_RANK(0,true,"M-RANK"),
	M_TRANSPOSE(0,true,"M-TRANSPOSE");
	
	// do not give rank below zero
	
	private int _rank; // the PEMDAS rank of this operation
	private boolean _isUnary; // true if this is a unary operation like determinant, power, or row-reduce
	private String _name;
	
	
	/** 
	 * Give the Op a rank
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
	
	/**
	 * Get's the icon for this enum
	 * 
	 * @return the symbol depicting this enum
	 */
	public String getIcon2(){
		switch(this){
		case PLUS:
			return "icons/plus.png";
		case MINUS:
			return "icons/minus.png";
		case SS_DIVIDE:
			return "icons/divide.png";
		case MULTIPLY: 
			return "icons/multiply.png";
		case DETERMINANT:
			return "icons/det.png";
		case ROW_REDUCE:
			return "icons/rr.png";
		case M_COLUMNSPACE:
			return "icons/col.png";
		case M_INVERSE:
			return "icons/inv.png";
		case POWER:
			return "icons/power.png";
		case M_RANK:
			return "icons/rank.png";
		case M_TRANSPOSE:
			return "icons/transpose.png";
		default:
			return "?";
		}
	}
}
