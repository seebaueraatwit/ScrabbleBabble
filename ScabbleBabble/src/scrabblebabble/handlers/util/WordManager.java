package scrabblebabble.handlers.util;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.board.Board;
import scrabblebabble.render.TilePane;

public class WordManager {

	EnumLetter[][] lastboard = new EnumLetter[15][15];
	EnumLetter[][] newboard;
	
	public static final WordManager instance = new WordManager();

		
	public int calcScore(GridPane grid, FlowPane labelToMod, boolean bonus) {
		EnumLetter[][] gridnew = getNewGrid(grid);
		EnumLetter[][] modifiedGrid = checkChanges(grid);
		LinkedList<Letter> newWord = extractNewWord(modifiedGrid, gridnew);
		int scoreOut = getScore(newWord);
		
		if (ScrabbleBabble.turn_handler.getCurrentPlayer().hand.isHandEmpty() && newWord.size() == 7) {
			bonus = true;
			scoreOut += 50;
		}
		
		((Label) labelToMod.getChildren().get(5)).setText("Latest Word : \"" + WordManager.instance.getWord(newWord) + "\" \nWith score = " + scoreOut  + (bonus ? "\nWith +50 Bonus!" : ""));
		//System.out.println(newWord.toString());
		return scoreOut;
	}
	
	public EnumLetter[][] getNewGrid(GridPane gridnew) {
		EnumLetter[][] e = new EnumLetter[15][15];
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				TilePane t = (TilePane) Board.getNodeByRowColumnIndex(i, j, gridnew);
				//System.out.print(t.letter);
				e[i][j] = t.letter;
			}
			//System.out.println("\n");
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
	 * extracts the word in the blank array above and puts into a an array with all corresponding spots
	 *  
	 * TODO
	 * 
	 * @param newgrid
	 * @param moddedArray
	 * @return
	 */
	public LinkedList<Letter> extractNewWord(EnumLetter[][] moddedArray, EnumLetter[][] newgrid) {
		ArrayList<EnumLetter> arrletter = new ArrayList<EnumLetter>();
		ArrayList<int[]> arrspots = new ArrayList<int[]>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				System.out.print(moddedArray[i][j]);
				if (moddedArray[i][j] != null) {
					arrspots.add(new int[] {i,j});
				}
			}
			System.out.printf("%n");
		}
		
		System.out.println("\n ============================================================== \n");

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				System.out.print(newgrid[i][j]);
			}
			System.out.printf("%n");
		}
		
		int colstart = 0;
		int rowstart = 0;
		int colstop = 0;
		int rowstop = 0;
		for (int i = 0; i < arrspots.size(); i++) {
			int rowComp = arrspots.get(i)[0];
			int colComp = arrspots.get(i)[1];
			//System.out.println("(" + rowComp + ", " + colComp + ")");
			if (i == 0) {
				colstart = colComp;
				rowstart = rowComp;
			}
			if(i == arrspots.size() - 1) {
				colstop = colComp;
				rowstop = rowComp;
			}
			
		}
		LinkedList<Letter> arr = new LinkedList<Letter>();
		if(!arrspots.isEmpty()) {
			System.out.println("(" + rowstart + ", " + colstart + ") -> " + "(" + rowstop + ", " + colstop + ")");
			//colstop/rowstop will ALWAYS be larger
			for (int i = colstart; i <= colstop; i++) {
				for (int j = rowstart; j <= rowstop; j++) {
					//System.out.print(moddedArray[j][i]);
					arr.add(new Letter(newgrid[j][i], i, j));
				}
				//System.out.println("");
			}
			
			//check for a tile on either end and append to end of array
			boolean vertical = false;
			if(colstart == colstop) {
				System.out.println("vertial");
				vertical = true;
			} else if (rowstart == rowstop) {
				System.out.println("horizontal");
				vertical = false;
			}
			
			if(vertical) {
				if (rowstart - 1 >= 0) {
					if(newgrid[rowstart-1][colstart] != null) {
						//System.out.println(newgrid[rowstart-1][colstart]);
						arr.addFirst(new Letter (newgrid[rowstart-1][colstart], colstart, rowstart-1));
					}
				}
				if (rowstop+1 < 15) {
					if(newgrid[rowstop+1][colstart] != null) {
						//System.out.println(newgrid[rowstop+1][colstart]);
						arr.addLast(new Letter(newgrid[rowstop+1][colstart], colstart, rowstop+1));		
					}
				}
			} else {
				if (colstart-1 >= 0) {
					if(newgrid[rowstart][colstart-1] != null) {
						//System.out.println(newgrid[rowstart][colstart-1]);
						arr.addFirst(new Letter(newgrid[rowstart][colstart-1], colstart-1, rowstart));
					}
				}
				if (colstop+1 < 15) {
					if(newgrid[rowstart][colstop+1] != null) {
						//System.out.println(newgrid[rowstop][colstart+1]);
						arr.addLast(new Letter(newgrid[rowstart][colstop+1], colstop+1, rowstart));	
					}
				}
			}
			
			for (int i = 0; i < arr.size(); i++) {
				
			}
//			
//			for(int i = 0; i < arrspots.size(); i++) {
//				Letter l = new Letter(arrletter.get(i), arrspots.get(i)[0], arrspots.get(i)[1]);
//				arr.add(l);
//			}
		}
		
		
		
//		
		
		return arr;
	}
	
	

	/**
	 * returns the score from an array of tiles gathered
	 * 
	 * @param tiles
	 * @return
	 */
	public int getScore(LinkedList<Letter> tiles) {
		int out = 0;
		int factor = 1;
		for (Letter ti : tiles) {
			int toAdds = ti.getLetter().score();
			EnumEffect e = ScrabbleBabble.board.grid[ti.getRow()][ti.getCol()].effect;
			System.out.println(ScrabbleBabble.board.grid[ti.getRow()][ti.getCol()].isUsed());
			if (!ScrabbleBabble.board.grid[ti.getRow()][ti.getCol()].isUsed()) {
				if (e == EnumEffect.DOUBLE_LETTER || e == EnumEffect.TRIPLE_LETTER) {
					toAdds *= e.level;
				} else {
					factor *= e.level;
				}
				ScrabbleBabble.board.grid[ti.getRow()][ti.getCol()].used();
			}
			out += toAdds;
			//System.out.println(ti.getLetter().letter());
		}
		return out * factor;
	}
	
	/**
	 * returns the word of the arraylist of letters sent
	 * 
	 * @param tiles
	 * @return
	 */
	public String getWord(LinkedList<Letter> newWord) {
		String out = "";
		for (Letter ti : newWord) {
			System.out.print(ti.getLetter().letter());
			out += ti.getLetter().letter();
		}
		return out;		
	}	
	
	static class Letter {
		
		EnumLetter letter;
		int row;
		int col;
		
		protected Letter(EnumLetter letterIn, int colIn, int rowIn) {
			this.letter = letterIn;
			this.row = rowIn;
			this.col = colIn;
		}
		
		protected EnumLetter getLetter() {
			return this.letter;
		}
		
		protected int getRow() {
			return row;
		}
		
		protected int getCol() {
			return col;
		}
	}
}
