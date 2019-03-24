package model.misc;

public class TreeNode {
	private int _value;
	private TreeNode _left;
	private TreeNode _right;
	
	public TreeNode (int value) {
		_value=value;
		_left=null;
		_right=null;
	}
	
	
	public int getValue() {
		return _value;
	}
	public TreeNode getLeft() {
		return _left;
	}
	public TreeNode getRight() {
		return _right;
	}
	public String toString() {
		return ""+_value;
	}
	public void setLeft(TreeNode node) {
		_left=node;
	}
	public void setRight(TreeNode node) {
		_right=node;
	}
}
