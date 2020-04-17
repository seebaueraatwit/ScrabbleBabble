package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

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
		
		for (int i = 0; i < 27; i++) {
			EnumLetter current = EnumLetter.getById(i);
			for (int j = 0; j < current.quantity() * 4; j++) {
				tiles.add(ScrabbleBabble.instance.getGeneratedTilePane(current, i * 100 + j));
			}
		}
		
		System.out.println("init bag " + tiles.size());
	}
	
	
	
	/**
	 * draws a random index and removes the tile from the ArrayList as well then sends the LetterTile 
	 */
	public TilePane drawRandom(int index) {
		Random rand = new Random();	
		int r = rand.nextInt(tiles.size() - 1);
		TilePane out = tiles.get(r);
		tiles.remove(r);
		ScrabbleBabble.board.moveTo(out, index, 0, 0);
		return out;
	}
}
