package backend.blocks;


/** A Bracket numerical
 * 
 * @author baebi
 */
public class Bracket implements Numerical {
	private boolean _isOpen;
	
	/** Constructor for bracket
	 * 
	 * @param isOpen true if this is an open bracket, else false
	 */
	public Bracket(boolean isOpen){
		_isOpen = isOpen;
	}
	
	
	/** Returns the open/close status of this Bracket
	 * 
	 * @return true if it is an open bracket, else false
	 */
	public boolean isOpen(){
		return _isOpen;
	}
}