package backend.computations.operations;

import backend.blocks.*;
import static org.junit.Assert.*;
import backend.blocks.Countable.DisplayType;
import org.junit.*;

import backend.computations.infrastructure.*;

public class M_RowReduceTest
{
   @Test
	public void size1() throws Exception
	{
		Double[][] v=new Double[1][1];
		v[0][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce t=new M_RowReduce(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==1);
		assertTrue(d[0].length==1);
		assertTrue(d[0][0].equals(1.0));
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
		M_RowReduce t=new M_RowReduce(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==2);
		assertTrue(d[0].length==2);
		assertTrue(d[0][0].equals(1.0));
		assertTrue(d[1][0].equals(0.0));
		assertTrue(d[0][1].equals(0.0));
		assertTrue(d[1][1].equals(1.0));
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
		M_RowReduce t=new M_RowReduce(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==1);
		assertTrue(d[0].length==5);
		assertTrue(d[0][0].equals(1.0));
		assertTrue(d[0][1].equals(0.0));
		assertTrue(d[0][2].equals(0.0));
		assertTrue(d[0][3].equals(0.0));
		assertTrue(d[0][4].equals(0.0));
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
		M_RowReduce t=new M_RowReduce(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==5);
		assertTrue(d[0].length==1);
		assertTrue(d[0][0].equals(1.0));
		assertTrue(d[1][0].equals(2.0));
		assertTrue(d[2][0].equals(3.0));
		assertTrue(d[3][0].equals(4.0));
		assertTrue(d[4][0].equals(5.0));
	}

	@Test
	public void ref3x3() throws Exception
	{
		Double[][] v=new Double[3][3];
		v[0][0]=new Double(1);
		v[1][0]=new Double(0);
		v[2][0]=new Double(0);
		v[0][1]=new Double(0);
		v[1][1]=new Double(1);
		v[2][1]=new Double(0);
		v[0][2]=new Double(0);
		v[1][2]=new Double(0);
		v[2][2]=new Double(1);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==3);
		assertTrue(a[0].length==3);
		for (int i=0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				assertTrue(a[i][j].equals(v[i][j]));
			}
		}
	}

	@Test
	public void simple4x3() throws Exception
	{
		Double[][] v=new Double[4][3];
		v[0][0]=new Double(0);
		v[1][0]=new Double(1);
		v[2][0]=new Double(0);
		v[3][0]=new Double(1);
		v[0][1]=new Double(0);
		v[1][1]=new Double(0);
		v[2][1]=new Double(1);
		v[3][1]=new Double(1);
		v[0][2]=new Double(3);
		v[1][2]=new Double(1);
		v[2][2]=new Double(2);
		v[3][2]=new Double(9);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==4);
		assertTrue(a[0].length==3);
		assertTrue(a[0][0].equals(1.0));
		assertTrue(a[1][0].equals(0.0));
		assertTrue(a[2][0].equals(0.0));
		assertTrue(a[3][0].equals(2.0));
		assertTrue(a[0][1].equals(0.0));
		assertTrue(a[1][1].equals(1.0));
		assertTrue(a[2][1].equals(0.0));
		assertTrue(a[3][1].equals(1.0));
		assertTrue(a[0][2].equals(0.0));
		assertTrue(a[1][2].equals(0.0));
		assertTrue(a[2][2].equals(1.0));
		assertTrue(a[3][2].equals(1.0));
	}

	@Test
	public void simple3x3() throws Exception
	{
		Double[][] v=new Double[3][3];
		v[0][0]=new Double(2);
		v[1][0]=new Double(1);
		v[2][0]=new Double(0);
		v[0][1]=new Double(6);
		v[1][1]=new Double(-3);
		v[2][1]=new Double(1);
		v[0][2]=new Double(3);
		v[1][2]=new Double(0);
		v[2][2]=new Double(2);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==3);
		assertTrue(a[0].length==3);
		for (int i=0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				if (i==j)
					assertTrue(a[i][j].equals(1.0));
				else
					assertTrue(a[i][j].equals(0.0));
			}
		}
	}

	@Test
	public void decimal3x3() throws Exception
	{
		Double[][] v=new Double[3][3];
		v[0][0]=new Double(2.4);
		v[1][0]=new Double(1);
		v[2][0]=new Double(0);
		v[0][1]=new Double(6.15);
		v[1][1]=new Double(-3.6);
		v[2][1]=new Double(1);
		v[0][2]=new Double(31);
		v[1][2]=new Double(0);
		v[2][2]=new Double(0);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==3);
		assertTrue(a[0].length==3);
		for (int i=0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				if (i==j)
					assertTrue(a[i][j].equals(1.0));
				else
					assertTrue(a[i][j].equals(0.0));
			}
		}
	}

	@Test
	public void allzeros() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(0);
		v[1][0]=new Double(0);
		v[0][1]=new Double(0);
		v[1][1]=new Double(0);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==2);
		assertTrue(a[0].length==2);
		for (int i=0;i<2;i++)
		{
			for (int j=0;j<2;j++)
			{
				assertTrue(a[i][j].equals(0.0));
			}
		}
	}

	@Test
	public void rowofzeros() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(0);
		v[1][0]=new Double(0);
		v[0][1]=new Double(3);
		v[1][1]=new Double(0);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==2);
		assertTrue(a[0].length==2);
		assertTrue(a[0][0].equals(1.0));
		assertTrue(a[0][1].equals(0.0));
		assertTrue(a[1][0].equals(0.0));
		assertTrue(a[1][1].equals(0.0));
	}

	@Test
	public void columnofzero() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(2);
		v[1][0]=new Double(0);
		v[0][1]=new Double(3);
		v[1][1]=new Double(0);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==2);
		assertTrue(a[0].length==2);
		assertTrue(a[0][0].equals(1.0));
		assertTrue(a[0][1].equals(0.0));
		assertTrue(a[1][0].equals(0.0));
		assertTrue(a[1][1].equals(0.0));
	}

	@Test
	public void colzero4x3() throws Exception
	{
		Double[][] v=new Double[4][3];
		v[0][0]=new Double(1);
		v[1][0]=new Double(6);
		v[2][0]=new Double(0);
		v[3][0]=new Double(0);
		v[0][1]=new Double(0);
		v[1][1]=new Double(0);
		v[2][1]=new Double(1);
		v[3][1]=new Double(1);
		v[0][2]=new Double(0);
		v[1][2]=new Double(0);
		v[2][2]=new Double(0);
		v[3][2]=new Double(4);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==4);
		assertTrue(a[0].length==3);
		assertTrue(a[0][0].equals(1.0));
		assertTrue(a[1][0].equals(6.0));
		assertTrue(a[2][0].equals(0.0));
		assertTrue(a[3][0].equals(0.0));
		assertTrue(a[0][1].equals(0.0));
		assertTrue(a[1][1].equals(0.0));
		assertTrue(a[2][1].equals(1.0));
		assertTrue(a[3][1].equals(0.0));
		assertTrue(a[0][2].equals(0.0));
		assertTrue(a[1][2].equals(0.0));
		assertTrue(a[2][2].equals(0.0));
		assertTrue(a[3][2].equals(1.0));
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
			new M_RowReduce(m);
			fail();
		}
		catch (Exception e)
		{}
	}

	@Test
	public void repeatrow() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(1);
		v[1][0]=new Double(2);
		v[0][1]=new Double(-3);
		v[1][1]=new Double(-6);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_RowReduce d=new M_RowReduce(m);
		Solution s=d.getSolution();
		Double[][] a=((Matrix)(s.getAnswer())).getValues();
		assertTrue(a.length==2);
		assertTrue(a[0].length==2);
		assertTrue(a[0][0].equals(1.0));
		assertTrue(a[0][1].equals(0.0));
		assertTrue(a[1][0].equals(2.0));
		assertTrue(a[1][1].equals(0.0));
	}
}
