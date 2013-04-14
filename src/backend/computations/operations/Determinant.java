package backend.computations.operations;

import java.util.*;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;

/** Determinant Operation
 *
 * @author dzee
 */
public class Determinant extends Computable
{
  private Solution _solution;

	@Override
	public Solution getSolution()
	{
		return _solution;
	}


	/** Finds the determinant of a matrix
	 *
	 * @param matrix the matrix
	 */
	public Determinant(Matrix matrix) throws Exception
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
				{
					throw new IllegalArgumentException("ERROR: Each index must contain a non-null entry");
				}
			}
		}

		List<Step> steps = calcDet(values, answerDisplayType);
		if (steps.isEmpty())
			throw new Exception("ERROR (Det): steps has zero size");
		Countable answer=steps.get(steps.size()-1).getCountable();

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

	/**returns the list of steps to find the determinant of the matrix
	 *the final step contains the determinant*/
	private List<Step> calcDet(Double[][] values, DisplayType distype) throws Exception
	{
		if (values.length<1)
			throw new IllegalArgumentException("ERROR (Det): Matrix size needs to be at least 1, given "+values.length);

		if (values.length!=values[0].length)
			throw new IllegalArgumentException("ERROR (Det): Matrix must have the same number of columns and rows");

		//if it is just a 1x1 matrix
		if (values.length==1)
		{
			List<Step> steps=new ArrayList<>();
			steps.add(new Step(new Scalar(values[0][0],distype)));
			return steps;
		}

		//a 2x2 matrix
		if (values.length==2)
		{
			List<Step> steps=new ArrayList<>();
			//the determinant
			double det=values[0][0]*values[1][1]-values[0][1]*values[1][0];
			steps.add(new Step(new Scalar(det,distype)));
			return steps;
		}

		//for any larger matrix
		List<Step> steps=new ArrayList<>();
		//the determinant
		double det=0;
		for (int i=0;i<values.length;i++)
		{
			//the matrix without the first row, and the i-th column
			Double[][] m=removeRowColumn(values,i,0);

			//calculate m's determinant
			List<Step> intSteps=calcDet(m,distype);

			if (intSteps.isEmpty())
				throw new Exception("ERROR: No steps");

			Countable c=intSteps.get(intSteps.size()-1).getCountable();
			if (!(c instanceof Scalar))
				throw new Exception("ERROR: Determinant is not Scalar");

			//alternate + or - sign
			int sign=1;
			if (i%2==1)
				sign=-1;

			double subDet=sign*values[i][0]*((Scalar)c).getValue();
			det+=subDet;
			Step s=new Step(new Scalar (subDet,distype));

			//include all the substeps
			steps.addAll(intSteps);
			steps.add(s);
		}

		//final answer
		Step answer=new Step(new Scalar(det,distype));
		steps.add(answer);
		return steps;
	}

}
