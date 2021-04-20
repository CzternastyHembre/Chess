package chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;

public class RookTest {
	
	private Rook rookBlack;
	private Rook rookWhite;
	private Game game;
		
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
		MovementTest.moveLines(game, rookBlack);
		MovementTest.moveLines(game, rookWhite);
	}
	
	@Test
	public void testIllegalMoves() {
		MovementTest.moveIllegalDiagonal(game, rookBlack);
		MovementTest.moveIllegalDiagonal(game, rookWhite);
	}
	
}
