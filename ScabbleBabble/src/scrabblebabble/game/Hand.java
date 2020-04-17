package scrabblebabble.game;

import java.util.ArrayList;

import scrabblebabble.ScrabbleBabble;
import scrabblebabble.render.TilePane;

public class Hand {

	public ArrayList<TilePane> content;
	
	public Hand() {
		content = new ArrayList<TilePane>(7);
	}
	
	
	/**
	 * grabs 7 random tiles from the bag, used on game start
	 */
	public void applyRandom() {
		// TODO
		for (int i = 0; i < 7; i++ ) {
			content.add(i, ScrabbleBabble.tile_bag.drawRandom(i));
		}
		System.out.println("RANDOMIZED HAND");
	}
	
	/**
	 * refills hand to 7 at the end of the turn
	 */
	public void refreshHand() {
		//TODO
	}
}
