package backend.computations.operations;

import java.util.*;

import backend.blocks.*;
import backend.computations.infrastructure.*;

/** Used for all plus minus, assign correct operation to use
 *
 * @author dzee
 */
public class PlusMinus extends Computable
{
    private Computable actual;

	/**Is a MM plusminus*/
  	public PlusMinus(Matrix m1, Matrix m2, boolean isPlus)
  	{
  		actual=new MM_PlusMinus(m1,m2,isPlus);
  	}

  	/**Is a SS plusminus*/
  	public PlusMinus(Scalar s1, Scalar s2, boolean isPlus)
  	{
  		actual=new SS_PlusMinus(s1,s2,isPlus);
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
