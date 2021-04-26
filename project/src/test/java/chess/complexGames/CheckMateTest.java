package chess.complexGames;

import chess.Game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckMateTest {

	
	private Game game;
	
	@BeforeEach
	public void beforeEach() {
		int s = 8;
		game = new Game(s);
		game.init(s);
	}
	
	@Test
	public void testScholarsMate() {
		String scholarsMate = "e2e4 e7e5 f1c4 g8f6 d1h5 f6e4 h5f7";
		
		game.movesFromStringLongLAN(scholarsMate);
		
		Assertions.assertTrue(game.isGameOver());
		Assertions.assertTrue(game.checkMate());
		Assertions.assertEquals(game.getTurn(), 1); //The ones turn that is it, is in mate
		
	}
	
	@Test
	public void testQuickMateForBlack() {
		String quickMateForBlack = "f2f3 e7e6 g2g4 d8h4";
		
		game.movesFromStringLongLAN(quickMateForBlack);
		
		Assertions.assertTrue(game.isGameOver());
		Assertions.assertTrue(game.checkMate());
		Assertions.assertEquals(game.getTurn(), 0); //The ones turn that is it, is in mate

	}

}
