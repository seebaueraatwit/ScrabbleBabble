package scrabblebabble.game;

public class Player {

	public Hand hand;
	public int id;
	
	public Player(int idIn)
	{
		hand = new Hand();
		hand.applyRandom();
		id = idIn;
		System.out.println("Made Player: " + id);
	}
}
