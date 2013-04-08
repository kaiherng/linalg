package backend.operations;

import backend.blocks.*;
import java.util.*;

/** An object that contains the answer to an Operation and the steps that 
 *  led to the answer
 * 
 * @author baebi
 */
public class Solution{
	private Countable _answer;
	private List<Step> _steps;

	/** Constructor for Solution object
	 * 
	 * @param answer the answer to the solution
	 * @param steps the steps to the answer
	 */
	public Solution(Countable answer, List<Step> steps){
		_answer = answer;
		_steps = steps;
	}
	
	/** Gets the answer from this Solution
	 * 
	 * @return the Countable representing the answer of this Solution 
	 */
	public Countable getAnswer(){
		return _answer;
	}
	

	/** Gets the list of steps to the answer
	 * 
	 * @return A list of steps to the answer
	 */
	public List<Step> getSteps(){
		return _steps;
	}
}