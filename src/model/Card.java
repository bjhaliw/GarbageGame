package model;

public class Card {

	CardValue value;
	CardSuit suit;
	boolean faceDown;

	public Card(CardSuit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
		this.faceDown = true;
	}

	public CardValue getValue() {
		return value;
	}

	public void setValue(CardValue value) {
		this.value = value;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}

	public boolean isFaceDown() {
		return faceDown;
	}

	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
	}

	public String cardImage() {
		if (this.faceDown == true) {
			return "gui/images/b1fv.gif";
		} else {

			return "gui/images/" + this.suit.getValue() + this.value.getValue() + ".gif";
		}
	}

	public String toString() {
		return "Value: " + this.value + ", Suit: " + this.suit + ", FaceDown: " + this.faceDown;
	}

}
