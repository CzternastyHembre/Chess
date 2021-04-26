package chess.complexGames;

import chess.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestInputs {

	private Game game;
	
	@BeforeEach
	public void beforeEach() {
		int s = 8;
		game = new Game(s);
		game.init(s);
	}
	
	@Test
	public void testNoPieceToMove() {
		//No piece there
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("a4a3"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("a3a4"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("b5b4"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("h3h5"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("d4a3"));
		
	}
	
	@Test
	public void testWrongColorMoves() {
		
		//Wrong color
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("a7a6"));
		
		//Moves to change the color to move
		game.moveFromStringLAN("a2a3");
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("b2b3"));
		
		game.moveFromStringLAN("b7b6");
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("c7c6"));

	}
	
	@Test
	public void testMovesOutsideOfBoard() {
		
		
		//Outside of the board
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("a0a1"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("a7a9"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("h2j3"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("j2h3"));
	}
	
	@Test
	public void testMoveWhileInChess() {
		//Sets the black king in chess
		game.movesFromStringLongLAN("e2e4 e7e5 d1h5 b8c6 h5f7");
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("a7a6"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("a7a5"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("b7b6"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("b7b5"));
		Assertions.assertThrows(IllegalStateException.class, () -> game.moveFromStringLAN("e8e7"));
		
		//Moves out of chess
		Assertions.assertDoesNotThrow(() -> game.moveFromStringLAN("e8f7"));
	}
	
	@Test
	public void testInputOutOfSize() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("e88e8e7"));		
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("2ee8e7"));		
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("ee8e7"));		
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("8e7"));		
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("e7"));		
		Assertions.assertThrows(IllegalArgumentException.class, () -> game.moveFromStringLAN("7"));		
	}
	
	@Test
	public void testIsInsideBoard() {
		Assertions.assertTrue(game.isInsideBoard(0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7));
		Assertions.assertFalse(game.isInsideBoard(-1,0,1,2,3,4,5,6,7));
		Assertions.assertFalse(game.isInsideBoard(0,1,2,3,4,5,6,7,8));
	}
	
	@Test
	public void testIsInsideBoard5x5() {
		game = new Game(5);
		Assertions.assertTrue(game.isInsideBoard(0,1,2,3,4,0,1,2,3,4));
		Assertions.assertFalse(game.isInsideBoard(-1,0,1,2,3,4));
		Assertions.assertFalse(game.isInsideBoard(0,1,2,3,4,5));
	}

}
