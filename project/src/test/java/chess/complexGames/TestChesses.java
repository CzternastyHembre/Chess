package chess.complexGames;

import chess.Game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the different ways to get out of chess
 */
public class TestChesses {

	Game game;
	
	@BeforeEach
	public void beforeEach() {
		int s = 8;
		game = new Game(s);
		game.init(s);
	}

	@Test
	public void testTakePieceBlack() {//In chess but can only take the piece to prevent it
		String moveString = "e2e4 e7e5 d1h5 e8e7 a2a4 b8c6 h5e5";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}
	
	@Test
	public void testTakePieceWhite() {//In chess but can only take the piece to prevent it
		String moveString = "e2e4 e7e5 b1c3 d8h4 e1e2 h4e4";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}
	
	
	@Test
	public void testMoveBetweenWhite() {//In chess but can only move piece between to prevent it
		String moveString = "e2e4 e7e5 a2a4 d8h4 a4a5 h4e4";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}

	
	@Test
	public void testMoveBetweenBlack() {//In chess but can only move piece between to prevent it
		String moveString = "e2e4 e7e5 d1h5 a7a5 h5e5";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}
	
	
	@Test
	public void testMoveAwayBlack() {//In chess but can move away with the kingto prevent it
		String moveString = "e2e4 e7e5 d1h5 d8h4 a2a4 e8e7 h5e5";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}

	@Test
	public void testMoveAwayWhite() {//In chess but can move away with the kingto prevent it
		String moveString = "e2e4 e7e5 d1h5 d8h4 e1e2 h4e4";
		
		game.movesFromStringLongLAN(moveString);
		Assertions.assertTrue(game.checkInChess());
		Assertions.assertFalse(game.checkMate());
	}

}
