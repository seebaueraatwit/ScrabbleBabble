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
		if (currentPlayer > numPlayers) {
			currentPlayer = 0;
		} else {
			currentPlayer++;
		}
		
	}
	
	
	
	/**
	 * return current player instance for hand collection.
	 * @return
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
}
