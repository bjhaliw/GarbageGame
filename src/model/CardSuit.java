package model;

public enum CardSuit {

	SPADES("Spades"), CLUBS("Clubs"), HEARTS("Hearts"), DIAMONDS("Diamonds");

	CardSuit(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private String value;
}
