package backend.computations.operations;

import backend.blocks.*;
import static org.junit.Assert.*;
import backend.blocks.Countable.DisplayType;
import org.junit.*;

import backend.computations.infrastructure.*;

/**
 * Tests for matrix transpose operation
 *
 * @author dzee
 */
public class M_TransposeTest
{
  	@Test
	public void size1() throws Exception
	{
		Double[][] v=new Double[1][1];
		v[0][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Transpose t=new M_Transpose(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==1);
		assertTrue(d[0].length==1);
		assertTrue(d[0][0]==5);
		assertTrue(t.toLatex().size()==1);
		String l="Column 1 = \\begin{bmatrix} 5.0\\end{bmatrix} becomes Row 1 = \\begin{bmatrix} 5.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(0).equals(l));
	}

	@Test
	public void size2() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(1);
		v[0][1]=new Double(2);
		v[1][0]=new Double(3);
		v[1][1]=new Double(4);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Transpose t=new M_Transpose(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==2);
		assertTrue(d[0].length==2);
		assertTrue(d[0][0]==1);
		assertTrue(d[1][0]==2);
		assertTrue(d[0][1]==3);
		assertTrue(d[1][1]==4);
		assertTrue(t.toLatex().size()==2);
		String l="Column 1 = \\begin{bmatrix} 1.0 & 2.0\\end{bmatrix} becomes"+
			" Row 1 = \\begin{bmatrix} 1.0\\\\2.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(0).equals(l));
		l="Column 2 = \\begin{bmatrix} 3.0 & 4.0\\end{bmatrix} becomes"+
			" Row 2 = \\begin{bmatrix} 3.0\\\\4.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(1).equals(l));
	}

	@Test
	public void size3x2() throws Exception
	{
		Double[][] v=new Double[3][2];
		v[0][0]=new Double(1);
		v[0][1]=new Double(2);
		v[1][0]=new Double(3);
		v[1][1]=new Double(4);
		v[2][0]=new Double(5);
		v[2][1]=new Double(6);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Transpose t=new M_Transpose(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==2);
		assertTrue(d[0].length==3);
		assertTrue(d[0][0]==1);
		assertTrue(d[1][0]==2);
		assertTrue(d[0][1]==3);
		assertTrue(d[1][1]==4);
		assertTrue(d[0][2]==5);
		assertTrue(d[1][2]==6);
		assertTrue(t.toLatex().size()==3);
		String l="Column 1 = \\begin{bmatrix} 1.0 & 2.0\\end{bmatrix} becomes"+
			" Row 1 = \\begin{bmatrix} 1.0\\\\2.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(0).equals(l));
		l="Column 2 = \\begin{bmatrix} 3.0 & 4.0\\end{bmatrix} becomes"+
			" Row 2 = \\begin{bmatrix} 3.0\\\\4.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(1).equals(l));
		l="Column 3 = \\begin{bmatrix} 5.0 & 6.0\\end{bmatrix} becomes"+
			" Row 3 = \\begin{bmatrix} 5.0\\\\6.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(2).equals(l));
	}

	@Test
	public void wideMatrix() throws Exception
	{
		Double[][] v=new Double[1][5];
		v[0][0]=new Double(1);
		v[0][1]=new Double(2);
		v[0][2]=new Double(3);
		v[0][3]=new Double(4);
		v[0][4]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Transpose t=new M_Transpose(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==5);
		assertTrue(d[0].length==1);
		assertTrue(d[0][0]==1);
		assertTrue(d[1][0]==2);
		assertTrue(d[2][0]==3);
		assertTrue(d[3][0]==4);
		assertTrue(d[4][0]==5);
		assertTrue(t.toLatex().size()==1);
		String l="Column 1 = \\begin{bmatrix} 1.0 & 2.0 & 3.0 & 4.0 & 5.0\\end{bmatrix} becomes"+
			" Row 1 = \\begin{bmatrix} 1.0\\\\2.0\\\\3.0\\\\4.0\\\\5.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(0).equals(l));
	}

	@Test
	public void longMatrix() throws Exception
	{
		Double[][] v=new Double[5][1];
		v[0][0]=new Double(1);
		v[1][0]=new Double(2);
		v[2][0]=new Double(3);
		v[3][0]=new Double(4);
		v[4][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Transpose t=new M_Transpose(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==1);
		assertTrue(d[0].length==5);
		assertTrue(d[0][0]==1);
		assertTrue(d[0][1]==2);
		assertTrue(d[0][2]==3);
		assertTrue(d[0][3]==4);
		assertTrue(d[0][4]==5);
		assertTrue(t.toLatex().size()==5);
		String l="Column 1 = \\begin{bmatrix} 1.0\\end{bmatrix} becomes"+
			" Row 1 = \\begin{bmatrix} 1.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(0).equals(l));
		l="Column 2 = \\begin{bmatrix} 2.0\\end{bmatrix} becomes"+
			" Row 2 = \\begin{bmatrix} 2.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(1).equals(l));
		l="Column 3 = \\begin{bmatrix} 3.0\\end{bmatrix} becomes"+
			" Row 3 = \\begin{bmatrix} 3.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(2).equals(l));
		l="Column 4 = \\begin{bmatrix} 4.0\\end{bmatrix} becomes"+
			" Row 4 = \\begin{bmatrix} 4.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(3).equals(l));
		l="Column 5 = \\begin{bmatrix} 5.0\\end{bmatrix} becomes"+
			" Row 5 = \\begin{bmatrix} 5.0\\end{bmatrix}";
		assertTrue(t.toLatex().get(4).equals(l));
	}
}
