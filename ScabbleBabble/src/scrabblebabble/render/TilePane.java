package scrabblebabble.render;

import javafx.scene.layout.StackPane;
import scrabblebabble.game.LetterTile;

public class TilePane extends StackPane {

	public LetterTile held;
	
	public int x;
	public int y;
	public int handIndex;
	
	public boolean empty;
	
	
	public TilePane(LetterTile holderIn) {
		held = holderIn;
		empty = holderIn != null ? false : true; 
	}
	
	
	
}
