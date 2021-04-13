package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.pieces.Piece;
import chess.pieces.Rook;

public class RookTest {
	
	private Rook rookBlack;
	private Rook rookWhite;
	private Game game;
	private final MovementTest m = new MovementTest();
		
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
	}
	
}
