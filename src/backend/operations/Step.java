package backend.operations;

import backend.blocks.Countable;

/** A Step contains a Countable and a Specification explaining how to display the Countable
 * 
 * @author baebi
 */
class Step{
	private Specification _spec;
	private Countable _countable;
	
	
	/** Constructor for a step
	 * 
	 * @param countable the Countable for this step
	 * @param specification the Specification explaining how to display the Countable
	 */
	public Step(Countable countable, Specification specification){
		_countable = countable;
		_spec = specification;
	}
	
	
	/**
	 * @return the Countable associated with this step
	 */
	public Countable getCountable(){
		return _countable;
	}
	
	
	/**
	 * @return the Specification associated with this step
	 */
	public Specification getSpec(){
		return _spec;
	}
}