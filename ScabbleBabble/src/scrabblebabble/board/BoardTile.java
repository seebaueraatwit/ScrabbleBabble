package scrabblebabble.board;

import scrabblebabble.game.LetterTile;
import scrabblebabble.handlers.util.EnumEffect;

public class BoardTile {

	public LetterTile occupant;	
	public final EnumEffect effect;
	
	public final int x;
	public final int y;
	
	public BoardTile(int xIn, int yIn) {
		this(xIn, yIn, null, EnumEffect.EMPTY);
	}
	
	public BoardTile(int xIn, int yIn, LetterTile occIn, EnumEffect effIn) {
		this.occupant = null;
		this.effect = effIn;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public String toString() {
		return "Space (" + x + "," + y + "), Bonus: " + effect.name() + ", Occupant: " + (occupant != null ? occupant.toString() : "null");		
	}
	
}
