/*Class Name: Record.java
Class Description: The class provides the getter methods for each record, storing the Key and data
Author: Eunsung Kim, 251275156
*/
public class Record {
	private Key theKey;
	private String data;

	// constructor to initialize Key and data
	public Record(Key k, String theData) {
		this.theKey = k;
		this.data = theData;
	}

	// Returns the Key
	public Key getKey() {
		return this.theKey;
	}

	// Returns the string data
	public String getDataItem() {
		return this.data;
	}
}
