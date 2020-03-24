package scabblebabble.board;

import scabblebabble.game.LetterTile;
import scabblebabble.handlers.util.EnumEffect;

public class BoardTile {

	public LetterTile occupant;	
	public final EnumEffect effect;
	
	public BoardTile() {
		this(null, EnumEffect.EMPTY);
	}
	
	public BoardTile(LetterTile occIn, EnumEffect effIn) {
		this.occupant = null;
		this.effect = effIn;
	}
	
	
}
