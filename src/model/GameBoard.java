package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameBoard {

	List<Card> deck;
	List<Card> discard;
	List<Player> playerList;
	boolean gameIsOver;
	Random random;

	public GameBoard(int numPlayers, Random random) {
		this.deck = new ArrayList<>();
		this.discard = new ArrayList<>();
		this.random = random;

		this.playerList = new ArrayList<>();

		for (int i = 0; i < numPlayers; i++) {
			this.playerList.add(new Player());
		}
	}

	public GameBoard(List<Card> cardList) {
		this.deck = cardList;
	}

	public boolean checkGameIsOver() {
		int counter = 0;

		for (int i = 0; i < this.playerList.size(); i++) {
			for (Card card : this.playerList.get(i).getCardList()) {
				if (card.isFaceDown() == false) {
					counter++;
				}

			}
			
			if (counter == this.playerList.get(i).getNumCards()) {
				i++;
				System.out.println("Player " + i + " is the winner!");
				return true;
			}
			
			counter = 0;

		}

		return false;

	}

	public List<Card> getCardList() {
		return this.deck;
	}

	public void setCardList(List<Card> cardList) {
		this.deck = cardList;
	}

	public void createDeck() {
		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				this.deck.add(new Card(suit, value));
			}
		}

		Collections.shuffle(this.deck);

	}

	public void dealCards() {

		createDeck();

		for (int i = 0; i < this.playerList.size(); i++) {
			for (int j = 0; j < this.playerList.get(i).getNumCards(); j++) {
				this.playerList.get(i).getCardList().add((this.deck.get(0)));

				this.deck.remove(0);
			}
		}

		this.discard.add(deck.get(0));

		this.deck.remove(0);

		this.gameIsOver = false;
	}

	public void takeTurn(Player player) {
		Card current;
		Card temp;

		if (this.deck.size() == 0) {
			gameIsOver = true;
			System.out.println("No one wins");
			return;
		}

		if (checkCard(player, discard.get(discard.size() - 1)) == true) {
			current = takeTurnAux(player, discard.get(discard.size() - 1));
		} else {
			current = takeTurnAux(player, deck.get(0));

			if (this.deck.size() > 0) {
				this.deck.remove(0);
			}

		}

		discard.add(current);

		gameIsOver = checkGameIsOver();

	}

	public Card takeTurnAux(Player player, Card card) {
		// Base case to end the recursive loop
		if (checkCard(player, card) == false) {
			return card;
		}

		Card current;

		current = player.getCardList().get(card.getValue().getIntValue() - 1);

		player.getCardList().set(card.getValue().getIntValue() - 1, card);

		player.getCardList().get(card.getValue().getIntValue() - 1).setFaceDown(false);

		return takeTurnAux(player, current);

	}

	/**
	 * Checks to see if the card that the player drew is actually needed
	 * 
	 * @param player
	 * @param card
	 * @return
	 */
	public boolean checkCard(Player player, Card card) {

		if (card.getValue() == CardValue.Jack || card.getValue() == CardValue.Queen) {
			return false;
		}

		if (card.value.getIntValue() <= player.getCardList().size()) {
			if (player.getCardList().get(card.value.getIntValue() - 1).faceDown == true) {
				return true;
			}
		}

		return false;
	}

	public void printDeck() {
		for (Card card : this.deck) {
			System.out.println(card);
		}
	}

	public void printPlayerList() {
		for (int i = 0; i < this.playerList.size(); i++) {
			for (Card card : this.playerList.get(i).getCardList()) {
				System.out.println("Player " + (i + 1) + ": " + card);
			}
		}
	}

}
