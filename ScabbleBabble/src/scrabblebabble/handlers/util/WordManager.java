package scrabblebabble.handlers.util;

import scrabblebabble.ScrabbleBabble;
import scrabblebabble.game.LetterTile;

public class WordManager {

	/**
	 * returns the score from an array of tiles gathered
	 * 
	 * @param tiles
	 * @return
	 */
	public int getScore(LetterTile[] tiles) {
		int out = 0;
		for (LetterTile ti : tiles) {
			out += ti.getScore();
		}
		return out;
	}
	
	/**
	 * returns the word of the array of tiles sent
	 * 
	 * @param tiles
	 * @return
	 */
	public String getWord(LetterTile[] tiles) {
		String out = "";
		for (LetterTile ti : tiles) {
			out += ti.getLetter();
		}
		return out;		
	}
	
	/** 
	 * creates a word from the range of tiles provided, with deterimination on row or coloumn
	 * 
	 * @param hv
	 * @param rowCol
	 * @param start
	 * @param end
	 * @return
	 */
	public LetterTile[] compileIterativeWord(String hv, int rowCol, int start, int end) {
		if(hv == "h") {
			LetterTile[] out = new LetterTile[end - start];
			for (int j = 0; j < ScrabbleBabble.board.grid[rowCol].length; j++) {
				if (j >= start && j <= end) {
					out[j-start] = ScrabbleBabble.board.grid[rowCol][j].occupant;			
				}
			}
			return out;
		} else if (hv == "v" ) {
			LetterTile[] out = new LetterTile[end - start];
			for (int j = 0; j < ScrabbleBabble.board.grid.length; j++) {
				if (j >= start && j <= end) {
					out[j-start] = ScrabbleBabble.board.grid[j][rowCol].occupant;			
				}
			}
			return out;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
}
