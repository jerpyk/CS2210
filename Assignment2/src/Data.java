/*Class Name: Data.java
Class Description: This class represents the data that will be stored in the 
HashDictionary.java
Author: Eunsung Kim, 251275156*/

public class Data {

	private String config;
	private int score;

	// constructor
	public Data(String config, int score) {
		this.config = config;
		this.score = score;
	}

	// return the string configuration
	public String getConfiguration() {
		return config;
	}

	// return the score
	public int getScore() {
		return score;
	}
}
