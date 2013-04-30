package backend.computations.operations;

import java.util.*;

import backend.blocks.*;
import backend.computations.infrastructure.*;

/** Used for all multiplications, assign correct operation to use
 *
 * @author dzee
 */
public class Multiply extends Computable
{
    private Computable actual;

	/**Is a MM multiply*/
  	public Multiply(Matrix m1, Matrix m2)
  	{
  		actual=new MM_Multiply(m1,m2);
  	}

  	/**Is a SS multiply*/
  	public Multiply(Scalar s1, Scalar s2, boolean isTimes)
  	{
  		actual=new SS_MultiplyDivide(s1,s2,isTimes);
  	}

  	/**Is a SM multiply*/
  	public Multiply(Scalar s, Matrix m)
  	{
  		actual=new MS_Multiply(s,m);
  	}

  	/**Is a MS multiply*/
  	public Multiply(Matrix m, Scalar s)
  	{
  		actual=new MS_Multiply(m,s);
  	}

	@Override
	public Solution getSolution()
	{
		return actual.getSolution();
	}

	@Override
	public List<String> toLatex()
	{
		return actual.toLatex();
	}
}
