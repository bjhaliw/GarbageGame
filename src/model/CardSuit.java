package model;

public enum CardSuit {

	SPADES("S"), CLUBS("C"), HEARTS("H"), DIAMONDS("D");

	CardSuit(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private String value;
}
