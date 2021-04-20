package chess.pieces;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;


public class BishopTest {
	
	private Bishop bishopBlack;
	private Bishop bishopWhite;
	private Game game;


	
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
		
		MovementTest.moveDiagonal(game, bishopBlack);
		MovementTest.moveDiagonal(game, bishopWhite);
	}

	@Test
	public void testIllegalMoves() {
		MovementTest.moveIllegalLines(game, bishopBlack);
		MovementTest.moveIllegalLines(game, bishopWhite);
	}

	

}
