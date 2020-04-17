package scrabblebabble.handlers.util;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.board.Board;
import scrabblebabble.render.TilePane;

public class WordManager {

	EnumLetter[][] lastboard = new EnumLetter[15][15];
	EnumLetter[][] newboard;
	

	public void updateScoreboard() {
		//TODO
	}
	
	public int calcScore() {
		//TODO
		return 0;		
	}
	
	public EnumLetter[][] getNewGrid(GridPane gridnew) {
		EnumLetter[][] e = new EnumLetter[15][15];
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				TilePane t = (TilePane) Board.getNodeByRowColumnIndex(i, j, gridnew);
				e[i][j] = t.letter;
			}
		}
		return e;
	}
	
	/**
	 * finds all discrepencies between the two arrays and outputs those values into blank array
	 * @param gridnew
	 * @return
	 */
	public EnumLetter[][] checkChanges(GridPane gridnew) {
		EnumLetter[][] out = new EnumLetter[15][15];
		this.newboard = getNewGrid(gridnew);
		
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (this.lastboard[i][j] != this.newboard[i][j]) {
					out[i][j] = this.newboard[i][j];
				}
			}
		}
		
		this.lastboard = this.newboard;
		return out;
	}
	
	/**
	 * extracts the word in the blank array above and puts into a single array while interpolating missing letters (sorta?)
	 *  
	 * TODO
	 * 
	 * @param newArray
	 * @param moddedArray
	 * @return
	 */
	public EnumLetter[] extractNewWord(EnumLetter[][] moddedArray, EnumLetter[][] newArray) {
		ArrayList<EnumLetter> arrletter = new ArrayList<EnumLetter>();
		ArrayList<int[]> arrspots = new ArrayList<int[]>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (moddedArray[i][j] != null) {
					arrspots.add(new int[] {i,j});
				}
			}	
		}

		int colstart = 0;
		int rowstart = 0;
		int colstop = 0;
		int rowstop = 0;
		for (int i = 0; i < arrspots.size(); i++) {
			int colComp = arrspots.get(i)[0];
			int rowComp = arrspots.get(i)[1];
			if (i == 0) {
				colstart = colComp;
				rowstart = rowComp;
			}
			if(i == arrspots.size() - 1) {
				colstop = colComp;
				rowstop = rowComp;
			}
		}
		
		//colstop/rowstop will ALWAYS be larger
		for (int i = colstart; i < colstop; i++) {
			for (int j = rowstart; j < rowstop; j++) {
				arrletter.add(moddedArray[i][j]);
			}
		}
		
		//check for a tile on either end and append to end of array
		boolean check = isHorizVerti(arrletter);
		
		return (EnumLetter[]) arrletter.toArray();
	}
	
	private boolean isHorizVerti(ArrayList<EnumLetter> arrletter) {
		
		return false;
	}

	/**
	 * returns the score from an array of tiles gathered
	 * 
	 * @param tiles
	 * @return
	 */
	public int getScore(EnumLetter[] tiles) {
		int out = 0;
		for (EnumLetter ti : tiles) {
			out += ti.score();
		}
		return out;
	}
	
	/**
	 * returns the word of the array of tiles sent
	 * 
	 * @param tiles
	 * @return
	 */
	public String getWord(EnumLetter[] tiles) {
		String out = "";
		for (EnumLetter ti : tiles) {
			out += ti.letter();
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
	public EnumLetter[] compileIterativeWord(String hv, int rowCol, int start, int end) {
		if(hv == "h") {
			EnumLetter[] out = new EnumLetter[end - start];
			for (int j = 0; j < ScrabbleBabble.board.grid[rowCol].length; j++) {
				if (j >= start && j <= end) {
					out[j-start] = ScrabbleBabble.board.grid[rowCol][j].occupant.letter;			
				}
			}
			return out;
		} else if (hv == "v" ) {
			EnumLetter[] out = new EnumLetter[end - start];
			for (int j = 0; j < ScrabbleBabble.board.grid.length; j++) {
				if (j >= start && j <= end) {
					out[j-start] = ScrabbleBabble.board.grid[j][rowCol].occupant.letter;			
				}
			}
			return out;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
}
