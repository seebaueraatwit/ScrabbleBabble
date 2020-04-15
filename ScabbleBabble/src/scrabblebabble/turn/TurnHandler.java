package scrabblebabble.turn;

import scrabblebabble.game.Player;

public class TurnHandler {

	
	private int currentPlayer = 0;
	public int turnCount;
	public int numPlayers;
	
	public Player[] players;
	
	public void nextTurnCycle() {
		if (currentPlayer >= numPlayers) {
			currentPlayer = 0;
		} else {
			currentPlayer++;
		}
		
	}
	
	public void newGame(int numPlayersIn) {
		 players = new Player[numPlayersIn];
		 numPlayers = numPlayersIn;
		 turnCount = 0;
		 currentPlayer = 0;
		 
	}
	
	public Player getCurrentPlayer() {
		return null;
	}
	
}
