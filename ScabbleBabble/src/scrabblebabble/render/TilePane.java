package scrabblebabble.render;

import javafx.scene.layout.StackPane;
import scrabblebabble.game.LetterTile;

public class TilePane extends StackPane {

	public LetterTile held;
	
	
	public TilePane(LetterTile holderIn) {
		held = holderIn;
	}
	
}
