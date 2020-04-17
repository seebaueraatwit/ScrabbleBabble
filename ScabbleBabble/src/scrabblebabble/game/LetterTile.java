package scrabblebabble.game;

import java.io.File;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.handlers.util.EnumLetter;
import scrabblebabble.render.TilePane;

public class LetterTile {

	private Object object;
	public EnumLetter letter;
	private final short uid;
	
	public TilePane renderingPane;
	
	public LetterTile(EnumLetter letterIn, int iIn) {
		this.letter = letterIn;
		this.uid = genUID(iIn);
		loadImage();
	}
	
	/**
	 * loads appropriate image from file, saves various resources for when needing to populate gridpane
	 * 
	 */
	private void loadImage() {
		
		this.renderingPane = ScrabbleBabble.instance.getGeneratedTilePane(this);
		
	}
	
	/**
	 * gives the stackpane containing the image for this tile for rendering purpose
	 * @return
	 */
	public TilePane getRenderPane() {
		return this.renderingPane;
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
	
	@Override
	public String toString() {
		return "\'" + getLetter() + "\'";
	}
	
	public void setTilePosition(int xIn, int yIn, int handIn) {
		this.renderingPane.x = xIn;
		this.renderingPane.y = yIn;
		this.renderingPane.handIndex = handIn;
	}
	
	
}
