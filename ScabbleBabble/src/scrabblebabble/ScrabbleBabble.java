package scrabblebabble;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
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
import scrabblebabble.board.Board;
import scrabblebabble.game.LetterTile;
import scrabblebabble.game.Player;
import scrabblebabble.game.TileBag;
import scrabblebabble.render.TilePane;
import scrabblebabble.turn.TurnHandler;

public class ScrabbleBabble extends Application implements Initializable {

	public static Board board;
	public static TurnHandler turn_handler;
	public static TileBag tile_bag;
	
	public static ScrabbleBabble instance;
	
	public static Node dragging;
	
	
	@FXML public Button game_options_1;
	@FXML public Label turn_label;
	
	@FXML public ToolBar toolbar1;
	
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
	@FXML public Label scores_label;
	@FXML public Label tiles_label;
	
	public static final DataFormat tilesFormat = new DataFormat("scrabblebabble.tile");
	
	public static void main(String[] args) {
		board = new Board();
		tile_bag = new TileBag();
		instance = new ScrabbleBabble();
		turn_handler = new TurnHandler();
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
		game_options_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newGame(2);
				updateHand(turn_handler.getCurrentPlayer());
			}
		});

		pass_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				turn_handler.nextTurnCycle();
				turn_label.setText("Current Player: Player " + (turn_handler.currentPlayer + 1) + " | (" + turn_handler.currentPlayer + ")");
			}
		});
		
		// Add blank TilePanes into the gridpane for temporary null
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				tiles_organizer.add(tile_bag.drawRandom(-1), i, j);
				// LetterTile.getGeneratedTilePane(null)
			}
		}
		
		// Load holders for the tile in the hand
		for (int i = 0; i < 7; i++) {
			StackPane sp = new StackPane();
			sp.setPrefSize(101, 101);
			hand_containers[i] = sp;
			hand_organizer.getChildren().add(i, sp);
		}
		
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
	
	

	public void updateHand(Player p) {
		for (int i = 0; i < p.hand.content.size(); i++) {
			LetterTile l = p.hand.content.get(i).held;
			System.out.println(l.toString());
			Node n = l.getRenderPane();
			hand_containers[i].getChildren().add(n);
		}
	}
	
	
}
