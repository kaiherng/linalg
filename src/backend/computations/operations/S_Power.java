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
	private Scalar _answerStep;
	
	
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
		_answerStep = new Scalar(answer,answerDisplayType);
		
		List<String> latex = toLatex();
		_solution = new Solution(Op.S_POWER,args,_answerStep,latex);
	}
	
	
	@Override
	/**
	 * One Step:
	 * - equation and answer
	 */
	public List<String> toLatex() {
		List<String> toReturn = new ArrayList<>();
		toReturn.add("\\vspace{10mm} \\mathrm{Scalar \\ Power}");
		StringBuilder b = new StringBuilder();
		b.append("\\hspace{15mm}"+_operatorStep);
		b.append(" \\ = \\ ");
		b.append(_answerStep.getDisplayValue());
		toReturn.add(b.toString());
		return toReturn;
	}

}
