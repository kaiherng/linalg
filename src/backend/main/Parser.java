/**
 * 
 */
package backend.main;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
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
	 * @return A ParseNode root of a ParseNode tree
	 * 
	 */
	public static ParseNode parse(List<Numerical> input) throws IllegalArgumentException {
		checkValidInput(input);
		// TODO 
		return null;
	}
	
	
	/** Checks that the input computation was valid
	 * 
	 * @param input the list of Numericals making up the input
	 * @throws IllegalArgumentException if input is invalid
	 */
	private static void checkValidInput(List<Numerical> input) throws IllegalArgumentException {
		if (input.size() == 1 && input.get(0) instanceof Operation){ // edge case
			if (((Operation) input.get(0)).isUnary()){
				throw new IllegalArgumentException("ERROR: Unary operator requires operand");
			}else{
				throw new IllegalArgumentException("ERROR: Binary operator requires two operands");
			}
		}
		
		Numerical last = null;
		for (Numerical numr : input){
			if (last != null && last instanceof Operation && numr instanceof Operation // check for two nonunary operators in a row
					&& !((Operation) numr).isUnary()){
				throw new IllegalArgumentException("ERROR: Binary operation requires two elements");
			}
			
			if (last != null && last instanceof Countable && numr instanceof Countable){ // check for adjacent Countables
				throw new IllegalArgumentException("ERROR: Two adjacent Countables");
			}
			
			if (last != null && last instanceof Operation && ((Operation) last).isUnary() && !(numr instanceof Countable)){ // check that each unary operator has operand
				throw new IllegalArgumentException("ERROR: Unary operator requires operand");
			}
			
			last = numr;
		}
		
		if (last instanceof Operation){ // edge case
			if (((Operation) last).isUnary()){
				throw new IllegalArgumentException("ERROR: Unary operator requires operand");
			}else{
				throw new IllegalArgumentException("ERROR: Binary operator requires two operands");
			}
		}
	}
	
	
	/** Converts the input list of Numericals representing a sequence of operations into 
	 *  a tree where leaves are Computables and non-leaves are Operations. Operations lower in the tree
	 *  must be computed before operators higher in the tree. Note that the leaves, which
	 *  will inevitably be Countables, are implicit as they are
	 *  included in the Operations with no left or right children.
	 * 
	 * @param input the list of Numericals making up the input equation
	 * @return
	 */
	private Operation createSortedTree(List<Numerical> input){
		// TODO
		int prefOpIndex = findPreferentialOp(input);
		if (prefOpIndex == -1){
			if (input.size() > 1){
				
			}
		}
		
		List<Numerical> prev = new ArrayList<>(input.subList(0, prefOpIndex));
		List<Numerical> next = new ArrayList<>(input.subList(prefOpIndex, input.size()));
		Operation root = (Operation) input.get(prefOpIndex);
		root.setFirstArg(createSortedTree(prev));
		root.setSecondArg(createSortedTree(next));
		return root;
	}
	
	
	/** Returns the index of the operation that must be performed first among the operations present
	 *  in the computation. If there is no preference due to Operator rank, preference is 
	 *  set by list order. (ex: A + B + C -> A + B comes first)
	 * 
	 * @param input the list of numericals making up the input equation
	 * @return
	 */
	private int findPreferentialOp(List<Numerical> input){
		Numerical currentNumr;
		int maxRank = -1;
		int currRank;
		int toReturn = -1; // index to return
		for (int i = 0; i < input.size(); i++){
			currentNumr = input.get(i);
			if (currentNumr instanceof Operation){
				currRank = ((Operation) currentNumr).getRank();
				if (currRank > maxRank){
					maxRank = currRank;
					toReturn = i;
				}
			} 
		}
		return toReturn;
	}
	
	
}
