package scrabblebabble;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
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
import scrabblebabble.game.Player;
import scrabblebabble.game.TileBag;
import scrabblebabble.turn.TurnHandler;

public class ScrabbleBabble  extends Application implements Initializable {

	public static Board board;
	public static TurnHandler turn_handler;
	public static TileBag tile_bag;
	
	public static ScrabbleBabble instance;
	
	public static Node dragging;
	
	
	@FXML
	public Button game_options_1;
	@FXML
	public Label turn_label;
	
	@FXML
	public ToolBar toolbar1;
	
	@FXML
	public BorderPane main_layout;
	
	@FXML
	public StackPane center_stack;
	@FXML
	public GridPane tiles_organizer;
	
	@FXML
	public StackPane bottom_stack;
	@FXML
	public FlowPane bottom_flow;
	@FXML
	public FlowPane hand_organizer;
	@FXML
	public StackPane hand_stack;
	
	@FXML
	public StackPane left_stack;
	@FXML
	public FlowPane left_organizer;

	@FXML
	public ImageView scores_background;
	@FXML
	public ImageView tiles_background;
	@FXML
	public ImageView hand_background;
	@FXML
	public Button pass_button;
	
	@FXML
	public StackPane right_stack;
	@FXML
	public Label info_label;
	@FXML
	public Label scores_label;
	@FXML
	public Label tiles_label;
	
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
			}
		});

		pass_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				turn_handler.nextTurnCycle();
				turn_label.setText("Current Player: Player " + (turn_handler.currentPlayer + 1) + " | (" + turn_handler.currentPlayer + ")");
			}
		});
		
		
		
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
		}
		turn_label.setText("Current Player: Player " + (turn_handler.currentPlayer + 1));
		
		tiles_organizer.add(tile_bag.drawRandom().getRenderPane(), 1, 1);

		ScrabbleBabble.instance.updateHand(turn_handler.getCurrentPlayer());
		 
	}

	
	public void onDragDetected(MouseEvent e) {
		
	}

	public void updateHand(Player p) {
		for (int i = 0; i < p.hand.content.size(); i++) {
			hand_organizer.getChildren().add(p.hand.content.get(0).getRenderPane());
		}
				
	}
	
	
}
