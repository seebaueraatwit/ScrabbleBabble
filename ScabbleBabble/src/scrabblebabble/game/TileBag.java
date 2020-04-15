package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

import scrabblebabble.handlers.util.EnumLetter;

public class TileBag {

	ArrayList<LetterTile> tiles = new ArrayList<LetterTile>();
	
	public TileBag() {
		initBag();
	}
	
	/**
	 * initializes the bag with all 100 tiles
	 * 
	 * for a list of all needed tile, google, i suggest making a file 
	 * that contains all the tile definitions and loading that so its easier
	 */
	public void initBag() {
		// TODO
		tiles.add(new LetterTile(EnumLetter.A, 0));
		tiles.add(new LetterTile(EnumLetter.B, 1));
		tiles.add(new LetterTile(EnumLetter.C, 1));
		tiles.add(new LetterTile(EnumLetter.D, 1));
		System.out.println("init bag");
	}
	
	/**
	 * suffles the bag
	 */
	@Deprecated
	public void randomize() {
		// TODO
	}
	
	/**
	 * draws a random index and sets null in place, skips if draws null until
	 * it finds a populated spot? (could be more efficient idk)
	 */
	public LetterTile drawRandom() {
		//TODO
		Random rand = new Random();	
		int r = rand.nextInt(tiles.size() - 1);
		LetterTile out = tiles.get(r);
		tiles.remove(r);
		return out;
	}
}
