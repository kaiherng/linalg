package backend.computations.operations;

import java.util.*;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** Addition Operation
 *
 * @author dzee
 */
public class M_Inverse extends Computable
{
	private Solution _solution;

	@Override
	public Solution getSolution()
	{
		return _solution;
	}


	/** Find the inverse of a square matrix
	 *
	 * @param matrix the first matrix to add
	 */
	public M_Inverse(Matrix matrix) throws Exception
	{
		List<Countable> matrixList = new ArrayList<>();
		matrixList.add(matrix);
		//TODO
		DisplayType answerDisplayType = null;//resolveDisplayType(matrixList); // choose DisplayType to use

		Double[][] values = matrix.getValues();

		for(int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values[0].length; j++)
			{
				if (values[i][j] == null)
					throw new IllegalArgumentException("ERROR: Each index must contain a non-null entry");
			}
		}

		if (values.length!=values[0].length)
			throw new IllegalArgumentException("ERROR (Inverse): Matrix must have the same number of columns and rows");

		//calculate the determinant of the matrix
		Solution sol=new Determinant(matrix).getSolution();
		List<Step> steps = sol.getSteps();

		//1x1 matrix
		if (values.length==1 && values[0].length==1)
		{
			if (values[0][0]==0)
				throw new Exception("ERROR (Inverse): Determinant of matrix is 0");

			Double[][] d=new Double[1][1];
			d[0][0]=1/values[0][0];
			Matrix answer=new Matrix(answerDisplayType,d);
			steps.add(new Step(answer));

			List<Countable> inputs = new ArrayList<>();
			inputs.add(matrix);

			_solution = new Solution(Op.DETERMINANT, inputs, answer, steps);
			return;
		}


		double det=((Scalar)(sol.getAnswer())).getValue();
		//determinant cannot be 0
		if (det==0)
			throw new Exception("ERROR (Inverse): Determinant of matrix is 0");

		//calculate cofactor matrix
		Double[][] cofactor=new Double[values.length][values.length];
		for(int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values.length; j++)
			{
				//alternating + and - sign
				int sign=1;
				if (i%2 != j%2)
					sign=-1;
				//calculate determinant
				Solution s=new Determinant(new Matrix(answerDisplayType,removeRowColumn(values,i,j))).getSolution();
				steps.addAll(sol.getSteps());
				cofactor[i][j]=sign*(((Scalar)(s.getAnswer())).getValue())/det;
			}
		}

		//calculate the transpose of the cofactor
		Solution ct=new M_Transpose(new Matrix(answerDisplayType,cofactor)).getSolution();
		steps.addAll(ct.getSteps());
		Countable answer=ct.getAnswer();

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.DETERMINANT, inputs, answer, steps);
	}

	/**returns the matrix without the y-th row, and the x-th column*/
	private Double[][] removeRowColumn(Double[][] m, int x, int y) throws Exception
	{
		if (x<0 || y<0 || x>=m.length || y>=m[0].length)
			throw new IllegalArgumentException("ERROR (Det): removing row "+x+" and column "+y+" is illegal in a "+m.length+"x"+
				m[0].length+" matrix.");

		Double[][] m2=new Double[m.length-1][m.length-1];
		for (int j=0;j<m.length-1;j++)
		{
			for (int k=0;k<m[0].length-1;k++)
			{
				int x2=j,y2=k;
				if (x2>=x)
					x2++;
				if (y2>=y)
					y2++;
				m2[j][k]=m[x2][y2];
			}
		}
		return m2;
	}

}
