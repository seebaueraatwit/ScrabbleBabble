package scrabblebabble.game;

import java.util.ArrayList;

public class TileBag {

	ArrayList<LetterTile> tiles = new ArrayList<LetterTile>(100);
	
	public TileBag() {
		
	}
	
	/**
	 * initializes the bag with all 100 tiles
	 */
	public void initBag() {
		// TODO
		System.out.println("init bag");
	}
	
	/**
	 * suffles the bag
	 */
	public void randomize() {
		// TODO
	}
	
	/**
	 * draws a random index and sets null in place, skips if draws null until
	 * it finds a populated spot? (could be more efficient idk)
	 */
	public void drawRandom() {
		// TODO
	}
}
