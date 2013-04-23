package backend.blocks;

/**
 *  A container for a scalar value
 */
public class Scalar extends Countable{
	private double _value;              // the double value of this Scalar
	private String _displayValue;       // this is automatically generated based on the decided number format
	private String _customDisplayValue; // this is manually set by an outside user
	private static String _name = "SCALAR";
	
	/** 
	 * Constructor for a Scalar object 
	 * 
	 * @param value the double value of this scalar
	 * @param displayType the way this Scalar should be rendered at display-time
	 */
	public Scalar (double value,DisplayType displayType){
		super(displayType);
		_value = value;
		_displayValue = setDisplayValue(_value);
	}
	
	/**
	 * Sets the custom display value of this Scalar
	 * 
	 * @param s the string you want to set the display value to
	 */
	public void setCustomDisplayValue(String s){
		_customDisplayValue = s;
	}
	
	/**
	 * @return the custom display value of this Scalar
	 */
	public String getCustomDisplayValue(){
		return _customDisplayValue;
	}
	
	
	/** 
	 * Gets the actual value of this Scalar as a double
	 * 
	 * @return the value of this scalar
	 */
	public double getValue(){
		return _value;
	}
	
	
	/** 
	 * Get the String representation of the contained value
	 * 
	 * @return the string representation of the value of this scalar
	 */
	public String getDisplayValue(){
		return _displayValue;
	}


	@Override // useful for testing
	public String getName() {
		return _name; 
	}


	/** Sets the display value to a string
	 * 
	 * @param operatorStep the value to set 
	 */
	public void setDisplayValue(String operatorStep) {
		_displayValue = operatorStep;
	}
	
	
	/** Sets the String representation of the value depending on global display parameters
	 * 
	 * @param val the value to display
	 * @return the string representing this value
	 */
	private String setDisplayValue(double val){
		// TODO add fraction handling
		switch (_displayType){
		case WHOLENUMBERFRACTION:{
			return null; // TODO
		}
		case DECIMALFRACTION:{
			return null; // TODO
		}
		case DECIMAL:{
			return Double.toString(val);
		}
		case WHOLENUMBER:{
			return Integer.toString((int) Math.floor(val));
		}
		case CUSTOM:{
			return null;
		}
		default: {
			System.err.println("ERROR: unrecognized display value");
			return null;
		}
		}
	}
	
}