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
	}
}
