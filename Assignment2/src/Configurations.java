/* Class Name; Configurations.java
 * Class Description: The class includes methods to set and evaluate the configurations 
 * retrieved from the hash dictionary.
 * Author: Eunsung Kim, 251275156
 */
public class Configurations {
	// instance variables
	private int boardSize;
	private int lengthToWin;
	private int maxLevels;
	private char[][] board;

	// constructor
	public Configurations(int board_size, int lengthToWin, int max_levels) {
		this.boardSize = board_size;
		this.lengthToWin = lengthToWin;
		this.maxLevels = max_levels;
		this.board = new char[boardSize][boardSize];
		// initialize the game board with empty char value
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				board[row][column] = ' ';
			}
		}
	}

	// Returns an empty hash dictionary with appropriate hash table size
	public HashDictionary createDictionary() {
		// table size set to a prime number between 6000 and 10000
		int tableSize = 6151;
		HashDictionary dict = new HashDictionary(tableSize);
		return dict;
	}

	// Returns the score of the pre-existing configuration in the hash table
	public int repeatedConfiguration(HashDictionary hashTable) {
		String config = "";
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				config += board[row][col];
			}
		}
		return hashTable.get(config);
	}

	// Adds the data with corresponding string configuration and score
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		String config = "";
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				config += board[row][col];
			}
		}
		Data newConfig = new Data(config, score);
		hashDictionary.put(newConfig);
	}

	// Sets the play of human('X') and computer ('O') at the corresponding position
	// of the board
	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol;
	}

	// Finds whether the board position is empty or not
	public boolean squareIsEmpty(int row, int col) {
		return board[row][col] == ' ';
	}

	// Checks whether the player or the computer has won by evaluating the game board
	public boolean wins(char symbol) {
		// length of the X or + shape
		int length = 0;
		// check only the inner squares (not most outer squares)
		for (int row = 1; row < boardSize - 1; row++) {
			
			for (int col = 1; col < boardSize - 1; col++) {
				
				// if the current square is the center of a + shape
				if (board[row][col] == symbol && board[row - 1][col] == symbol && board[row + 1][col] == symbol
						&& board[row][col - 1] == symbol && board[row][col + 1] == symbol) {
					length = 5;
					// get the length of the + shape
					if (length == 5 && lengthToWin > 5) {
						length = this.getPlusLength(row, col, symbol);
					}
					
					// if the current square is the center of a X shape
				} else if (board[row][col] == symbol && board[row - 1][col - 1] == symbol
						&& board[row - 1][col + 1] == symbol && board[row + 1][col - 1] == symbol
						&& board[row + 1][col + 1] == symbol) {
					length = 5;
					// get the length of the X shape
					if (length == 5 && lengthToWin > 5) {
						length = this.getXLength(row, col, symbol);
					}
					
				}
				// if the length has reached the length to win
				if (length >= lengthToWin)
					return true;
				
				// reset length 
				length = 0;
				
			}
		}
		// else return false
		return false;
	}

	// Private helper method to get the length of the + shape
	private int getPlusLength(int row, int col, char symbol) {
		// variables checking connection to four directions of the + shape
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		// distance from the center square
		int distLength = 2;
		// length begins at 5
		int length = 5;

		// while one of four directions are connected with the symbol
		while (up || down || left || right) {
			// Check connection to upper part of plus shape
			if (up && row - distLength >= 0 && board[row - distLength][col] == symbol)
				// increment length if there is connection
				length++;
			else
				up = false;
			// Check connection to lower part of plus shape
			if (down && row + distLength < boardSize && board[row + distLength][col] == symbol)
				length++;
			else
				down = false;
			// Check connection to left part of plus shape
			if (left && col - distLength >= 0 && board[row][col - distLength] == symbol)
				length++;
			else
				left = false;
			// Check connection to right part of plus shape
			if (right && col + distLength < boardSize && board[row][col + distLength] == symbol)
				length++;
			else
				right = false;
			
			// increment the distance length
			distLength++;
		}
		return length;
	}

	// Private helper method to get the length of the X shape
	private int getXLength(int row, int col, char symbol) {
		// variables checking connection to four directions of the X shape
		boolean upLeft = true;
		boolean upRight = true;
		boolean downLeft = true;
		boolean downRight = true;
		int distLength = 2;
		int length = 5;

		// while one of four directions are connected with the symbol
		while (upLeft || upRight || downLeft || downRight) {
			// Check connection to upper left part of x shape
			if (upLeft && row - distLength >= 0 && col - distLength >= 0
					&& board[row - distLength][col - distLength] == symbol)
				length++;
			else
				upLeft = false;
			// Check connection to upper right part of x shape
			if (upRight && row - distLength >= 0 && col + distLength < boardSize
					&& board[row - distLength][col + distLength] == symbol)
				length++;
			else
				upRight = false;
			// Check connection to lower left part of x shape
			if (downLeft && row + distLength < boardSize && col - distLength >= 0
					&& board[row + distLength][col - distLength] == symbol)
				length++;
			else
				downLeft = false;
			// Check connection to lower right part of x shape
			if (downRight && row + distLength < boardSize && col + distLength < boardSize
					&& board[row + distLength][col + distLength] == symbol)
				length++;
			else
				downRight = false;

			distLength++;
		}
		return length;
	}

	// Checks if the game is a draw
	public boolean isDraw() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				// if the board has empty squares, it is not a draw
				if (this.squareIsEmpty(row, col))
					return false;
			}
		}
		// if there is a winner, it is not a draw
		if (this.wins('X') || this.wins('O'))
			return false;
		// else, it is a draw
		else
			return true;
	}

	// Returns the score of the board for the computer
	public int evalBoard() {
		if (this.wins('O'))
			return 3;
		else if (this.wins('X'))
			return 0;
		else if (this.isDraw())
			return 2;
		// isDraw method already checks whether there are still empty positions on the
		// board
		else
			return 1;
	}

}
