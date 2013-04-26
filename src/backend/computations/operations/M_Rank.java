package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

import java.util.*;

/** Matrix Rank Operation
 *
 * @author dzee
 */
public class M_Rank extends Computable
{
  private Solution _solution;

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
		@SuppressWarnings("unused")
		DisplayType answerDisplayType = matrix.getDisplayType();

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

		List<Step> steps = new ArrayList<Step>();

		/**the reduced matrix*/
		Solution refsol=(new M_RowReduce(matrix)).getSolution();
		Matrix ref=(Matrix)(refsol.getAnswer());
		Double[][] refv=ref.getValues();
		steps.addAll(refsol.getSteps());
		//whether a column is pivot or not
		Matrix isPivot=new Matrix(DisplayType.CUSTOM,1,refv.length);
		//first zero row
		int fzr=0;
		int rank=0;
		for (int i=0;i<refv.length;i++)
		{
			if (fzr>=refv[0].length)//beyond the last row
				break;
			if (refv[i][fzr]!=0)
			{
				rank++;
				isPivot.setCustomDisplayIndex(0,i,"Pivot");
				//get to bottom of non-zero
				while (fzr<refv[0].length && refv[i][fzr]!=0)
				{
					fzr++;
				}
			}
		}

		steps.add(new Step(isPivot));
		//answer in scalar frm
		Scalar answer=new Scalar(rank,DisplayType.WHOLENUMBER);
		steps.add(new Step(answer));

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.M_RANK, inputs, answer, steps);
	}

	@Override
	public List<String> toLatex() {
		// TODO Auto-generated method stub
		return null;
	}

}
