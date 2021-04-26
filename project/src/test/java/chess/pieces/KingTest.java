package chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;

public class KingTest {
	
	private King KingBlack;
	private King KingWhite;
	private Game game;

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);	
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		
		KingBlack = new King(5, 0, 1, game.getBoard());
		game.getBoard()[0][5] = KingBlack;
		
		KingWhite= new King(2, 7, 0, game.getBoard());
		game.getBoard()[7][2] = KingWhite;
	}
	
	@Test
	public void testMoveKing() {
	
		this.moveKing(KingBlack);
		this.moveKing(KingWhite);

	}
	
	private void moveKing(Piece p) {
		
		int x_pos = p.getX();
		int y_pos = p.getY();	

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (!(x == 0 && y == 0)) {//Checks if it doesn't move
					int targetX = x_pos + x;
					int targetY = y_pos + y;
					if (game.isInsideBoard(targetX, targetY)) {
						p.moveTo(targetX, targetY);
						this.checkPos(targetX, targetY, p);
						
						p.moveTo(x_pos, y_pos);
						this.checkPos(x_pos, y_pos, p);
					}
				}
			}
		}
	}
	
	private void moveIllegalKing(Piece p) {
		int s = game.getSize();
		
		int x_pos = p.getX();
		int y_pos = p.getY();	

		for (int x_to = 0; x_to < s; x_to++) {
			for (int y_to = 0; y_to < s; y_to++) {
				if (Math.max(Math.abs(x_pos - x_to), Math.abs(y_pos - y_to)) > 1) { //Checks if the distance is > 1
					int x = x_to;
					int y = y_to;
					Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x, y));
				}
			}
		}
		
	}
	
	@Test
	public void testIllegalMoves() {
		this.moveIllegalKing(KingBlack);
		this.moveIllegalKing(KingWhite);
//		Assertions.assertThrows(IllegalStateException.class, () -> KingBlack.moveTo(4, 1));
//		checkPos(5, 0, KingBlack);
//		
//		Assertions.assertThrows(IllegalStateException.class, () -> KingWhite.moveTo(3, 6));
//		checkPos(2, 7, KingWhite);
	}
	

}
