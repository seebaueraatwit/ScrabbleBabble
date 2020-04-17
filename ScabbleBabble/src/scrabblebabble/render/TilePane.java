package scrabblebabble.render;

import javafx.scene.layout.StackPane;
import scrabblebabble.handlers.util.EnumLetter;

public class TilePane extends StackPane {
	
	public int x;
	public int y;
	public int handIndex;
	
	public boolean empty;

	public EnumLetter letter;
	private final short uid;
		
	public TilePane(EnumLetter letterIn, int iIn) {
		this.letter = letterIn;
		this.uid = genUID(iIn);
		
		empty = letterIn != null ? false : true; 
	}
	
	/**
	 * Generates a uid for the tile
	 * @param i
	 * @return
	 */
	@Deprecated
	private short genUID(int i) {
		short uidout = (short) i;
		if (this.letter != null) {
			uidout += this.letter.letter().charAt(0) + letter.id() + letter.score();
		}
		return uidout;
	}
	/**
	 * compares two uids
	 * @param uidIn
	 * @return
	 */
	@Deprecated
	public boolean compareUID(int uidIn) {
		return this.uid == uidIn;
	}
	
	/**
	 * returns the enumLetter score value held
	 * @return
	 */
	public int getScore() {
		return letter.score();
	}
	
	/**
	 * returns the enumLetter letter value held
	 * @return
	 */
	public String getLetter() {
		return letter.letter();
	}
	
	
	@Override
	public String toString() {
		return "\'" + getLetter() + "\'";
	}	
	
	
}
