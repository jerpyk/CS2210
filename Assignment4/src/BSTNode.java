/*Class Name: BSTNode.java
Class Description: The class provides the get and set methods for the Record,
parent, left and right children, and check if the node is leaf
Author: Eunsung Kim, 251275156
*/
public class BSTNode {
	private Record rec;
	private BSTNode left, right, parent;

	//constructor to initialize the Record with item
	public BSTNode(Record item) {
		this.rec = item;
	}

	// Get the Record
	public Record getRecord() {
		return this.rec;
	}

	// Set the Record
	public void setRecord(Record d) {
		this.rec = d;
	}

	// Get the left child
	public BSTNode getLeftChild() {
		return this.left;
	}

	// Get the right child
	public BSTNode getRightChild() {
		return this.right;
	}

	// Get the parent node
	public BSTNode getParent() {
		return this.parent;
	}

	// Set the left child
	public void setLeftChild(BSTNode u) {
		this.left = u;
	}

	// Set the right child
	public void setRightChild(BSTNode u) {
		this.right = u;
	}

	// Set the parent node
	public void setParent(BSTNode u) {
		this.parent = u;
	}
	
	// Returns true if both children are null
	public boolean isLeaf() {
		return (this.left == null && this.right == null);
	}
	

}
