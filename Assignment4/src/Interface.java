/*Program Name: Interface.java
Program Description: The program provides the user with interface to store the data from input 
file into a dictionary and perform various operations on the files.
Author: Eunsung Kim, 251275156
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Interface {

	public static void main(String[] args) {
		// BSTDictionary to store the file information
		BSTDictionary dict = new BSTDictionary();
		// error handling 
		try {
			String label;
			int type;
			// reader for the input file
			BufferedReader freader = new BufferedReader(new FileReader(args[0]));
			// get label in every first line, repeat until there is none or blank line in the file
			while ((label = freader.readLine()) != null && label.length() > 0) {
				String line = freader.readLine();
				String data = null;
				// get the type from the starting and ending formats
				if (line.startsWith("-")) {
					type = 3;
				} else if (line.startsWith("+")) {
					type = 4;
				} else if (line.startsWith("*")) {
					type = 5;
				} else if (line.startsWith("/")) {
					type = 2;
				} else if (line.endsWith(".gif")) {
					type = 7;
				} else if (line.endsWith(".jpg")) {
					type = 6;
				} else if (line.endsWith(".html")) {
					type = 8;
				} else {
					type = 1;
				}
				// get the data in every second line
				switch (type) {
				case 1:
				case 6:
				case 7:
				case 8:
					data = line;
					break;
				case 2:
				case 3:
				case 4:
				case 5:
					// exclude the first character of the line
					data = line.substring(1);
					break;
				}
				dict.put(new Record(new Key(label, type), data));
			}
			// error handling for possible exceptions
			// BSTDictionary object can throw this exception
		} catch (DictionaryException e) {
			System.out.println(e.getMessage());
			// BufferedReader object can throw this exception
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// file input exception handling
		try {
			boolean exitLoop = false;
			// initialize variables for reading user command
			StringReader keyboard = new StringReader();
			String line = keyboard.read("Enter next command: ");
			// initialize multimedia reader objects
			SoundPlayer sound = new SoundPlayer();
			PictureViewer picture = new PictureViewer();
			ShowHTML html = new ShowHTML();
			StringTokenizer st = new StringTokenizer(line, " ");
			String token = st.nextToken();
			// loop until the user types "exit"
			while (!exitLoop) {
				// StringTokenizer object for reading string as tokens, separated by a space

				switch (token) {
				// define w
				case "define": {
					// get the variables given
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 1));
					if (rec == null) {
						System.out.println("The word " + w + " is not in the ordered dictionary");
					} else {
						// print the definition of the label given
						System.out.println(rec.getDataItem());
					}
					break;
				}
				// translate w
				case "translate": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 2));
					if (rec == null) {
						System.out.println("There is no definition for the word " + w);
					} else {
						// print the translation of the label given
						System.out.println(rec.getDataItem());
					}
					break;
				}
				// sound w
				case "sound": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 3));
					if (rec == null) {
						System.out.println("There is no sound file for " + w);
					} else {
						// play the sound of the label given
						sound.play(rec.getDataItem());
					}
					break;
				}
				// play w
				case "play": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 4));
					if (rec == null) {
						System.out.println("There is no music file for " + w);
					} else {
						// play the music of the label given
						sound.play(rec.getDataItem());
					}
					break;
				}
				// say w
				case "say": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 5));
					if (rec == null) {
						System.out.println("There is no voice file for " + w);
					} else {
						// play the voice of the label given
						sound.play(rec.getDataItem());
					}
					break;
				}
				// show w
				case "show": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 6));
					if (rec == null) {
						System.out.println("There is no image file for " + w);
					} else {
						// play the picture of the label given
						picture.show(rec.getDataItem());
					}
					break;
				}
				// animate w
				case "animate": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 7));
					if (rec == null) {
						System.out.println("There is no animated file for " + w);
					} else {
						// play the animation of the label given
						picture.show(rec.getDataItem());
					}
					break;
				}
				// browse w
				case "browse": {
					String w = st.nextToken();
					Record rec = dict.get(new Key(w, 8));
					if (rec == null) {
						System.out.println("There is no webpage called " + w);
					} else {
						// browse the html of the label given
						html.show(rec.getDataItem());
					}
					break;
				}
				// delete w k
				case "delete": {
					String w = st.nextToken();
					String k = st.nextToken();
					Record rec = dict.get(new Key(w, Integer.parseInt(k)));
					if (rec == null) {
						System.out.println("No record in the ordered dictionary has key (" + w + "," + k + ").");
					} else {
						// remove the record with the given label and key
						dict.remove(rec.getKey());
					}
					break;
				}
				// add w t c
				case "add": {
					String w = st.nextToken();
					String t = st.nextToken();
					String c = st.nextToken();
					// change the string to int
					Record rec = dict.get(new Key(w, Integer.parseInt(t)));
					if (rec != null) {
						System.out.println("A record with the given key (" + w + "," + t
								+ ") is already in the orderd dictionary.");
					} else {
						// put the record into the dictionary with the given label, key, and data
						dict.put(new Record(new Key(w, Integer.parseInt(t)), c));
					}
					break;
				}
				// list prefix
				case "list": {
					String prefix = st.nextToken();
					// start at type 1 to find all types
					Record rec = dict.get(new Key(prefix, 1));
					// if the label with exact prefix is not present move to its successor
					if (rec == null) {
						rec = dict.successor(new Key(prefix, 1));
					}
					// if the successor does not exist, or it does not start with the prefix
					if (rec == null || !rec.getKey().getLabel().startsWith(prefix)) {
						System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
						break;
					}
					// repeat until the starting prefix matches the label
					while (rec.getKey().getLabel().startsWith(prefix)) {
						System.out.print(rec.getKey().getLabel());
						// if the current record is the last, or the next element does not start with the prefix
						if (rec == dict.largest()
								|| !dict.successor(rec.getKey()).getKey().getLabel().startsWith(prefix)) {
							System.out.println();
							break;
						} else {
							System.out.print(", ");
						}
						// get the successor of the current record
						rec = dict.successor(rec.getKey());

					}
					break;
				}
				// first
				case "first": {
					// get the element with the lowest lexicographical order
					Record rec = dict.smallest();
					System.out
							.println(rec.getKey().getLabel() + "," + rec.getKey().getType() + "," + rec.getDataItem());
					break;
				}
				// last
				case "last": {
					// get the element with the highest lexicographical order
					Record rec = dict.largest();
					System.out
							.println(rec.getKey().getLabel() + "," + rec.getKey().getType() + "," + rec.getDataItem());
					break;
				}
				// exit
				case "exit": {
					exitLoop = true;
					break;
				}
				// for invalid command
				default:
					System.out.println("Invalid command.");
				}
				
				if (exitLoop) {
					break;
				}
				// reinitialize variables for user input
				line = keyboard.read("Enter next command: ");
				st = new StringTokenizer(line, " ");
				token = st.nextToken();
			}
			// error handling for possible exceptions
			// StringTokenizer object can throw this exception
		} catch (

		NoSuchElementException e) {
			System.out.println("Error: Improper call of a command.");
			// SoundPlayer, PictureViewer, and ShowHTML objects can throw this exception
		} catch (MultimediaException e) {
			System.out.println(e.getMessage());
			// BSTDictionary object can throw this exception
		} catch (DictionaryException e) {
			System.out.println(e.getMessage());
		}
	}

}
