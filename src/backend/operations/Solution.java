package backend.operations;

import backend.blocks.*;
import java.util.*;

/** An object that contains the answer to an Operation and the steps that 
 *  led to the answer
 * 
 * @author baebi
 */
class Solution{
	private Countable result;
	private List<Step> steps;

	/** Gets the answer from this Solution
	 * 
	 * @return the Countable representing the answer of this Solution 
	 */
	public Countable getAnswer(){
		return result;
	}
	

	/** Gets the list of steps to the answer
	 * 
	 * @return A list of steps to the answer
	 */
	public List<Step> getSteps(){
		return steps;
	}
}