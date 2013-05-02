/**
 * 
 */
package backend.main;

/**
 * Used to generate the current equation position string for each ParseNode in the ParseNode tree
 * 
 * @author baebi
 */
public class ToComputeTreeNode {
	private ToComputeTreeNode _left,_right;
	private String _value;
	
	/**
	 * Creates a node of a simple tree containing strings
	 */
	public ToComputeTreeNode(ToComputeTreeNode left, ToComputeTreeNode right, String value){
		_left = left;
		_right = right;
		_value = value;
	}
	
	/**
	 * @return left child
	 */
	public ToComputeTreeNode getLeft(){
		return _left;
	}

	/**
	 * @return right child
	 */
	public ToComputeTreeNode getRight(){
		return _right;
	}

	/**
	 * @return string value
	 */
	public String getValue(){
		return _value;
	}
	
	/**
	 * @param left set the left child
	 */
	public void setLeft(ToComputeTreeNode left){
		_left = left;
	}
	
	/**
	 * @param right set the right child
	 */
	public void setRight(ToComputeTreeNode right){
		_right = right;
	}
	
	/**
	 * @param value set the string value
	 */
	public void setValue(String value){
		_value = value;
	}

}
