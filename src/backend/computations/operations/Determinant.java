package backend.computations.operations;

import java.util.*;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import backend.computations.infrastructure.Computable;
import backend.computations.infrastructure.Solution;
import matrixDraw.*;

/** Determinant Operation
 *
 * @author dzee
 */
public class Determinant extends Computable
{
  	private Solution _solution;
  	private List<String> steps=new ArrayList<>();
  	private DisplayType answerDisplayType;
  	private boolean _hideSteps = false;

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
		
		steps.add(" \\mathrm{\\vspace{10mm} Calculate \\ the \\ Determinant \\\\ \\vspace{10mm}");

		answerDisplayType = matrix.getDisplayType();
		steps.add("\\vspace{10mm} \\hspace{15mm} " +MatrixDraw.getCorrectLatex(answerDisplayType,matrix));
		
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
		Scalar answer=calcDet(values,0);

		List<Countable> inputs = new ArrayList<>();
		inputs.add(matrix);
		
		List<String> latex;
		if (!_hideSteps){
			latex = toLatex();
		}else{
			latex = new ArrayList<>();
			latex.add("\\mathrm{\\vspace{10mm} Calculate \\ the \\ Determinant");
			latex.add("\\mathrm{\\vspace{10mm}  Steps \\ omitted \\ for \\ matrices  \\ larger \\ than \\ 3 \\ by \\ 3, \\ for \\ your \\ own \\ good!");
			List<String> l = toLatex();
			latex.add("\\vspace{10mm}"+l.get(l.size()-1));
		}
		
		_solution = new Solution(Op.DETERMINANT, inputs, answer, latex);
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
	 *the final step contains the determinant
	 * @param indentLength */
	private Scalar calcDet(Double[][] values, int indentLength) throws Exception
	{
		if (values.length<1)
			throw new IllegalArgumentException("Matrix size needs to be at least 1, given "+values.length);

		if (values.length!=values[0].length)
			throw new IllegalArgumentException("Matrix must have the same number of columns and rows");

		if (values.length > 3){
			_hideSteps = true;
		}
		//if it is just a 1x1 matrix
		if (values.length==1)
		{
			steps.add("\\hspace{"+indentLength+"mm}\\mathrm{The \\ determinant \\ of \\ a \\ 1x1 \\ matrix \\ is \\ its \\ value:} "+values[0][0]);
			return new Scalar(values[0][0],answerDisplayType);
		}

		//a 2x2 matrix
		if (values.length==2)
		{
			double det=values[0][0]*values[1][1]-values[0][1]*values[1][0];
			steps.add("\\vspace{15mm}\\hspace{"+indentLength+"mm}"+values[0][0]+" \\times "+values[1][1]+" - "+values[0][1]+" \\times "+values[1][0]+" = "+det);
			//steps.add("\\hspace{"+indentLength+"mm}\\mathrm{The \\ determinant \\ is \\ "+det+"}");
			return new Scalar(det,answerDisplayType);
		}

		//for any larger matrix
		//the determinant
		double det=0;
		for (int i=0;i<values.length;i++)
		{
			//the matrix without the first row, and the i-th column
			Double[][] m=removeRowColumn(values,i,0);
			steps.add("\\vspace{20mm}\\hspace{"+indentLength+"mm}\\mathrm{" +(i+1)+ ". \\ Calculate \\ the \\ determinant \\ of \\ submatrix} "+
				MatrixDraw.getCorrectLatex(answerDisplayType,new Matrix(answerDisplayType,m)));

			//calculate m's determinant
			Scalar d=calcDet(m,indentLength+15);

			//alternate + or - sign
			int sign=1;
			if (i%2==1)
				sign=-1;

			double subDet=sign*values[i][0]*d.getValue();
			det+=subDet;
			steps.add("\\vspace{20mm}\\hspace{15mm}\\hspace{"+indentLength+"mm}\\mathrm{Multiply \\ result \\ with \\ diagonally \\ opposite \\ index \\ in \\ the \\ encompassing \\ matrix \\\\ and \\ with \\ appropriate \\ sign:}");
			steps.add("\\vspace{10mm} \\hspace{"+indentLength+"mm}\\hspace{30mm}"+sign+" \\times "+values[i][0]+" \\times "+d.getValue()+" = " + (sign*values[i][0]*d.getValue()));
			steps.add("\\vspace{10mm} \\hspace{"+indentLength+"mm}\\mathrm{\\hspace{15mm}Add \\ result \\ to \\ the \\ overall \\ determinant}");
		}

		String detString = shortenDecimal(Double.toString(det));
		steps.add("\\vspace{15mm}\\hspace{"+indentLength+"mm}\\mathrm{The \\ overall \\ determinant \\ is \\ "+detString+".}");
		return new Scalar(det,answerDisplayType);
	}

	@Override
	public List<String> toLatex()
	{
		return steps;
	}

}
