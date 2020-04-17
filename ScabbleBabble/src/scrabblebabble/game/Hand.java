package scrabblebabble.game;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.render.TilePane;

public class Hand {

	public Map<Integer, TilePane> content;
	
	public Hand() {
		content = new HashMap<Integer, TilePane>(7);
	}
	
	
	/**
	 * grabs 7 random tiles from the bag, used on game start
	 */
	public void applyRandom() {
		// TODO
		for (int i = 0; i < 7; i++ ) {
			content.put(i, ScrabbleBabble.tile_bag.drawRandom(i));
		}
		System.out.println("RANDOMIZED HAND");
	}
	
	/**
	 * refills hand to 7 at the end of the turn
	 */
	public void refreshHand() {
		//TODO
		
		for (int i = 0; i < content.size(); i++) {
			if(content.get(i).letter == null) {
				content.replace(i, ScrabbleBabble.tile_bag.drawRandom(i));
				System.out.println("Refilled slot " + i);
				continue;
			}
		}
	}
	
	/**
	 * takes the index and returns the contents
	 * @param index
	 * @return
	 */
	public Node removeFromHand(int index) {
		TilePane n = content.get(index);
		return n;
	}
	
	/**
	 * replace the index with the given TilePane
	 * @param tIn
	 * @param index
	 */
	public void placeInHand(TilePane tIn, int index) {
		content.replace(index, tIn);
		System.out.println("Debug");
	}
	
	public boolean isHandEmpty() {
		boolean check = true;
		for (int i = 0; i < content.size(); i++) {
			if (!content.get(i).empty) {
				check = false;
				break;
			}
		}
		return check;
	}
	
}
