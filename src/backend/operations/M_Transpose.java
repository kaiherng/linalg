package backend.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import java.util.*;

/** Matrix Transpose Operation
 *
 * @author dzee
 */
public class M_Transpose extends Computable
{
	private Solution _solution;

	@Override
	public Solution getSolution()
	{
		return _solution;
	}

	/**Returns the transpose of a matrix
	 *
	 *@param matrix the matrix*/
	public M_Transpose(Matrix matrix)
	{
		List<Countable> matrixList = new ArrayList<>();
		matrixList.add(matrix);
		//TODO
		DisplayType answerDisplayType = null;//resolveDisplayType(matrixList); // choose DisplayType to use

		Double[][] values = matrix.getValues();

		//the transposed values
		Double[][] trans = new Double[values[0].length][values.length];
		for (int i=0;i<values[0].length;i++)
		{
			for (int j=0;j<values.length;j++)
			{
				trans[i][j]=values[j][i];
			}
		}
		//the transposed matrix
		Matrix answer=new Matrix(answerDisplayType,trans);

		List<Step> steps = new ArrayList<Step>();
		steps.add(new Step(answer));

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.DETERMINANT, inputs, answer, steps);
	}

}
