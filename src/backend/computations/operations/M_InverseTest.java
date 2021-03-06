package backend.computations.operations;

import backend.blocks.*;
import static org.junit.Assert.*;
import backend.blocks.Countable.DisplayType;
import org.junit.*;

import backend.computations.infrastructure.*;

public class M_InverseTest
{
  @Test
	public void size1() throws Exception
	{
		Double[][] v=new Double[1][1];
		v[0][0]=new Double(5);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Inverse t=new M_Inverse(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==1);
		assertTrue(d[0].length==1);
		assertTrue(d[0][0]==0.2);
	}

	@Test
	public void size2() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(2);
		v[0][1]=new Double(5);
		v[1][0]=new Double(3);
		v[1][1]=new Double(8);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Inverse t=new M_Inverse(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==2);
		assertTrue(d[0].length==2);
		assertTrue(d[0][0]==8);
		assertTrue(d[1][0]==-3);
		assertTrue(d[0][1]==-5);
		assertTrue(d[1][1]==2);
	}

	@Test
	public void detIsNot1() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(4);
		v[1][0]=new Double(10);
		v[0][1]=new Double(1);
		v[1][1]=new Double(2);
		//det=-2
	Matrix m=new Matrix(DisplayType.DECIMAL,v);
		M_Inverse t=new M_Inverse(m);
		Solution s=t.getSolution();
		Double[][] d=((Matrix)(s.getAnswer())).getValues();
		assertTrue(d.length==2);
		assertTrue(d[0].length==2);
		assertTrue(d[0][0]==-1);
		assertTrue(d[1][0]==5);
		assertTrue(d[0][1]==0.5);
		assertTrue(d[1][1]==-2);
	}

	@Test
	public void detIs0() throws Exception
	{
		Double[][] v=new Double[2][2];
		v[0][0]=new Double(4);
		v[1][0]=new Double(2);
		v[0][1]=new Double(2);
		v[1][1]=new Double(1);
		Matrix m=new Matrix(DisplayType.DECIMAL,v);
		try
		{
			M_Inverse t=new M_Inverse(m);
			Solution s=t.getSolution();
			((Matrix)(s.getAnswer())).getValues();
			fail();
		}
		catch (Exception e)
		{}
	}
}
