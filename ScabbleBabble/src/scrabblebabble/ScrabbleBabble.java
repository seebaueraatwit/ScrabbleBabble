package scrabblebabble;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import scrabblebabble.board.Board;

public class ScrabbleBabble  extends Application implements Initializable {

	public static Board board;
	
	public Button button1;
	
	public static void main(String[] args) {
		board = new Board();
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		
		//TODO the fxml is made but needs to be filled
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
}
