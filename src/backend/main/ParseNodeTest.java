/**
 * 
 */
package backend.main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author baebi
 *
 */
public class ParseNodeTest {
	 
	
	@Test
	public void getComputeStringTest() {
		ToComputeTreeNode n1 = new ToComputeTreeNode(null,null," + ");
		ToComputeTreeNode n2 = new ToComputeTreeNode(null,null," 5.0 ");
		ToComputeTreeNode n3 = new ToComputeTreeNode(null,null," 6.0 ");
		n1.setLeft(n2);
		n1.setRight(n3);
		ParseNode p = new ParseNode(null,null,null);
		p.setComputeStringTreeNonRecursive(n1);
		try {
			System.out.println(p.getComputeString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
