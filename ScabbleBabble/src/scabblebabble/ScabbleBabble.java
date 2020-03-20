package scabblebabble;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScabbleBabble  extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		s.setWidth(800);
		s.setHeight(600);
		
		s.setTitle("Hello World!");
		final Button btn = new Button();
		btn.setText("Click Me!");
		
		
		
		final StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		s.setScene(new Scene(root, 300, 250));
		s.show();
	}

	
}
