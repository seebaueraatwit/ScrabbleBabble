package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

/*
 * Covers all tile movement for this program
 */
public class TileMovement {
	
	 ArrayList<Player> playerHand = new ArrayList<Player>();
	 
	 //letter NEEDS TO BE CHANGED BUT CANT FIGURE OUT HOW TO CALL FROM OTHER CLASS
	 ArrayList<Character> letter = new ArrayList<Character>();
	 
	/*
	 * Places tile from hand to board
	 */
	
	public void placeTile (int column, int row, int number) {
		 if(row<0||row>15||column<0||column>15) {
			    return;
		 }
		 
		 if(Board[row][column]==' ') {
			 if(playerHand.get(number)!=' ') {
				 
			 }
		 }
	}
	
	/*
	 * Refills player's hand whenever there is less seven tiles in the players hand
	 */
	
	public void moveToHand() {
		for(int i=0; i<playerHand.size(); i++) {
			while(playerHand.size()!=7) {
				int random=randomTile(letter.size());
				playerHand.add(letter.get(random));
				letter.remove(random);
			}
		}
	}
	
	public int randomTile(int max) {
		Random r= new Random();
		return r.nextInt(max);
	}
}
