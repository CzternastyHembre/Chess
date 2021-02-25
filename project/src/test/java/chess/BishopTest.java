package chess;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {
	
	private Bishop bishopBlack;
	private Bishop bishopWhite;
	private Game game;

	
	private void checkPos(int x, int y, Piece p) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);	
	}
	
	@BeforeEach
	public void beforeEach() {
		game = new Game(8);
		bishopBlack = new Bishop(5, 0, 1, game.getBoard());
		game.getBoard()[0][5] = bishopBlack;
		bishopWhite= new Bishop(2, 7, 0, game.getBoard());
		game.getBoard()[7][2] = bishopWhite;
	}
	
	@Test
	public void testMoveBishops() {
		bishopBlack.moveTo(4, 1);
		checkPos(4, 1, bishopBlack);
		bishopWhite.moveTo(3, 6);
		checkPos(3, 6, bishopWhite);
	}
	

}
