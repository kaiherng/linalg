/**
 * 
 */
package backend.main;

import java.util.List;

import backend.blocks.Numerical;
import backend.blocks.Operation;

/**
 * @author baebi
 *
 */
public class Parser {
	//Don't construct
	private Parser(){}
	
	/** Returns a tree of ParseNodes that each contain Solution objects.
	 *  The tree reflects the order that each solution was arrived at. Solutions
	 *  nearer to the leaves were computed first because of order of operations.
	 *  The root node is the last computation performed in the sequence
	 *  (ex: 4 * 2 + 3 * 4 -> '+' would be the last computation) The tree would look like:
	 *  
	 *           +
	 *         /   \
	 *        *     *
	 *       / \   / \
	 *      4   2  3  4
	 * 
	 * It's important to note that the ParseNodes each contain solution objects, so the leaves aren't
	 * actually present in the tree. Each solution contains its own inputs though, so all the information
	 * is there 
	 * 
	 * @param input A list of numericals representing a sequence of computations (ex: MatrixA * ScalarB + .... ) 
	 * @return A Parsnode root of a ParseNode tree
	 * 
	 */
	public ParseNode parse(List<Numerical> input){
		//TODO:
		return null;
	}
	
	
	/** Converts the input list of Numericals representing a sequence of operations into 
	 *  a tree where leaves are Computables and nonLeaves are operators. Operators lower in the tree
	 *  must be computed before operators higher in the tree
	 * 
	 * @param input the list of Numericals making up the input equation
	 * @return
	 */
	private Operation createSortedTree(List<Numerical> input){
		// TODO
		return null;
	}
	
	
	/** Returns the index of the operation that must be performed first among the operations present
	 *  in the computation. If there is no preference due to Operator rank, preference is 
	 *  set by list order. (ex: A + B + C -> A + B comes first)
	 * 
	 * @param input the list of numericals making up the input equation
	 * @return
	 */
	private int findPreferentialOp(List<Numerical> input){
		// TODO
		return 0;
	}
	
	
	
}
