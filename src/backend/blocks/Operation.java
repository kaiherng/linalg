package backend.blocks;


/** Mathematical operation
 * 
 * @author baebi
 */
public class Operation implements Numerical{
	
	// this enum also specifies the rank and isUnary status of this Operation
	private Op _type; 
	
	// can be a Countable, but could also be another operation that results in a the Countable
	private Numerical _firstArg, _secondArg;

	
	/** Constructor for this Operation
	 * 
	 * @param operator the type of operation this is to be
	 */
	public Operation(Op operator){
		_type = operator;
	}
	
	
	/**
	 *  @return Getter for the type of operator this Operation represents
	 */
	public Op getType(){
		return _type;
	}
	
	
	/**
	 * @return Getter for the operator (PEMDAS) rank. Lower ranks are preferred operations
	 */
	public int getRank(){
		return _type.getRank();
	}
	
	
	/** Sets the first argument to this operation
	 * 
	 * @param arg1 the argument. Can be a Countable or can be an Operation whose result is used in this Operation
	 */
	public void setFirstArg(Numerical arg1){
		_firstArg = arg1;
	}
	
	
	/** Sets the second argument to this operation
	 * 
	 * @param arg2 the argument. Can be a Countable or can be an Operation whose result is used in this Operation
	 */
	public void setSecondArg(Numerical arg2){
		_secondArg = arg2;
	}
	
	
	/**
	 * @return the first argument. Can be a Countable or can be an Operation whose result is used in this Operation
	 */
	public Numerical getFirstArg(){
		return _firstArg;
	}
	
	
	/**
	 * @return the 2nd argument. Can be a Countable or can be an Operation whose result is used in this Operation
	 */
	public Numerical getSecondArg(){
		return _secondArg;
	}
	
	/** 
	 * @return true if this is a unary operator
	 */
	public boolean isUnary(){
		return _type.isUnary();
	}


	@Override
	public String getName() {
		return _type.getName();
	}
	
	//TODO: fill with something???
	public String toLatex(){
		return "";
	}
	
}