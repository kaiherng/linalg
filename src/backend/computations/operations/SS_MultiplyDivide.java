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
 * @author
 *
 */
public class SS_MultiplyDivide extends Computable {
	private Solution _solution;
	
	/** 
	 * Computes the solution to a scalar multiplication or division operation. Expects non-null inputs
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @param isPlus true iff this is an addition operation. false iff this is a subtraction operation
	 */
	public SS_MultiplyDivide(Scalar a, Scalar b, boolean isTimes){
		List<Countable> args = new ArrayList<>();
		args.add(a);
		args.add(b);
		DisplayType answerDisplayType = resolveDisplayType(args);
		Double aVal = a.getValue();
		Double bVal = b.getValue();
		
		String operatorStep; Double answer;
		if (isTimes){
			operatorStep = aVal + " * " + bVal;
			answer = aVal * bVal;
		}else{
			operatorStep = aVal + " / " + bVal;
			answer = aVal / bVal;
		}
		
		Scalar opStep = new Scalar(answer,DisplayType.CUSTOM);
		opStep.setCustomDisplayValue(operatorStep);
		Step step1 = new Step(opStep);
		Scalar answerStep = new Scalar(answer,answerDisplayType);
		Step step2 = new Step(answerStep);
		List<Step> steps = new ArrayList<>();
		steps.add(step1);
		steps.add(step2);
		if (isTimes){
			_solution = new Solution(Op.SS_PLUS,args,answerStep,steps);
		}else{
			_solution = new Solution(Op.SS_MINUS,args,answerStep,steps);
		}
	}
	
	/* (non-Javadoc)
	 * @see backend.operations.Computable#getSolution()
	 */
	@Override
	public Solution getSolution() {
		return _solution;
	}

	@Override
	public List<String> toLatex() {
		// TODO Auto-generated method stub
		return null;
	}

}
