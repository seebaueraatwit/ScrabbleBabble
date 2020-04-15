package scrabblebabble.turn;

import scrabblebabble.ScrabbleBabble;
import scrabblebabble.game.Player;

public class TurnHandler {

	
	private int currentPlayer = 0;
	public int turnCount;
	public int numPlayers;
	
	public Player[] players;
	
	/**
	 * goes to the next turn
	 */
	public void nextTurnCycle() {
		if (currentPlayer >= numPlayers) {
			currentPlayer = 0;
		} else {
			currentPlayer++;
		}
		
		ScrabbleBabble.instance.turn_label.setText("Current Player: Player " + (currentPlayer + 1));
		
	}
	
	/** 
	 * starts a new game
	 * @param numPlayersIn
	 */
	public void newGame(int numPlayersIn) {
		 
		players = new Player[numPlayersIn];
		numPlayers = numPlayersIn;
		turnCount = 0;
		currentPlayer = 0;
		ScrabbleBabble.tile_bag.randomize();
		 
		for (int i = 0; i < numPlayers; i++) {
			Player p = new Player(i);
			players[i] = p;
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
