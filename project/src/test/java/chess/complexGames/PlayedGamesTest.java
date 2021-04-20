package chess.complexGames;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chess.Game;
import chess.pieces.Piece;
import chess.pieces.Pawn;
import chess.saveHandler.ChessSaveHandler;
import javafx.util.Pair;

public class PlayedGamesTest {

	/**
	 * Checks all the played games in playedGames.txt
	 */
	@Test
	public void testAllPlayedGames() {
		List<String[]> playedGames = ChessSaveHandler.getGames();
		
		for (String[] playedGame : playedGames) {
			
			int i = (Integer.parseInt(playedGame[1]));
			Game game = new Game(i);
			game.init(i);
			
			for (String move : playedGame[2].split(" ")) {
				Pair<String, String> movePair = game.convertLANtoPair(move);
				int startX = Integer.parseInt(movePair.getKey().charAt(0) + "");
				int startY = Integer.parseInt(movePair.getKey().charAt(1) + "");
				int endX = Integer.parseInt(movePair.getValue().charAt(0) + "");
				int endY = Integer.parseInt(movePair.getValue().charAt(1) + "");
				
				Piece p = game.getPiece(startX, startY);
				game.moveFromStringLAN(move);
				
				Assertions.assertNotEquals(p, game.getPiece(startX, startY)); // Checks if the piece has moved
				
				if (game.getPiece(endX, endY).isFresh()) { // If pawn queens(or anything else), then there should not be a pawn there
					Assertions.assertFalse(game.getPiece(endX, endY) instanceof Pawn);
				} else {
					Assertions.assertEquals(p, game.getPiece(endX, endY)); // Checks if the piece is at the end pos			
				}
			}	
		}
	}

}
