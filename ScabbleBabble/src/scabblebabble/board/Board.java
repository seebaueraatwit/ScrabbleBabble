package scabblebabble.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import scabblebabble.handlers.util.EnumEffect;

public class Board {

	public BoardTile[][] grid = new BoardTile[15][15];
	
	//Deque<BoardTile> tiles = new LinkedList<BoardTile>();
	
	
	public Board() {
		Load();
		Fill();
	}
	
	//TODO
	/**
	 * Loads the tiles file and any other necesary loading files, subroutine of constructor
	 */
	public void Load() {
		try {
			Scanner scnr = new Scanner(new File(System.getProperty("user.dir") + "/tiles.txt"));
			int xF = 0;
			int yF = 0;
			String bonF = "";
			BoardTile tileF;
			while (scnr.hasNext()) {
				xF = scnr.nextInt();
				yF = scnr.nextInt();
				bonF = scnr.next();
				tileF = new BoardTile(xF, yF, null, parseBonus(bonF));
				grid[xF][yF] = tileF;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Fills the array of board tiles using another method to get the desired board tile bonus for that space
	 * 
	 */
	public void Fill() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (grid[i][j] == null) {
					grid[i][j] = new BoardTile(i,j);
				}
			}
		}
	}

	/**
	 * Takes and index for a board tile and returns grid tile
	 * @param i
	 * @param j
	 * @return
	 */
	private BoardTile getBoardTile(int i, int j) {
		return grid[i][j];
	}
	
	
	/**
	 * parses a string into a bonus for tiles
	 * @param bonIn
	 * @return
	 */
	public EnumEffect parseBonus(String bonIn) {
		switch (bonIn) {
			case "DL":
				return EnumEffect.DOUBLE_LETTER;
			case "TL":
				return EnumEffect.TRIPLE_LETTER;
			case "DW":
				return EnumEffect.DOUBLE_WORD;
			case "TW":
				return EnumEffect.TRIPLE_WORD;
			case "ST":
				return EnumEffect.START;
			default:
				return EnumEffect.EMPTY;
		
		}
	}
}
