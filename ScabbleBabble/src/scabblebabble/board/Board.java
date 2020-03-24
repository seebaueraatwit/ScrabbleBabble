package scabblebabble.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {

	public BoardTile[][] grid = new BoardTile[15][15];
	
	public static Board instance = new Board();
	
	Deque<BoardTile> tiles = new LinkedList<BoardTile>();
	
	
	
	public Board() {
		Load();
		Fill();
	}
	
	//TODO
	public void Load() {
		try {
			Scanner scnr = new Scanner(new File(System.getProperty("user.dir") + "/tiles.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void Fill() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				grid[i][j] = getBoardTile(i, j);
			}
		}
	}

	private BoardTile getBoardTile(int i, int j) {
		
		return null;
	}
}
