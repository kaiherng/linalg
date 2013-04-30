package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;

import backend.computations.infrastructure.*;

public class DeterminantTest
{
  @Test
	public void size1() throws Exception
	{
		Double[][] v=new Double[1][1];
		v[0][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==5);
		assertTrue(d.toLatex().size()==1);
		String l="The determinant of a 1x1 matrix is its value: 5.0";
		assertTrue(d.toLatex().get(0).equals(l));
	}

	@Test
	public void size2() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(2);
		v[1][1]=new Double(2);
		v[0][1]=new Double(3);
		v[1][0]=new Double(1);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==1);
		assertTrue(d.toLatex().size()==2);
		String l="$2.0 * 2.0 - 3.0 * 1.0 = 1.0$";
		assertTrue(d.toLatex().get(0).equals(l));
		l="The determinant is 1.0";
		assertTrue(d.toLatex().get(1).equals(l));
	}

	@Test
	public void size2_2() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(6);
		v[1][1]=new Double(-8);
		v[0][1]=new Double(5);
		v[1][0]=new Double(3);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==-63);
		assertTrue(d.toLatex().size()==2);
		String l="$6.0 * -8.0 - 5.0 * 3.0 = -63.0$";
		assertTrue(d.toLatex().get(0).equals(l));
		l="The determinant is -63.0";
		assertTrue(d.toLatex().get(1).equals(l));
	}

	@Test
	public void size2_3() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(0.2);
		v[1][1]=new Double(2.22);
		v[0][1]=new Double(-3.5);
		v[1][0]=new Double(11);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==38.944);
		assertTrue(d.toLatex().size()==2);
		String l="$0.2 * 2.22 - -3.5 * 11.0 = 38.944$";
		assertTrue(d.toLatex().get(0).equals(l));
		l="The determinant is 38.944";
		assertTrue(d.toLatex().get(1).equals(l));
	}

	@Test
	public void size3() throws Exception
	{
		Double[][] v=new Double[3][3];
		v[0][0]=new Double(6);
		v[1][0]=new Double(3);
		v[2][0]=new Double(1);
		v[0][1]=new Double(-2);
		v[1][1]=new Double(5);
		v[2][1]=new Double(11);
		v[0][2]=new Double(0);
		v[1][2]=new Double(7);
		v[2][2]=new Double(8);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==-188);
		assertTrue(d.toLatex().size()==13);
		String l="Calculate the determinant of \\begin{bmatrix} 5.0 & 7.0\\\\11.0 & 8.0\\end{bmatrix}";
		assertTrue(d.toLatex().get(0).equals(l));
		l="$5.0 * 8.0 - 7.0 * 11.0 = -37.0$";
		assertTrue(d.toLatex().get(1).equals(l));
		l="The determinant is -37.0";
		assertTrue(d.toLatex().get(2).equals(l));
		l="Add $1 * 6.0 * -37.0$ to the overall determinant.";
		assertTrue(d.toLatex().get(3).equals(l));
		l="Calculate the determinant of \\begin{bmatrix} -2.0 & 0.0\\\\11.0 & 8.0\\end{bmatrix}";
		assertTrue(d.toLatex().get(4).equals(l));
		l="$-2.0 * 8.0 - 0.0 * 11.0 = -16.0$";
		assertTrue(d.toLatex().get(5).equals(l));
		l="The determinant is -16.0";
		assertTrue(d.toLatex().get(6).equals(l));
		l="Add $-1 * 3.0 * -16.0$ to the overall determinant.";
		assertTrue(d.toLatex().get(7).equals(l));
		l="Calculate the determinant of \\begin{bmatrix} -2.0 & 0.0\\\\5.0 & 7.0\\end{bmatrix}";
		assertTrue(d.toLatex().get(8).equals(l));
		l="$-2.0 * 7.0 - 0.0 * 5.0 = -14.0$";
		assertTrue(d.toLatex().get(9).equals(l));
		l="The determinant is -14.0";
		assertTrue(d.toLatex().get(10).equals(l));
		l="Add $1 * 1.0 * -14.0$ to the overall determinant.";
		assertTrue(d.toLatex().get(11).equals(l));
		l="The overall determinant is -188.0.";
		assertTrue(d.toLatex().get(12).equals(l));
	}

	@Test
	public void size3_2() throws Exception
	{
		Double[][] v=new Double[3][3];
		v[0][0]=new Double(52.3);
		v[1][0]=new Double(-10);
		v[2][0]=new Double(2);
		v[0][1]=new Double(2);
		v[1][1]=new Double(0);
		v[2][1]=new Double(0);
		v[0][2]=new Double(0);
		v[1][2]=new Double(16);
		v[2][2]=new Double(33.2);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		Determinant d=new Determinant(m);
		Solution s=d.getSolution();
		assertTrue(((Scalar)(s.getAnswer())).getValue()==728);

		assertTrue(d.toLatex().size()==13);
		String l="Calculate the determinant of \\begin{bmatrix} 0.0 & 16.0\\\\0.0 & 33.2\\end{bmatrix}";
		assertTrue(d.toLatex().get(0).equals(l));
		l="$0.0 * 33.2 - 16.0 * 0.0 = 0.0$";
		assertTrue(d.toLatex().get(1).equals(l));
		l="The determinant is 0.0";
		assertTrue(d.toLatex().get(2).equals(l));
		l="Add $1 * 52.3 * 0.0$ to the overall determinant.";
		assertTrue(d.toLatex().get(3).equals(l));
		l="Calculate the determinant of \\begin{bmatrix} 2.0 & 0.0\\\\0.0 & 33.2\\end{bmatrix}";
		assertTrue(d.toLatex().get(4).equals(l));
		l="$2.0 * 33.2 - 0.0 * 0.0 = 66.4$";
		assertTrue(d.toLatex().get(5).equals(l));
		l="The determinant is 66.4";
		assertTrue(d.toLatex().get(6).equals(l));
		l="Add $-1 * -10.0 * 66.4$ to the overall determinant.";
		assertTrue(d.toLatex().get(7).equals(l));
		l="Calculate the determinant of \\begin{bmatrix} 2.0 & 0.0\\\\0.0 & 16.0\\end{bmatrix}";
		assertTrue(d.toLatex().get(8).equals(l));
		l="$2.0 * 16.0 - 0.0 * 0.0 = 32.0$";
		assertTrue(d.toLatex().get(9).equals(l));
		l="The determinant is 32.0";
		assertTrue(d.toLatex().get(10).equals(l));
		l="Add $1 * 2.0 * 32.0$ to the overall determinant.";
		assertTrue(d.toLatex().get(11).equals(l));
		l="The overall determinant is 728.0.";
		assertTrue(d.toLatex().get(12).equals(l));
	}

	@Test
	public void toowide()
	{
		Double[][] v=new Double[2][1];
		v[0][0]=new Double(5);
		v[1][0]=new Double(10);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			new Determinant(m);
			fail();
		}
		catch (Exception e)
		{}
	}

	@Test
	public void toolong()
	{
		Double[][] v=new Double[1][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			new Determinant(m);
			fail();
		}
		catch (Exception e)
		{}
	}

	@Test
	public void toohigh()
	{
		Double[][] v=new Double[1][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			new Determinant(m);
			fail();
		}
		catch (Exception e)
		{}
	}

	@Test
	public void nullvalue()
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		v[1][0]=null;
		v[1][1]=new Double(2);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			new Determinant(m);
			fail();
		}
		catch (Exception e)
		{}
	}
}
