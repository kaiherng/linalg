/**
 * TODO: make this support brackets
 */
package backend.main;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Bracket;
import backend.blocks.Countable;
import backend.blocks.Matrix;
import backend.blocks.Numerical;
import backend.blocks.Op;
import backend.blocks.Operation;
import backend.operations.Plus;
import backend.operations.Solution;

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
		Numerical operationTree = createSortedTree(input); 
		return compute(operationTree);
	}
	
	
	
	/** Computes a sequence of computations organized into a tree structure of Numericals. (See
	 *  createSortedTree). 
	 * 
	 * @param root
	 * @return
	 */
	private static ParseNode compute(Numerical root){

		
//		if (root instanceof Operation){
//			Operation rootAsOp = (Operation) root;
//			Op op = rootAsOp.getType();
//			switch (op){
//				case PLUS: {
//					if ((rootAsOp.getFirstArg() == null || (rootAsOp.getSecondArg() == null))){
//						throw new IllegalArgumentException("ERROR: Plus requires two arguments");
//					}
//					
//					if (rootAsOp.getFirstArg())
//					ParseNode firstArg = compute(rootAsOp.getFirstArg());
//					ParseNode secondArg= compute(rootAsOp.getSecondArg());
//					Solution arg1Sol = firstArg.getSolution();
//					Solution arg2Sol = secondArg.getSolution();
//					Numerical arg1 = arg1Sol.getAnswer();
//					Numerical arg2 = arg2Sol.getAnswer();
//					
//					if (!(arg1 instanceof Matrix) || !(arg2 instanceof Matrix)){
//						throw new IllegalArgumentException("ERROR: Plus arguments must be matrices");
//					}
//					
//					Plus plus = new Plus((Matrix) arg1, (Matrix) arg2);
//					Solution sum = plus.getSolution();
//					
//					
//					return new ParseNode(sum,firstArg,secondArg);
//				}
//				case MINUS:
//				case SCALAR_MULTIPLY:
//				case TIMES:
//				case DETERMINANT:
//				case ROW_REDUCE:
//				default:
//				
//			}
//		}else if(root instanceof Countable){
//			
//		}else{
//			// should be unreachable code
//			System.err.println("ERROR (compute): compute should not receive anything but countables or ");
//		}
		
		// TODO
		return null;
	}
	
	
	/** Checks that the input computation was valid
	 * 
	 * @param input the list of Numericals making up the input
	 * @throws IllegalArgumentException if input is invalid
	 */
	protected static void checkValidInput(List<Numerical> input) throws IllegalArgumentException {
		if (input.size() == 0){
			throw new IllegalArgumentException("ERROR: Require expression to compute");
		}
		
		checkBrackets(input); // check that brackets are valid

		if (input.size() == 1){ // edge case
			if (input.get(0) instanceof Operation){
				if (((Operation) input.get(0)).isUnary()){
					throw new IllegalArgumentException("ERROR: Unary operator requires operand");
				}else{
					throw new IllegalArgumentException("ERROR: Binary operator requires two operands");
				}
			}else if(input.get(0) instanceof Bracket){
				throw new IllegalArgumentException("ERROR: Unpaired bracket");
			}else{
				throw new IllegalArgumentException("ERROR: Require expression to compute"); // maybe don't need to throw expection for this
			}
		}
		
		if (input.get(0) instanceof Operation && !((Operation) input.get(0)).isUnary()){
			throw new IllegalArgumentException("ERROR: Binary operation requires two operands");
		}
		
		Numerical last = null;
		for (Numerical numr : input){
			if (last != null && last instanceof Operation && numr instanceof Operation // check for two nonunary operators in a row
					&& !((Operation) numr).isUnary()){
				throw new IllegalArgumentException("ERROR: Binary operation requires two operands");
			}
			if (last != null && last instanceof Countable && numr instanceof Countable){ // check for adjacent Countables
				throw new IllegalArgumentException("ERROR: Two adjacent Countables");
			}
			if (last != null && last instanceof Operation && ((Operation) last).isUnary() && !(numr instanceof Countable)){ // check that each unary operator has operand
				if (numr instanceof Bracket && !((Bracket) numr).isOpen()){
					throw new IllegalArgumentException("ERROR: Unary operator requires operand");
				}
			}
			if (last != null && last instanceof Bracket && ((Bracket) last).isOpen() && numr instanceof Bracket && !((Bracket) numr).isOpen()){
				throw new IllegalArgumentException("ERROR: Empty brackets");
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
	
	
	/** Checks that an input computation properly opens and closes brackets
	 * 
	 * @param input A series of Numericals representing a computation
	 * @throws IllegalArgumentException thrown if the brackets aren't valid
	 */
	protected static void checkBrackets(List<Numerical> input) throws IllegalArgumentException {
		boolean firstBracket = true;
		int openBrackets,closedBrackets;
		openBrackets = closedBrackets = 0;
		boolean isOpen;
		for (Numerical numr : input){
			if (numr instanceof Bracket){
				if (firstBracket && !((Bracket) numr).isOpen()){
					throw new IllegalArgumentException("ERROR: Close bracket without open bracket");
				}else{
					firstBracket = false;
				}
				
				isOpen = ((Bracket) numr).isOpen();
				if (isOpen){
					openBrackets++;
				}else{
					closedBrackets++;
				}
			}
		}
		
		if (openBrackets != closedBrackets){
			throw new IllegalArgumentException("ERROR: Every open bracket must have a close bracket and vice versa");
		}
	}
	
	
	/** Converts the input list of Numericals representing a sequence of operations into 
	 *  a tree where leaves are Computables and non-leaves are Operations. Operations lower in the tree
	 *  must be computed before operators higher in the tree. Note that the leaves, which
	 *  will inevitably be Countables, are implicit as they are
	 *  included in the Operations with no left or right children.
	 * 
	 * @param input the list of Numericals making up the input equation
	 * @return  Numerical that is the root of the parsed tree of Operations
	 */
	protected static Numerical createSortedTree(List<Numerical> input){
//		System.out.println("called");
//		System.out.println(input.size());
//		for (Numerical n : input){
//			System.out.print(n.getName() + " ");
//		}
//		System.out.println();
		input = removeOuterBrackets(input);
		
		if (input.size() == 0){
			return null;
		}else if(input.size() == 1){
			return input.get(0); //This must be a Countable
		}
		
		int prefOpIndex = findLeastPreferentialOp(input);

		// This code should be unreachable
		if (prefOpIndex == -1){
				System.err.println("ERROR: createSortedTree passed invalid input");	
		}
		
		List<Numerical> prev = new ArrayList<>(input.subList(0, prefOpIndex));
		List<Numerical> next = new ArrayList<>(input.subList(prefOpIndex+1, input.size()));
//		System.out.println("prev " + prev.size());
//		System.out.println("next "+next.size());
		Operation root = (Operation) input.get(prefOpIndex);
		root.setFirstArg(createSortedTree(prev));
		root.setSecondArg(createSortedTree(next));
		return root;
	}
	
	
	/** Returns the index of the operation that must be performed last among the operations present
	 *  in the computation. If there is no preference due to Operator rank, preference is 
	 *  set by list order. (ex: A + B + C -> B + C comes last). EXPECTS A SEQUENCE OF NUMERICALS
	 *  THAT IS NOT IN ITS ENTIRETY SURROUNDED BY A PAIR OF PARENS
	 * 
	 * @param input the list of numericals making up the input equation
	 * @return the index in the list of the operation that should be computed last
	 */
	protected static int findLeastPreferentialOp(List<Numerical> input){
		Numerical currentNumr;
		int maxRank = -1;
		int currRank;
		int toReturn = -1; // index to return
		int openBrackets = 0;
		for (int i = 0; i < input.size(); i++){
			currentNumr = input.get(i);
			if (currentNumr instanceof Bracket){
				if(((Bracket) currentNumr).isOpen()){
					openBrackets++;
				}else{
					openBrackets--;
				}
			}else{
				if (currentNumr instanceof Operation && openBrackets == 0){
					currRank = ((Operation) currentNumr).getRank();
					if (currRank >= maxRank){
						maxRank = currRank;
						toReturn = i;
					}
				} 
			}
		}
		return toReturn;
	}
	
	
	/** If the input is begun by a bracket that is closed by a bracket at the end of the input, this removes
	 *  those outer brackets. EXPECTS VALID PARENTHESES, THAT IS EACH OPEN PARENS SHOULD HAVE A CLOSED PARENS
	 * 
	 * @param input the list of Numericals representing a computation
	 * @return the same list except without first and last brackets if they existed as a pair
	 */
	protected static List<Numerical> removeOuterBrackets(List<Numerical> input){
		if (input.size() == 0){
			return input;
		}
		if (!(input.get(0) instanceof Bracket)){ // computation not begun by a bracket
			return input;
		}else{
			int unclosedBrackets = 1;
			for (int i = 1; i < input.size()-1; i++){
				if (input.get(i) instanceof Bracket){
					if (((Bracket) input.get(i)).isOpen()){
						unclosedBrackets++;
					}else{
						unclosedBrackets--;
						if (unclosedBrackets == 0){
							return input;
						}
					}
				}
			}
			return new ArrayList<Numerical>(input.subList(1, input.size()-1));
		}
	}
	
}
