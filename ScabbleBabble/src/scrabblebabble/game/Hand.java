package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Deque;

import scrabblebabble.ScrabbleBabble;

public class Hand {

	public ArrayList<LetterTile> content = new ArrayList<LetterTile>(7);
	
	
	/**
	 * grabs 7 random tiles from the bag, used on game start
	 */
	public void applyRandom() {
		// TODO
		content.add(ScrabbleBabble.tile_bag.drawRandom());
		System.out.println("RANDOMIZED HAND");
	}
	
	/**
	 * refills hand to 7 at the end of the turn
	 */
	public void refreshHand() {
		//TODO
	}
}
