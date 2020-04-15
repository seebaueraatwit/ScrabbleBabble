package scrabblebabble;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scrabblebabble.board.Board;

public class ScrabbleBabble  extends Application implements Initializable {

	public static Board board;
	
	@FXML
	public Button game_options_1;
	
	@FXML
	public Label scores_label;
	
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
	
	public StackPane tilePrefab;
	
	public static void main(String[] args) {
		board = new Board();
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("ScabbleBabble.fxml"));
		final Pane p = loader.load();
		
		s.setScene(new Scene(p));
		s.show();		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		game_options_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			
			}
		});

		
		
	}

	
	
}
