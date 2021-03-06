package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import matrixDraw.*;

import java.util.*;

/** Matrix Row Reduce Operation
 *
 * @author dzee
 */
public class M_RowReduce extends Computable
{
  	private Solution _solution;
  	private List<String> steps=new ArrayList<>();
  	private int stepNumber = 1;

	@Override
	public Solution getSolution()
	{
		return _solution;
	}

	/**Returns the reduced echelon form of a matrix
	 *
	 *@param matrix the matrix*/
	public M_RowReduce(Matrix matrix) throws Exception
	{
		steps.add("\\vspace{10mm} \\mathrm{Row \\ Reduction}");
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

		//rearrange matrix
		//first nonzero of each row
		int[] firstnz=new int[values[0].length];
		for (int i=0;i<firstnz.length;i++)
		{
			int j=0;
			while (j<values.length && values[j][i].equals(0.0))
				j++;
			firstnz[i]=j;
		}

		//whether we exchanged rows or not
		boolean changed=false;
		//matrix with the rows rearranged so that it forms as much of a echelon form as possible
		Double[][] moved = values;
		//check each row with the one above
		//if its first nonzero is more to the left, move it up until it is not
		for (int i=1;i<firstnz.length;i++)
		{
			//index of the row we are looking at
			int j=i;
			while (j>0 && firstnz[j-1]>firstnz[j])
			{
				changed=true;
				//swap firstnz value
				int temp=firstnz[j-1];
				firstnz[j-1]=firstnz[j];
				firstnz[j]=temp;
				//swap each value in the rows
				for (int k=0;k<moved.length;k++)
				{
					Double val=moved[k][j-1];
					moved[k][j-1]=moved[k][j];
					moved[k][j]=val;
				}
				j--;
			}
		}

		Matrix stepMatrix=new Matrix(DisplayType.DECIMAL,moved);
		if (changed)
			steps.add("");
			steps.add("\\vspace{15mm} "+stepNumber+". \\ \\mathrm{Rearrange \\ rows \\ to:}");
			stepNumber++;
			steps.add("\\hspace{15mm} "+MatrixDraw.getCorrectLatex(matrix.getDisplayType(),stepMatrix));

		//the row to work on
		for (int j=0;j<Math.min(moved.length,moved[0].length);j++)
		{
			//get the first non-zero
			int i=0;
			while (i<moved.length && moved[i][j].equals(0.0))
				i++;
			//whole row of zero
			if (i>=moved.length)
				continue;

			double pivot=moved[i][j];

			if (pivot!=1.0)
			{
				//make the pivot 1 by dividing the row by pivot
				for (int k=i;k<moved.length;k++)
				{
					moved[k][j]/=pivot;
				}
				stepMatrix=new Matrix(DisplayType.DECIMAL,moved);
				String piv = shortenDecimal(Double.toString(pivot));
				steps.add("\\vspace{15mm}"+stepNumber+". \\ \\mathrm{Divide \\ Row \\ "+(j+1)+" \\ by} \\ "+piv);
				stepNumber++;
				steps.add("\\hspace{15mm} = "+ MatrixDraw.getCorrectLatex(DisplayType.DECIMAL,stepMatrix));
			}

			//make the whole column zero except for pivot
			for (int l=0;l<moved[0].length;l++)
			{
				//don't do anything if it is the pivot row
				if (l==j)
					continue;

				double factor=moved[i][l];
				//don't do anything if it is 0
				if (factor==0.0)
					continue;
				//subtract the pivot row*factor
				moved[i][l]=0.0;
				for (int k=i+1;k<moved.length;k++)
				{
					moved[k][l]-=moved[k][j]*factor;
				}
				
				String num = shortenDecimal(Double.toString(factor));

				
				
				stepMatrix=new Matrix(DisplayType.DECIMAL,moved);
				steps.add("\\vspace{15mm}"+stepNumber+". \\ \\mathrm{Subtract \\ Row \\ "+(l+1)+" \\ by \\ (Row} \\ "+(j+1)+" \\ \\times \\ "+
					num+")");
				stepNumber++;
				steps.add("\\hspace{15mm} = "+ MatrixDraw.getCorrectLatex(DisplayType.DECIMAL,stepMatrix));
			}
		}

		//move any whole zero rows to bottom
		for (int j=0;j<moved[0].length-1;j++)
		{
			boolean zero=true;
			for (int i=0;i<moved.length;i++)
			{
				if (!moved[i][j].equals(0.0))
				{
					zero=false;
					break;
				}
			}
			//if whole row of zero
			if (zero)
			{
				//move to lowest spot
				for (int k=j;k<moved[0].length-1;k++)
				{
					//move each element in the row
					for (int l=0;l<moved.length;l++)
					{
						moved[l][k]=moved[l][k+1];
					}
				}
				//last row of zero
				for (int l=0;l<moved.length;l++)
				{
					moved[l][moved[0].length-1]=0.0;
				}
			}
		}

		Matrix answer=new Matrix(DisplayType.DECIMAL,moved);
		steps.add("\\vspace{15mm} \\mathrm{The \\ row \\ reduced \\ echelon \\ form \\ is}");
		steps.add("\\vspace{5mm} \\hspace{15mm} "+MatrixDraw.getCorrectLatex(DisplayType.DECIMAL,answer));

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);

		_solution = new Solution(Op.ROW_REDUCE, inputs, answer, steps);
	}

	@Override
	public List<String> toLatex()
	{
		return steps;
	}
	
	

}
