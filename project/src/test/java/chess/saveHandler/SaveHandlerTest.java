package chess.saveHandler;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.Game;


public class SaveHandlerTest {

	
	private ChessSaveHandler ch;
	private Game game;
	
	@BeforeEach
	public void beforeEach() {
		int s = 8;
		game = new Game(s);
		game.init(s);
		ch = new ChessSaveHandler();
	}
	
	@Test
	public void testCanGetGame(){
		List<String[]> Allgames = ChessSaveHandler.getGames();
		for (String[] games : Allgames) {
			String gameName = games[0];
			Assertions.assertTrue(ch.canGetGame(gameName));			
		}
	}
	
	@Test
	public void testGetWrongName() {
		
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> ch.getGame(""));
		Assertions.assertThrows(IllegalArgumentException.class, () -> ch.getGame(null));
		
	}
	
}


