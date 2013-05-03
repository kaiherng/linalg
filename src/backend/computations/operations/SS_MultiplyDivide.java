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

/**
 * Operation for multiplication or division of scalars
 * 
 * @author baebi
 */
public class SS_MultiplyDivide extends Computable {
	private Solution _solution;
	private String _operatorStep;
	private Scalar _answerStep;
	private String _op;
	
	
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
		a.setDisplayType(answerDisplayType);
		b.setDisplayType(answerDisplayType);
		String aString = a.getDisplayValue();
		String bString = b.getDisplayValue();
		
		Double answer;
		if (isTimes){
			_op = "Multiplication";
			_operatorStep = aString + " * " + bString;
			answer = aVal * bVal;
		}else{
			_op = "Division";
			_operatorStep = aString + " / " + bString;
			answer = aVal / bVal;
		}
		
		Scalar opStep = new Scalar(answer,DisplayType.CUSTOM);
		opStep.setCustomDisplayValue(_operatorStep);
		_answerStep = new Scalar(answer,answerDisplayType);
		
		List<String> latex = toLatex();
		if (isTimes){
			_solution = new Solution(Op.SS_MULTIPLY,args,_answerStep,latex);
		}else{
			_solution = new Solution(Op.SS_DIVIDE,args,_answerStep,latex);
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
		List<String> toReturn = new ArrayList<>();
		toReturn.add("\\vspace{10mm} \\mathrm{"+_op+"}");
		StringBuffer b = new StringBuffer();
		b.append("\\hspace{15mm}");
		b.append(_operatorStep);
		b.append(" = ");
		b.append(_answerStep.getDisplayValue());
		toReturn.add(b.toString());
		return toReturn;
	}

}
