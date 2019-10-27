package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {

	List<Card> deck;
	List<List<Card>> playerList;
	boolean gameStatus;
	
	public GameBoard() {
		this.deck = new ArrayList<>();
		this.playerList = new ArrayList<>();
	}
	
	public GameBoard(List<Card> cardList) {
		this.deck = cardList;
	}

	public List<Card> getCardList() {
		return deck;
	}

	public void setCardList(List<Card> cardList) {
		this.deck = cardList;
	}
	
	public void createDeck() {
		for(CardSuit suit : CardSuit.values()) {
			for(CardValue value : CardValue.values()) {
				this.deck.add(new Card(suit, value));
			}
		}
		
		Collections.shuffle(this.deck);
		
		System.out.println(this.deck.size());
	}
	
	public void dealCards(int numPlayers) {
		
		createDeck();
		
		for(int i = 0; i < numPlayers; i++) {
			playerList.add(new ArrayList<>());
		}
		int j = 0;
		
		for(int i = 0; i < numPlayers; i++) {
			for(; j < 10; j++) {
				this.playerList.get(i).add(this.deck.get(j));

			}
		}

		
		gameStatus = true;
	}
	
	
	public void printDeck() {
		for(Card card : this.deck) {
			System.out.println(card);
		}
	}
	
	public void printPlayerList() {
		for(int i = 0; i < this.playerList.size(); i++) {
			for (Card card : this.playerList.get(0)) {
				System.out.println("Player " + i + ": " + card);
			}
		}
	}
	
	
}
