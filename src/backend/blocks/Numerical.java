package backend.blocks;

// Some unit that has to do with math. Could be an operation, a bracket, a matrix, or a scalar
public interface Numerical{
	
	/**
	 * @return the string name referring to the class
	 */
	public String getName();
	
	public String toLatex();
}