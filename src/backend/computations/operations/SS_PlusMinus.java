/**
 * TODO: test the toLatex
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Op;
import backend.blocks.Scalar;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;


/** 
 * Scalar addition and subtraction operations
 * 
 * @author baebi
 */
public class SS_PlusMinus extends Computable {
	private Solution _solution;
	private String _operatorStep;
	private Scalar _answerStep;
	
	
	/** 
	 * Computes the solution to a scalar addition or subtraction operation. Expects non-null inputs
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @param isPlus true iff this is an addition operation. false iff this is a subtraction operation
	 */
	public SS_PlusMinus(Scalar a, Scalar b, boolean isPlus){
		List<Countable> args = new ArrayList<>();
		args.add(a);
		args.add(b);
		DisplayType answerDisplayType = resolveDisplayType(args);
		a.setDisplayType(answerDisplayType);
		b.setDisplayType(answerDisplayType);
		
		Double aVal = a.getValue();
		Double bVal = b.getValue();
		
		
		Double answer;
		if (isPlus){
			_operatorStep = a.getDisplayValue() + " \\ + \\ " + b.getDisplayValue();
			answer = aVal + bVal;
		}else{
			_operatorStep = a.getDisplayValue() + " \\ - \\ " + b.getDisplayValue();
			answer = aVal - bVal;
		}
		
		Scalar opStep = new Scalar(answer,DisplayType.CUSTOM);
		opStep.setCustomDisplayValue(_operatorStep);
		_answerStep = new Scalar(answer,answerDisplayType);
		
		List<String> latex = toLatex();
		if (isPlus){
			_solution = new Solution(Op.SS_PLUS,args,_answerStep,latex);
		}else{
			_solution = new Solution(Op.SS_MINUS,args,_answerStep,latex);
		}
	}
	
	
	@Override
	public Solution getSolution() {
		return _solution;
	}


	@Override
	/**
	 * One Step:
	 * - equation and answer
	 */
	public List<String> toLatex() {
		List<String> toReturn = new ArrayList<>();
		StringBuilder b = new StringBuilder();
		b.append("$");
		b.append(_operatorStep);
		b.append(" \\ = \\ ");
		b.append(_answerStep.getDisplayValue());
		b.append("$");
		toReturn.add(b.toString());
		return toReturn;
	}

}
