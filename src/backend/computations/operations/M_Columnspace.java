package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import matrixDraw.*;

import java.util.*;

/** Matrix Column Space Operation
 *
 * @author dzee
 */
public class M_Columnspace extends Computable
{
  	private Solution _solution;
  	private List<String> steps=new ArrayList<>();

	@Override
	public Solution getSolution()
	{
		return _solution;
	}

	/**Returns the column space basis of a matrix
	 *
	 *@param matrix the matrix*/
	public M_Columnspace(Matrix matrix) throws Exception
	{
		steps.add("\\vspace{10mm} \\mathrm{Column \\ Space}");
		DisplayType answerDisplayType = matrix.getDisplayType(); // choose DisplayType to use

		Double[][] values = matrix.getValues();
		for (int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values[0].length; j++)
			{
				if (values[i][j] == null){
					throw new IllegalArgumentException("ERROR: Each matrix index must contain a non-null entry");
				}
			}
		}

		/**the reduced matrix*/
		M_RowReduce rowreduce=new M_RowReduce(matrix);
		Solution refsol=rowreduce.getSolution();
		Matrix ref=(Matrix)(refsol.getAnswer());
		Double[][] refv=ref.getValues();
		List<String> rr = rowreduce.toLatex();
		rr.remove(0);
		steps.add("\\vspace{10mm}\\mathrm{1. \\ Determine \\ the \\ row \\ reduced \\ echelon \\ form \\ of \\ the \\ matrix}");
		for (String stp : rr){
			steps.add("\\hspace{15mm}" + stp);
		}
		steps.add("\\vspace{5mm}");
		List<Integer> isPivot=new ArrayList<>();
		//first zero row
		int fzr=0;
		steps.add("\\vspace{15mm} \\mathrm{2. \\ Identify \\ pivot \\ columns }");
		for (int i=0;i<refv.length;i++)
		{
			if (fzr>=refv[0].length)//beyond the last row
				break;
			if (refv[i][fzr]!=0)
			{
				isPivot.add(i);
				//get to bottom of non-zero
				while (fzr<refv[0].length && !refv[i][fzr].equals(0.0))
				{
					fzr++;
				}
				steps.add("\\hspace{15mm} \\mathrm{Column \\ "+(i+1)+" \\ is \\ a \\ pivot \\ column.}");
			}
		}

		//the values of the pivot columns
		Double[][] pivots=new Double[isPivot.size()][values[0].length];
		for (int i=0;i<isPivot.size();i++)
		{
			for (int j=0;j<values[0].length;j++)
			{
				pivots[i][j]=values[isPivot.get(i)][j];
			}
		}

		Matrix answer=new Matrix(answerDisplayType,pivots);
		String basis="\\vspace{20mm} \\mathrm{The \\ basis \\ consists \\ of \\ }";
		for (Double[] col:pivots)
		{
			Double[][] v=new Double[1][values[0].length];
			v[0]=col;
			Matrix m=new Matrix(answerDisplayType,v);
			basis+=MatrixDraw.getCorrectLatex(answerDisplayType,m)+" ";
		}
		steps.add(basis);

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.M_COLUMNSPACE, inputs, answer, steps);
	}

	@Override
	public List<String> toLatex()
	{
		return steps;
	}

}
