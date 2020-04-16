package scrabblebabble.game;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import scrabblebabble.ScrabbleBabble;
import scrabblebabble.handlers.util.EnumLetter;

public class LetterTile {

	EnumLetter letter;
	private final short uid;
	
	public StackPane renderingPane;
	public ImageView renderingView;
	public Image renderingImage;
	
	public int x;
	public int y;
	
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
		File aImage = new File(System.getProperty("user.dir") + "/letters/" + letter.letter().toLowerCase() + ".png");
		this.renderingImage = new Image(aImage.toURI().toString());
		this.renderingView = new ImageView(this.renderingImage);
		this.renderingView.setFitHeight(45);
		this.renderingView.setFitWidth(45);
		this.renderingPane = new StackPane();
		this.renderingPane.setAlignment(Pos.CENTER);
		
		this.renderingPane.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        //drag was detected, start a dragdrop
		        // allow any transfer mode
		        Dragboard db = renderingPane.startDragAndDrop(TransferMode.ANY);
		        
		        // Put a string on a dragboard
		        ClipboardContent content = new ClipboardContent();
		        
		        //TODO BIGGGGG TODO ================================================
		        int[] coord = new int[] {x,y};
		        content.put(ScrabbleBabble.tilesFormat, coord);
		        db.setContent(content);
		        //TODO BIGGGGG TODO  =================================================
		        
		        event.consume();
		    }
		});
		
		this.renderingPane.getChildren().add(this.renderingView);
	}
	
	/**
	 * gives the stackpane containing the image for this tile for rendering purpose
	 * @return
	 */
	public StackPane getRenderPane() {
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
}
