package backend.blocks;

/** A number value Numerical. This includes scalar values
 *  and matrices
 */
public abstract class Countable implements Numerical{
	// Ways to display the result of this computable
	// NOTE: When in doubt, prefer Custom > Decimal > DecimalFraction > WholeNumberFraction > WholeNumber 
	public enum DisplayType{
		DECIMAL,WHOLENUMBER,WHOLENUMBERFRACTION,CUSTOM;
	}
	protected DisplayType _displayType;
	
	/** Constructor initializes fields
	 * 
	 * @param isDouble true if the numbers in this countable should be displayed as decimals
	 * @param isFraction true if the numbers in this countable should be displayed as fractions
	 */
	public Countable(DisplayType displayType){
		_displayType = displayType;
	}
	
	/** 
	 * @return the DisplayType of this countable
	 */
	public DisplayType getDisplayType(){
		return _displayType;
	}
	
	
	/** Sets the DisplayType of this Countable
	 * 
	 * @param toSet the DisplayType to set this to
	 */
	public void setDisplayType(DisplayType toSet){
		_displayType = toSet;
	}
	
	public abstract String toLatex();

}