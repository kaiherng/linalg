package backend.operations;

import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;


// Implementors for Computable take in 1-2 matrices, perform a specified 
// operation on the matrices, and provide getters for the result
public abstract class Computable{

	
	/** Returns the solution computed in the constructor of this Computable
	 * 
	 * @return the Solution to this Computable
	 */
	public abstract Solution getSolution();
	
	
	/** Given multiple countables, returns the preferential DisplayType
	 * 
	 * @param countables the countables with DisplayTypes
	 * @return the dominant DisplayType (Reminder: prefer Custom > Decimal > DecimalFraction > WholeNumberFraction > WholeNumber)
	 */
	public static DisplayType resolveDisplayType(List<Countable> countables){
		boolean foundCustom,foundDecimal,foundDecimalFraction,foundWholeNumber,foundWholeNumberFraction;
		foundCustom = foundDecimal = foundDecimalFraction = foundWholeNumber = foundWholeNumberFraction = false;
		for (Countable countable : countables){
			DisplayType type = countable.getDisplayType();
			switch (type){
			case CUSTOM: {
				foundCustom = true;
				break;
			}
			case WHOLENUMBER: {
				foundWholeNumber = true;
				break;
			}
			case WHOLENUMBERFRACTION:{
				foundWholeNumberFraction = true;
				break;
			}
			case DECIMALFRACTION:{
				foundDecimalFraction = true;
				break;
			}
			case DECIMAL:{
				foundDecimal = true;
				break;
			}
			default:{
				System.err.println("ERROR (ResolveDisplayType): Unrecognized DisplayType");
			}
			}
		}
		
		if (foundCustom){
			return DisplayType.CUSTOM;
		}else if(foundDecimal){
			return DisplayType.DECIMAL;
		}else if(foundDecimalFraction){
			return DisplayType.DECIMALFRACTION;
		}else if (foundWholeNumberFraction){
			return DisplayType.WHOLENUMBERFRACTION;
		}else if (foundWholeNumber){
			return DisplayType.WHOLENUMBER;
		}else{
			System.err.println("ERROR (Computable): Did not find any enums");
			return null;
		}
	}
}