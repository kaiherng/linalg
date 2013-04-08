package backend.blocks;

import java.util.HashMap;
import java.util.Map;

/** Mathematical operation
 * 
 * @author baebi
 */
public class Operation implements Numerical{
	public enum Op {PLUS, MINUS, TIMES} //TODO add more Ops
	private int _rank;
	private Op _type;
	private Map<Op,Integer> rankMap; // Maps operations to a rank (think PEMDAS). Lower ranks are preferred

	
	/** Constructor for this Operation
	 * 
	 * @param operator the type of operation this is to be
	 */
	public Operation(Op operator){
		rankMap = new HashMap<>();
		rankMap.put(Op.PLUS,2);
		rankMap.put(Op.MINUS,2);
		rankMap.put(Op.TIMES,1);
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