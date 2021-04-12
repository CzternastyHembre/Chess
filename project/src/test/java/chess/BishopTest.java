package chess;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BishopTest {
	
	private Bishop bishopBlack;
	private Bishop bishopWhite;
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
		bishopBlack = new Bishop(4, 3, 1, game.getBoard());
		game.getBoard()[3][4] = bishopBlack;
		bishopWhite= new Bishop(4, 4, 0, game.getBoard());
		game.getBoard()[4][4] = bishopWhite;
	}
	
	@Test
	public void testMoveBishops() {
		
		m.moveDiagonal(game, bishopBlack);
		m.moveDiagonal(game, bishopWhite);
	}

	@Test
	public void testIllegalMoves() {
		m.moveIllegalLines(game, bishopBlack);
		m.moveIllegalLines(game, bishopWhite);
//		int x = bishopBlack.getX();
//		int y = bishopBlack.getY();
//		
//		Assertions.assertThrows(IllegalStateException.class, () -> m.moveLines(game, bishopBlack));
//		checkPos(x, y, bishopBlack);
//
//		x = bishopWhite.getX();
//		y = bishopWhite.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> m.moveLines(game, bishopWhite));
//		checkPos(x, y, bishopWhite);

//		int x = bishopBlack.getX();
//		int y = bishopBlack.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> bishopBlack.moveTo(0, 0));
//		checkPos(x, y, bishopBlack);
//		
//		x = bishopWhite.getX();
//		y = bishopWhite.getY();
//		Assertions.assertThrows(IllegalStateException.class, () -> bishopWhite.moveTo(7, 7));
//		checkPos(x, y, bishopWhite);
		
	}

	

}
