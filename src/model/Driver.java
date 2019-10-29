package model;

public class Driver {

	public static void main(String[] args) {
		GameBoard board = new GameBoard(3);

		board.dealCards();
		
		while(board.gameIsOver == false) {
			board.takeTurn(board.playerList.get(0));
			
			if(board.gameIsOver == true) {
				break;
			}
			board.takeTurn(board.playerList.get(1));
			
			if(board.gameIsOver == true) {
				break;
			}
			
			board.takeTurn(board.playerList.get(2));
			
		}
		
		board.printPlayerList();
				
		System.out.println(board.deck.size());

	}
}
