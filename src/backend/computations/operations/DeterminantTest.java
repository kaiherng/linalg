package backend.computations.operations;

import backend.blocks.*;
import backend.blocks.Countable.DisplayType;
import static org.junit.Assert.*;
import org.junit.*;

import backend.computations.infrastructure.*;

public class DeterminantTest
{
  @Test
	public void size1()
	{
		Double[][] v=new Double[1][1];
		v[0][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==5);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			fail();

		}
	}

	@Test
	public void size2()
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(2);
		v[1][1]=new Double(2);
		v[0][1]=new Double(3);
		v[1][0]=new Double(1);
		Matrix m=new Matrix(null,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==1);
		}
		catch (Exception e)
		{
			assertTrue(false);
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void size2_2()
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(6);
		v[1][1]=new Double(-8);
		v[0][1]=new Double(5);
		v[1][0]=new Double(3);
		Matrix m=new Matrix(null,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==-63);
		}
		catch (Exception e)
		{
			assertTrue(false);
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void size2_3()
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(0.2);
		v[1][1]=new Double(2.22);
		v[0][1]=new Double(-3.5);
		v[1][0]=new Double(11);
		Matrix m=new Matrix(null,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==38.944);
		}
		catch (Exception e)
		{
			assertTrue(false);
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void size3()
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
		Matrix m=new Matrix(null,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==-188);
		}
		catch (Exception e)
		{
			assertTrue(false);
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void size3_2()
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
		Matrix m=new Matrix(null,v);
		try
		{
			Determinant d=new Determinant(m);
			Solution s=d.getSolution();
			assertTrue(((Scalar)(s.getAnswer())).getValue()==728);
		}
		catch (Exception e)
		{
			assertTrue(false);
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void toowide()
	{
		Double[][] v=new Double[2][1];
		v[0][0]=new Double(5);
		v[1][0]=new Double(10);
		Matrix m=new Matrix(null,v);
		try
		{
			new Determinant(m);
			assertTrue(false);
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void toolong()
	{
		Double[][] v=new Double[1][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		Matrix m=new Matrix(null,v);
		try
		{
			new Determinant(m);
			assertTrue(false);
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void toohigh()
	{
		Double[][] v=new Double[1][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		Matrix m=new Matrix(null,v);
		try
		{
			new Determinant(m);
			assertTrue(false);
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void nullvalue()
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(5);
		v[0][1]=new Double(10);
		v[1][0]=null;
		v[1][1]=new Double(2);
		Matrix m=new Matrix(null,v);
		try
		{
			new Determinant(m);
			assertTrue(false);
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}
}
