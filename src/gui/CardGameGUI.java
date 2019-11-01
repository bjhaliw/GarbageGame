package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameBoard;

public class CardGameGUI extends Application {

	GameBoard board;
	
	public CardGameGUI() {
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		int sceneWidth = 1000, sceneHeight = 1000;
		
		BorderPane pane = new BorderPane();
		

		pane.backgroundProperty().set(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Fitness Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public void dealCards() {
		
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	

}
