
import java.util.*;

public class Board {
	private char[][] board;
	private WordList wordList;
	public Board (WordList wordList, int size) {
		this.wordList = wordList;
		Random r = new Random();
		board = new char[size][size];
		// Assign random letters within the word list to the board
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String word = wordList.get(r.nextInt(wordList.size()));
				board[i][j] = word.charAt(r.nextInt(word.length()));
			}
		}

	}

	public ArrayList<String> find() {
		// Create array list of found words to return
		ArrayList<String> found = new ArrayList<String>();
		// Bread crumbs to see if letter has been used
		boolean[][] tracker = new boolean[board.length][board[0].length];
		// Call recursive helper method
		startingPoint(found, 3, tracker);
		
		// Override Comparator interface for sorting
		WordComparator compare = new WordComparator();
		Collections.sort(found, compare);
		return found;
	}
	
	// find() main helper method that flood fills for a given point
	private void findHelper(int i, int j, String build, int length,
			ArrayList<String> found, boolean[][] tracker) {
		
		// Base case of if the word length is too long
		if (build.length() > length) {
			// Check to see if word is a word and not repeated
			if (wordList.contains(build) && !found.contains(build)) {
				found.add(build);
			}
			return;
		}
		// Second Base case to see if the coordinates fall off the board or has 
			// already been used
		if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || tracker[i][j]) {
			return;
		}
		build += board[i][j];
		tracker[i][j] = true;
		
		// Flood fill each direction
		findHelper(i, j+1, build, length, found, tracker);
		findHelper(i+1, j, build, length, found, tracker);
		findHelper(i+1, j+1, build, length, found, tracker);
		findHelper(i-1, j, build, length, found, tracker);
		findHelper(i, j-1, build, length, found, tracker);
		findHelper(i-1, j-1, build, length, found, tracker);
		findHelper(i-1, j+1, build, length, found, tracker);
		findHelper(i+1, j-1, build, length, found, tracker);
		// Reset tracker
		tracker[i][j] = false;
	}
	
	// Helper method that tests each word length
	private void perLength(ArrayList<String> found, int length, int i, int j, boolean[][] tracker) {
		if (length > wordList.getLongestWordLength()) {
			return;
		}
		findHelper(i, j, "", length, found, tracker);
		perLength(found, length+1, i, j, tracker);
	}
	
	// Helper method that tests each starting point
	private void startingPoint(ArrayList<String> found, int length, boolean[][] tracker) {
		for (int k = 0; k < board.length; k++) {
			for (int l = 0; l < board[0].length; l++) {
				perLength(found, length-1, k, l, tracker);
			}
		}
	}

	// Overrides toString() to print the board
	public String toString() {
		String send = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				send += board[i][j] + " ";
			}
			send += "\n";
		}
		return send;
	}
}