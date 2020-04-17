package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.handlers.util.EnumLetter;
import scrabblebabble.render.TilePane;

public class TileBag {

	ArrayList<TilePane> tiles;
	
	public TileBag() {
		tiles = new ArrayList<TilePane>();
		initBag();
	}
	
	/**
	 * initializes the bag with all 100 tiles using the EnumLetter information
	 */
	public void initBag() {
		
		tiles.clear();
		
		for (EnumLetter current : EnumLetter.values()) {
			for (int j = 0; j < current.quantity(); j++) {
				tiles.add(ScrabbleBabble.instance.getGeneratedTilePane(current, current.id() * 100 + j));
			}
		}
		
		System.out.println("init bag " + tiles.size());
	}
	
	
	
	/**
	 * draws a random index and removes the tile from the ArrayList as well then sends the LetterTile 
	 */
	public TilePane drawRandom(int index) {
		Random rand = new Random();	
		TilePane out = ScrabbleBabble.instance.getGeneratedTilePane(null, -1);
		System.out.println("Before Draw: " + tiles.size());
		if (tiles.size() - 1 > 0) {
			int r = rand.nextInt(tiles.size() - 1);
			out = tiles.get(r);
			tiles.remove(r);
			ScrabbleBabble.board.moveTo(out, index, 0, 0);
		} else if (tiles.size() == 1) {
			out = tiles.get(0);
			tiles.clear();
			ScrabbleBabble.board.moveTo(out, index, 0, 0);
		}
		System.out.println("After Draw: " + tiles.size());
		return out;
	}

	public boolean isBagEmpty() {
		return tiles.size() <= 0;
	}

	public void updateTilesLeft(Label tiles_label) {
		tiles_label.setText("Tiles Remaining: " + tiles.size() + "/100");
	}
}
