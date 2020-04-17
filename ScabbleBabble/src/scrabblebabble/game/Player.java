package scrabblebabble.game;

public class Player {

	public Hand hand;
	public int id;
	public int score;
	
	public Player(int idIn)
	{
		hand = new Hand();
		hand.applyRandom();
		id = idIn;
		resetScore();
		//System.out.println("Made Player: " + id);
	}
	
	/**
	 * Adds the amount to the players score
	 * @param toAdd
	 */
	public void addScore(int toAdd) {
		this.score += toAdd;
	}
	
	/**
	 * returns the current score
	 * @return
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * sets the score to zero
	 */
	public void resetScore() {
		this.score = 0;
	}
}
