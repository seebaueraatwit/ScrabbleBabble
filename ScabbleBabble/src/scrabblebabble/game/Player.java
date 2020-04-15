package scrabblebabble.game;

public class Player {

	public Hand hand;
	
	public Player()
	{
		hand = new Hand();
		hand.applyRandom();
	}
}
