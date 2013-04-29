package backend.computations.infrastructure;

import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Op;

/** 
 * An object that contains the answer to an Operation and the steps that 
 * led to the answer
 * 
 * @author baebi
 */
public class Solution{
	private Countable _answer;
	private List<String> _latex;
	private Op _op;
	private List<Countable> _inputs;

	
	/** 
	 * Constructor for Solution object
	 * 
	 * @parm op the operation that resulted in this solution
	 * @param inputs the input countables that resulted in this solution. THIS SHOULD BE ORDERED. (ex: A - B;  List {A, B})
	 * @param answer the answer to the solution
	 * @param steps the steps to the answer
	 */
	public Solution(Op op, List<Countable> inputs, Countable answer, List<String> latex){
		_op = op;
		_inputs = inputs;
		_answer = answer;
		_latex = latex;
	}
	
	
	/** 
	 * Gets the answer from this Solution
	 * 
	 * @return the Countable representing the answer of this Solution
	 */
	public Countable getAnswer(){
		return _answer;
	}
	

	/** 
	 * Gets the list of steps to the answer
	 * 
	 * @return A list of steps to the answer
	 */
	public List<String> getLatex(){
		return _latex;
	}
	

	/**
	 * @return the list of input arguments (will be of length 1 or 2)
	 */
	public List<Countable> getInputs(){
		return _inputs;
	}
	
	
	/**
	 * @return the operation that resulted in this solution
	 */
	public Op getOp(){
		return _op;
	}
}