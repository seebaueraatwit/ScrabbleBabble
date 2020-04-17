package scrabblebabble.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.handlers.util.EnumEffect;
import scrabblebabble.render.TilePane;

public class Board {

	public BoardTile[][] grid = new BoardTile[15][15];
	
	//Deque<BoardTile> tiles = new LinkedList<BoardTile>();
	
	
	public Board() {
		Load();
		Fill();
	}
	
	//
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
				System.out.println("Loaded Tile from bonus File: " + tileF.toString());
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
		BoardTile t = null;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (grid[i][j] == null) {
					t = new BoardTile(i,j);
					grid[i][j] = t;
					System.out.println("Loaded Tile from bonus File: " + t.toString());
					t = null;
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
	
	/**
	 * Set a tile in the board from externally
	 * @param x
	 * @param y
	 * @param t
	 */
	public void setTile(int x, int y, TilePane t) {
		grid[y][x].occupant = t;
	}

	/**
	 * Moves a tile from given spot in either hand or board to another in the hand or board
	 * @param hand
	 * @param xstart
	 * @param ystart
	 * @param xend
	 * @param yend
	 */
	public void moveToFrom(int handfrom, int xstart, int ystart, int handto, int xend, int yend, GridPane grid, StackPane[] handPanes) {
		//TODO
		
		TilePane from;
		TilePane to;
		
//		if (!isSlotEmpty(xend, yend, grid)) {
//			System.out.println("Slot is not empty");
//			return;
//		}
		
		//get to swap
		if(handfrom == -1) {
			from = (TilePane) removeNodeByRowColumnIndex(ystart, xstart, grid);
			System.out.println("1 | from:empty=" + from.empty);
		} else {
			from = (TilePane) ScrabbleBabble.turn_handler.getCurrentPlayer().hand.removeFromHand(handfrom);
			System.out.println("2 | from:empty=" + from.empty);
		}

		if(handto == -1) {
			to = (TilePane) removeNodeByRowColumnIndex(yend, xend, grid);
			System.out.println("3 | to:empty=" + to.empty);
		} else {
			to = (TilePane) ScrabbleBabble.turn_handler.getCurrentPlayer().hand.removeFromHand(handto);
			System.out.println("4 | to:empty=" + to.empty);
		}
		

		//swap
		if(handto == -1) {
			ScrabbleBabble.board.moveTo(from, -1, xend, yend);
			grid.add(from, xend, yend);
			System.out.println("5");
		} else {
			ScrabbleBabble.board.moveTo(from, handto, 0, 0);
			ScrabbleBabble.board.moveToHand(from, handto, handPanes);
			ScrabbleBabble.turn_handler.getCurrentPlayer().hand.placeInHand(from, handto);
			System.out.println("6");
		}

		
		if(handfrom == -1) {
			ScrabbleBabble.board.moveTo(to, -1, xstart, ystart);
			grid.add(to, xstart, ystart);
			System.out.println("7");
		} else {
			ScrabbleBabble.board.moveTo(to, handfrom, 0, 0);
			ScrabbleBabble.board.moveToHand(to, handfrom, handPanes);
			ScrabbleBabble.turn_handler.getCurrentPlayer().hand.placeInHand(to, handfrom);;
			System.out.println("8");
		}
		
		System.out.println("Moved to new spot");
	}
	
	/**
	 * Sets the x and y and hand coord, mostly for initializing
	 * @param handto
	 * @param xto
	 * @param yto
	 */
	public void moveTo(TilePane tIn, int handto, int rowto, int colto) {
		tIn.x = rowto;
		tIn.y = colto;
		tIn.handIndex = handto;
	}
	
	/**
	 * sets the TilePane into the active hand, used by gridmovement
	 * @param tIn
	 * @param handto
	 */
	public void moveToHand(TilePane tIn, int handto, StackPane[] sIn) {
		if(handto != -1) {
			sIn[handto].getChildren().clear();
			sIn[handto].getChildren().add(tIn);
			System.out.println("Hand");
		}
	}
	
	/**
	 * Uses row and collumn to get object in Gridpane, used for getting children when moving
	 * @param row
	 * @param column
	 * @param gridPane
	 * @return
	 */
	public static Node getNodeByRowColumnIndex(int row, int column, GridPane grid) {
	    Node result = null;
	    ObservableList<Node> children = grid.getChildren();

	    for (Node node : children) {
	        if(grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	/**
	 * checks if the spot in the gridpane is an empty slot
	 * @param row
	 * @param column
	 * @param gridPane
	 * @return
	 */
	public static boolean isSlotEmpty(int row, int column, GridPane grid) {
		return ((TilePane) getNodeByRowColumnIndex(row, column, grid)).empty;
	}
	
	/**
	 * removes a node from the slot given, only removes one from that location, but one one should be there at any given time.
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	public Node removeNodeByRowColumnIndex(int row, int column, GridPane grid) {
		Node n = getNodeByRowColumnIndex(row, column, grid);
		grid.getChildren().remove(n);
		return n;
	}
}
