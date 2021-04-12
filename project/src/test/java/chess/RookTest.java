package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookTest {
	
	private Rook rookBlack;
	private Rook rookWhite;
	private Game game;
	private final MovementTest m = new MovementTest();
	

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);	
		System.out.println(game);
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		rookBlack = new Rook(6, 0, 1, game.getBoard());
		game.getBoard()[0][6] = rookBlack;
		rookWhite= new Rook(7, 7, 0, game.getBoard());
		game.getBoard()[7][7] = rookWhite;
	}
	
	@Test
	public void testMoveRooks() {
		m.moveLines(game, rookBlack);
		m.moveLines(game, rookWhite);
	}
	
	@Test
	public void testIllegalMoves() {
		m.moveIllegalDiagonal(game, rookBlack);
		m.moveIllegalDiagonal(game, rookWhite);
//		int x = rookBlack.getX();
//		int y = rookBlack.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> m.moveDiagonal(game, rookBlack));
//		checkPos(x, y, rookBlack);
//		
//		x = rookWhite.getX();
//		y = rookWhite.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> m.moveDiagonal(game, rookWhite));
//		checkPos(x, y, rookWhite);
		
		
		
//		int x = rookBlack.getX();
//		int y = rookBlack.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> rookBlack.moveTo(5, 1));
//		checkPos(x, y, rookBlack);
//		
//		x = rookWhite.getX();
//		y = rookWhite.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> rookWhite.moveTo(6, 6));
//		checkPos(x, y, rookWhite);

	}
	
}
