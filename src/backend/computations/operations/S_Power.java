/**
 * 
 */
package backend.computations.operations;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Op;
import backend.blocks.Scalar;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/**
 * A scalar power computable
 * 
 * @author baebi
 */
public class S_Power extends Computable {
	private Solution _solution;
	private String _operatorStep;
	
	
	/* (non-Javadoc)
	 * @see backend.operations.Computable#getSolution()
	 */
	@Override
	public Solution getSolution() {
		return _solution;
	}
	
	
	/** 
	 * Computes the solution to a scalar power operation. Expects non-null inputs
	 * 
	 * @param a the base number
	 * @param b the exponent number
	 */
	public S_Power(Scalar a, Scalar b){
		List<Countable> args = new ArrayList<>();
		args.add(a);
		args.add(b);
		DisplayType answerDisplayType = resolveDisplayType(args);
		
		a.setDisplayType(answerDisplayType);
		b.setDisplayType(answerDisplayType);
		
		Double aVal = a.getValue();
		Double bVal = b.getValue();
		
		_operatorStep = a.getDisplayValue() +"^{"+b.getDisplayValue()+"}";
		Double answer = Math.pow(aVal,bVal);
		
		Scalar opStep = new Scalar(answer,DisplayType.CUSTOM);
		opStep.setCustomDisplayValue(_operatorStep);
		Step step1 = new Step(opStep);
		Scalar answerStep = new Scalar(answer,answerDisplayType);
		Step step2 = new Step(answerStep);
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		_solution = new Solution(Op.S_POWER,args,answerStep,steps);
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
		b.append(((Scalar)_solution.getAnswer()).getDisplayValue());
		b.append("$");
		toReturn.add(b.toString());
		return toReturn;
	}

}
