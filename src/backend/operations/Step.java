package backend.operations;

import backend.blocks.Countable;

/** A Step contains a Countable and a Specification explaining how to display the Countable
 * 
 * @author baebi
 */
class Step{
	private Countable _countable;
	
	
	/** Constructor for a step
	 * 
	 * @param countable the Countable for this step
	 * @param specification the Specification explaining how to display the Countable
	 */
	public Step(Countable countable){
		_countable = countable;
	}
	
	
	/**
	 * @return the Countable associated with this step
	 */
	public Countable getCountable(){
		return _countable;
	}
	
}