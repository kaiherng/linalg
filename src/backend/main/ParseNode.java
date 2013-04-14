/**
 * 
 */
package backend.main;

import backend.operations.Solution;

/** A tree of ParseNodes is returned to the front-end after a computation. This allows the front-end to understand the order 
 *  of operations. Less preferential operations like + or - will generally appear at the top of the tree. Basically, a current node's
 *  children must contain solutions that were computed before the solution contained in the current node.  
 * 
 * @author baebi
 */
public class ParseNode {
	private Solution _solution; // The Solution of this ParseNode
	private ParseNode _left;    // contains the Solution to the 1st argument to the operation that created _solution
	private ParseNode _right;   // contains the Solution to the 2nd argument to the operation that created _solution
	// Note, if the 1st or 2nd arguments to the operation that created _solution were not themselves computed in
	// a Computable, the respective _left or _right will be null
	
	
	/** Constructor
	 * 
	 * @param solution the Solution of a single operation
	 * @param arg1 the ParseNode that contains the Solution which was used as the first argument to compute <solution>
	 * @param arg2 the ParseNode that contains the Solution which was used as the second argument to compute <solution>
	 */
	public ParseNode(Solution solution,ParseNode arg1, ParseNode arg2){
		_solution = solution;
		_left = arg1;
		_right = arg2;
	}
	
	
	/**
	 * @return the solution in this ParseNode
	 */
	public Solution getSolution(){
		return _solution;
	}
	
	
	/**
	 * @return the solution comprising the first argument to the solution in this ParseNode
	 */
	public ParseNode getLeft(){
		return _left;
	}
	
	
	/**
	 * @return the solution comprising the second argument to the solution in this ParseNode
	 */
	public ParseNode getRight(){
		return _right;
	}
	
}
