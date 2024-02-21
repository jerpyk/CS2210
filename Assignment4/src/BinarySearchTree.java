/*Class Name: BinarySearchTree.java
Class Description: The class provides the BSTDictionary the methods for a binary search tree
Author: Eunsung Kim, 251275156
*/
public class BinarySearchTree {
	private BSTNode root;

	// constructor to create a binary search tree with a root node
	public BinarySearchTree() {
		this.root = new BSTNode(null);
	}

	// Returns the root node
	public BSTNode getRoot() {
		return this.root;
	}

	// Returns the node where the node is, and if not found, where the node should be
	public BSTNode get(BSTNode r, Key k) {
		// return the node if it is a leaf
		if (r.isLeaf()) {
			return r;
		} else {
			// if the Key of the node is equal to the searching key
			if (r.getRecord().getKey().compareTo(k) == 0) {
				return r;
			} else {
				// if the Key is smaller than searching key
				if (r.getRecord().getKey().compareTo(k) < 0) {
					// recursively call on the right child
					return this.get(r.getRightChild(), k);
				} else {
					// recursively call on the left child otherwise
					return this.get(r.getLeftChild(), k);
				}
			}
		}
	}

	// Inserts the Record in the proper position in a tree
	public void insert(BSTNode r, Record d) throws DictionaryException {
		// get the node's proper position
		BSTNode p = get(r, d.getKey());
		// if it is not a leaf, then it already exists
		if (!p.isLeaf()) {
			throw new DictionaryException("Record already exists.");
		} else {
			// if it is a leaf, then insert a new node
			p.setRecord(d);
			BSTNode c1 = new BSTNode(null);
			BSTNode c2 = new BSTNode(null);
			p.setLeftChild(c1);
			c1.setParent(p);
			p.setRightChild(c2);
			c2.setParent(p);
		}
	}

	// Removes the node with Key k
	public void remove(BSTNode r, Key k) throws DictionaryException {
		BSTNode p = get(r, k);
		// if the key is not present, it is a leaf
		if (p.isLeaf()) {
			throw new DictionaryException("Record not present.");
		} else {
			// one of the children is a leaf node
			if (p.getLeftChild().isLeaf() || p.getRightChild().isLeaf()) {
				BSTNode c;
				// set the other child to c
				if (p.getLeftChild().isLeaf()) {
					c = p.getRightChild();
				} else {
					c = p.getLeftChild();
				}
				BSTNode pp = p.getParent();
				// if the parent of the current node is not null
				if (pp != null) {
					// set the parent of current node as the parent of c, and vise versa for the parent
					c.setParent(pp);
					if (p.equals(pp.getLeftChild())) {
						pp.setLeftChild(c);
					} else {
						pp.setRightChild(c);
					}
				} else {
					// set c as the root
					root = c;
				}
				// if both children are not leaf
			} else {
				// replace the node with the smallest from the right child
				BSTNode s = this.smallest(p.getRightChild());
				p.setRecord(s.getRecord());
				// recursively remove the smallest of the right child, which has 2 leaf nodes children
				this.remove(s, s.getRecord().getKey());
			}
		}
	}

	// Returns the successor node with Key k
	public BSTNode successor(BSTNode r, Key k) {
		// get the position for Key k
		BSTNode p = get(r, k);
		// if the position is not a leaf node, or the right child is not a leaf
		if (!p.isLeaf() && !p.getRightChild().isLeaf()) {
			// get the smallest from the right child
			return this.smallest(p.getRightChild());
		} else {
			// go to the parent until the node with greater Key than k is found, null if not found
			p = p.getParent();
			while (p != null && p.getRecord().getKey().compareTo(k) < 0) {
				p = p.getParent();
			}
			return p;
		}

	}

	// Returns the predecessor node with Key k
	public BSTNode predecessor(BSTNode r, Key k) {
		BSTNode p = get(r, k);
		// if the position is not a leaf node, or the right left child is not a leaf
		if (!p.isLeaf() && !p.getLeftChild().isLeaf()) {
			// get the largest from the right child
			return this.largest(p.getLeftChild());
		} else {
			// go to the parent until the node with smaller Key than k is found, null if not found
			p = p.getParent();
			while (p != null && p.getRecord().getKey().compareTo(k) > 0) {
				p = p.getParent();
			}
			return p;
		}
	}
	
	// Returns the node with the smallest Key
	public BSTNode smallest(BSTNode r) {
		// return null if the root is null
		if (r == null) {
			return null;
			// get the left most node
		} else {
			BSTNode p = r;
			while (!p.isLeaf()) {
				p = p.getLeftChild();
			}
			// return one above the empty leaf node
			return p.getParent();
		}
	}
	
	// Returns the node with the smallest Key
	public BSTNode largest(BSTNode r) {
		if (r == null) {
			return null;
			// get the right most node
		} else {
			BSTNode p = r;
			while (!p.isLeaf()) {
				p = p.getRightChild();
			}
			// return one above the empty leaf node
			return p.getParent();
		}
	}

}
