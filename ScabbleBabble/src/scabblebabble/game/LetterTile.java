package scabblebabble.game;

import scabblebabble.handlers.util.EnumLetter;

public class LetterTile {

	EnumLetter letter;
	private final short uid;
	
	public LetterTile(EnumLetter letterIn, int iIn) {
		this.letter = letterIn;
		this.uid = genUID(iIn);
	}
	
	private short genUID(int i) {
		return (short) (this.letter.letter().charAt(0) + letter.id() + letter.score() + i);
	}
	
	public boolean compareUID(int uidIn) {
		return this.uid == uidIn;
	}
	
	public int getScore() {
		return letter.score();
	}
	
	public String getLetter() {
		return letter.letter();
	}
}
