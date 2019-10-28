package model;

public class Driver {

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		
		board.addPlayers(3);

		board.dealCards();
		
		board.printPlayerList();
		
		System.out.println(board.deck.size());

	}
}
