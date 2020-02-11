package gui;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Card;
import model.GameBoard;
import model.Player;

public class CardGameGUI extends Application {

	GameBoard board;
	BorderPane pane;
	ArrayList<VBox> players;

	public CardGameGUI() {
		pane = new BorderPane();
		players = new ArrayList<>();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int sceneWidth = 1000, sceneHeight = 1000;
		AnchorPane anchor = new AnchorPane();
		MenuBar bar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem newGame = new MenuItem("New Game");
		file.getItems().add(newGame);
		bar.getMenus().add(file);
		anchor.getChildren().add(pane);
		pane.setPrefSize(sceneWidth, sceneHeight);

		VBox box = new VBox(10);

		
		box.getChildren().addAll(bar, pane);

		mainMenu();

		Scene scene = new Scene(box, sceneWidth, sceneHeight);
		primaryStage.setTitle("Card Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void mainMenu() {
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		Label label = new Label("Please enter number of players");
		TextField field = new TextField();
		field.setPromptText("# Players");
		field.setMaxWidth(100);
		Button button = new Button("Submit");

		field.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				if(Integer.parseInt(field.getText()) < 5 && Integer.parseInt(field.getText()) > 0) {
					board = new GameBoard(Integer.parseInt(field.getText()), new Random());
					pane.setCenter(null);
					dealCards();
					pane.setBottom(players.get(0));		
					pane.setTop(players.get(1));
				}
				
			}

		});

		button.setOnAction(e -> {
			if(Integer.parseInt(field.getText()) < 5 && Integer.parseInt(field.getText()) > 0) {
				board = new GameBoard(Integer.parseInt(field.getText()), new Random());
				pane.setCenter(null);
				dealCards();
				pane.setBottom(players.get(0));		
				pane.setTop(players.get(1));
			}
		});

		box.getChildren().addAll(label, field, button);
		pane.setCenter(box);
	}

	public void dealCards() {
		board.dealCards();
		VBox box = new VBox(); 
		HBox center = new HBox(10);
		center.setAlignment(Pos.CENTER);
		center.getChildren().add(new ImageView(new Image("gui/images/b1fv.gif")));
		Button button = new Button("Turn");
		button.setOnAction(e -> {
			takeTurn();
		});

		Card card = board.getDiscardCard();

		String suit = card.getSuit().getValue();
		String value = card.getValue().getValue();

		String cardInfo = suit + value;

		center.getChildren().add(new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
		center.getChildren().add(button);

		pane.setCenter(center);

		for (int i = 0; i < board.getNumPlayers(); i++) {
			HBox top = new HBox(10);
			HBox bottom = new HBox(10);
			VBox playerBox = new VBox(10);
			playerBox.setAlignment(Pos.CENTER);
			top.setAlignment(Pos.CENTER);
			bottom.setAlignment(Pos.CENTER);

			players.add(playerBox);
			players.get(i).getChildren().addAll(top, bottom);

			for (int j = 0; j < 10; j++) {
				if (j < 5) {
					top.getChildren().add(new ImageView(new Image("gui/images/b1fv.gif")));
				} else {
					bottom.getChildren().add(new ImageView(new Image("gui/images/b1fv.gif")));
				}
			}
		}

		

	}

	public void takeTurn() {
		if (board.checkGameIsOver() == false) {
			for (int j = 0; j < board.getNumPlayers(); j++) {
				Player player = board.getPlayerList().get(j);
				board.takeTurn(player);

				for (int i = 0; i < 10; i++) {
					if (board.getPlayerList().get(j).getCardList().get(i).isFaceDown() == false) {
						String suit = player.getCardList().get(i).getSuit().getValue();
						String value = player.getCardList().get(i).getValue().getValue();
						String cardInfo = suit + value;
						HBox box;

						if (i < 5) {
							box = (HBox) players.get(j).getChildren().get(0);
							box.getChildren().set(i, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
						} else {
							box = (HBox) players.get(j).getChildren().get(1);
							box.getChildren().set(i - 5, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
						}
					}
				}
			}

			String suit = board.getDiscard().get(board.getDiscard().size() - 1).getSuit().getValue();
			String value = board.getDiscard().get(board.getDiscard().size() - 1).getValue().getValue();
			String cardInfo = suit + value;

			HBox box = (HBox) pane.getCenter();
			box.getChildren().set(1, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));

			if (board.getDeck().size() == 0) {
				box.getChildren().remove(0);
			}
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
