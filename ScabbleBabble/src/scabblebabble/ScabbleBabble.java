package scabblebabble;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scabblebabble.board.Board;

public class ScabbleBabble  extends Application {

	public static Board board;
	
	public static void main(String[] args) {
		board = new Board();
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		
		
		
	}

	
}
