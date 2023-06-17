

import java.util.*;

public class Program {
	public static void main (String[] args) {
		// Print Title
		System.out.println("Welcome to Boggle, by Seanna");
		// Declare variables
		WordList wordList = new WordList("WordList.txt", 3, 8);
		Board board = new Board(wordList, 4);
		
		// Print the board
		System.out.println(board.toString());
		
		// Sort the board
		ArrayList<String> words = board.find();
		
		// Print the words per length
		System.out.println("Found " + words.size() + " word(s)");
		
		for (int i = 0; i < words.size(); i++) {
			if (i == 0 || words.get(i).length() != words.get(i-1).length()) {
				System.out.println();
				System.out.println(words.get(i).length() + " letter words");
			}
			System.out.println(words.get(i));
		}
		
	}
}




