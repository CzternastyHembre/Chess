package chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;

public class KnightTest {
	
	private Knight knightBlack;
	private Knight knightWhite;
	private Game game;

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		
		knightBlack = new Knight(5, 0, 1, game.getBoard());
		game.getBoard()[0][5] = knightBlack;
		
		knightWhite= new Knight(2, 7, 0, game.getBoard());
		game.getBoard()[7][2] = knightWhite;
	}
	
	@Test
	public void testMoveKnight() {
		int x = knightBlack.getX();
		int y = knightBlack.getY();
		y = y + 2;
		x = x + 1;
		
		knightBlack.moveTo(x, y);
		checkPos(x, y, knightBlack);
		
		x = knightWhite.getX();
		y = knightWhite.getY();
		y = y - 1;
		x = x - 2;
		knightWhite.moveTo(x, y);
		checkPos(x, y, knightWhite);
	}
	
	@Test
	public void testIllegalMoves() {
		int x = knightBlack.getX();
		int y = knightBlack.getY();
		Assertions.assertThrows(IllegalStateException.class, () -> knightBlack.moveTo(5, 1));
		checkPos(x, y, knightBlack);
		
		x = knightWhite.getX();
		y = knightWhite.getY();
		Assertions.assertThrows(IllegalStateException.class, () -> knightWhite.moveTo(6, 6));
		checkPos(x, y, knightWhite);
	}
	

}
