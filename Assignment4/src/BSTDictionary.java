/*Class Name: BSTDictionary.java
Class Description: The class provides uses the methods of BinarySearchTree class to create an ordered dictionary
Author: Eunsung Kim, 251275156
*/
public class BSTDictionary implements BSTDictionaryADT {
	// binarySearchTree variable for the tree
	private BinarySearchTree bst;

	// Constructor to initialize the tree
	public BSTDictionary() {
		bst = new BinarySearchTree();
	}

	// Returns the Record stored in the position Key k
	public Record get(Key k) {
		return bst.get(bst.getRoot(), k).getRecord();
	}

	// Puts the Record in the proper position, throw exception if already stored
	public void put(Record d) throws DictionaryException {
		bst.insert(bst.getRoot(), d);
	}

	// Removes the Record in the position for Key k, throw exception if not stored
	public void remove(Key k) throws DictionaryException {
		bst.remove(bst.getRoot(), k);
	}

	// Returns the Record of the successor of the Key k
	public Record successor(Key k) {
		BSTNode p = bst.successor(bst.getRoot(), k);
		if (p == null) {
			return null;
		} else {
			return p.getRecord();
		}
	}
	
	// Returns the Record of the predecessor of the Key k
	public Record predecessor(Key k) {
		BSTNode p = bst.predecessor(bst.getRoot(), k);
		if (p == null) {
			return null;
		} else {
			return p.getRecord();
		}
	}

	// Returns the Record of the smallest Key
	public Record smallest() {
		BSTNode p = bst.smallest(bst.getRoot());
		if (p == null) {
			return null;
		} else {
			return p.getRecord();
		}
	}

	// Returns the Record of the largest Key
	public Record largest() {
		BSTNode p = bst.largest(bst.getRoot());
		if (p == null) {
			return null;
		} else {
			return p.getRecord();
		}
	}

}
