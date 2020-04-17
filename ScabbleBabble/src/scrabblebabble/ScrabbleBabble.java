package scrabblebabble;

import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import scrabblebabble.board.Board;
import scrabblebabble.game.Player;
import scrabblebabble.game.TileBag;
import scrabblebabble.handlers.util.EnumLetter;
import scrabblebabble.render.TilePane;
import scrabblebabble.turn.TurnHandler;

public class ScrabbleBabble extends Application implements Initializable {

	public static Board board;
	public static TurnHandler turn_handler;
	public static TileBag tile_bag;
	
	public static ScrabbleBabble instance;
	
	public static Node dragging;
	
	
	
	@FXML public ToolBar toolbar1;
	@FXML public Button game_options_1;
	@FXML public Label turn_label;
	@FXML public Spinner players_input;
		  
	
	@FXML public BorderPane main_layout;
	
	@FXML public StackPane center_stack;
	@FXML public GridPane tiles_organizer;
	
	@FXML public StackPane bottom_stack;
	@FXML public FlowPane bottom_flow;
	@FXML public FlowPane hand_organizer;
		  public StackPane[] hand_containers = new StackPane[7];
	@FXML public StackPane hand_stack;
	
	@FXML public StackPane left_stack;
	@FXML public FlowPane left_organizer;

	@FXML public ImageView scores_background;
	@FXML public ImageView tiles_background;
	@FXML public ImageView hand_background;
	@FXML public Button pass_button;
	
	@FXML public StackPane right_stack;
	@FXML public Label info_label;
	@FXML public StackPane bag_stack;
	@FXML public ImageView bag_image;
	@FXML public Label tiles_label;
	@FXML public Label score_label_1;
	@FXML public Label score_label_2;
	@FXML public Label score_label_3;
	@FXML public Label score_label_4;
	
	public static final DataFormat tilesFormat = new DataFormat("scrabblebabble.tile");
	
	public static void main(String[] args) {
		instance = new ScrabbleBabble();
		board = new Board();
		turn_handler = new TurnHandler();
		tile_bag = new TileBag();
		launch(args);
	}
	
	public ScrabbleBabble() {
		
	}
	
	@Override
	public void start(Stage s) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/scrabblebabble/ScrabbleBabble.fxml"));
		final Pane p = loader.load();
		
//		tilePrefab = new StackPane();
//		ImageView i = new ImageView();
//		tilePrefab.getChildren().add(i);
		
		
		
		s.setScene(new Scene(p));
		s.show();		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//buton press event handlers
		game_options_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newGame((int) players_input.getValueFactory().getValue());
				updateHand(turn_handler.getCurrentPlayer());
				tile_bag.updateTilesLeft(tiles_label);
			}
		});

		pass_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				turn_handler.nextTurnCycle();
				updateHand(turn_handler.getCurrentPlayer());
				tile_bag.updateTilesLeft(tiles_label);
				turn_label.setText("Current Player: Player " + (turn_handler.currentPlayer + 1));
			}
		});
		
		// Add blank TilePanes into the gridpane for temporary null
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				TilePane p = getGeneratedTilePane(null, -1);
				board.moveTo(p, -1, i, j);
				tiles_organizer.add(p, i, j);
				// LetterTile.getGeneratedTilePane(null)
				//tile_bag.drawRandom(-1)
			}
		}
		
		// Load holders for the tile in the hand
		for (int i = 0; i < 7; i++) {
			StackPane sp = new StackPane();
			sp.setPrefSize(101, 101);
			hand_containers[i] = sp;
			hand_organizer.getChildren().add(i, sp);
		}
		
		//tooltip for the spinner
		Tooltip players_input_tooltip = new Tooltip("Number of players playing.");
		players_input.setTooltip(players_input_tooltip);
		players_input.setPromptText("2");
		
		//checks if the key input into editor is a non-numeral
		NumberFormat format = NumberFormat.getIntegerInstance();
		UnaryOperator<TextFormatter.Change> filter = c -> {
		    if (c.isContentChange()) {
		        ParsePosition parsePosition = new ParsePosition(0);
		        // NumberFormat checks the beginning of the text
		        format.parse(c.getControlNewText(), parsePosition);
		        if (parsePosition.getIndex() == 0 ||
		                parsePosition.getIndex() < c.getControlNewText().length()) {
		            // reject parsing the complete text failed
		            return null;
		        }
		    }
		    return c;
		};
		TextFormatter<Integer> playersFormatter = new TextFormatter<Integer>(
		        new IntegerStringConverter(), 4, filter);
		
		players_input.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, Integer.parseInt("4")));
		players_input.setEditable(true);
		players_input.getEditor().setTextFormatter(playersFormatter);
		
		//Tooltips for the buttons
		Tooltip new_game_tooltip = new Tooltip("Start a new game.");
		game_options_1.setTooltip(new_game_tooltip);
		
		Tooltip pass_Tooltip = new Tooltip("Pass the turn to the next player.");
		pass_button.setTooltip(pass_Tooltip);
		
		//set infromation text for the rules and instructions
		info_label.setText("               How to Play: \n"
						 + "1) Use the spinner in the top bar \n"
						 + "to choose the number of players. \n"
						 + "2) Hit \"New game\" to start the game. \n"
						 + "3) The first player places a word "
						 + "\ndown using the center tile, \n"
						 + "that word is worth double.\n"
						 + "4) That player passes their turn \n"
						 + "to the next player who then \n"
						 + "builds off that firs played word. \n"
						 + "5) The game ends when the bag \n"
						 + "is empty and one player\'s hand \n"
						 + "is empty as well \n\n\n\n\n");
		
		//Image for the tile bag
		File aImage = new File(System.getProperty("user.dir") + "/pouch.png");
		Image bagImg1 = new Image(aImage.toURI().toString());
		bag_image.setImage(bagImg1);
		tiles_label.setTranslateY(25.0);
	}
	
	/** 
	 * starts a new game
	 * @param numPlayersIn
	 */
	public void newGame(int numPlayersIn) {
		turn_handler.players = new Player[numPlayersIn];
		turn_handler.numPlayers = numPlayersIn;
		turn_handler.turnCount = 0;
		turn_handler.currentPlayer = 0;
		tile_bag.initBag();
		 
		for (int i = 0; i < turn_handler.numPlayers; i++) {
			Player p = new Player(i);
			turn_handler.players[i] = p;
			System.out.println(p.toString());
		}
		turn_label.setText("Current Player: Player " + (turn_handler.currentPlayer + 1));
		
//		tiles_organizer.add(tile_bag.drawRandom().getRenderPane(), 1, 1);

		
		 
		
	}
	
	

	/**
	 * Method used to update player hand at gamestart and newturn, all movements go through board manipulator
	 * @param p
	 */
	public void updateHand(Player p) {
		for (int i = 0; i < p.hand.content.size(); i++) {
			TilePane l = p.hand.content.get(i);
			hand_containers[i].getChildren().clear();
			hand_containers[i].getChildren().add(l);
		}
	}
	
	/**
	 * Generates a TilePane for the letter given, id is just for identifying in debug. 
	 * If letterIn is null, then the tilepane is given the emoty tag. 
	 * (mostly used for the board and when the bag is empty)
	 * 
	 * @param letterIn
	 * @param idIn
	 * @return
	 */
	public TilePane getGeneratedTilePane(EnumLetter letterIn, int idIn) {
		TilePane p = new TilePane(letterIn, idIn);
		if (letterIn != null) {
			File aImage = new File(System.getProperty("user.dir") + "/letters/" + letterIn.letter().toLowerCase() + ".png");
			Image renderingImage = new Image(aImage.toURI().toString());
			ImageView renderingView = new ImageView(renderingImage);
			renderingView.setFitHeight(45);
			renderingView.setFitWidth(45);
			p.getChildren().add(renderingView);
		}
		// DEBUG RECTANGLE
//		Rectangle r = new Rectangle();
//		r.setWidth(30.0);
//		r.setHeight(30.0);
//		r.setFill(Color.GREEN);
//		p.getChildren().add(r);
//		p.setAlignment(Pos.CENTER);		
		
		p.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent e) {
		    	Dragboard db = ((Node)e.getSource()).startDragAndDrop(TransferMode.ANY);
				if (p.letter != null) {
					ArrayList<Integer> a = new ArrayList<Integer>(); 
							
					//add info to dragboard
					a.add(p.x);
					a.add(p.y);
					a.add(p.handIndex);

					ClipboardContent content = new ClipboardContent();
					content.put(ScrabbleBabble.tilesFormat, a);
					System.out.println("Strart Drag: (" + p.x + ", " + p.y + ", " + p.handIndex + ")");
					db.setContent(content);
					
				}
		        e.consume();
		    }
		});
		
		p.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent e) {
		    	e.acceptTransferModes(TransferMode.ANY);

				//System.out.println("Dragging");
				
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
		    	//grab dragboard
		    	Dragboard db = e.getDragboard();
		        boolean success = false;
		        ArrayList<Integer> dragged;
		        System.out.println("Attempting drop");
		        //check for content, mostly always true
		    	if (db.hasContent(ScrabbleBabble.tilesFormat)) {
		    		success = true;
		    		dragged = (ArrayList<Integer>) db.getContent(ScrabbleBabble.tilesFormat);
		    		//dragboard coords
		    		int xfrom = dragged.get(0);
		    		int yfrom = dragged.get(1);
		    		int handFrom = dragged.get(2);
		    		//dropping coordinates
		    		int xto = p.x;
		    		int yto = p.y;
		    		int handto = p.handIndex;
		    		ScrabbleBabble.board.moveToFrom(handFrom, xfrom, yfrom, handto, xto, yto, tiles_organizer, hand_containers);
		    		//ScrabbleBabble.instance.updateHand(ScrabbleBabble.turn_handler.getCurrentPlayer());
		    		System.out.println("Start Drag: (" + xfrom + ", " + yfrom + ", " + handFrom + ")");
		    		System.out.println("End Drag: (" + xto + ", " + yto + ", " + handto + ")");
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
