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
	EnumLetter letter;
	private final short uid;
	
	public TilePane renderingPane;
	
	public int x;
	public int y;
	public int handIndex;
	
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
		
		this.renderingPane = getGeneratedTilePane(this);
		
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
		this.x = xIn;
		this.y = yIn;
		this.handIndex = handIn;
	}
	
	public static TilePane getGeneratedTilePane(LetterTile tIn) {
		TilePane p = new TilePane(tIn);
		if (tIn != null) {
			File aImage = new File(System.getProperty("user.dir") + "/letters/" + tIn.getLetter().toLowerCase() + ".png");
			Image renderingImage = new Image(aImage.toURI().toString());
			ImageView renderingView = new ImageView(renderingImage);
			renderingView.setFitHeight(45);
			renderingView.setFitWidth(45);
			p.getChildren().add(renderingView);
		}
		p.setAlignment(Pos.CENTER);		
		p.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent e) {
		    	Dragboard db = ((Node)e.getSource()).startDragAndDrop(TransferMode.ANY);
				if (p.held != null) {
					ArrayList<Integer> a = new ArrayList<Integer>(); 
//					a.add(xdest);
//					a.add(ydest);
//					a.add(LetteTile b);
					
					int x = 0;
					int y = 0;
					int isHand = -1;
					
					if (true) {
						isHand = p.held.handIndex;
						x = p.held.x;
						y = p.held.y;
					}
					
					a.add(x);
					a.add(y);
					a.add(isHand);


					ClipboardContent content = new ClipboardContent();
					content.put(ScrabbleBabble.tilesFormat, a);
					db.setContent(content);
					
					System.out.println("Started Drag");
				}
		        e.consume();
		    }
		});
		
		p.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent e) {
		    	if (e.getGestureSource() != p) {
		            e.acceptTransferModes(TransferMode.ANY);
		        }

				System.out.println("Dragging");
				
		        e.consume();
		    }
		});
		
		p.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    
		         event.consume();
		    }
		});
		
		p.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    
		         event.consume();
		    }
		});
		
		p.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent e) {
		    	Dragboard db = e.getDragboard();
		        boolean success = false;
		        System.out.println("onDragDrop fired");
		        ArrayList<Integer> dragged;
		    	if (db.hasContent(ScrabbleBabble.tilesFormat)) {
		    		success = true;
		    		dragged = (ArrayList<Integer>) db.getContent(ScrabbleBabble.tilesFormat);
		    		int xfrom = dragged.get(0);
		    		int yfrom = dragged.get(1);
		    		int handFrom = dragged.get(2);
		    		int xto;
		    		int yto;
		    		int handto;
		    		ScrabbleBabble.board.moveToFrom(handFrom, xfrom, yfrom, p.held.x, p.held.y, p.held.handIndex);
		    		System.out.println("End Drag: " + xfrom + yfrom + handFrom + p.held.x + p.held.y + p.held.handIndex);
		    	}
		    	
		        e.setDropCompleted(success);
		        e.consume();
		    }
		});
		
		p.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent e) {
		    	TransferMode modeUsed = e.getTransferMode();
		    	 
		        if (modeUsed == TransferMode.MOVE) 
		        {
		        	System.out.println("Done");
		        }
		    	e.consume();
		    }
		});
		
		return p;
	}
}
