package scrabblebabble.turn;

import scrabblebabble.ScrabbleBabble;
import scrabblebabble.game.Player;

public class TurnHandler {

	
	public int currentPlayer = 0;
	public int turnCount;
	public int numPlayers;
	
	public Player[] players;
	
	public TurnHandler() {
		
	}
	
	/**
	 * goes to the next turn
	 */
	public void nextTurnCycle() {
		// refill hand
		int scoreToAdd = 0;
		getCurrentPlayer().hand.refreshHand();
		
		if (getCurrentPlayer().hand.isHandEmpty() && ScrabbleBabble.tile_bag.isBagEmpty()) {
			end();
		}
		
		// cycle hand
		currentPlayer++;
		if (currentPlayer >= numPlayers) {
			currentPlayer = 0;
		}
	}
	
	/**
	 * Ends the game, run when a player's hand is detected as empty.
	 */
	public void end() {
		System.out.println("ENDING GAME");
	}
	
	/**
	 * return current player instance for hand collection.
	 * @return
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
}
