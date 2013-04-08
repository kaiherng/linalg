package backend.operations;


// Implementors for Computable take in 1-2 matrices, perform a specified 
// operation on the matrices, and provide getters for the result
interface Computable{
	// Ways to display the result of this computable
	// NOTE: When in doubt, prefer Decimal > DecimalFraction > WholeNumberFraction > WholeNumber
	public enum DisplayType{
		Decimal, WholeNumber,DecimalFraction, WholeNumberFraction;
	}
	
	public Solution getSolution();
}