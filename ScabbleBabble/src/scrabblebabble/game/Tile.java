package scrabblebabble.game;

/*
 * This class represents a tile
 */

public class Tile {
	char letter;
	int value;
	
	public Tile(char letter, int value) {
		this.letter=letter;
		this.value=value;
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String toString() {
		return ""+ letter + "{" + value + "}";
	}
	
	public boolean equals(Object obj) {
		Tile newTile;
		
		if(!(obj instanceof Tile)) {
			return false;
		}else {
			newTile=(Tile)obj;
		}
		
		if(this.letter==newTile.letter) {
			return true;
		}else {
			return false;
		}
	}
}
