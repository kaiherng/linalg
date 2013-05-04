package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import matrixDraw.*;

import java.util.*;

/** Matrix Transpose Operation
 *
 * @author dzee
 */
public class M_Transpose extends Computable
{
	private Solution _solution;
	private Double[][] input;
	private Double[][] output;
	DisplayType answerDisplayType;

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
		answerDisplayType = matrix.getDisplayType(); // choose DisplayType to use

		Double[][] values = matrix.getValues();
		input=values;

		//the transposed values
		output = new Double[values[0].length][values.length];
		for (int i=0;i<values[0].length;i++)
		{
			for (int j=0;j<values.length;j++)
			{
				output[i][j]=values[j][i];
			}
		}
		//the transposed matrix
		Matrix answer=new Matrix(answerDisplayType,output);

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		List<String> latex = toLatex();
		_solution = new Solution(Op.DETERMINANT, inputs, answer, latex);
	}

	@Override
	public List<String> toLatex()
	{
		List<String> steps=new ArrayList<>();
		steps.add("\\vspace{10mm} \\mathrm{Matrix \\ Transpose}");
		for (int i=0;i<input.length;i++)
		{
			//the original column
			Double[][] column=new Double[1][input[0].length];
			column[0]=input[i];
			Matrix from=new Matrix(answerDisplayType,column);
			//the resulted row
			Double[][] row=new Double[output.length][1];
			for (int j=0;j<output.length;j++)
			{
				row[j][0]=output[j][i];
			}
			Matrix to=new Matrix(answerDisplayType,row);
			steps.add("\\vspace{10mm} "+(i+1)+". \\\\");
			steps.add("\\hspace{15mm} \\mathrm{Column} \\ "+(i+1)+" = "+MatrixDraw.getCorrectLatex(answerDisplayType,from)+
				" \\mathrm{becomes \\ Row} \\ "+(i+1)+" = "+MatrixDraw.getCorrectLatex(answerDisplayType,to));
		}
		return steps;
	}

}
