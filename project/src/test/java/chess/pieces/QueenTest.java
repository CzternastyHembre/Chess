package chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;

public class QueenTest {
	
	private Queen queenBlack;
	private Queen queenWhite;
	private Game game;

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		queenBlack = new Queen(4, 3, 1, game.getBoard());
		game.getBoard()[3][4] = queenBlack;
		queenWhite= new Queen(4, 4, 0, game.getBoard());
		game.getBoard()[4][4] = queenWhite;
	}
	
	@Test
	public void testMoveQueen() {
		MovementTest.moveDiagonal(game, queenBlack);
		MovementTest.moveDiagonal(game, queenWhite);
		
		MovementTest.moveLines(game, queenBlack);
		MovementTest.moveLines(game, queenWhite);
	}
	


	@Test
	public void testIllegalMoves() {
		int x_black = queenBlack.getX();
		int y_black = queenBlack.getY();
		Assertions.assertThrows(IllegalStateException.class, () -> queenBlack.moveTo(x_black + 1, y_black + 2));
		checkPos(x_black, y_black, queenBlack);
		
		int x_white = queenWhite.getX();
		int y_white = queenWhite.getY();		
		Assertions.assertThrows(IllegalStateException.class, () -> queenWhite.moveTo(x_white - 2, y_white - 1));
		checkPos(x_white , y_white, queenWhite);
	}
	

}
