package model;

public class Driver {

	public static void main(String[] args) {
		GameBoard board = new GameBoard();

		board.dealCards(3);
		
		board.printPlayerList();
		
		System.out.println(board.deck.size());

	}
}
