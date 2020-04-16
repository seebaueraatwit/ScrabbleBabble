package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

import scrabblebabble.ScrabbleBabble;

/*
 * Covers all tile movement for this program
 */

public class TileMovement {
	
     //remove and replace with calls to hand class within activeplayer called from TurnHandler
//	 ArrayList<Player> playerHand = new ArrayList<Player>();
	 
	 //letter NEEDS TO BE CHANGED BUT CANT FIGURE OUT HOW TO CALL FROM OTHER CLASS
	 //LetterTile class has this property, call <LetterTile>.getLetter(); from whatever letter you need.
	 //ideally move the LetterTile instances around.
	 ArrayList<Character> letter = new ArrayList<Character>();
	 
	/*
	 * Places tile from hand to board
	 */
	
	
	public void placeTile (int column, int row, LetterTile t) {
		ScrabbleBabble.board.setTile(row, column, t);
//		 if(row<0||row>15||column<0||column>15) {
//			    return;
//		 }
//		 
//		 //to get board, call ScrabbleBabble.board.<>
//		 if(Board[row][column]==' ') {
//			 //use the <Hand> object described above
//			 if(playerHand.get(number)!=' ') {
//				 
//			 }
//		 }
	}
	
	/*
	 * Refills player's hand whenever there is less seven tiles in the players hand
	 */
	
	/**
	 * Check out Hand.java for a few methods regarding this. Treat the tiles as objects rather than letters
	 * It will help understanding of the system
	 */
	public void moveToHand() {
//		for(int i=0; i<playerHand.size(); i++) {
//			while(playerHand.size()!=7) {
//				int random=randomTile(letter.size());
//				playerHand.add(letter.get(random));
//				letter.remove(random);
//			}
//		}
	}
	
	@Deprecated
	public int randomTile(int max) {
		Random r= new Random();
		return r.nextInt(max);
	}
}
