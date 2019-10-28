package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {

	List<Card> deck;
	List<Card> discard;
	List<Player> playerList;
	boolean gameStatus;

	public GameBoard() {
		this.deck = new ArrayList<>();
		this.playerList = new ArrayList<>();
		this.discard = new ArrayList<>();
	}

	public GameBoard(List<Card> cardList) {
		this.deck = cardList;
	}
	
	public boolean checkGameStatus() {
		
	}

	public List<Card> getCardList() {
		return this.deck;
	}

	public void addPlayers(int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			this.playerList.add(new Player());
		}
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

		gameStatus = true;
	}

	public void takeTurn(List<Card> deck, List<Card> discard, Player player) {
		Card current;
		Card temp;
		
		if(checkCard(player, discard.get(discard.size() - 1)) == true) {
			current = takeTurnAux(player, discard.get(discard.size() - 1));
		} else {
			current = takeTurnAux(player, deck.get(0));
		}
		
	
		if (this.gameStatus == true) {
			
		}
	}
	
	public Card takeTurnAux(Player player, Card card) {
		// Base case to end the recursive loop
		if (checkCard(player, card) == false) {
			return card;
		}
		
		Card current;
		
		player.getCardList().get(card.getValue().getIntValue()).setFaceDown(false);
		
		current = player.getCardList().get(card.getValue().getIntValue());
		
		player.getCardList().set(card.getValue().getIntValue() - 1, card);
	
		takeTurnAux(player, current);
		
		return card;
		
	}

	public boolean checkCard(Player player, Card card) {
		if (card.value.getIntValue() == player.getCardList().get(card.value.getIntValue()).value.getIntValue()) {
			if (player.getCardList().get(card.value.getIntValue()).faceDown == true) {
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
				System.out.println("Player " + i + ": " + card);
			}
		}
	}

}
