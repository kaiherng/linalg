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
	SS_MULTIPLY(1,false,"SS_TIMES"), MM_MULTIPLY(1,false,"MM_TIMES"), 
	SM_MULTIPLY(1,false,"SM_MULTIPLY"), SS_DIVIDE(1,false,"SS_DIVIDE"),
	
	//rank 0 (unary) operations
	DETERMINANT(0,true,"DETERMINANT"),
	ROW_REDUCE(0,true,"ROW-REDUCE"),
	M_COLUMNSPACE(0,true,"COLUMN-SPACE"),
	M_INVERSE(0,true,"M-INVERSE"),
	M_POWER(0,true,"M-POWER"),
	M_RANK(0,true,"M-RANK"),
	M_TRANSPOSE(0,true,"M-TRANSPOSE"),
	NULLSPACE(0,true,"NULL-SPACE"), 
	S_POWER(0,true,"S-POWER");
	
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
			return "+";
		case MINUS:
			return "-";
		case SS_DIVIDE:
			return "S/S";
		case SS_MULTIPLY:
			return "S*S";
		case MM_MULTIPLY:
			return "M*M";
		case SM_MULTIPLY:
			return "S*M";
		case DETERMINANT:
			return "det";
		case ROW_REDUCE:
			return "RR";
		case M_COLUMNSPACE:
			return "CS";
		case M_INVERSE:
			return "INV";
		case M_POWER:
			return "^";
		case M_RANK:
			return "R";
		case NULLSPACE:
			return "NS";
		case M_TRANSPOSE:
			return "T";
		case S_POWER:
			return "S^";
		default:
			return "?";
		}
	}
}
