
/* Class Name: HashDictionary.java
 * Class Description: The class stores all the configurations data using hash function and 
 * separate chaining. 
 * Author: Eunsung Kim, 251275156
 */
import java.util.Iterator;
import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
	// hash table with linked list for each element(separate chaining)
	private LinkedList<Data> table[];

	// constructor
	public HashDictionary(int size) {
		// initialize the empty array
		table = new LinkedList[size];
		for (int i = 0; i < size; i++) {
			// initialize each each element of the table array as LinkedList of Data
			// elements
			table[i] = new LinkedList<Data>();
		}
	}

	// polynomial hash function method returning the integer value of the
	// corresponding String configuration input
	private int hashFunction(String key, int x, int M) {
		// start with the last character ASCII value of the configuration
		int val = (int) key.charAt(key.length() - 1);
		// Using Horner's rule to prevent extremely large value
		for (int i = key.length() - 2; i >= 0; i--) {
			// M is a prime number (set in Configurations class)
			val = (val * x + (int) key.charAt(i)) % M;
		}
		return val;
	}

	// Puts data record into the hash dictionary using the hash function and returns 1
	// if there is a collision and 0 if there is not.
	public int put(Data record) throws DictionaryException {
		boolean collisions = false;
		// set the value of x to a number that has been proven to work well (39)
		// convert the string configuration to integer for index of hash table
		int pos = hashFunction(record.getConfiguration(), 11, table.length);
		// Use iterator to go through the linked list
		Iterator<Data> itr = table[pos].iterator();
		Data p = null;
		while (itr.hasNext()) {
			p = itr.next();
			// throw an exception if there is exactly same configuration
			if (p.getConfiguration().equals(record.getConfiguration()))
				throw new DictionaryException();
			// if there was already a data at the table index, there was a collision
			collisions = true;
		}
		// add data to the end of the linked list
		table[pos].add(record);
		if (!collisions)
			return 0;
		else
			return 1;
	}

	// Removes the data with the configuration from the hash dictionary
	public void remove(String config) throws DictionaryException {
		boolean found = false;
		// get the index of the hash table
		int pos = hashFunction(config, 11, table.length);
		Iterator<Data> itr = table[pos].iterator();
		Data p = null;
		// traverse through the linked list at the hash table index
		while (itr.hasNext()) {
			p = itr.next();
			// if there is a match, remove the data
			if (p.getConfiguration().equals(config)) {
				found = true;
				break;
			}
		}
		// else, throw an exception
		if (!found)
			throw new DictionaryException();
	}

	// Returns the score stored in the data with config as the key
	public int get(String config) {
		int pos = hashFunction(config, 11, table.length);
		Iterator<Data> itr = table[pos].iterator();
		Data p = null;
		// traverse through the linked list at the hash table index
		while (itr.hasNext()) {
			p = itr.next();
			// if there is a match of configuration, return the score
			if (p.getConfiguration().equals(config)) {
				return p.getScore();
			}
		}
		// if the configuration is not present, return 1
		return -1;
	}

	// Returns the number of data all together in the hash table
	public int numRecords() {
		int dataNumb = 0;
		// traverse through the hash table array
		for (int i = 0; i < table.length; i++) {
			dataNumb += table[i].size();
		}
		return dataNumb;
	}

}
