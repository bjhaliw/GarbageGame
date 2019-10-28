package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	List<Card> cardList;
	int numCards;

	public Player() {
		this.cardList = new ArrayList<>();
		this.numCards = 10;
	}

	public Player(List<Card> cardList, int numCards) {
		this.cardList = cardList;
		this.numCards = numCards;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public int getNumCards() {
		return numCards;
	}

	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

}
