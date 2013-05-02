package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import java.util.*;

/** Matrix Rank Operation
 *
 * @author dzee
 */
public class M_Rank extends Computable
{
  	private Solution _solution;
  	private List<String> steps=new ArrayList<>();

	@Override
	public Solution getSolution()
	{
		return _solution;
	}

	/**Returns the rank of a matrix
	 *
	 *@param matrix the matrix*/
	public M_Rank(Matrix matrix) throws Exception
	{
		steps.add("\\vspace{10mm} \\mathrm{Determining \\ Matrix \\ Rank}");
		@SuppressWarnings("unused")
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
		steps.addAll(rowreduce.toLatex());
		//first zero row
		int fzr=0;
		int rank=0;
		
		steps.add("\\vspace{15mm} \\mathrm{Identify \\ pivot \\ columns}");
		for (int i=0;i<refv.length;i++)
		{
			if (fzr>=refv[0].length)//beyond the last row
				break;
			if (refv[i][fzr]!=0)
			{
				rank++;
				//get to bottom of non-zero
				while (fzr<refv[0].length && refv[i][fzr]!=0)
				{
					fzr++;
				}
				steps.add("\\mathrm{Column \\ "+(i+1)+" \\ is \\ a \\ pivot \\ column.}");
			}
		}

		//answer in scalar frm
		Scalar answer=new Scalar(rank,DisplayType.WHOLENUMBER);
		if (rank==1)
			steps.add("\\vspace{15mm} \\mathrm{There \\ is \\ in \\ total \\ 1 \\ pivot \\ column \\ so \\ the \\ rank \\ is \\ 1}");
		else
			steps.add("\\vspace{15mm} \\mathrm{There \\ are \\ in \\ total} \\ "+answer.getDisplayValue()+" \\ \\mathrm{pivot \\ columns \\ so \\ the \\ rank \\ is} \\ "+answer.getDisplayValue());
		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.M_RANK, inputs, answer, steps);
	}

	@Override
	public List<String> toLatex()
	{
		return steps;
	}

}
