package scrabblebabble.turn;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.game.Player;
import scrabblebabble.handlers.util.WordManager;

public class TurnHandler {

	
	public int currentPlayer = 0;
	public int turnCount;
	public int numPlayers;
	
	public Player[] players;
	
	public TurnHandler() {
		
	}
	
	/**
	 * runs the next turn cycle, passes grid to scorer
	 * @param grid
	 */
	public void nextTurnCycle(GridPane grid, FlowPane labelToMod, Label winner_label, Button pass_button) {
		// check for empty hand to add 50 points
		boolean bonus = false;
		int scoreToAdd = 0;
		
		
		//refill hand
		getCurrentPlayer().hand.refreshHand();
		
		scoreToAdd += WordManager.instance.calcScore(grid, labelToMod, bonus);
		this.getCurrentPlayer().addScore(scoreToAdd);
		
		((Label) labelToMod.getChildren().get(currentPlayer)).setText("Player " + (currentPlayer + 1) + " Score: " + this.getCurrentPlayer().getScore());
		
		//if hand is still empty and bag is empty end game and declare winner
		if (getCurrentPlayer().hand.isHandEmpty() && ScrabbleBabble.tile_bag.isBagEmpty()) {
			end(winner_label, pass_button);
			return;
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
	public boolean end(Label winner_label, Button pass_button) {
		//System.out.println("ENDING GAME");
		pass_button.setDisable(true);
		winner_label.setText("Player " + (currentPlayer + 1) + " Wins!!  With " + getCurrentPlayer().getScore() + "Points!!!");
		return true;
	}
	
	/**
	 * return current player instance for hand collection.
	 * @return
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
}
