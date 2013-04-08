package backend.blocks;

/** Mathematical operation
 * 
 * @author baebi
 */
class Operation implements Numerical{
	public enum Op {PLUS, MINUS, TIMES} //TODO add more Ops
	private int _rank;
	private Op _type;
	
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
		return _rank;
	}
	
	//TODO set rank
}