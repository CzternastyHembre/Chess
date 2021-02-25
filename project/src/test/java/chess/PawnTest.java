package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnTest {
	
	private Pawn pawnBlack;
	private Pawn pawnWhite;
	private Game game;

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);		
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		pawnBlack = new Pawn(0, 1, 1, game.getBoard());
		game.getBoard()[1][0] = pawnBlack;
		pawnWhite= new Pawn(1, 6, 0, game.getBoard());
		game.getBoard()[6][1] = pawnWhite;
	}
	
	@Test
	public void testConstrutor() {
		checkPos(0, 1, pawnBlack);
		checkPos(1, 6, pawnWhite);
	}
	
	@Test
	public void testUp() {
		pawnBlack.moveTo(0, 2);
		checkPos(0, 2, pawnBlack);
		pawnWhite.moveTo(1, 5);
		checkPos(1, 5, pawnWhite);
	}
	
	@Test
	public void test2Up() {
		pawnBlack.moveTo(0, 3);
		checkPos(0, 3, pawnBlack);
		pawnWhite.moveTo(1, 4);
		checkPos(1, 4, pawnWhite);
	}
	
	@Test
	public void testTakeWithWhite() {
		pawnBlack.moveTo(0, 3);
		pawnWhite.moveTo(1, 4);
		pawnWhite.moveTo(0,3);
		checkPos(0, 3, pawnWhite);
	}
	
	@Test
	public void testTakeWithBlack() {
		pawnBlack.moveTo(0, 3);
		pawnWhite.moveTo(1, 4);
		pawnBlack.moveTo(1,4);
		checkPos(1, 4, pawnBlack);
	}

	@Test
	public void testMoveToNextLastRank() {
		pawnBlack.moveTo(0, 3);
		pawnBlack.moveTo(0, 4);
		pawnBlack.moveTo(0, 5);
		pawnBlack.moveTo(0, 6);
		checkPos(0, 6, pawnBlack);

		pawnWhite.moveTo(1, 4);
		pawnWhite.moveTo(1, 3);
		pawnWhite.moveTo(1, 2);
		pawnWhite.moveTo(1, 1);
		checkPos(1, 1, pawnWhite);
	}
	
	@Test
	public void testPawnToQueen() { //TODO Havent implementet queening
		pawnBlack.moveTo(0, 3);
		pawnBlack.moveTo(0, 4);
		pawnBlack.moveTo(0, 5);
		pawnBlack.moveTo(0, 6);
		pawnBlack.moveTo(0, 7);

		pawnWhite.moveTo(1, 4);
		pawnWhite.moveTo(1, 3);
		pawnWhite.moveTo(1, 2);
		pawnWhite.moveTo(1, 1);
		pawnWhite.moveTo(1, 0);
	}

}
