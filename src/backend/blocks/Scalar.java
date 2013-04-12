package backend.blocks;

/**
 *  A container for a scalar value
 */
public class Scalar extends Countable{
	private double _value;
	private String _displayValue;
	private static String _name = "SCALAR";
	
	/** Constructor for 
	 * 
	 * @param value
	 */
	public Scalar (double value,DisplayType displayType){
		super(displayType);
		_value = value;
//		_displayValue = setDisplayValue(_value); // TODO: implement
	}
	
	
	/** Gets the actual value of this Scalar as a double
	 * 
	 * @return the value of this scalar
	 */
	public double getValue(){
		return _value;
	}
	
	
	/** Get the String representation of the contained value
	 * 
	 * @return the string representation of the value of this scalar
	 */
	public String getDisplayValue(){
		return _displayValue;
	}


	@Override
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
	
	
//	/** Sets the String representation of the value depending on global display parameters
//	 * 
//	 * @param val the value to display
//	 * @return the string representing this value
//	 */
//	private String setDisplayValue(double val){
////		// TODO add fraction handling
////		if (_isDouble){
////			return Double.toString(val);
////		}else{
////			return Integer.toString((int) Math.floor(val));
////		}
//	}
	
}