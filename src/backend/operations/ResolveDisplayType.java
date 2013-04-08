/**
 * 
 */
package backend.operations;

import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;

/** Given several computables with possibly different set DisplayTypes, decides which DisplayType
 *  to use
 * 
 * @author baebi
 */
public class ResolveDisplayType {
	private ResolveDisplayType(){}

	/** Given multiple countables, returns the preferential DisplayType
	 * 
	 * @param countables the countables with DisplayTypes
	 * @return the dominant DisplayType (Reminder: prefer Custom > Decimal > DecimalFraction > WholeNumberFraction > WholeNumber)
	 */
	public static DisplayType resolve(List<Countable> countables){
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
			System.err.println("ERROR (ResolveDisplayType): Did not find any enums");
			return null;
		}
	}
}
