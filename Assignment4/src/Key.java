/*Class Name: Key.java
Class Description: The class provides the getter methods for each Key, holding the label and type
Author: Eunsung Kim, 251275156
*/
public class Key {

	private String label;
	private int type;

	// constructor to initialize lable and type
	public Key(String theLabel, int theType) {
		// store the label in lower case
		this.label = theLabel.toLowerCase();
		this.type = theType;
	}

	// Returns the string label
	public String getLabel() {
		return this.label;
	}

	// Returns the int type
	public int getType() {
		return this.type;
	}

	// Returns int depending on the comparison of the two Keys
	public int compareTo(Key k) {
		// if the label and type are both same, return 0
		if (this.label.equals(k.label) && this.type == k.type) {
			return 0;
			// if the labels are equal, but the type is lower in this key, return -1
		} else if ((this.label.compareTo(k.label) < 0) || (this.label.equals(k.label) && this.type < k.type)) {
			return -1;
			// return 1 when this key is higher
		} else {
			return 1;
		}
	}
}
