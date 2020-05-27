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

/**
 * This class creates and controls the GUI to display the card game. The
 * cards are dealt out to the players and then at the click of a button the 
 * cards are automatically dealt and flipped.
 * @author Brenton Haliw
 *
 */
public class CardGameGUI extends Application {

	private GameBoard board;
	private BorderPane pane;
	private ArrayList<VBox> players;

	/**
	 * Default constructor for the CardGameGUI object. Initializes the 
	 * BorderPane object and the ArrayList containing the players
	 */
	public CardGameGUI() {
		this.pane = new BorderPane();
		this.players = new ArrayList<>();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int sceneWidth = 600, sceneHeight = 720;
		AnchorPane anchor = new AnchorPane();
		MenuBar bar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem newGame = new MenuItem("New Game");
		file.getItems().add(newGame);
		bar.getMenus().add(file);
		anchor.getChildren().add(this.pane);
		this.pane.setPrefSize(sceneWidth, sceneHeight);

		VBox box = new VBox(10);
	
		box.getChildren().addAll(bar, this.pane);

		mainMenu();

		Scene scene = new Scene(box, sceneWidth, sceneHeight);
		primaryStage.setTitle("Card Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * The intro menu that the user is greeted with. Allows the user
	 * to input the amount of players that they wish to face (max of 4)
	 */
	public void mainMenu() {
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		Label label = new Label("Please enter between 2 and 4 players");
		Label warningLabel = new Label("Must be between 2 and 4 players!");
		warningLabel.setVisible(false);
		TextField field = new TextField();
		field.setPromptText("# Players");
		field.setMaxWidth(100);
		Button button = new Button("Submit");

		field.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				if(Integer.parseInt(field.getText()) < 5 && Integer.parseInt(field.getText()) > 1) {
					this.board = new GameBoard(Integer.parseInt(field.getText()), new Random());
					this.pane.setCenter(null);
					dealCards();
					this.pane.setBottom(this.players.get(0));		
					this.pane.setTop(this.players.get(1));
				} else {
					warningLabel.setVisible(true);
				}			
			}

		});

		button.setOnAction(e -> {
			if(Integer.parseInt(field.getText()) < 5 && Integer.parseInt(field.getText()) > 1) {
				this.board = new GameBoard(Integer.parseInt(field.getText()), new Random());
				this.pane.setCenter(null);
				dealCards();
				this.pane.setBottom(this.players.get(0));		
				this.pane.setTop(this.players.get(1));
			} else {
				warningLabel.setVisible(true);
			}
		});

		box.getChildren().addAll(label, field, button, warningLabel);
		this.pane.setCenter(box);
	}

	public void dealCards() {
		this.board.dealCards();
		VBox box = new VBox(); 
		HBox center = new HBox(10);
		center.setAlignment(Pos.CENTER);
		center.getChildren().add(new ImageView(new Image("gui/images/b1fv.gif")));
		Button button = new Button("Turn");
		button.setOnAction(e -> {
			takeTurn();
		});

		Card card = this.board.getDiscardCard();

		String suit = card.getSuit().getValue();
		String value = card.getValue().getValue();

		String cardInfo = suit + value;

		center.getChildren().add(new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
		center.getChildren().add(button);

		this.pane.setCenter(center);

		for (int i = 0; i < this.board.getNumPlayers(); i++) {
			HBox top = new HBox(10);
			HBox bottom = new HBox(10);
			VBox playerBox = new VBox(10);
			playerBox.setAlignment(Pos.CENTER);
			top.setAlignment(Pos.CENTER);
			bottom.setAlignment(Pos.CENTER);

			this.players.add(playerBox);
			this.players.get(i).getChildren().addAll(top, bottom);

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
		if (this.board.checkGameIsOver() == false) {
			for (int j = 0; j < this.board.getNumPlayers(); j++) {
				Player player = this.board.getPlayerList().get(j);
				this.board.takeTurn(player);

				for (int i = 0; i < 10; i++) {
					if (this.board.getPlayerList().get(j).getCardList().get(i).isFaceDown() == false) {
						String suit = player.getCardList().get(i).getSuit().getValue();
						String value = player.getCardList().get(i).getValue().getValue();
						String cardInfo = suit + value;
						HBox box;

						if (i < 5) {
							box = (HBox) this.players.get(j).getChildren().get(0);
							box.getChildren().set(i, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
						} else {
							box = (HBox) this.players.get(j).getChildren().get(1);
							box.getChildren().set(i - 5, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));
						}

					}
				}
			}

			String suit = this.board.getDiscard().get(this.board.getDiscard().size() - 1).getSuit().getValue();
			String value = this.board.getDiscard().get(this.board.getDiscard().size() - 1).getValue().getValue();
			String cardInfo = suit + value;

			HBox box = (HBox) this.pane.getCenter();
			box.getChildren().set(1, new ImageView(new Image("gui/images/" + cardInfo + ".gif")));

			if (this.board.getDeck().size() == 0) {
				box.getChildren().remove(0);
			}
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
